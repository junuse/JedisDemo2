package com.example.jedisdemo2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JedisDemo2ApplicationTests {

    @Autowired
    MyService myService;

    @Test
    void contextLoads() {
    }

    @Test
    void testGet() {
        myService.setValue("looey", 1);

    }


}
