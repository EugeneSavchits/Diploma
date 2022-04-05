package tests;

import baseEntities.BaseTest;
import core.ReadProperties;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginNegativeScreenshotTest extends BaseTest {


    @Test
    public void loginNegativeScreenshotTest () {
        User user = new User();
        user.setEmail(ReadProperties.getUsername());
        user.setPassword(ReadProperties.getPassword());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage(driver);

        Assert.assertTrue(!dashboardPage.getAddProjectButton().isDisplayed());
    }

}


