package tests;

import baseEntities.BaseTest;
import core.ReadProperties;
import models.Project;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddProjectPage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.AdministrationProjectsPage;
import utils.Randomization;

public class CreateDeleteProjectTest extends BaseTest {

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
    public void addProjectTest () {
        User user = new User();
        user.setEmail(ReadProperties.getUsername());
        user.setPassword(ReadProperties.getPassword());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.getAddProjectButton().click();
        AddProjectPage addProjectPage = new AddProjectPage(driver);
        setupProjects();
        addProjectPage.addProject(addProject);
        AdministrationProjectsPage administrationProjectsPage = new AdministrationProjectsPage(driver);

        Assert.assertEquals(administrationProjectsPage.getOpenProject(addProject).getText(), addProject.getName());
        Assert.assertTrue(administrationProjectsPage.getMessageSuccess().isDisplayed());
    }

    @Test (dependsOnMethods = "addProjectTest")
    public void deleteProjectTest () {
        AdministrationProjectsPage administrationProjectsPage = new AdministrationProjectsPage(driver);
        administrationProjectsPage.deleteProject(addProject);


        Assert.assertEquals(administrationProjectsPage.getMessageSuccess().getText(), "Successfully deleted the project.");
    }
}


