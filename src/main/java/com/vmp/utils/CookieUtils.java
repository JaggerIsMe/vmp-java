package com.vmp.utils;

import com.vmp.entity.enums.ResponseCodeEnum;
import com.vmp.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieUtils {

    private static final Logger logger = LoggerFactory.getLogger(CookieUtils.class);

    /**
     * 添加普通Cookie
     *
     * @param response 响应对象
     * @param name     Cookie名称
     * @param value    Cookie值
     * @param maxAge   有效时间（秒）
     * @param httpOnly 是否HttpOnly
     */
    public static void addCookie(HttpServletResponse response,
                                 String name,
                                 String value,
                                 int maxAge,
                                 boolean httpOnly) {

        Cookie cookie = new Cookie(name, encode(value));
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(httpOnly);
        cookie.setPath("/api");

        response.addCookie(cookie);
    }

    /**
     * 添加HttpOnly Cookie
     */
    public static void addHttpOnlyCookie(HttpServletResponse response,
                                         String name,
                                         String value,
                                         int maxAge) {

        addCookie(response, name, value, maxAge, true);
    }

    /**
     * 添加安全Cookie
     *
     * HttpOnly + Secure + SameSite
     *
     * @param response 响应对象
     * @param name Cookie名称
     * @param value Cookie值
     * @param maxAge 有效时间（秒）
     */
    public static void addSecureCookie(HttpServletResponse response,
                                       String name,
                                       String value,
                                       int maxAge) {

        String cookie = String.format(
                "%s=%s; Max-Age=%d; Path=/api; HttpOnly; Secure; SameSite=Strict",
                name,
                encode(value),
                maxAge
        );

        response.addHeader("Set-Cookie", cookie);
    }

    /**
     * 获取指定Cookie值
     */
    public static String getCookieValue(HttpServletRequest request,
                                        String name) {

        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return decode(cookie.getValue());
            }
        }

        return null;
    }

    /**
     * 删除Cookie
     */
    public static void deleteCookie(HttpServletResponse response,
                                    String name) {

        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        cookie.setPath("/api");

        response.addCookie(cookie);
    }

    /**
     * Cookie值编码
     */
    private static String encode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        }
    }

    /**
     * Cookie值解码
     */
    private static String decode(String value) {
        try {
            return URLDecoder.decode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        }
    }
}
