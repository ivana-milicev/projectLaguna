package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LoginPage extends BasePage {

//    Locators:

    private By emailInputFieldLocator = By.id("broj-f");
    private By passwordInputFieldLocator = By.id("lozinka-f");
    private By submitInputFieldLocator = By.id("form-prijava-s");


//    Constructor:

    public LoginPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Actions:

    public void loginFunctionality(String emailInput, String passwordInput) {
        driver.findElement(emailInputFieldLocator).sendKeys(emailInput);
        driver.findElement(passwordInputFieldLocator).sendKeys(passwordInput);
        click(submitInputFieldLocator);
    }

}