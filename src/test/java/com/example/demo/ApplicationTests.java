package com.example.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    static final Logger logger;

    static {
        logger= LoggerFactory.getLogger(Application.class);
    }

    @Before
    public void setUp() throws Exception {

        logger.info("Application test running!");
        logger.info("====================");
    }
    @After
    public void tearDown() throws Exception {

        logger.info("Application test over!");
        logger.info("====================");
    }
    @Test
    void contextLoads() {

    }

}
