package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddTestCasePage extends BasePage {


    private static final By PAGE_OPENED_IDENTIFIER = By.id("content-inner");

    protected By titleTestCaseSelector = By.id("title");
    protected By preconditionsSelector = By.id("custom_preconds_display");
    protected By addTestCaseButton = By.id("accept");
    protected By entityAttachmentListSelector = By.id("entityAttachmentListEmptyIcon");
    protected By choseFileUploadButton = By.id("libraryAttachmentsAddItem");
    protected By attachButton = By.id("attachmentNewSubmit");
    protected By fileUploadAttach = By.xpath("//div[@title='oboi_pogonia.jpg']");
    protected By fileUpload = By.xpath("//div[@title='oboi_pogonia.jpg\t(Click and hold to enter delete mode)']");
    protected By loadingErrorMessage = By.id("ui-dialog-title-messageDialog");


    public AddTestCasePage(WebDriver driver) {
        super(driver);
    }

    public AddTestCasePage(WebDriver driver, boolean openPageByUrl) {
        super(driver, openPageByUrl);
    }

    public WebElement getTitleTestCaseField() {
        return driver.findElement(titleTestCaseSelector);
    }

    public WebElement getPreconditionsField() {
        return driver.findElement(preconditionsSelector);
    }

    public WebElement getAddTestCaseButton() {
        return driver.findElement(addTestCaseButton);
    }

    public WebElement getFileUploadButton() {
        return driver.findElement(entityAttachmentListSelector);
    }

    public WebElement getChoseFileUploadButton() {
        waits.waitForVisibility(choseFileUploadButton).isDisplayed();
        return driver.findElement(choseFileUploadButton);
    }

    public WebElement getFileUploadAttach() throws InterruptedException {
        waits.waitForVisibility(fileUploadAttach);
        return driver.findElement(fileUploadAttach);
    }

    public WebElement getAttachButton() {
        waits.waitForClickable(attachButton);
        return driver.findElement(attachButton);
    }

    public WebElement getFileUpload() {
        waits.waitForVisibility(fileUpload);
        return driver.findElement(fileUpload);
    }

    public WebElement getLoadingErrorMessage() {
        waits.waitForVisibility(loadingErrorMessage);
        return driver.findElement(loadingErrorMessage);
    }

    @Override
    protected void openPage() {
    }

    @Override
    protected boolean isPageOpened() {
        return waits.waitForVisibility(PAGE_OPENED_IDENTIFIER).isDisplayed();
    }
}
