package org.example.service.logic;

import org.example.entity.User;
import org.example.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceLogicTest {
    @Autowired
    private UserService userService;
    private User kim;
    private User lee;
    @BeforeEach
    public void startUp() {
        this.kim = new User("kim", "kim@aa.aa");
        this.lee = new User("lee", "lee@bb.bb");
        this.userService.register(kim);
        this.userService.register(lee);
    }

    @Test
    public void registerTest(){
        User userSample = User.sample();
        assertThat(this.userService.register(userSample)).isEqualTo(userSample.getId());
        assertThat(this.userService.findAll().size()).isEqualTo(3);
        this.userService.remove(userSample.getId());
    }

    @Test
    public void find() {
        assertThat(this.userService.find(lee.getId())).isNotNull();
        assertThat(this.userService.find(lee.getId()).getEmail()).isEqualTo(lee.getEmail());
    }

    @AfterEach
    public void cleanUp() {
        this.userService.remove(kim.getId());
        this.userService.remove(lee.getId());
    }
}
