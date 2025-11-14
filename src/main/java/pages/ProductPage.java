package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ProductPage extends BasePage {

//    Locators:

    private By productPageHeadlineLocator = By.xpath("//*[@id=\"sadrzaj\"]//*[@class=\"row hidden-sm hidden-xs podaci\"]//*[@class=\"naslov\"]");
    private By addToCartButtonLocator = By.id("dugme-korpa");
    private By cartButtonLocator = By.id("korpa_broj");


//    Constructor:

    public ProductPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

//    Actions:

    public void addToCartFunctionality() {
        click(addToCartButtonLocator);
        click(cartButtonLocator);
    }

}
