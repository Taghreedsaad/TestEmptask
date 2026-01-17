package Tests;

import BaseDate.Base;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.RegisterPage;

public class RegisterTests extends Base {

    @Test
    public void validUserCanRegisterSuccessfully() {

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.openRegisterPage();
        registerPage.enterFirstName("Taghreed");
        registerPage.enterLastName("Saad");
        registerPage.enterEmail("Taghreed" + System.currentTimeMillis() + "@mail.com");
        registerPage.enterPassword("Password123");
        registerPage.enterConfirmPassword("Password123");
        registerPage.clickRegister();

        Assert.assertTrue(
                registerPage.getSuccessMessage().contains("Thank you for registering"),
                "Registration failed"
        );
    }
}
