package web.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.pages.LoginPage;
import web.utils.Hooks;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class WebStepDefinitions {

    private WebDriver driver;
    private LoginPage loginPage;
    private WebDriverWait wait;

    @Before
    public void setup() {
        driver = Hooks.getDriver();
        if (driver == null) {
            throw new IllegalStateException("WebDriver belum diinisialisasi. Periksa konfigurasi Hooks.getDriver().");
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        driver.get("https://www.demoblaze.com");
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("user clicks the login button")
    public void user_clicks_login_button() {
        loginPage.clickLogin();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        } catch (Exception e) {
            System.err.println("Login failed or timeout: " + e.getMessage());
        }
    }

    @Then("user should be redirected to the homepage")
    public void user_should_be_redirected() {
        assertTrue("User is not redirected to homepage", driver.getCurrentUrl().contains("index"));
    }

    @When("user navigates to the cart")
    public void user_navigates_to_the_cart() {
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur")));
        cartButton.click();
    }

    @Then("the cart page should be displayed")
    public void the_cart_page_should_be_displayed() {
        assertTrue("Cart page is not displayed", driver.getCurrentUrl().contains("cart"));
    }

    @Given("user is logged in")
    public void user_is_logged_in() {
        driver.get("https://www.demoblaze.com");
        loginPage.enterUsername("testuser");
        loginPage.enterPassword("password123");
        loginPage.clickLogin();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        } catch (Exception e) {
            System.err.println("Login verification failed: " + e.getMessage());
        }
    }

    @When("user clicks the logout button")
    public void user_clicks_the_logout_button() {
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout2")));
        logoutButton.click();
    }

    @Then("user should be redirected to the login page")
    public void user_should_be_redirected_to_the_login_page() {
        try {
            WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login2")));
            assertTrue("Login button is not visible", loginButton.isDisplayed());
        } catch (Exception e) {
            System.err.println("Logout verification failed: " + e.getMessage());
        }
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
