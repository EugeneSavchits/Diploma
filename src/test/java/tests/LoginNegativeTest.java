package tests;

import baseEntities.BaseTest;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginNegativeTest extends BaseTest {


    @Test
    public void loginNegativeTest() {
        User user = new User();
        user.setEmail("12345");
        user.setPassword("12345");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        Assert.assertEquals(loginPage.getErrorMessage().getText(), "Sorry, there was a problem.");
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
    }

}


