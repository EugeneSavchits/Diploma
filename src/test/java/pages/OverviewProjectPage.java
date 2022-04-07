package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OverviewProjectPage extends BasePage {

    private static final By PAGE_OPENED_IDENTIFIER = By.id("navigation-overview-addrunssuite");



    protected By addTestCasesButtonSelector = By.id("sidebar-cases-add");


    public OverviewProjectPage(WebDriver driver) {
        super(driver);
    }
    public OverviewProjectPage(WebDriver driver, boolean openPageByUrl) {
        super(driver, openPageByUrl);
    }

    @Override
    protected void openPage() {}


    @Override
    protected boolean isPageOpened() {
        return waits.waitForVisibility(PAGE_OPENED_IDENTIFIER).isDisplayed();
    }

    public WebElement getAddTestCasesButton() {
        return driver.findElement(addTestCasesButtonSelector);
    }


    }
