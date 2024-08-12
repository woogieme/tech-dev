package org.c4marathon.api.request;


import lombok.RequiredArgsConstructor;

import org.c4marathon.api.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestPart MultipartFile files) throws IOException {

        fileService.upload(files);

        return ResponseEntity.status(HttpStatus.OK).body("성공 완료");

    }

    @PostMapping("/invite")
    public void invite() throws IOException {

        System.out.println("abc");
    }
}
