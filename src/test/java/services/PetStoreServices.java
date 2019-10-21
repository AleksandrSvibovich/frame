package services;

import com.google.inject.Inject;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import json.BodyForPets;
import logger.ProjectLog;

import static io.restassured.RestAssured.given;

/**
 * Created by Aleksandr_Svibovich on 9/30/2019.
 */
public class PetStoreServices {
    private static final String URI = "https://petstore.swagger.io/v2";
    private static final String METHOD_FIND_BY_STATUS = "/pet/findByStatus?status=available";
    private static final String STORE_INVENTORY = "/store/inventory";
    private static final String PETSTORE_URL = "https://petstore.swagger.io/v2/pet";

    @Inject
    protected ProjectLog projectLog;

   private ResponseSpecification specification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();

    public void verifyStatusCode(Integer code, String method) {
        String chosenMethod = getChosenMethod(method);
        projectLog.info("verifyStatusCode started");
        given()
                .baseUri(URI)
                .when()
                .get(chosenMethod)
                .then()
                .extract().response()
                .then()
                .statusCode(code);
        projectLog.info("verifyStatusCode finished");
    }

    public void addNewPetToStore(int id,String name,String status) {
//        String postBody = "{\"id\":%s,\"category\":{\"id\":0,\"name\":\"UniqueCategoryName\"},\"name\":\"%s\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":77744455887,\"name\":\"string\"}],\"status\":\"%s\"}";
        String postBody = BodyForPets.NEW_PET;
        postBody = String.format(postBody,id,name,status);
        Integer result = given()
                .contentType(ContentType.JSON)
                .body(postBody)
                .post(PETSTORE_URL)
                .then()
                .assertThat()
                .spec(specification)
                .and()
                .extract().body().jsonPath().get("id");
        projectLog.info("pet id was " + result);
    }

    public void getPetById(int id, String expectedName) {
        given()
                .baseUri(PETSTORE_URL)
                .get(String.valueOf(id))
                .then()
                .assertThat()
                .spec(specification)
                .and()
                .assertThat()
                .extract().body().jsonPath().get("name").equals(expectedName);
    }

    public void deletePetById(int id) {
        given()
                .when()
                .baseUri(PETSTORE_URL)
                .delete(String.valueOf(id))
                .then()
                .assertThat()
                .spec(specification);
    }

    private String getChosenMethod(String method) {
        String chosenMethod = null;
        if (method.equalsIgnoreCase("getAvailable")) {
            chosenMethod = METHOD_FIND_BY_STATUS;
        } else if (method.equalsIgnoreCase("getInventory")) {
            chosenMethod = STORE_INVENTORY;
        } else {
            projectLog.error("chosen method is not exist");
        }
        return chosenMethod;
    }
}
