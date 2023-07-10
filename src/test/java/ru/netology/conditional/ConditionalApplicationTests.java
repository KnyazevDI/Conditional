package ru.netology.conditional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;


//@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConditionalApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

//    @Container
    private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp");

//    @Container
    private static final GenericContainer<?> devApp = new GenericContainer<>("devapp");

    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void contextLoadsDev() {
        final String expected = "Current profile is dev";

        ResponseEntity<String> forEntityDev = restTemplate.getForEntity("http://localhost:" +
                devApp.getMappedPort(8080), String.class);
        System.out.println(forEntityDev.getBody());
        final String result = forEntityDev.getBody();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void contextLoadsProd () {
        final String expected = "Current profile is production";

        ResponseEntity<String> forEntityProd = restTemplate.getForEntity("http://localhost:" +
                prodApp.getMappedPort(8080), String.class);
        System.out.println(forEntityProd.getBody());
        final String result = forEntityProd.getBody();

        Assertions.assertEquals(expected, result);
    }
}
