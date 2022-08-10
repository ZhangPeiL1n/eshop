package com.zpl.eshop.common.util;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * 文件工具类
 *
 * @author ZhangPeiL1n
 * @date 2022/8/8 22:33
 **/
public class FileUtils {

    /**
     * 获取一个相对路径
     *
     * @param relativePath 相对路径
     * @return 相对路径对应的绝对路径
     * @throws Exception
     */
    public static String getPathByRelative(String relativePath) throws Exception {
        File appBaseDir = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!appBaseDir.exists()) {
            appBaseDir = new File("");
        }
        File targetDir = new File(appBaseDir.getAbsolutePath(), relativePath);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        return targetDir.getAbsolutePath();
    }

    /**
     * 上传一个文件
     *
     * @param file          文件
     * @param uploadDirPath 上传目录的路径
     * @return
     * @throws Exception
     */
    public static String uploadFile(MultipartFile file, String uploadDirPath) throws Exception {
        File uploadDir = new File(uploadDirPath);
        // 如果上传目录不存在，则创建目录
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String filename = UUID.randomUUID().toString().replace("-", "");
        File targetFile = new File(uploadDirPath + filename);

        file.transferTo(targetFile);

        return targetFile.getAbsolutePath();
    }
}
