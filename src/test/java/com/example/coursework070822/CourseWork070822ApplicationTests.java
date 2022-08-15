package com.example.coursework070822;

import com.example.coursework070822.layers.RepositoryCourseWork;
import com.example.coursework070822.pattern.Amount;
import com.example.coursework070822.pattern.Card;
import com.example.coursework070822.pattern.ConfirmOperationCode;
import com.example.coursework070822.pattern.Transfer;



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

    private String host = "http://localhost:";

    @Autowired
    TestRestTemplate testRestTemplate;
    @Container
    public static final GenericContainer<?> generic = new GenericContainer<>(
            new ImageFromDockerfile()
                    .withDockerfile(Paths.get("./Dockerfile")))
            .withExposedPorts(8080);

    @Test
    void testCreateCard() {
        Card card = new Card("1111111111111111", "12/15", "321", 100);

        String answer = testRestTemplate.postForObject(host + generic.getMappedPort(8080)+ "/createCard",
                card, String.class);

        Assertions.assertEquals("map created successfully",answer);
    }
    @Test
    void testTransfer(){
        Amount amount = new Amount(100,"RUR");
        Transfer transfer = new Transfer("1111111111111111","12/25",
                "321","2222222222222222",amount);

        String answer = testRestTemplate.postForObject(host +
                generic.getMappedPort(8080)+"/transfer",transfer,String.class);

        Assertions.assertEquals("Number is incorrect", answer);
    }

    @Test
    void testConfirmOperation(){

        ConfirmOperationCode confirmOperationCode = new ConfirmOperationCode("1", "0000");

        String answer = testRestTemplate.postForObject(host +
                generic.getMappedPort(8080)+ "/confirmOperation", confirmOperationCode, String.class);

        Assertions.assertEquals("operation number or code entered incorrectly", answer);
    }




}
