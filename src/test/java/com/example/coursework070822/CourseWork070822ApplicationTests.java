package com.example.coursework070822;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.file.Paths;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourseWork070822ApplicationTests {

    @Autowired
    TestRestTemplate testRestTemplate;
     @Container
    public static final GenericContainer<?> generic = new GenericContainer<>(
        new ImageFromDockerfile()
                .withDockerfile(Paths.get("./Dockerfile")))
            .withExposedPorts(8080);

    @Test
    void contextLoads() {
        String host = "http://localhost:";
        String answer = testRestTemplate.getForObject(host + generic.getMappedPort(8080)
                + "/1111111111111111", String.class);

        Assertions.assertEquals("{\"number\":\"1111111111111111\",\"data\":\"12/25\",\"cvv\":\"321\",\"balance\":10000}", answer);
    }

}
