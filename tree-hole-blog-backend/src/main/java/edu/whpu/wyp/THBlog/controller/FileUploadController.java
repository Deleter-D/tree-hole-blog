package edu.whpu.wyp.THBlog.controller;

import edu.whpu.wyp.THBlog.bean.FileRespond;
import edu.whpu.wyp.THBlog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.UUID;

@RestController
@CrossOrigin
public class FileUploadController {

    @Value("${upload-path}")
    private String filePath;

    @Autowired
    private ArticleService articleService;

    @PostMapping("/upload")
    public FileRespond upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        FileRespond fileRespond = new FileRespond();
        if (file.isEmpty()) {
            fileRespond.setMsg("Failed to upload");
            fileRespond.setCode("50001");
        } else {
//            String articleId = String.valueOf(articleService.getMaxId() + 1);
//            String finalPath = filePath + "\\" + articleId;
            File file1 = new File(filePath);
            if (!file1.exists()) {
                file1.mkdir();
            }

            // 提取文件格式
            String fileName = file.getOriginalFilename();
            String format = "";
            StringTokenizer stringTokenizer = new StringTokenizer(fileName, ".");
            while (stringTokenizer.hasMoreTokens()) {
                format = stringTokenizer.nextToken();
            }
            System.out.println(format);

            // 设置UUID防止文件名重复
            String fileUUID = UUID.randomUUID().toString().replace("-", "");
            File dest = new File(filePath + fileUUID + "." + format);
            try {
                file.transferTo(dest);
                fileRespond.setMsg("Uploaded successfully");
                fileRespond.setCode("50000");
                String fileUrl = request.getScheme() + "://"
                        + request.getServerName() + ":"
                        + request.getServerPort()
                        + "/uploads/" + fileUUID + "." + format;
                fileRespond.setURL(fileUrl);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        return fileRespond;
    }

    @PostMapping("/uploadById")
    public FileRespond uploadById(@RequestParam("file") MultipartFile file, String articleId, HttpServletRequest request) {
        FileRespond fileRespond = new FileRespond();
        if (file.isEmpty()) {
            fileRespond.setMsg("Failed to upload");
            fileRespond.setCode("50001");
        } else {
            String finalPath = filePath + "\\" + articleId;
            File file1 = new File(finalPath);
            if (!file1.exists()) {
                file1.mkdir();
            }
            String fileName = file.getOriginalFilename();
            File dest = new File(finalPath + "\\" + fileName);
            try {
                file.transferTo(dest);
                fileRespond.setMsg("Uploaded successfully");
                fileRespond.setCode("50000");
                String fileUrl = request.getScheme() + "://"
                        + request.getServerName() + ":"
                        + request.getServerPort()
                        + "/" + articleId + "/" + fileName;
                fileRespond.setURL(fileUrl);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        return fileRespond;
    }
}
