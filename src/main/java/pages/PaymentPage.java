package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class PaymentPage extends BasePage {

//    Locators:

    private By paymentMethodLocator = By.xpath("//*[text()=\"Platnom karticom - dostava kurirskom službom\"]");
    private By nextButtonLocator = By.xpath("//*[text()=\"Dalje\"]");


//    Constructor:

    public PaymentPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Actions:

    public void clickOnCreditCard() {
        click(paymentMethodLocator);
        click(nextButtonLocator);
    }

}
