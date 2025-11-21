package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class PaymentPage extends BasePage {

//    Locators:

    private By paymentMethod = By.xpath("//*[contains(text(),'Izaberite način plaćanja')]");
    private By creditCard = By.xpath("//*[text()=\"Platnom karticom - dostava kurirskom službom\"]");
    private By nextButton = By.xpath("//*[text()=\"Dalje\"]");


//    Constructor:

    public PaymentPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Methods:

    public boolean paymentPresenceCheck() {
        return isDisplayed(paymentMethod);
    }

    public void clickOnCreditCard() {
        click(creditCard);
    }

    public void clickOnNextButton() {
        closeGdprIfVisible();
        WebElement next = find(nextButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", next);
        click(nextButton);
    }

}
