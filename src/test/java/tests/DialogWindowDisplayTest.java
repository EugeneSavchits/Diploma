package tests;

import baseEntities.BaseTest;
import core.ReadProperties;
import models.Project;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddProjectPage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.AdministrationProjectsPage;
import utils.Randomization;

public class DialogWindowDisplayTest extends BaseTest {

    Project addProject;

    private void setupProjects() {
        addProject = Project.builder()
                .name(Randomization.getRandomString(10))
                .typeOfProject(Randomization.getRandomType())
                .announcement(Randomization.getRandomString(25))
                .isShowAnnouncement(true)
                .build();
    }

    @Test
    public void dialogWindowDisplayTest () {
        User user = new User();
        user.setEmail(ReadProperties.getUsername());
        user.setPassword(System.getProperty("PASSWORD"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.getAddProjectButton().click();

        AddProjectPage addProjectPage = new AddProjectPage(driver);
        setupProjects();
        addProjectPage.addProject(addProject);

        AdministrationProjectsPage projectViewPage = new AdministrationProjectsPage(driver);
        projectViewPage.dialogWindowDisplay(addProject);

        Assert.assertTrue(projectViewPage.getDialogWindow().isDisplayed());

    }
}


