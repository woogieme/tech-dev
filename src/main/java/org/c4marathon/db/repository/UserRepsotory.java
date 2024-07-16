package org.c4marathon.db.repository;


import org.c4marathon.db.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepsotory extends JpaRepository<User, Long> {

    @Query("select o from User o join fetch o.pets")
    List<User> findAllJoinFetch();

    @EntityGraph(attributePaths = "pets")
    @Query("select o from User o")
    List<User> findAllEntityGraph();
}
