package org.c4marathon.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

    public void upload(MultipartFile files) throws IOException {

        //file의 진짜이름
        String fileName = files.getOriginalFilename();

        //mimeType
        String mimeType = files.getContentType();

        //서버 스토리지에 저장되는 새로운 이름
        String newFileName = generateUniqueFileName(fileName);

        long content = files.getSize();

        //파일 용량체크
        //checkFileSize(content);

        Path filePath = Paths.get(File.separator + newFileName);

        log.info(String.valueOf(filePath));

        //서버 스토리지에 업로드

        try {
            java.nio.file.Files.copy(files.getInputStream(), filePath);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private String generateUniqueFileName(String title) {

        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        Random random = new Random();

        String randomNumber = Integer.toString(random.nextInt(Integer.MAX_VALUE));

        String timeStamp = dataFormat.format(new Date());

        return timeStamp + randomNumber + title;
    }

}
