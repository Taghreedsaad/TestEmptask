package Tests;

import BaseDate.Base;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.AccessoriesPage;
import Pages.LoginPage;
import Pages.ProductDetailsPage;

@Epic("Ecommerce")
@Feature("Add Product To Cart")
public class AddToCartTests extends Base {

    @Test(description = "Add Dorian Shoes from Accessories to cart")
    @Story("User adds Dorian Shoes to cart")
    public void addShoesToCartTest() {

        LoginPage loginPage = new LoginPage(driver);
        AccessoriesPage accessoriesPage = new AccessoriesPage(driver);
        ProductDetailsPage productPage = new ProductDetailsPage(driver);

        loginPage.openLoginPage();
        loginPage.enterEmail("Ahmed@mail.net");
        loginPage.enterPassword("1234Ahmed");
        loginPage.clickLogonBtn();

        Assert.assertTrue(driver.getCurrentUrl().contains("account"),
                "User is not redirected to dashboard");
        Assert.assertEquals(loginPage.getDashboardText().trim(),
                "MY DASHBOARD",
                "Dashboard text mismatch, login might have failed");

        // Navigate to Accessories -> Shoes
        accessoriesPage.openAccessoriesMenu();
        accessoriesPage.clickShoes();
        Assert.assertEquals(accessoriesPage.getShoesHeader(), "SHOES");

        // Select product and add to cart
        accessoriesPage.selectDorianShoes();

        Assert.assertEquals(
                productPage.getProductTitle(),
                "Dorian Shoes"
        );

        productPage.selectColor();
        productPage.selectSize();
        productPage.addToCart();

        Assert.assertTrue(productPage.getSuccessMessage().contains("was added to your shopping cart"),
                "Product not added to cart");
    }
}
