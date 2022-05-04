package pages;

import baseEntities.BasePage;
import enums.ProjectType;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddProjectPage extends BasePage {

    private static String ENDPOINT = "admin/projects/add/1";
    private static final By PAGE_OPENED_IDENTIFIER = By.id("accept");
    private static String typeRadioButtonSelector = "//*[@name = 'suite_mode' and @value='replace']";

    protected By nameProjectSelector = By.id("name");
    protected By announcementSelector = By.id("announcement");
    protected By isShowAnnouncementSelector = By.id("show_announcement");
    protected By isCompletedSelector = By.id("is_completed");
    protected By addProjectButton = By.id("accept");

    public AddProjectPage(WebDriver driver) {
        super(driver);
    }

    public AddProjectPage(WebDriver driver, boolean openPageByUrl) {
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

    public WebElement getAddProjectButton () {
        return driver.findElement(addProjectButton);
    }

    public void setType (ProjectType type) {
        driver.findElement(By.xpath(typeRadioButtonSelector.replace("replace", String.valueOf(type)))).click();
    }

    public void addProject (Project project) {
    getNameProjectField().sendKeys(project.getName());
    getAnnouncementSelectorField().sendKeys(project.getAnnouncement());
    getIsShowAnnouncementField().click();
    setType(project.getTypeOfProject());
    getAddProjectButton().click();
    }

}
