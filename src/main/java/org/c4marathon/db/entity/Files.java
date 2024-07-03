package org.c4marathon.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
@Setter
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fileId;

    @Column
    private String originalName;

    @Column
    private String changeName;

    @Column
    private long fileSize;

    @Builder
    public Files(long fileId, String originalName, String changeName, long fileSize) {
        this.fileId = fileId;
        this.originalName = originalName;
        this.changeName = changeName;
        this.fileSize = fileSize;
    }
}
