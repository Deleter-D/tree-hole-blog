package edu.whpu.wyp.THBlog.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUploadTest {

    @Test
    public void filePathTest() throws MalformedURLException {
        File file = new File("D:\\BaiduNetdiskWorkspace\\毕设\\03_项目\\files\\infinity-1098519.jpg");
        String filePath = file.getPath();
        URL url = file.toURL();
        System.out.println(filePath);
        System.out.println(url);
    }

    @Test
    public void fileCopyTest() {
        try {
            String oldPath = "D:\\BaiduNetdiskWorkspace\\毕设\\03_项目\\files\\1\\";
            String newPath = "D:\\BaiduNetdiskWorkspace\\毕设\\03_项目\\files\\2\\";
            File old = new File(oldPath);
            String[] file = old.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                temp = new File(oldPath + file[i]);
                String fileName = newPath + "\\" + (temp.getName()).toString();
                File testFile = new File(fileName);
                if (!testFile.exists()) {
                    FileInputStream fileInputStream = new FileInputStream(temp);
                    FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                    byte[] bytes = new byte[1024 * 10];
                    int len;
                    while ((len = fileInputStream.read(bytes)) != -1) {
                        fileOutputStream.write(bytes, 0, len);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    fileInputStream.close();
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }


    }
}
