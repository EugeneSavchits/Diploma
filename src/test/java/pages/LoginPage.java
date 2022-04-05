package pages;

import baseEntities.BasePage;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private static String ENDPOINT = "/auth/login";

    private static final By PAGE_OPENED_IDENTIFIER = By.id("button_primary");

    protected By emailSelector = By.id("name");
    protected By passwordSelector = By.id("password");
    protected By loginSelector = By.id("button_primary");
    protected By errorMessageSelector = By.xpath("//span[@class='error-on-top']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void openPage() {
        driver.get(BASE_URL+ENDPOINT);
    }

    @Override
    protected boolean isPageOpened() {
        return waits.waitForVisibility(PAGE_OPENED_IDENTIFIER).isDisplayed();
    }


    public WebElement getEmailField() {
        return driver.findElement(emailSelector);
    }

    public WebElement getPasswordField() {
        return driver.findElement(passwordSelector);
    }

    public WebElement getLoginButton() {
        return driver.findElement(loginSelector);
    }

    public WebElement getErrorMessage() {return  driver.findElement(errorMessageSelector);}

    public void login (User user) {
        getEmailField().sendKeys(user.getEmail());
        getPasswordField().sendKeys(user.getPassword());
        getLoginButton().click();
    }
}
