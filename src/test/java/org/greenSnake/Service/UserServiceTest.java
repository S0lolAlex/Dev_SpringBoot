package org.greenSnake.Service;

import org.greenSnake.entities.User;
import org.greenSnake.enums.UsersRoles;
import org.greenSnake.repository.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
@SpringBootTest
@ComponentScan("org.greenSnake")
public class UserServiceTest {

    UserService service;
    @Autowired
    UsersRepository repository;

    @BeforeEach
    void beforeEach() {
        service = new UserService(repository);
        User user = new User();
        user.setUsername("TestUser");
        user.setPassword("$2a$12$pID5GADwbgVBiOIVcgD/9eltUw7fpOOhd7GI6Zr/OE413devlaoXG");
        user.setRole(UsersRoles.ROLE_USER);
        user.setEnabled(1);
        service.add(user);
    }

    @Test
    @Order(5)
    public void testThatListNotNull() {
        Assertions.assertNotNull(service.listAll());
    }

    @Test
    @Order(1)
    public void testCorrectAdd() {
        User user = service.listAll().stream().findAny().orElse(null);
        Assertions.assertEquals(user.getUsername(), service.add(user).getUsername());
    }

    @Test
    @Order(2)
    public void testThatGetByIdCorrect() {
        User user = service.listAll().stream().findAny().orElse(null);
        long id = user.getId();
        Assertions.assertEquals(user.getUsername(),service.getById(id).getUsername());
    }

    @Test
    @Order(4)
    public void testThatUserDelete() {
        User user = service.listAll().stream().filter(u -> u.getUsername().equals("TestUser")).findAny().orElse(null);
        service.deleteById(user.getId());
        Assertions.assertNull(service.getById(user.getId()));
    }

    @Test
    @Order(3)
    public void testNoteUpdate() {
        User user = service.listAll().stream().findAny().orElse(null);
        user.setUsername("newName");
        service.update(user);
        String upContent = service.getById(user.getId()).getUsername();
        Assertions.assertEquals("newName", upContent);
    }
}
