package com.qx.api.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.qx.common.config.ProjectConfig;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.utils.StringUtils;
import com.qx.common.utils.file.FileUploadUtils;
import com.qx.common.utils.file.FileUtils;
import com.qx.framework.config.ServerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用请求处理
 * 
 * @author suhp
 */
@RestController
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired(required = false)
    private ServerConfig serverConfig;

    /**
     * 通用下载请求
     * 
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("common/download")
    @ResponseBody
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.isValidFilename(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = ProjectConfig.getDownloadPath() + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 多图片上传请求
     */
    @PostMapping("/common/upload")
    @ResponseBody
    public AjaxResult uploadFile(@RequestParam("files") MultipartFile[] files) throws Exception
    {
        try
        {
            Gson gson = new Gson();
            AjaxResult ajax = AjaxResult.success();
            String fileName = "";
            String url="";
            List<String> urlList = new ArrayList<String>();
            List<String> fileNameList = new ArrayList<String>();

            if (files != null && files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    MultipartFile file = files[i];
                    // 上传文件路径
                    String filePath = ProjectConfig.getUploadPath();
                    // 上传并返回新文件名称
                     fileName = FileUploadUtils.upload(filePath, file);
                     url = serverConfig.getUrl() + fileName;
                    urlList.add(url);
                    fileNameList.add(fileName);

                }
            }
            ajax.put("urlList", urlList);
            ajax.put("fileNameList", fileNameList);

            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }
}
