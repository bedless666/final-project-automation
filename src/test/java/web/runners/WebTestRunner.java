package web.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Test;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/web",
        glue = "web.stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class WebTestRunner {
    // Tidak perlu anotasi @Test di sini
}

