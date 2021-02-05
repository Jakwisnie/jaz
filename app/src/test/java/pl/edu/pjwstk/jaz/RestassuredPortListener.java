package pl.edu.pjwstk.jaz;

import io.restassured.RestAssured;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import java.util.Objects;

public class RestassuredPortListener implements TestExecutionListener {

    @Override
    public void beforeTestClass(TestContext testContext){
        var enviroment = testContext.getApplicationContext().getBean(Environment.class);
        var portAsString = Objects.requireNonNull(enviroment.getProperty("local.server.port"));

        RestAssured.port = Integer.parseInt(portAsString);
    }
}
