package org.example.service.logic;

import org.example.entity.User;
import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceLogicTest {
    @Autowired
    private UserService userService;

    @Test
    public void registerTest(){
        User userSample = User.sample();
        assertThat(this.userService.register(userSample)).isEqualTo(userSample.getId());
        assertThat(this.userService.findAll().size()).isEqualTo(1);
    }
}
