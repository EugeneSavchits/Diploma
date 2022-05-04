package pages;

import baseEntities.BasePage;
import enums.ProjectType;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdministrationProjectsPage extends BasePage {

    private static String ENDPOINT = "/admin/projects/overview";
    private static final By PAGE_OPENED_IDENTIFIER = By.xpath("//*[@class = 'content-header-title page_title display-inline-block']");

    private static String openProjectSelector = "//*[. = 'replace']";

    private static String typeRadioButtonSelector = "//*[@name = 'suite_mode' and @value='replace']";

    private static String containsNameProject = "//*[contains(text(),'replace')]";

    private static String nameProject = "//*[. = 'replace']";

    protected By nameProjectSelector = By.id("name");
    protected By announcementSelector = By.id("announcement");
    protected By isShowAnnouncementSelector = By.id("show_announcement");
    protected By isCompletedSelector = By.id("is_completed");
    protected By saveProjectButton = By.id("accept");
    protected By dialogWindow = By.id("deleteDialog");
    protected By dashboardButton = By.id("navigation-dashboard");
    protected By messageSuccess = By.xpath("//*[@class = 'message message-success']");

    public AdministrationProjectsPage(WebDriver driver) {
        super(driver);
    }

    public AdministrationProjectsPage(WebDriver driver, boolean openPageByUrl) {
        super(driver, openPageByUrl);
    }

    @Override
    protected void openPage() {
        driver.get(BASE_URL+ENDPOINT);
    }

    @Override
    protected boolean isPageOpened() {
        return waits.waitForVisibility(PAGE_OPENED_IDENTIFIER).isDisplayed();
    }

    public WebElement getOpenProject (Project project) {
        return driver.findElement(By.xpath(openProjectSelector.replace("replace", project.getName())));
    }

    public void setType (ProjectType type) {
        driver.findElement(By.xpath(typeRadioButtonSelector.replace("replace", String.valueOf(type)))).click();
    }
    public WebElement getNameProjectField() {
        return driver.findElement(nameProjectSelector);
    }

    public WebElement getAnnouncementSelectorField() {
        return driver.findElement(announcementSelector);
    }

    public WebElement getIsShowAnnouncementField () {
        return driver.findElement(isShowAnnouncementSelector);
    }

    public WebElement getIsCompletedField () {
        return driver.findElement(isCompletedSelector);
    }

    public WebElement getSaveProjectButton () {
        return driver.findElement(saveProjectButton);
    }

    public WebElement getDialogWindow() {return driver.findElement(dialogWindow);}

    public WebElement getDashboardButton () {return driver.findElement(dashboardButton);}

    public WebElement getMessageSuccess() {return driver.findElement(messageSuccess);}

    public int lengthNameProject (Project project) {
        WebElement result = driver.findElement(By.xpath(containsNameProject.replace("replace", project.getName().substring(0,100))));
        return result.getText().length();
    }

    public void deleteProject (Project addProject) {
        driver.findElement(By.xpath("//*[. = '"+addProject.getName()+"']/following::td[2]")).click();
        driver.findElement(By.xpath("//strong [. = 'Yes, delete this project (cannot be undone)']")).click();
        driver.findElement(By.xpath("//div[@id='deleteDialog']//a[@class = 'button button-ok button-left button-positive dialog-action-default']")).click();
    }

    public void dialogWindowDisplay (Project addProject) {
        driver.findElement(By.xpath("//*[. = '"+addProject.getName()+"']/following::td[2]")).click();
    }
}
