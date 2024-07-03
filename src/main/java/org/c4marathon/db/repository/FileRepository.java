package org.c4marathon.db.repository;

import jakarta.persistence.Id;
import org.c4marathon.db.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<Files, Long> {


//    Optional<File> findByPostId(long postId);
}
