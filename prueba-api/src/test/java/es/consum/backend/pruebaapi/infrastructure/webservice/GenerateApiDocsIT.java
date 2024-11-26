package es.consum.backend.pruebaapi.infrastructure.webservice;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.WebApplicationContext;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest
class GenerateApiDocsIT {
    @Autowired
    WebApplicationContext context;

    @Test
    void generateApiDocs() throws IOException {
        var apiJson = RestAssuredMockMvc.given().accept(ContentType.JSON).when()
                .get("/api-docs")
                .then().assertThat().status(HttpStatus.OK).contentType(ContentType.JSON)
                .extract().response().asString();

        FileUtils.writeStringToFile(new File("./target/api-docs.json"),
                apiJson, StandardCharsets.UTF_8);
    }
}