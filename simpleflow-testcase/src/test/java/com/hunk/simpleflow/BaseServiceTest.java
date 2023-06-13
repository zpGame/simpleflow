package com.hunk.simpleflow;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created on 2023/4/27.
 *
 * @author YCKJ4297
 *     <p>单元测试
 */
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@TestPropertySource(value = "classpath:/application.properties")
@SpringBootTest(
        classes = BaseServiceTest.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ComponentScan({"com.hunk.simpleflow.test.flow"})
public abstract class BaseServiceTest {}
