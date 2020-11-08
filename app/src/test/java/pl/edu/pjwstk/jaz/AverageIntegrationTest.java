package pl.edu.pjwstk.jaz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringRunner.class)
@IntegrationTest
public class AverageIntegrationTest {
    @Test
    public void czy_dodaje_i_dzieli() {
        // @formatter:off
        given()
                .when()
                .param("numbers", "4,4,8,0,2,6")
                .get("/api/average")
                .then()
                .body("message",equalTo("Average equals: 4"));
        //formatter:on
    }
    @Test
    public void czy_usuwa_zero() {
        // @formatter:off
        given()
                .when()
                .param("numbers", "3,0,3,0")
                .get("/api/average")
                .then()
                .body("message",equalTo("Average equals: 1.5"));
        //formatter:on
    }
    @Test
    public void czy_podnosi_w_gore() {
        // @formatter:off
        given()
                .when()
                .param("numbers", "2,1,1,1,1,2")
                .get("/api/average")
                .then()
                .body("message",equalTo("Average equals: 1.33"));
        //formatter:on
    }
    @Test
    public void gdy_brak() {
        // @formatter:off
        given()
                .when()
                .get("/api/average")
                .then()
                .body("message",equalTo("Please put parameters."));
        //formatter:on
    }
}