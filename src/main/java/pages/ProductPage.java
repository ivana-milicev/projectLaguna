package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ProductPage extends BasePage {

//    Locators:

    private By productPageHeadline = By.xpath("//*[@id=\"sadrzaj\"]//*[@class=\"row hidden-sm hidden-xs podaci\"]//*[@class=\"naslov\"]");
    private By addToCartButton = By.id("dugme-korpa");
    private By cartButton = By.id("korpa_broj");
    private By cartBadgeNumber = By.cssSelector("#korpa_broj");


//    Constructor:

    public ProductPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Methods:

    public boolean isProductSelected(String productName) {
        return isDisplayed(productPageHeadline);
    }

    public void addToCart() {
        click(addToCartButton);
        sleep(1000);
    }

    public void clickOnCartButton() {
        click(cartButton);
    }

    public int getCartCount() {
        try {
            String countText = getText(cartBadgeNumber).trim();

            if (countText.contains("(") && countText.contains(")")) {
                String numberPart = countText.substring(countText.indexOf("(") + 1, countText.indexOf(")"));
                return Integer.parseInt(numberPart);
            }

            if (!countText.isEmpty() && countText.matches("\\d+")) {
                return Integer.parseInt(countText);
            }

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean wasItemAddedToCart(int expectedCount) {
        int actualCount = getCartCount();
        return actualCount == expectedCount;
    }

}