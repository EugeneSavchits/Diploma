package baseEntities;

import core.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class BaseApiTest {

    @BeforeTest
    public void setupApiTest() {
        //Setup RestAssured
        RestAssured.baseURI = ReadProperties.getUrl();

        //Setup request Object
        RestAssured.requestSpecification = given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON) //указание в каком формате будут приходить данные
                .auth().preemptive().basic(ReadProperties.getUsername(), System.getProperty("PASSWORD")); //метод preemptive() для старых сайтов, для новых только basic()

    }
}