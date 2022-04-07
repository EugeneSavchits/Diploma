package testsApi;

import baseEntities.BaseApiTest;
import enums.ProjectType;
import io.restassured.mapper.ObjectMapperType;
import models.ProjectApi;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import utils.Endpoints;
import utils.Randomization;

import static io.restassured.RestAssured.given;


public class ApiAfeTest extends BaseApiTest {

    @Test
    public void negativeAddProjectTest() {

        ProjectApi negativeProject = ProjectApi.builder()
                .name(Randomization.getRandomString(1000))
                .announcement(Randomization.getRandomString(100))
                .typeOfProject(ProjectType.SINGLE.getProjectType())
                .build();

        given()
                .body(negativeProject, ObjectMapperType.GSON)
                .log().body()
                .when()
                .post(Endpoints.POST_ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test(dependsOnMethods = "negativeAddProjectTest")
    public void negativeGetProjectTest() {

        given()
                .pathParam("project_id", "id")
                .when()
                .get(Endpoints.GET_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test(dependsOnMethods = "negativeAddProjectTest")
    public void negativeGetUserByEmailTest() {

        given()
                .pathParam("email", "123@mail.ru")
                .when()
                .get(Endpoints.GET_USER_BY_EMAIL)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
