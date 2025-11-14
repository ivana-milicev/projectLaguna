package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class HomePage extends BasePage {

    //    Locators:

    private By searchInputFieldLocator = By.id("pretraga_rec");
    private By loginButtonLocator = By.xpath("//*[text()=\"Prijava\"]");
    public By validLoginProofLocator = By.xpath("//*[text()=\"Odjava\"]");
    public By invalidLoginProofLocator = By.xpath("//*[text()=\"Prijava\"]");


//    Constructor:

    public HomePage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Actions:

    public void clickOnLoginButton() {
        click(loginButtonLocator);
    }

    public void searchFunctionality(String inputText) {
        driver.findElement(searchInputFieldLocator).sendKeys(inputText);
        driver.findElement(searchInputFieldLocator).submit();
    }

    public String validLoginProofCheck() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(validLoginProofLocator));
        return driver.findElement(validLoginProofLocator).getText();
    }

    public String invalidLoginProofCheck() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(invalidLoginProofLocator));
        return driver.findElement(invalidLoginProofLocator).getText();
    }

}
