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
    public void when_no_parameter_supplied_should_print_a_message() {
      // @formatter:off
        given()
        .when()
              .get("/api/average")
        .then()
              .body("message",equalTo("Please put parameters."));
        //formatter:on
    }
    @Test
    public void should_remove_trailing_zero_case_1() {
        // @formatter:off
        given()
        .when()
                .param("numbers", "1,2")
                .get("/api/average")
        .then()
                .body("message",equalTo("Average equals: 1.5"));
        //formatter:on
    }
    @Test
    public void should_round_with_HALF_UP() {
        // @formatter:off
        given()
        .when()
                .param("numbers", "2,1,1")
                .get("/api/average")
        .then()
                .body("message",equalTo("Average equals: 1.33"));
        //formatter:on
    }
    @Test
    public void should_print_integer_if_zero_after_dot() {
        // @formatter:off
        given()
        .when()
                .param("numbers", "10,25,5,4")
                .get("/api/average")
        .then()
                .body("message",equalTo("Average equals: 11"));
        //formatter:on
    }
}