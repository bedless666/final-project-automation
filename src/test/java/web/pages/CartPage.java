package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By cartButton = By.id("cartur");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openCartPage() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
    }

    public boolean isCartPageDisplayed() {
        return wait.until(ExpectedConditions.urlContains("cart.html"));
    }
}
