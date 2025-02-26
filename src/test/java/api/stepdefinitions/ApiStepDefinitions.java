// ApiStepDefinitions.java
package api.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiStepDefinitions {

    private String baseUrl = "https://dummyapi.io/data/v1/user";
    private String apiKey = "YOUR_API_KEY";

    @Given("user has an API key")
    public void user_has_an_api_key() {
        System.out.println("User API key is set.");
    }

    @When("user sends a request to get user with ID {string}")
    public void user_sends_request_to_get_user(String userId) {
        given()
                .header("app-id", apiKey)
                .when()
                .get(baseUrl + "/" + userId)
                .then()
                .statusCode(200);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        given()
                .header("app-id", apiKey)
                .when()
                .get(baseUrl + "/123")
                .then()
                .statusCode(statusCode);
    }
}