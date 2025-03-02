package web.stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.utils.Hooks;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class CartStepDefinitions {
    WebDriver driver;
    WebDriverWait wait;

    public CartStepDefinitions() {
        driver = Hooks.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @When("user navigates to the cart page")
    public void user_navigates_to_cart_page() {
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur")));

        try {
            cartButton.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartButton);
        }
    }

    @Then("the cart page should be displayed")
    public void the_cart_page_should_be_displayed() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Perpanjang waktu tunggu
        wait.until(ExpectedConditions.urlContains("cart.html"));
        assertTrue("Cart page not displayed", driver.getCurrentUrl().contains("cart.html"));
    }

}
