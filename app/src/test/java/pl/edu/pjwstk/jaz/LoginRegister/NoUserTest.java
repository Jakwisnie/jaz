package pl.edu.pjwstk.jaz.LoginRegister;




import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.LoginRequest;
import pl.edu.pjwstk.jaz.IntegrationTest;
import static io.restassured.RestAssured.given;


@RunWith(SpringRunner.class)
@IntegrationTest
public class NoUserTest {

    @Test
    public void adminLoginTest() {
        // @formatter:off
        given()
                .body(new LoginRequest("Admin","Admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .then()
                .statusCode(org.springframework.http.HttpStatus.UNAUTHORIZED.value());
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
                .statusCode(org.springframework.http.HttpStatus.UNAUTHORIZED.value());
        // @formatter:on
    }

    @Test
    public void openAdminPage() {
        // @formatter:off
        given()
                .get("/api/admin")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
        // @formatter:on

    }
    @Test
    public void openUserPage() {
        // @formatter:off
        given()
                .get("/api/users")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
        // @formatter:on

    }
    @Test
    public void openEveryonePage() {
        // @formatter:off
        given()
                .get("/api/auth0/is-ready")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
        // @formatter:on

    }
}