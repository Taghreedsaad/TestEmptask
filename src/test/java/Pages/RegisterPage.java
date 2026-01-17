package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By accountMenu = By.cssSelector(".skip-account");
    private final By registerLink = By.linkText("Register");

    private final By firstName = By.id("firstname");
    private final By lastName = By.id("lastname");
    private final By email = By.id("email_address");
    private final   By password = By.id("password");
    private final By confirmPassword = By.id("confirmation");
    private final By registerButton = By.cssSelector("button[title='Register']");
    private final By successMessage = By.cssSelector(".success-msg span");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Open Register Page")
    public void openRegisterPage() {
        driver.findElement(accountMenu).click();
        driver.findElement(registerLink).click();
    }

    public void enterFirstName(String value) {
        driver.findElement(firstName).sendKeys(value);
    }

    public void enterLastName(String value) {
        driver.findElement(lastName).sendKeys(value);
    }

    public void enterEmail(String value) {
        driver.findElement(email).sendKeys(value);
    }

    public void enterPassword(String value) {
        driver.findElement(password).sendKeys(value);
    }

    public void enterConfirmPassword(String value) {
        driver.findElement(confirmPassword).sendKeys(value);
    }

    @Step("Click Register Button safely")
    public void clickRegister() {

        WebElement registerBtn =
                wait.until(ExpectedConditions.presenceOfElementLocated(registerButton));

        // 1️⃣ Scroll to button
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", registerBtn);

        // 2️⃣ Wait until clickable
        wait.until(ExpectedConditions.elementToBeClickable(registerBtn));

        // 3️⃣ JS Click (avoids interception)
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", registerBtn);
    }

    public String getSuccessMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(successMessage)
        ).getText();
    }
}
