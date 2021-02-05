package pl.edu.pjwstk.jaz.LoginRegister;


import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.LoginRequest;
import pl.edu.pjwstk.jaz.RegisterRequest;
import pl.edu.pjwstk.jaz.IntegrationTest;


import static io.restassured.RestAssured.given;
@RunWith(SpringRunner.class)
@IntegrationTest
public class AdminTest {

    @BeforeClass
    public static void beforeClassRegisterAdmin(){
        given()
                .body(new RegisterRequest("Admin","Admin"))
                .contentType(ContentType.JSON)
                .post("/api/register");
    }

    @Test
    public void adminRegisterTest() {
        // @formatter:off
        given()
                .body(new RegisterRequest("Admin","Admin"))
                .contentType(ContentType.JSON)
                .post("/api/register")
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value());
        // @formatter:on
    }
    @Test
    public void adminLoginTest() {
        // @formatter:off
        given()
                .body(new LoginRequest("Admin","Admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value());
        // @formatter:on
    }

    @Test
    public void adminLoginOnAdminPage() {
        // @formatter:off
        var response =given()
                .body(new LoginRequest("Admin","Admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/admin")
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value());
        // @formatter:on
    }

    @Test
    public void adminLoginOnIsReadyPage() {
        // @formatter:off
        var response =given()
                .body(new LoginRequest("Admin","Admin"))
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
    public void adminLoginOnUserPage() {
        // @formatter:off
        var response =given()
                .body(new LoginRequest("Admin","Admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/users")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
        // @formatter:on
    }

}