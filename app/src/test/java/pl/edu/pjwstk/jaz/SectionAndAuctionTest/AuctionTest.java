package pl.edu.pjwstk.jaz.SectionAndAuctionTest;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.Authorization.LoginRequest;
import pl.edu.pjwstk.jaz.Authorization.RegisterRequest;
import pl.edu.pjwstk.jaz.IntegrationTest;
import pl.edu.pjwstk.jaz.Requests.*;
import java.util.Arrays;
import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class AuctionTest {

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
        given()
                .body(new RegisterRequest("User2","User2"))
                .contentType(ContentType.JSON)
                .post("/api/register");
    }

  public Response adminLogging(){
    return given()
              .body(new LoginRequest("Admin","Admin"))
              .contentType(ContentType.JSON)
              .post("/api/login")
              .thenReturn();
  }

    public Response userLogging(){
        return given()
                .body(new LoginRequest("User","User"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
    }
    public Response user2Logging(){
        return given()
                .body(new LoginRequest("User2","User2"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
    }

    @Test
    public void adminAddAuctionTest403() {
        // @formatter:off
        given()
                .body(new SectionRequest("A",Arrays.asList("","")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("A",Arrays.asList("A1","A2")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory");
        given()
                .body(new AuctionRequest("Comment1","Stuff1",1,"A1",Arrays.asList("P1","P2"),Arrays.asList("V1","V2"),Arrays.asList("Pa1","Pa2")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addAuction")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
        // @formatter:on
    }

    @Test
    public void notLoggedPersonAddAuctionTest403() {
        // @formatter:off
        given()
                .body(new SectionRequest("G",Arrays.asList("","")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("G",Arrays.asList("G1","G2")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory");
        given()
                .body(new AuctionRequest("Comment1","Stuff1",1,"G1",Arrays.asList("P1","P2"),Arrays.asList("V1","V2"),Arrays.asList("Pa1","Pa2")))
                .contentType(ContentType.JSON)
                .post("/api/addAuction")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
        // @formatter:on
    }

    @Test
    public void userAddAuctionTest200() {
        // @formatter:off
        given()
                .body(new SectionRequest("H",Arrays.asList("","")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("H",Arrays.asList("H1","H2")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory");
        given()
                .body(new AuctionRequest("Comment1","Stuff1",1,"H1",Arrays.asList("P1","P2"),Arrays.asList("V1","V2"),Arrays.asList("Pa1","Pa2")))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/addAuction")
                .then()
                .statusCode(HttpStatus.OK.value());
        // @formatter:on
    }
    @Test
    public void userAddAuctionTestWithBadCategoryName400() {
        // @formatter:off
        given()
                .body(new SectionRequest("L",Arrays.asList("","")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("L",Arrays.asList("L1","L2")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory");
        given()
                .body(new AuctionRequest("Comment1","Stuff1",1,"L3",Arrays.asList("P1","P2"),Arrays.asList("V1","V2"),Arrays.asList("Pa1","Pa2")))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/addAuction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        // @formatter:on
    }

    @Test
    public void userAddAuctionTestWithEmptyValueForParameter400() {
        // @formatter:off
        given()
                .body(new SectionRequest("Z",Arrays.asList("","")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("Z",Arrays.asList("Z1","Z2")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory");
        given()
                .body(new AuctionRequest("Comment1","Stuff1",1,"Z2",Arrays.asList("P1","P2"),Arrays.asList("V1"),Arrays.asList("Pa1","Pa2")))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/addAuction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        // @formatter:on
    }

    @Test
    public void userAddAuctionTestWithEmptyParameterForValue400() {
        // @formatter:off
        given()
                .body(new SectionRequest("X",Arrays.asList("","")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("X",Arrays.asList("X1","X2")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory");
        given()
                .body(new AuctionRequest("Comment1","Stuff1",1,"X2",Arrays.asList("P1","P2"),Arrays.asList("V1","V2"),Arrays.asList("Pa1")))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/addAuction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        // @formatter:on
    }

    @Test
    public void userAddAuctionTestWithEmptyCategoryName400() {
        // @formatter:off
        given()
                .body(new SectionRequest("S",Arrays.asList("","")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("S",Arrays.asList("S1","S2")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory");
        given()
                .body(new AuctionRequest("Comment1","Stuff1",1,"",Arrays.asList("P1","P2"),Arrays.asList("V1","V2"),Arrays.asList("Pa1")))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/addAuction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        // @formatter:on
    }

    @Test
    public void userAddAuctionTestWithEmptyTitleName400() {
        // @formatter:off
        given()
                .body(new SectionRequest("D",Arrays.asList("","")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("D",Arrays.asList("D1","D2")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory");
        given()
                .body(new AuctionRequest("Comment1","",1,"D1",Arrays.asList("P1","P2"),Arrays.asList("V1","V2"),Arrays.asList("Pa1")))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/addAuction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        // @formatter:on
    }

    @Test
    public void userEditAuctionTest200() {
        // @formatter:off
        given()
                .body(new SectionRequest("F",Arrays.asList("","")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("F",Arrays.asList("F1","F2")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory");
        given()
                .body(new AuctionRequest("Comment1","Stuff1",1,"F1",Arrays.asList("P1","P2"),Arrays.asList("V1","V2"),Arrays.asList("Pa1","Pa2")))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/addAuction");
        given()
                .body(new AuctionEditRequest(1L,"Stuff1",1,"Pa1","V3","Stuff2",2))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/editAuction")
                .then()
                .statusCode(HttpStatus.OK.value());
        // @formatter:on
    }

    @Test
    public void userEditAuctionTestWithEmptyNewTitle400() {
        // @formatter:off
        given()
                .body(new SectionRequest("Se1",Arrays.asList("","")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("Se1",Arrays.asList("Au1","Au2")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory");
        given()
                .body(new AuctionRequest("Comment1","Stuff1",1,"Au1",Arrays.asList("P1","P2"),Arrays.asList("V1","V2"),Arrays.asList("Pa1","Pa2")))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/addAuction");
        given()
                .body(new AuctionEditRequest(1L,"Stuff1",1,"Pa1","V3","",2))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/editAuction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        // @formatter:on
    }


    @Test
    public void userEditAuctionTestWithEmptyNewValue400() {
        // @formatter:off
        given()
                .body(new SectionRequest("Se2",Arrays.asList("","")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("Se2",Arrays.asList("Au3","Au4")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory");
        given()
                .body(new AuctionRequest("Comment1","Stuff1",1,"Au3",Arrays.asList("P1","P2"),Arrays.asList("V1","V2"),Arrays.asList("Pa1","Pa2")))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/addAuction");
        given()
                .body(new AuctionEditRequest(1L,"Stuff1",1,"Pa1","","New1",2))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/editAuction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        // @formatter:on
    }

    @Test
    public void user2EditAuctionTestWithBadAuctionId400() {
        // @formatter:off
        given()
                .body(new SectionRequest("Se3",Arrays.asList("","")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("Se3",Arrays.asList("Au5","Au6")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory");
        given()
                .body(new AuctionRequest("Comment1","Stuff1",1,"Au5",Arrays.asList("P1","P2"),Arrays.asList("V1","V2"),Arrays.asList("Pa1","Pa2")))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/addAuction");
        given()
                .body(new AuctionEditRequest(1L,"Stuff1",1,"Pa1","V3","New1",2))
                .contentType(ContentType.JSON)
                .cookies(user2Logging().getCookies())
                .post("/api/editAuction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        // @formatter:on
    }

    @Test
    public void userEditAuctionTestWithNewParameter200() {
        // @formatter:off
        given()
                .body(new SectionRequest("Se4",Arrays.asList("","")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("Se4",Arrays.asList("Au7","Au8")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory");
        given()
                .body(new AuctionRequest("Comment1","Stuff1",1,"Au7",Arrays.asList("P1","P2"),Arrays.asList("V1","V2"),Arrays.asList("Pa1","Pa2")))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/addAuction");
        given()
                .body(new AuctionEditRequest(1L,"Stuff1",1,"Pa3","V3","New1",2))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/editAuction")
                .then()
                .statusCode(HttpStatus.OK.value());
        // @formatter:on
    }







    @Test
    public void adminAddPhoto403() {
        // @formatter:off
        given()
                .body(new PhotosEditRequest(1L,"newlink",1L))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addPhoto")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
        // @formatter:on
    }

    @Test
    public void user2AddPhoto403() {
        // @formatter:off
        given()
                .body(new PhotosEditRequest(1L,"newlink",1L))
                .contentType(ContentType.JSON)
                .cookies(user2Logging().getCookies())
                .post("/api/addPhoto")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        // @formatter:on
    }

    @Test
    public void userAddPhoto403() {
        // @formatter:off
        given()
                .body(new PhotosEditRequest(1L,"newlink",1L))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/addPhoto")
                .then()
                .statusCode(HttpStatus.OK.value());
        // @formatter:on
    }


    @Test
    public void adminEditPhoto403() {
        // @formatter:off
        given()
                .body(new PhotosEditRequest(1L,"newlink",1L))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/editPhoto")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
        // @formatter:on
    }

    @Test
    public void user2EditPhoto403() {
        // @formatter:off
        given()
                .body(new PhotosEditRequest(1L,"newlink",1L))
                .contentType(ContentType.JSON)
                .cookies(user2Logging().getCookies())
                .post("/api/editPhoto")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        // @formatter:on
    }

    @Test
    public void userEditPhoto403() {
        // @formatter:off
        given()
                .body(new PhotosEditRequest(1L,"newlink",1L))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/editPhoto")
                .then()
                .statusCode(HttpStatus.OK.value());
        // @formatter:on
    }





    @Test
    public void userGetAuctionsTest200() {
        // @formatter:off
        given()
                .cookies(userLogging().getCookies())
                .get("/api/getAuctionWithPhoto")
                .then()
                .statusCode(HttpStatus.OK.value());
        // @formatter:on
    }


    @Test
    public void user2GetAuctionsTest200() {
        // @formatter:off
        given()
                .cookies(user2Logging().getCookies())
                .get("/api/getAuctionWithPhoto")
                .then()
                .statusCode(HttpStatus.OK.value());
        // @formatter:on
    }

    @Test
    public void adminGetAuctionsTest403() {
        // @formatter:off
        given()
                .cookies(adminLogging().getCookies())
                .get("/api/getAuctionWithPhoto")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
        // @formatter:on
    }
}