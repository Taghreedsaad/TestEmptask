package Tests;

import BaseDate.Base;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.LoginPage;

public class LoginTests extends Base {

    @Test(description = "Valid user can login successfully")
    public void validLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        loginPage.enterEmail("Ahmed@mail.net");
        loginPage.enterPassword("1234Ahmed");
        loginPage.clickLogonBtn();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("account"),
                "User is not redirected to dashboard"
        );

        // Assert 2: Dashboard header
        Assert.assertEquals(
                loginPage.getDashboardText().trim(),
                "MY DASHBOARD",
                "Dashboard text mismatch, login might have failed"
        );
    }

    @Test(description = "Invalid login shows error message")
    public void invalidLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        loginPage.enterEmail("wronguser@mail.com");
        loginPage.enterPassword("WrongPassword");
        loginPage.clickLogonBtn();

        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message not displayed for invalid credentials"
        );
    }
}
