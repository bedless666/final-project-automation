package web.stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.pages.LoginPage;
import web.utils.Hooks;
import java.time.Duration;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class WebStepDefinitions {
    WebDriver driver;
    LoginPage loginPage;
    WebDriverWait wait;

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        assertFalse("Error message not displayed", alert.getText().isEmpty());
        alert.accept();
    }

    @Given("user is logged in")
    public void user_is_logged_in() {
        user_is_on_the_login_page();
        user_enters_credentials("bedless666", "jayjay666");
        user_clicks_login_button();
        assertTrue("Login failed, username not displayed.", loginPage.isUserNameDisplayed());
    }

    @When("user navigates to the cart page")
    public void user_navigates_to_cart_page() {
        driver.findElement(By.id("cartur")).click();
    }

    @Then("the cart page should be displayed")
    public void the_cart_page_should_be_displayed() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("cart.html"));
        assertTrue("Cart page not displayed", driver.getCurrentUrl().contains("cart.html"));
    }


    @When("user clicks logout")
    public void user_clicks_logout() {
        driver.findElement(By.id("logout2")).click();
    }

    @Then("user should be logged out")
    public void user_should_be_logged_out() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login2")));
        assertFalse("Logout failed, user still logged in", loginPage.isUserNameDisplayed());
    }
}
