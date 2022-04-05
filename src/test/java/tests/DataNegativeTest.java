package tests;

import baseEntities.BaseTest;
import core.ReadProperties;
import enums.ProjectType;
import models.Project;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.Randomization;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class DataNegativeTest extends BaseTest {
    Project addProject;

    private void setupProjects() {
        addProject = Project.builder()
                .name(Randomization.getRandomString(10))
                .typeOfProject(ProjectType.SINGLE)
                .announcement(Randomization.getRandomString(25))
                .isShowAnnouncement(true)
                .build();
    }

    @Test
    public void dataNegativeTest() throws InterruptedException, AWTException {
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
        administrationProjectsPage.getDashboardButton().click();


        dashboardPage.getNameProjectButton(addProject);

        OverviewProjectPage overviewProjectPage = new OverviewProjectPage(driver);
        overviewProjectPage.getAddTestCasesButton().click();


        AddTestCasePage addTestCasePage = new AddTestCasePage(driver);
        addTestCasePage.getFileUploadButton().click();
        addTestCasePage.getChoseFileUploadButton().click();
        Thread.sleep(6000);

        Robot rb = new Robot();

        // copying File path to Clipboard
        StringSelection str = new StringSelection("C:\\Users\\Lenovo\\Downloads\\Lesson_24 CICD.mp4");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        // press Contol+V for pasting
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        // release Contol+V for pasting
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        // for pressing and releasing Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);


        //Thread.sleep(10000);

        Assert.assertTrue(addTestCasePage.getLoadingErrorMessage().isDisplayed());
        Assert.assertEquals(addTestCasePage.getLoadingErrorMessage().getText(), "Loading error");

        //Thread.sleep(10000);
    }
}


//        Assert.assertTrue(header.isDisplayed());
//        //Assert.assertTrue(header != null);
//        Assert.assertEquals(fileName.getText(), "oboi-pogonia-gerb-wallpapers-pagonia-belarus-emblem-belarus.jpg");
//
//    }

