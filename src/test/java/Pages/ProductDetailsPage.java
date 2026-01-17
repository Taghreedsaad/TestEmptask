package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {

    private WebDriver driver;

    private By productTitle = By.xpath(".//h2[@class='product-name']//a");
    private By colorOption = By.id("swatch20");
    private By sizeOption = By.id("swatch77");
    private By addToCartButton = By.cssSelector("button[title='Add to Cart']");
    private By successMessage = By.cssSelector(".success-msg span");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Get product title")
    public String getProductTitle() {
        return driver.findElement(productTitle).getText();
    }

    @Step("Select color")
    public void selectColor() {
        driver.findElement(colorOption).click();
    }

    @Step("Select size")
    public void selectSize() {
        driver.findElement(sizeOption).click();
    }

    @Step("Add product to cart")
    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    @Step("Get success message")
    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }
}
