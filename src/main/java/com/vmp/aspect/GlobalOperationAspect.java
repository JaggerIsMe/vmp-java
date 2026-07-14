package com.vmp.aspect;

import com.vmp.annotation.GlobalInterceptor;
import com.vmp.annotation.VerifyParam;
import com.vmp.entity.enums.AdminStatusEnum;
import com.vmp.redis.RedisUtils;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.dto.TokenUserInfoDto;
import com.vmp.entity.enums.ResponseCodeEnum;
import com.vmp.exception.BusinessException;
import com.vmp.utils.StringTools;
import com.vmp.utils.VerifyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Component("operationAspect")
@Aspect
public class GlobalOperationAspect {

    private static Logger logger = LoggerFactory.getLogger(GlobalOperationAspect.class);
    private static final String TYPE_STRING = "java.lang.String";
    private static final String TYPE_INTEGER = "java.lang.Integer";
    private static final String TYPE_LONG = "java.lang.Long";

    @Resource
    private RedisUtils redisUtils;


    @Pointcut("@annotation(com.vmp.annotation.GlobalInterceptor)")
    private void requestInterceptor() {
    }

    @Before("requestInterceptor()")
    public void interceptorDo(JoinPoint point) throws BusinessException {
        try {
            Object target = point.getTarget();
            Object[] arguments = point.getArgs();
            String methodName = point.getSignature().getName();
            Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
            Method method = target.getClass().getMethod(methodName, parameterTypes);
            GlobalInterceptor interceptor = method.getAnnotation(GlobalInterceptor.class);
            if (null == interceptor) {
                return;
            }
            /**
             * 校验登录
             */
            if (interceptor.checkLogin() || interceptor.checkAdmin()) {
                checkLogin(interceptor.checkAdmin());
            }
            /**
             * 校验参数
             */
            if (interceptor.checkParams()) {
                validateParams(method, arguments);
            }
        } catch (BusinessException e) {
            logger.error("全局拦截器异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("全局拦截器异常", e);
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        } catch (Throwable e) {
            logger.error("全局拦截器异常", e);
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        }
    }


    /**
     * 校验登录和管理员
     *
     * @param checkAdmin
     */
    private void checkLogin(Boolean checkAdmin) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        }
        String token = null;
        for (Cookie cookie : cookies) {
            if (Constants.WEB_TOKEN_KEY.equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        if (StringTools.isEmpty(token)) {
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        }
        TokenUserInfoDto tokenUserInfoDto = (TokenUserInfoDto) redisUtils.get(Constants.REDIS_KEY_ONLINE_TOKEN + token);
        if (null == tokenUserInfoDto) {
            throw new BusinessException(ResponseCodeEnum.CODE_901);
        }
        String latestToken = (String) redisUtils.get(Constants.REDIS_KEY_ONLINE_USERID_LATEST_TOKEN + tokenUserInfoDto.getUserId());
        if (!token.equals(latestToken)) {
            throw new BusinessException(ResponseCodeEnum.CODE_902);
        }
        // token续期(小于10分钟)
        Long ttl = redisUtils.getExpires(Constants.REDIS_KEY_ONLINE_TOKEN + token);
        if (null != ttl && ttl < Constants.REDIS_KEY_EXPIRES_TEN_MIN) {
            redisUtils.expire(Constants.REDIS_KEY_ONLINE_TOKEN + token, Constants.REDIS_KEY_EXPIRES_ONE_HOUR);
            redisUtils.expire(Constants.REDIS_KEY_ONLINE_USERID_LATEST_TOKEN + tokenUserInfoDto.getUserId(), Constants.REDIS_KEY_EXPIRES_ONE_HOUR);
        }
        // 需要管理员权限验证
        if (checkAdmin && !AdminStatusEnum.ADMIN.getStatus().equals(tokenUserInfoDto.getAdmin())) {
            throw new BusinessException(ResponseCodeEnum.CODE_404);
        }
    }


    private void validateParams(Method m, Object[] arguments) throws BusinessException {
        Parameter[] parameters = m.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object value = arguments[i];
            VerifyParam verifyParam = parameter.getAnnotation(VerifyParam.class);
            if (verifyParam == null) {
                continue;
            }
            //基本数据类型
            if (TYPE_STRING.equals(parameter.getParameterizedType().getTypeName()) || TYPE_LONG.equals(parameter.getParameterizedType().getTypeName()) || TYPE_INTEGER.equals(parameter.getParameterizedType().getTypeName())) {
                checkValue(value, verifyParam);
                //如果传递的是对象
            } else {
                checkObjValue(parameter, value);
            }
        }
    }

    private void checkObjValue(Parameter parameter, Object value) {
        try {
            String typeName = parameter.getParameterizedType().getTypeName();
            Class classz = Class.forName(typeName);
            Field[] fields = classz.getDeclaredFields();
            for (Field field : fields) {
                VerifyParam fieldVerifyParam = field.getAnnotation(VerifyParam.class);
                if (fieldVerifyParam == null) {
                    continue;
                }
                field.setAccessible(true);
                Object resultValue = field.get(value);
                checkValue(resultValue, fieldVerifyParam);
            }
        } catch (BusinessException e) {
            logger.error("校验参数失败", e);
            throw e;
        } catch (Exception e) {
            logger.error("校验参数失败", e);
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
    }

    /**
     * 校验参数
     *
     * @param value
     * @param verifyParam
     * @throws BusinessException
     */
    private void checkValue(Object value, VerifyParam verifyParam) throws BusinessException {
        Boolean isEmpty = value == null || StringTools.isEmpty(value.toString());
        Integer length = value == null ? 0 : value.toString().length();

        /**
         * 校验空
         */
        if (isEmpty && verifyParam.required()) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }

        /**
         * 校验长度
         */
        if (!isEmpty && (verifyParam.max() != -1 && verifyParam.max() < length || verifyParam.min() != -1 && verifyParam.min() > length)) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        /**
         * 校验正则
         */
        if (!isEmpty && !StringTools.isEmpty(verifyParam.regex().getRegex()) && !VerifyUtils.verify(verifyParam.regex(), String.valueOf(value))) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
    }
}
