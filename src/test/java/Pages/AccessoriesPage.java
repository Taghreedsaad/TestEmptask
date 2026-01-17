package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.*;

import java.time.Duration;

public class AccessoriesPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    // Locators
    private By accessoriesMenu = By.cssSelector("li.nav-3");
    private By shoesLink = By.cssSelector("li.nav-3 li.nav-3-3 > a");
    private By shoesHeader = By.xpath("//h1[normalize-space()='Shoes']");
    private By privacyPopupClose = By.xpath("//html/body/div[3]/div/div[3]");
    private By privacyPopup = By.xpath("/html/body/div[3]/div");
   private By dorianShoesProduct = By.cssSelector("body>div.wrapper>div>div.main-container.col3-layout>div>div.col-wrapper>div.col-main>div.category-products>ul>li:nth-child(4)>div>h2>a");


    public AccessoriesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions=new Actions(driver);
    }
    @Step("Close privacy popup if present")
    public void closePrivacyPopupIfPresent() {
        try {
            WebElement popupCloseButton = driver.findElement(privacyPopupClose);
            popupCloseButton.click();
            System.out.println("Privacy popup closed with X.");

            wait.until(ExpectedConditions.invisibilityOfElementLocated(privacyPopup));
        } catch (Exception e) {
            System.out.println("No privacy popup present.");
        }
    }

    @Step("open Accessories Menu")

    public void openAccessoriesMenu() {
        closePrivacyPopupIfPresent();

        WebElement accessories =
                wait.until(ExpectedConditions.visibilityOfElementLocated(accessoriesMenu));

        actions.moveToElement(accessories).pause(Duration.ofMillis(500)).perform();
    }
    @Step("clickShoes")
    public void clickShoes() {
        WebElement shoes =
                wait.until(ExpectedConditions.visibilityOfElementLocated(shoesLink));

        actions.moveToElement(shoes).click().perform();
    }
    @Step("getShoesHeader")
    public String getShoesHeader() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(shoesHeader)
        ).getText();    }

    @Step("selectDorianShoes")

    public void selectDorianShoes() {
        closePrivacyPopupIfPresent();

        WebElement product = wait.until(
                ExpectedConditions.visibilityOfElementLocated(dorianShoesProduct)
        );

        ((JavascriptExecutor) driver).executeScript(
                "window.scrollTo(0, arguments[0].getBoundingClientRect().top + window.scrollY - 100);",
                product
        );

        wait.until(ExpectedConditions.elementToBeClickable(product));

        product.click();

        System.out.println("Clicked on Dorian Shoes successfully!");
    }


}
