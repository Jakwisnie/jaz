package pl.edu.pjwstk.jaz;


import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class UserTest {


    @BeforeClass
    public static void beforeClassRegisterAdmin(){
        given()
                .body(new RegisterRequest("Admin","Admin"))
                .contentType(ContentType.JSON)
                .post("/api/register");
        given()
                .body(new RegisterRequest("User","User"))
                .contentType(ContentType.JSON)
                .post("/api/register");

    }
    @Test
    public void adminRegisterTest() {
        // @formatter:off
        given()
                .body(new RegisterRequest("User","User"))
                .contentType(ContentType.JSON)
                .post("/api/register")
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value());
        // @formatter:on
    }
    @Test
    public void userLoginTest() {
        // @formatter:off
        given()
                .body(new LoginRequest("User","User"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value());
        // @formatter:on
    }

    @Test
    public void userLoginOnUserPage() {
        // @formatter:off
        var response =given()
                .body(new LoginRequest("User","User"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/users")
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value());
        // @formatter:on
    }

    @Test
    public void userLoginOnIsReadyPage() {
        // @formatter:off
        var response =given()
                .body(new LoginRequest("User","User"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/auth0/is-ready")
                .then()
                .statusCode(HttpStatus.OK.value());
        // @formatter:on
    }
    @Test
    public void userLoginOnAdminPage() {
        // @formatter:off
        var response =given()
                .body(new LoginRequest("User","User"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/admin")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
        // @formatter:on
    }

}