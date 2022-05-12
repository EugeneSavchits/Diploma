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
import java.io.File;

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
    public void fileUploaderTest() throws InterruptedException, AWTException {
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

        dashboardPage.getNameProjectButton(addProject);

        OverviewProjectPage overviewProjectPage = new OverviewProjectPage(driver);
        overviewProjectPage.getAddTestCasesButton().click();


        AddTestCasePage addTestCasePage = new AddTestCasePage(driver);
        addTestCasePage.getFileUploadButton().click();
        addTestCasePage.getChoseFileUploadButton().click();
        Thread.sleep(5000);

        Robot rb = new Robot();

        // copying File path to Clipboard
        File file = new File("src/test/resources/data/oboi_pogonia.jpg");
        String absolutePath = file.getAbsolutePath();
        StringSelection str = new StringSelection(absolutePath);
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

        Assert.assertTrue(addTestCasePage.getFileUpload().isDisplayed());
    }
}




