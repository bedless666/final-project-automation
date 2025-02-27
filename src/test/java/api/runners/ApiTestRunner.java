package api.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = "api.stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-reports/api.html", "json:target/cucumber-reports/api.json"}
)
public class ApiTestRunner {
}
