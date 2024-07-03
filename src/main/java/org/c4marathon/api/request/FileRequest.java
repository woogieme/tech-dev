package org.c4marathon.api.request;

import lombok.Getter;
import lombok.Setter;
import org.c4marathon.db.entity.Files;

@Getter
@Setter
public class FileRequest {

    private String originalName;//원본 파일명
    private String saveName;    //저장 파일명
    private long fileSize;          //파을 크기

    public Files toEntity(FileRequest fileRequest) {

        Files file = Files.builder()
                .originalName(fileRequest.getOriginalName())
                .changeName(fileRequest.getSaveName())
                .fileSize(fileRequest.getFileSize())
                .build();

        return file;
    }
}
