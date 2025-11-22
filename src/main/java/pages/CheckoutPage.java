package pages;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class CheckoutPage extends BasePage {

//    Constructor:

    public CheckoutPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Methods:

    public boolean isOnWSPayPage() {
        return driver.getCurrentUrl().contains("wspay");
    }
}