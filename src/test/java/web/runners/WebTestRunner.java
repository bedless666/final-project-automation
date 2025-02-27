package web.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.junit.BeforeClass; // Tambahkan import

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/web",
        glue = {"web.stepdefinitions", "web.utils"}, // Tambahkan "web.utils"
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class WebTestRunner {
    @BeforeClass
    public static void setup() {
        System.out.println("WebTestRunner setup called");
    }
}