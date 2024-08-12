package org.c4marathon.db.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String userEmail;

    @BatchSize(size=5)
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Animal> pets = new ArrayList<>();

    @Builder
    public User(Long id, String name, String password, String userEmail) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.userEmail = userEmail;
    }
}
