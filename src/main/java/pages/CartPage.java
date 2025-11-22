package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class CartPage extends BasePage {

//    Locators:

    private By productTitle(String product) {
        return By.xpath("//*[@class=\"naslov\" and text()='" + product + "']");
    }
    private By nextButton = By.xpath("//*[text()=\"Dalje\"]");
    private By removeProductButton = By.xpath("//*[text()=\"Brisanje\"]");
    private By okButton = By.xpath("//*[text()=\"OK\"]");
    private By emptyCartMessage = By.cssSelector(".cart-empty, .empty-msg");


//    Constructor:

    public CartPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Methods:

    public boolean isProductInCart(String product) {
        return isDisplayed(productTitle(product));
    }

    public void clickOnNextButton() {
        closeGdprIfVisible();
        WebElement next = find(nextButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", next);
        click(nextButton);
    }

    public void removeFromCart() {
        closeGdprIfVisible();
        WebElement remove = find(removeProductButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", remove);
        click(removeProductButton);
    }

    public void clickOkToRemove() {
        click(okButton);
    }

    public boolean isProductRemoved(String product) {
        waitForInvisible(productTitle(product));

        boolean productStillExists = isPresent(productTitle(product));
        boolean emptyCartVisible = isDisplayed(emptyCartMessage);

        return !productStillExists;
    }
}