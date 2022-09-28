package com.cloud.common.base.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.apache.commons.fileupload.FileItem;

import java.io.*;
import java.nio.file.Files;

@Slf4j
public class FileUtil {

    /**
     * File 转 MultipartFile
     *
     * @param file
     * @return
     */
    private MultipartFile createMultipartFile(File file) {
        try {
            FileItem fileItem = null;
            MultipartFile multipartFile = null;
            fileItem = new DiskFileItem(file.getName(), Files.probeContentType(file.toPath()),
                    false, file.getName(), (int) file.length(), file.getParentFile());
            InputStream input = new FileInputStream(file);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);
            multipartFile = new CommonsMultipartFile(fileItem);
            os.close();
            input.close();
            return multipartFile;
        } catch (Exception e) {
            e.getStackTrace();
            throw new RuntimeException("File转MultipartFile失败");
        }
    }

    /**    InputStream 转为 MultipartFile
     *
     * @param inputStream inputStream
     * @param fileName fileName
     * @return
     */
    public MultipartFile getMultipartFile(InputStream inputStream, String fileName){
        FileItem fileItem = createFileItem(inputStream,fileName);
        return new CommonsMultipartFile(fileItem);
    }


    /**    InputStream 转为 MultipartFile
     * FileItem类对象创建
     *
     * @param inputStream inputStream
     * @param fileName    fileName
     * @return FileItem
     */
    public FileItem createFileItem(InputStream inputStream, String fileName) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "file";
        FileItem item = factory.createItem(textFieldName, MediaType.MULTIPART_FORM_DATA_VALUE, true, fileName);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        OutputStream os = null;
        //使用输出流输出输入流的字节
        try {
            os = item.getOutputStream();
            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            inputStream.close();
        } catch (IOException e) {
            log.error("Stream copy exception", e);
            throw new IllegalArgumentException("文件上传失败");
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.error("Stream close exception", e);

                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("Stream close exception", e);
                }
            }
        }

        return item;
    }
}
