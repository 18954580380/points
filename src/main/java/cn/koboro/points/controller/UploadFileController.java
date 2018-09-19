package cn.koboro.points.controller;

import cn.koboro.points.utils.PropertiesUtil;
import cn.koboro.points.utils.SftpFileUtil;
import cn.koboro.points.utils.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;
@Controller
@RequestMapping("/upload")
public class UploadFileController {
    @PostMapping("/uploadingFile")
    @ResponseBody
    public String uploadFile(@RequestParam("files") MultipartFile file) {
        //上传图片
        String fileName = "";
        if (!Validator.isEmpty(file.getOriginalFilename())) {
            fileName = uploadFileSftp(file);
        }
        return fileName;
    }

    /**
     * 上传
     * @param file
     * @return
     */
    public String uploadFileSftp(MultipartFile file) {
        String fileName = System.currentTimeMillis() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String[] keyName = new String[]{"userName", "password", "host", "port", "dir",};
        try {
            InputStream is = file.getInputStream();
            Map<String, String> map = PropertiesUtil.getPropertiesValue(keyName);
            SftpFileUtil sftpFileUtil = new SftpFileUtil(map.get("userName"), map.get("password"), map.get("host"), Integer.valueOf(map.get("port")));
            //链接ftp
            sftpFileUtil.login();
            sftpFileUtil.upload(map.get("dir"), fileName, is);
        } catch (Exception e) {
            //图片上传失败
            System.out.println(e);
            return "";
        }
        return fileName;
    }
}