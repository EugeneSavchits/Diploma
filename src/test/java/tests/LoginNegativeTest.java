package tests;

import baseEntities.BaseTest;
import core.ReadProperties;
import models.Project;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddProjectPage;
import pages.AdministrationProjectsPage;
import pages.DashboardPage;
import pages.LoginPage;
import utils.Randomization;

public class LoginNegativeTest extends BaseTest {


    @Test
    public void loginNegativeTest () {
        User user = new User();
        user.setEmail("12345");
        user.setPassword("12345");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        Assert.assertEquals(loginPage.getErrorMessage().getText(), "Sorry, there was a problem.");
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
    }

}


