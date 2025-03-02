package web.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By loginNavButton = By.id("login2");
    private By usernameField = By.id("loginusername");
    private By passwordField = By.id("loginpassword");
    private By loginButton = By.xpath("//button[text()='Log in']");
    private By loggedInUser = By.id("nameofuser");
    private By logoutButton = By.id("logout2");
    private By errorMessage = By.xpath("//div[contains(text(),'Wrong password')]|//div[contains(text(),'User does not exist.')]|//div[contains(text(),'Please fill out Username and Password.')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openLoginModal() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginNavButton));
        scrollToElement(loginBtn);
        safeClick(loginBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
    }

    public void enterUsername(String username) {
        WebElement userInput = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        userInput.clear();
        userInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passInput.clear();
        passInput.sendKeys(password);
    }

    public void clickLogin() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        scrollToElement(loginBtn);
        closePopupIfExists();
        safeClick(loginBtn);
    }

    public boolean isUserLoggedIn() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInUser)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUserNameDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInUser)).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public void clickLogout() {
        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        scrollToElement(logoutBtn);
        safeClick(logoutBtn);
        wait.until(ExpectedConditions.elementToBeClickable(loginNavButton)); // Pastikan logout sukses
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void safeClick(WebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private void closePopupIfExists() {
        try {
            WebElement popupClose = driver.findElement(By.xpath("//button[contains(@class, 'close')]"));
            if (popupClose.isDisplayed()) {
                popupClose.click();
            }
        } catch (NoSuchElementException ignored) {
        }
    }
}
