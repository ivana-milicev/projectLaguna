package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class ConfirmationPage extends BasePage {

//    Locators:

    private By termsCheckbox = By.id("potvrda");
    private By costsCheckbox = By.id("potvrda2");
    private By paymentButton = By.xpath("//*[text()=\"Plaćanje\"]");

//    Constructor:

    public ConfirmationPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Methods:

    public void acceptTerms() {
        closeGdprIfVisible();
        WebElement terms = find(termsCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", terms);
        jsClick(termsCheckbox);
    }

    public void acceptCosts() {
        closeGdprIfVisible();
        WebElement costs = find(costsCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", costs);
        jsClick(costsCheckbox);
    }

    public void clickOnPaymentButton() {
        closeGdprIfVisible();
        WebElement pay = find(paymentButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pay);
        click(paymentButton);
    }

}
