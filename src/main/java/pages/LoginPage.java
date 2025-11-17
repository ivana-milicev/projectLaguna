package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LoginPage extends BasePage {

//    Locators:

    private By emailInputField = By.id("broj-f");
    private By passwordInputField = By.id("lozinka-f");
    private By submitInputField = By.id("form-prijava-s");


//    Constructor:

    public LoginPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Actions:

    public void login(String email, String password) {
        type(emailInputField, email);
        type(passwordInputField, password);
        click(submitInputField);
    }

    // Optional helpers (uncomment / adapt if your site shows error messages)
    // private final By loginError = By.cssSelector(".login-error");
    // public boolean isLoginErrorVisible() { return isDisplayed(loginError); }
    // public String getLoginErrorText() { return getText(loginError); }

}