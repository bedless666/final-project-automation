package web.stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.pages.LoginPage;
import web.utils.Hooks;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginStepDefinitions {
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
        loginPage = new LoginPage(driver); // Re-inisialisasi objek LoginPage
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Perpanjang waktu tunggu

        try {
            // Tunggu sampai username muncul di halaman setelah login
            WebElement userNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
            assertTrue("Login failed, username not displayed", userNameElement.isDisplayed());
        } catch (TimeoutException e) {
            Assert.fail("Login failed, username element did not appear within timeout");
        }
    }



    @Then("an error message should be displayed")
    public void error_message_should_be_displayed() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String errorMessage = alert.getText();
            alert.accept(); // Tutup alert setelah ditangkap

            // Validasi pesan error yang mungkin muncul
            assertTrue("Unexpected error message: " + errorMessage,
                    errorMessage.contains("Wrong password.") ||
                            errorMessage.contains("User does not exist.") ||
                            errorMessage.contains("Please fill out Username and Password."));
        } catch (TimeoutException e) {
            // Tambahkan fallback untuk mengecek elemen error di halaman
            if (!loginPage.isErrorMessageDisplayed()) {
                Assert.fail("No alert or error message displayed after incorrect login");
            }
        }
    }


    @Given("user is logged in")
    public void user_is_logged_in() {
        user_is_on_the_login_page();
        user_enters_credentials("bedless666", "jayjay666");
        user_clicks_login_button();
        assertTrue("Login failed, username not displayed.", loginPage.isUserNameDisplayed());
    }

    @When("user clicks logout")
    public void user_clicks_logout() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout2")));

        try {
            logoutButton.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutButton);
        }
    }

    @Then("user should be logged out")
    public void user_should_be_logged_out() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Pastikan elemen "nameofuser" menghilang sebelum mengecek tombol login muncul
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("nameofuser")));

            // Tunggu hingga tombol login muncul setelah logout
            WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login2")));
            assertTrue("Logout failed, login button not visible", loginButton.isDisplayed());
        } catch (TimeoutException e) {
            Assert.fail("Logout failed, login button did not appear within timeout");
        }
    }



}
