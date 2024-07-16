package org.c4marathon.db.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @BatchSize(size=5)
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Animal> pets = new ArrayList<>();

}
