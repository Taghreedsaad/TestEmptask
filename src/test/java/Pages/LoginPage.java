package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collection;
import io.qameta.allure.*;


public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By accountMenu = By.cssSelector(".skip-account");
    private final By Login = By.linkText("Log In");

    private By emailInput = By.id("email");
    private By passwordInput = By.id("pass");
    private By loginButton = By.id("send2");
    private By dashboardHeader = By.cssSelector(".page-title h1");
    private By errorMsg = By.cssSelector(".messages .error-msg span");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    @Step("Open login Page")
    public void openLoginPage() {
        driver.findElement(accountMenu).click();
        driver.findElement(Login).click();
    }

    @Step("Enter email: {email}")
    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Click login Button safely")
    public void clickLogonBtn() {

        WebElement loginBtn =
                wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));

        // 1️⃣ Scroll to button
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);",loginBtn);

        // 2️⃣ Wait until clickable
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));

        // 3️⃣ JS Click (avoids interception)
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", loginBtn);
    }

    @Step("Get dashboard header")
    public String getDashboardText() {
        return driver.findElement(dashboardHeader).getText();
    }
    public boolean isErrorMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait
                .until(ExpectedConditions.visibilityOfElementLocated(errorMsg))
                .isDisplayed();
    }

    public Collection<Object> getErrorMessage() {
        return java.util.List.of();
    }
}
