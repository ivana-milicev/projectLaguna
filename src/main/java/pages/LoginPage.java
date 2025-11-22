package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LoginPage extends BasePage {

//    Locators:

    private By loginButton = By.xpath("//*[normalize-space(.)=\"Prijava\"]");
    private By loginProof = By.xpath("//*[normalize-space(.)=\"Odjava\"]");
    private By emailInputField = By.id("broj-f");
    private By passwordInputField = By.id("lozinka-f");
    private By submitInputField = By.id("form-prijava-s");


//    Constructor:

    public LoginPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Methods:

    public void login(String email, String password) {
        click(loginButton);
        type(emailInputField, email);
        type(passwordInputField, password);
        click(submitInputField);
    }

    public boolean validLoginProofCheck() {
        return isDisplayed(loginProof);
    }

    public boolean invalidLoginProofCheck() {
        return isDisplayed(loginButton);
    }

}