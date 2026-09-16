package com.vmp.controller;

import com.vmp.entity.config.AppConfig;
import com.vmp.entity.dto.DownloadFileDto;
import com.vmp.redis.RedisComponent;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

public class CommonFileController extends ABaseController {

    @Resource
    protected AppConfig appConfig;

    @Resource
    protected RedisComponent redisComponent;

    /**
     * 下载文件
     *
     * @param request
     * @param response
     * @param code
     * @throws Exception
     */
    protected void download(HttpServletRequest request, HttpServletResponse response, String code) throws Exception {
        DownloadFileDto downloadFileDto = redisComponent.getDownloadCode(code);
        if (null == downloadFileDto) {
            return;
        }
        String filePath = downloadFileDto.getFilePath();
        String fileName = downloadFileDto.getFileName();
        response.setContentType("application/x-msdownload; charset=UTF-8");
        if (request.getHeader("User-Agent").toLowerCase().indexOf("msie") > 0) {//IE浏览器
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        }
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        readFile(response, filePath);
    }

}
