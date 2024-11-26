package es.consum.backend.pruebaapi.infrastructure.webservice.testconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "es.consum.backend.pruebaapi")
public class PruebaApiApplicationTests {

    public static void main(String[] args) {
        SpringApplication.run(PruebaApiApplicationTests.class, args);
    }
}
