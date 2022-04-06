package tests;

import baseEntities.BaseTest;
import core.ReadProperties;
import enums.ProjectType;
import models.Project;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.Randomization;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class FileUploaderTest extends BaseTest {
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
    public void fileUploaderTest () throws InterruptedException, AWTException {
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
        AdministrationProjectsPage administrationProjectsPage = new AdministrationProjectsPage(driver);
        administrationProjectsPage.getDashboardButton().click();

        //driver.findElement(By.id("navigation-dashboard")).click();
        dashboardPage.getNameProjectButton(addProject);
        //Thread.sleep(4000);
        OverviewProjectPage overviewProjectPage = new OverviewProjectPage(driver);
        overviewProjectPage.getAddTestCasesButton().click();

        //driver.findElement(By.id("sidebar-cases-add")).click();
        AddTestCasePage addTestCasePage = new AddTestCasePage(driver);
        addTestCasePage.getFileUploadButton().click();
        addTestCasePage.getChoseFileUploadButton().click();
        Thread.sleep(5000);

        Robot rb = new Robot();

        // copying File path to Clipboard
        StringSelection str = new StringSelection("C:\\Users\\Lenovo\\IdeaProjects\\diploma\\src\\test\\resources\\data\\oboi_pogonia.jpg");
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

        addTestCasePage.getFileUploadAttach().isDisplayed();
        addTestCasePage.getAttachButton().click();
        //Thread.sleep(10000);
        Assert.assertTrue(addTestCasePage.getFileUpload().isDisplayed());

        //Thread.sleep(10000);
        }
}


//        Assert.assertTrue(header.isDisplayed());
//        //Assert.assertTrue(header != null);
//        Assert.assertEquals(fileName.getText(), "oboi-pogonia-gerb-wallpapers-pagonia-belarus-emblem-belarus.jpg");
//
//    }

