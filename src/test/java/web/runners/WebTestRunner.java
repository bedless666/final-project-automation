package web.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/web",
        glue = "web.stepdefinitions",
        tags = "@web",
        plugin = {"pretty", "html:target/cucumber-reports/web.html", "json:target/cucumber-reports/web.json"}
)
public class WebTestRunner {
}
