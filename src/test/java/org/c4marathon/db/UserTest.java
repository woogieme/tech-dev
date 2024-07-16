package org.c4marathon.db;

import jakarta.persistence.EntityManager;
import org.c4marathon.db.entity.Animal;
import org.c4marathon.db.entity.User;
import org.c4marathon.db.repository.AnimalRepository;
import org.c4marathon.db.repository.UserRepsotory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserTest {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private UserRepsotory userRepsotory;

    EntityManager entityManager;

    @BeforeEach
    public void setUp() {


    }
    @Test
    void test(){
        List<Animal> pets = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            Animal pet = Animal.builder().name("pet"+ i).build();

            pets.add(pet);

        }
        
        animalRepository.saveAll(pets);

        List<User> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            User user = User.builder().name("user"+i).build();
            user.setPets(pets);
            users.add(user);

        }

        userRepsotory.saveAll(users);

        System.out.println("===========================");


        List<User> userList = userRepsotory.findAll();

        //Fetch Join
        //List<User> userList2 = userRepsotory.findAllJoinFetch();

        //EntityGraph
        //List<User> userList3 = userRepsotory.findAllEntityGraph();
    }
}
