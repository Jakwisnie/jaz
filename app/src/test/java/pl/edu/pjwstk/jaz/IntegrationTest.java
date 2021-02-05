package pl.edu.pjwstk.jaz;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test", "noSecurity"})
<<<<<<< HEAD
@TestExecutionListeners(listeners = {
        RestassuredPortListener.class,
        TestContextHolder.class
}, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
=======
@TestExecutionListeners(listeners = {TestContextHolder.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
>>>>>>> parent of d6e0615... 23
public @interface IntegrationTest {
}