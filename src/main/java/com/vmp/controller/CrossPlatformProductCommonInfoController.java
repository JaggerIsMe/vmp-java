package com.vmp.controller;

import com.vmp.annotation.GlobalInterceptor;
import com.vmp.annotation.VerifyParam;
import com.vmp.entity.config.AppConfig;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.dto.TokenUserInfoDto;
import com.vmp.entity.enums.AdminStatusEnum;
import com.vmp.entity.enums.ResponseCodeEnum;
import com.vmp.entity.po.CrossPlatformProductCommonInfo;
import com.vmp.entity.query.CrossPlatformProductCommonInfoQuery;
import com.vmp.entity.vo.ResponseVO;
import com.vmp.exception.BusinessException;
import com.vmp.service.CrossPlatformProductCommonInfoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * 跨平台全产品公共属性 Controller
 */
@RestController("crossPlatformProductCommonInfoController")
@RequestMapping("/productCommon")
public class CrossPlatformProductCommonInfoController extends ABaseController {

    @Resource
    private CrossPlatformProductCommonInfoService crossPlatformProductCommonInfoService;

    @Resource
    private AppConfig appConfig;

    /**
     * 根据条件分页查询
     */
    @RequestMapping("/loadDataList")
    public ResponseVO loadDataList(CrossPlatformProductCommonInfoQuery query) {
        return getSuccessResponseVO(crossPlatformProductCommonInfoService.findListByPage(query));
    }

    /**
     * 获取产品图片
     * @param response
     * @param uid
     */
    @RequestMapping("/getProductImg/{uid}")
    @GlobalInterceptor(checkParams = true)
    public void getAvatar(HttpServletResponse response, @VerifyParam(required = true) @PathVariable("uid") String uid) {
        CrossPlatformProductCommonInfo productCommonInfo = crossPlatformProductCommonInfoService.getCrossPlatformProductCommonInfoByUid(uid);

        String imgPath = appConfig.getProjectFolder() + productCommonInfo.getProductImgPath();
        File file = new File(imgPath);
        if (!file.exists()) {
            if (!new File(appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE + Constants.DEFAULT_IMG).exists()) {
                printNoDefaultImage(response);
                return;
            }
            imgPath = appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE + Constants.DEFAULT_IMG;
        }
        response.setContentType("image/jpg");
        readFile(response, imgPath);
    }

}