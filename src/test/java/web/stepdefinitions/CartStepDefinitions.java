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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        wait.until(ExpectedConditions.urlContains("cart.html"));
        assertTrue("Cart page not displayed", driver.getCurrentUrl().contains("cart.html"));
    }

    @When("user adds an item to the cart")
    public void user_adds_an_item_to_the_cart() {
        WebElement firstItem = driver.findElement(By.cssSelector(".card-title a"));
        firstItem.click(); // Klik item pertama

        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add to cart')]")));
        addToCartButton.click(); // Klik tombol "Add to cart"

        // Tunggu alert muncul dan diterima
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        // Kembali ke halaman utama
        driver.navigate().to("https://www.demoblaze.com/");
    }

    @When("user proceeds to checkout")
    public void user_proceeds_to_checkout() {
        WebElement placeOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Place Order')]")));
        placeOrderButton.click();
    }

    @When("user enters payment details and confirms purchase")
    public void user_enters_payment_details_and_confirms_purchase() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderModal")));

        // Isi data checkout
        driver.findElement(By.id("name")).sendKeys("John Doe");
        driver.findElement(By.id("country")).sendKeys("Indonesia");
        driver.findElement(By.id("city")).sendKeys("Jakarta");
        driver.findElement(By.id("card")).sendKeys("4111 1111 1111 1111");
        driver.findElement(By.id("month")).sendKeys("12");
        driver.findElement(By.id("year")).sendKeys("2025");

        // Klik tombol "Purchase"
        WebElement purchaseButton = driver.findElement(By.xpath("//button[contains(text(),'Purchase')]"));
        purchaseButton.click();
    }

    @Then("user should see a confirmation message")
    public void user_should_see_a_confirmation_message() {
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Thank you for your purchase!')]")));
        assertTrue("Purchase confirmation message not displayed", confirmationMessage.isDisplayed());
    }
}
