package testsApi;

import baseEntities.BaseApiTest;
import com.google.gson.Gson;
import enums.ProjectType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.*;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Endpoints;
import utils.Randomization;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class ApiNfeTest extends BaseApiTest {

    ProjectApi project;
    Milestone milestone;
    Case case1;
    int projectID;
    int milestoneID;
    int sectionID;
    int caseID;


    @Test
    public void addProjectTest() {

        project = ProjectApi.builder()
                .name(Randomization.getRandomString(10))
                .announcement(Randomization.getRandomString(100))
                .typeOfProject(ProjectType.SINGLE.getProjectType())
                .build();

        projectID = given()
                .body(project, ObjectMapperType.GSON)
                .log().body()
                .when()
                .post(Endpoints.POST_ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("name", is(project.getName()))
                .body("suite_mode", is(project.getTypeOfProject()))
                .extract().jsonPath().get("id");

    }

    @Test(dependsOnMethods = "addProjectTest")
    public void getProjectTest() {

        given()
                .pathParam("project_id", projectID)
                .when()
                .get(Endpoints.GET_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("name", is(project.getName()))
                .body("suite_mode", is(project.getTypeOfProject()));
    }

    @Test(dependsOnMethods = "getProjectTest")
    public void addMilestoneTest() {

        milestone = Milestone.builder()
                .name(Randomization.getRandomString(10))
                .description(Randomization.getRandomString(15))
                .refs(Randomization.getRandomString(5))
                .build();

        milestoneID = given()
                .pathParam("project_id", projectID)
                .body(milestone, ObjectMapperType.GSON)
                .log().body()
                .when()
                .post(Endpoints.POST_ADD_MILESTONE)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("name", is(milestone.getName()))
                .body("description", is(milestone.getDescription()))
                .extract().jsonPath().get("id");
    }

    @Test(dependsOnMethods = "addMilestoneTest")
    public void getMilestoneTest() {

        Response response = given()
                .pathParam("milestone_id", milestoneID)
                .when()
                .get(Endpoints.GET_MILESTONE)
                .then()
                .assertThat()
                .extract()
                .response();

        Assert.assertEquals(response.getBody().jsonPath().get("name"),
                milestone.getName());
    }

    @Test(dependsOnMethods = "getMilestoneTest")
    public void addSection() {

        Section section = Section.builder()
                .name(Randomization.getRandomString(8))
                .description(Randomization.getRandomString(10))
                .build();

        sectionID = given()
                .pathParam("project_id", projectID)
                .body(section, ObjectMapperType.GSON)
                .log().body()
                .when()
                .post(Endpoints.POST_ADD_SECTION)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");
    }

    @Test(dependsOnMethods = "addSection")
    public void addCase() {

        case1 = Case.builder()
                .title(Randomization.getRandomString(5))
                .priorityId(4)
                .build();

        caseID = given()
                .pathParam("section_id", sectionID)
                .body(case1, ObjectMapperType.GSON)
                .log().body()
                .when()
                .post(Endpoints.POST_ADD_CASE)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");
    }

    @Test(dependsOnMethods = "addCase")
    public void getCase() {
        Gson gson = new Gson();

        Response response = given()
                .pathParam("case_id", caseID)
                .when()
                .get(Endpoints.GET_CASE);

        Case actualCase = gson.fromJson(response.getBody().asString(), Case.class);
        Assert.assertEquals(actualCase, case1);
    }

    @Test(dependsOnMethods = "getCase")
    public void updateProject() {

        ProjectApi updateProject = ProjectApi.builder()
                .name(Randomization.getRandomString(15))
                .announcement(Randomization.getRandomString(150))
                .typeOfProject(ProjectType.SINGLE_WITH_BASELINE.getProjectType())
                .isCompleted(true)
                .build();

        given()
                .pathParam("project_id", projectID)
                .body(updateProject, ObjectMapperType.GSON)
                .log().body()
                .when()
                .post(Endpoints.POST_UPDATE_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("name", is(updateProject.getName()))
                .body("announcement", is(updateProject.getAnnouncement()));
    }

    @Test(dependsOnMethods = "updateProject")
    public void deleteProject() {

        given()
                .pathParam("project_id", projectID)
                .when()
                .post(Endpoints.POST_DELETE_PROJECT)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
