package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class HomePage extends BasePage {

    //    Locators:

    private By searchInputField = By.id("pretraga_rec");
    private By loginButton = By.xpath("//*[normalize-space(.)=\"Prijava\"]");
    public By loginProof = By.xpath("//*[normalize-space(.)=\"Odjava\"]");


//    Constructor:

    public HomePage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Actions:

    public void clickOnLoginButton() {
        click(loginButton);
    }

    public void search(String inputText) {
        type(searchInputField, inputText);
        find(searchInputField).submit();
    }

    public String validLoginProofCheck() {
        return getText(loginProof);
    }

    public String invalidLoginProofCheck() {
        return getText(loginButton);
    }

}
