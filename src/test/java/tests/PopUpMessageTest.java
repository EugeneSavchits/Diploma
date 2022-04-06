package tests;

import baseEntities.BaseTest;
import core.ReadProperties;
import models.User;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class PopUpMessageTest extends BaseTest {

    @Test
    public void popUpMessageTest () {
        User user = new User();
        user.setEmail(ReadProperties.getUsername());
        user.setPassword(System.getProperty("PASSWORD"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage(driver);

        Actions action = new Actions(driver);

        action
                .moveToElement(dashboardPage.getPopUp())
                .build()
                .perform();

        Assert.assertTrue(dashboardPage.getPopUpMessage().isDisplayed());
    }
}


