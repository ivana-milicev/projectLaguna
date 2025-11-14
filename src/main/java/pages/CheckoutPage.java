package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class CheckoutPage extends BasePage {

//    Locators:

    private By termsAgreementLoctor = By.xpath("//*[text()\"Slažem se sa \"]");
    private By costsAgreementLocator = By.xpath("//*[text()\"Saglasan sam da snosim sve prikazane troškove\"]");
    private By paymentButtonLocator = By.xpath("//*[text()=\"Plaćanje\"]");

//    Constructor:

    public CheckoutPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Actions:

    public void agreeToCheckout() {
        click(termsAgreementLoctor);
        click(costsAgreementLocator);
        click(paymentButtonLocator);
    }

}
