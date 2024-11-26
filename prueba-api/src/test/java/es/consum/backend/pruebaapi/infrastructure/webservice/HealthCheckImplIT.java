package es.consum.backend.pruebaapi.infrastructure.webservice;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import es.consum.backend.test.BaseITAbstract;

class HealthCheckImplIT extends BaseITAbstract {

    private static final String INFO_API_BASE = "/actuator/info";

    @Test
    void shoudlBeDeployed() {
        RestAssured.given().accept(ContentType.JSON).when().auth().oauth2(accessToken).get(INFO_API_BASE).then()
                .assertThat().statusCode(HttpStatus.OK.value()).contentType(ContentType.JSON);
    }
}