package com.dio.cloudparking.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.dio.cloudparking.controller.dto.ParkingCreateDTO;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerTestIT extends AbstractContainerBase {
 
    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllCheckResult() {
        whenCreateThenCheckIsCreated();

        RestAssured.given()
                    .auth()
                    .basic("user", "senha")
                    .when()
                    .get("/parking")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("license[0]", Matchers.equalTo("MMA-5874"));
    }

    @Test
    void whenCreateThenCheckIsCreated() {

        var crateDTO = new ParkingCreateDTO();
        crateDTO.setColor("Amarelo");
        crateDTO.setLicense("MMA-5874");
        crateDTO.setModel("Brasilia");
        crateDTO.setState("DF");

        RestAssured.given()
                    .when()
                    .auth().basic("user", "senha")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(crateDTO)
                    .post("/parking")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("license", Matchers.equalTo("MMA-5874"))
                    .body("color", Matchers.equalTo("Amarelo"));
                    
    }
}


//docker run -p 15432:15432 --name parkingdb -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=parking -d postgres:11.16-alpine