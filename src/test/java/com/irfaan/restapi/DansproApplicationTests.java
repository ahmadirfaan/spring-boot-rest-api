package com.irfaan.restapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class DansproApplicationTests {

    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        int beanDefinitionCount = applicationContext.getBeanDefinitionCount();
        Assertions.assertTrue(beanDefinitionCount > 0);
    }

}
