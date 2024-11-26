package es.consum.backend.pruebaapi.infrastructure.webservice;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

import java.util.Collections;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.CoreMatchers.is;



@SpringBootTest
@RunWith(SpringRunner.class)
public class EntidadesApiIT {

    private static final String ENTIDADES_BASE_URL = "/entidades/v1";

    // TODO: Establece para cada variable un valor distinto acorde a su tipo

    private static final String ENTIDADES_ID_PATH_PARAM = "id";
    private static final Long FOUND_ENTIDADES_ID = 1L;
    private static final Long TO_UPDATE_ENTIDADES_ID = 1L;

    private static final Long TO_CREATE_ENTIDADES_ID = null;
    private static final Long TO_DELETE_ENTIDADES_ID = null;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void initialiseRestAssuredMockMvcWebApplicationContext() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    public void shouldRetrieveEntidades() {
        MockMvcResponse response = RestAssuredMockMvc.given()
            .param(ENTIDADES_ID_PATH_PARAM, FOUND_ENTIDADES_ID)
            .auth()
            .with(jwt().authorities(Collections.emptyList()))
            .accept(ContentType.JSON).when()
            .get(ENTIDADES_BASE_URL + "/" + FOUND_ENTIDADES_ID)
            .then().assertThat().statusCode(HttpStatus.OK.value()).contentType(ContentType.JSON).extract().response();

        response.then().assertThat()
            .body("data.nroent",
                is(1))
            .body("data.noment",
                is("abc"))
            .body("data.codagrup",
                is("abc"))
            .body("data.tpopro",
                is(1))
            .body("data.disabled",
                is(true))
            ;
    }

    @Test
    public void shouldCreateEntidades() {
        String json = "{" +
                "\"nroent\":\"1\"," +
                "\"noment\":\"abc\"," +
                "\"codagrup\":\"abc\"," +
                "\"tpopro\":\"1\"," +
                "\"disabled\":\"true\"," +
                "\"id\":\""+ TO_CREATE_ENTIDADES_ID+"\"}";

        // Creamos una entidad
        RestAssuredMockMvc.given().contentType(ContentType.JSON).body(json)
            .auth().with(jwt().authorities(Collections.emptyList()))
            .accept(ContentType.JSON)
            .when().post(ENTIDADES_BASE_URL)
            .then().assertThat().statusCode(HttpStatus.OK.value()).contentType(ContentType.JSON).extract().response();

        // Recuperamos la entidad
        MockMvcResponse response = RestAssuredMockMvc.given()
            .param(ENTIDADES_ID_PATH_PARAM, TO_CREATE_ENTIDADES_ID)
            .auth()
            .with(jwt().authorities(Collections.emptyList()))
            .accept(ContentType.JSON).when()
            .get(ENTIDADES_BASE_URL + "/" + TO_CREATE_ENTIDADES_ID)
            .then().assertThat().statusCode(HttpStatus.OK.value()).contentType(ContentType.JSON).extract().response();

        response.then().assertThat()
            .body("data.nroent",
                is(1))
            .body("data.noment",
                is("abc"))
            .body("data.codagrup",
                is("abc"))
            .body("data.tpopro",
                is(1))
            .body("data.disabled",
                is(true))
        ;

    }

    @Test
    public void shouldDeleteEntidades() {
        String json = "{" +
                "\"nroent\":\"1\"," +
                "\"noment\":\"abc\"," +
                "\"codagrup\":\"abc\"," +
                "\"tpopro\":\"1\"," +
                "\"disabled\":\"true\"," +
                "\"id\":\""+ TO_DELETE_ENTIDADES_ID+"\"}";

        // Creamos una entidad
        RestAssuredMockMvc.given().contentType(ContentType.JSON).body(json)
            .auth().with((jwt().authorities(Collections.emptyList())))
            .accept(ContentType.JSON)
            .when().post(ENTIDADES_BASE_URL);

        // Borramos la entidad
        RestAssuredMockMvc.given().param(ENTIDADES_ID_PATH_PARAM, TO_DELETE_ENTIDADES_ID).contentType(ContentType.JSON)
            .auth().with((jwt().authorities(Collections.emptyList())))
            .when().delete(ENTIDADES_BASE_URL + "/" + TO_DELETE_ENTIDADES_ID)
            .then().assertThat().statusCode(HttpStatus.OK.value());

        // Intentamos recuperar el elemento ya eliminado y producirá un HttpStatus 404
        RestAssuredMockMvc.given().param(ENTIDADES_ID_PATH_PARAM, TO_DELETE_ENTIDADES_ID)
            .auth().with((jwt().authorities(Collections.emptyList())))
            .accept(ContentType.JSON)
            .when().get(ENTIDADES_BASE_URL + "/" + TO_DELETE_ENTIDADES_ID)
            .then().assertThat().statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void shouldUpdateEntidades() {
        String json = "{" +
                "\"nroent\":\"1\"," +
                "\"noment\":\"abc\"," +
                "\"codagrup\":\"abc\"," +
                "\"tpopro\":\"1\"," +
                "\"disabled\":\"true\"," +
                "\"id\":\""+ TO_UPDATE_ENTIDADES_ID+"\"}";

        //Actualizamos la entidad
        RestAssuredMockMvc.given().contentType(ContentType.JSON)
            .param(ENTIDADES_ID_PATH_PARAM, TO_UPDATE_ENTIDADES_ID).body(json)
            .auth().with((jwt().authorities(Collections.emptyList()))).accept(ContentType.JSON)
            .when()
            .put(ENTIDADES_BASE_URL + "/" + TO_UPDATE_ENTIDADES_ID)
            .then().assertThat().statusCode(HttpStatus.OK.value()).contentType(ContentType.JSON).extract().response();

        //Recuperamos la entidad
        MockMvcResponse updatedResponse = RestAssuredMockMvc.given().contentType(ContentType.JSON)
            .param(ENTIDADES_ID_PATH_PARAM, TO_UPDATE_ENTIDADES_ID)
            .auth().with((jwt().authorities(Collections.emptyList()))).accept(ContentType.JSON)
            .when().get(ENTIDADES_BASE_URL + "/" + TO_UPDATE_ENTIDADES_ID)
            .then().assertThat().statusCode(HttpStatus.OK.value()).contentType(ContentType.JSON).extract().response();

        updatedResponse.then().assertThat()
            .body("data.nroent",
                is(1))
            .body("data.noment",
                is("abc"))
            .body("data.codagrup",
                is("abc"))
            .body("data.tpopro",
                is(1))
            .body("data.disabled",
                is(true))
        ;
    }

    @Test
    public void shouldGetEntidadesPagedAndOrderedAndFiltered() {
        MockMvcResponse response = RestAssuredMockMvc.given()
            .auth().with((jwt().authorities(Collections.emptyList()))).accept(ContentType.JSON)
            .when().get(ENTIDADES_BASE_URL)
            .then().assertThat().statusCode(HttpStatus.OK.value()).contentType(ContentType.JSON).extract().response();

        response.then().assertThat()
            .body("paging.pageNumber", is(0))
            .body("paging.pageSize", is(10))
            .body("paging.totalNumberOfPages", is(1))
            .body("paging.hasNextPage", is(false))
            .body("paging.hasPreviousPage", is(false));
    }
}