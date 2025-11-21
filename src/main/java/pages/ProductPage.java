package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ProductPage extends BasePage {

//    Locators:

    protected By productPageHeadline = By.xpath("//*[@id=\"sadrzaj\"]//*[@class=\"row hidden-sm hidden-xs podaci\"]//*[@class=\"naslov\"]");
    private By addToCartButton = By.id("dugme-korpa");
    private By cartButton = By.id("korpa_broj");



//    Constructor:

    public ProductPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

//    Actions:

    public boolean isProductSelected(String productName) {
        return isDisplayed(productPageHeadline);
    }

    public void addToCart() {
        click(addToCartButton);
    }

    public void clickOnCartButton() {
        click(cartButton);
    }

}
