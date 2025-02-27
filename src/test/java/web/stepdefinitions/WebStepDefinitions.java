package web.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import web.pages.LoginPage;
import web.utils.Hooks;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class WebStepDefinitions {
    WebDriver driver;
    LoginPage loginPage;

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        driver = Hooks.getDriver();
        driver.get("https://www.demoblaze.com");
        loginPage = new LoginPage(driver);
        loginPage.openLoginModal();
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("user clicks the login button")
    public void user_clicks_login_button() {
        loginPage.clickLogin();
    }

    @Then("user should be redirected to the homepage")
    public void user_should_be_redirected() {
        assertTrue("Username is not displayed after login", loginPage.isUserNameDisplayed());
    }

    @Then("an error message should be displayed")
    public void error_message_should_be_displayed() {
        String errorMsg = loginPage.getErrorMessage();
        assertFalse("Error message not displayed", errorMsg.isEmpty());
    }

    @Given("user is logged in")
    public void user_is_logged_in() {
        driver = Hooks.getDriver();
        driver.get("https://www.demoblaze.com");
        loginPage = new LoginPage(driver);
        loginPage.openLoginModal();
        loginPage.enterUsername("bedless666");
        loginPage.enterPassword("jayjay666");
        loginPage.clickLogin();
        assertTrue("Login failed, username not displayed.", loginPage.isUserNameDisplayed());
    }

    @When("user navigates to the cart page")
    public void user_navigates_to_cart_page() {
        driver.get("https://www.demoblaze.com/cart.html");
    }

    @Then("cart page should be displayed")
    public void cart_page_should_be_displayed() {
        assertTrue("Cart page not displayed", driver.getCurrentUrl().contains("cart.html"));
    }

    @When("user clicks logout")
    public void user_clicks_logout() {
        driver.findElement(By.id("logout2")).click();
    }

    @Then("user should be logged out")
    public void user_should_be_logged_out() {
        assertFalse("Logout failed, user still logged in", loginPage.isUserNameDisplayed());
    }
}
