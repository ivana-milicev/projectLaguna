package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class CartPage extends BasePage {

//    Locators:

    private By productInCart(String product) {
        return By.xpath(".//a[@class='naslov' and contains(text(),'" + product + "')]");
    }
    private By nextButton = By.xpath("//*[text()=\"Dalje\"]");
    private By removeProductButton = By.xpath("//*[text()=\"Brisanje\"]");
    private By okButton = By.xpath("//*[text()=\"OK\"]");


//    Constructor:

    public CartPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Methods:

    public boolean isProductInCart(String product) {
        waitForVisible(productInCart(product));
        return isPresent(productInCart(product));
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

    public void clickOkToRemove(String product) {
        closeGdprIfVisible();
        click(okButton);

        // Wait for the removal to process
        sleep(1000);

        // Wait for the product to be removed with retry logic
        int maxAttempts = 10;
        for (int i = 0; i < maxAttempts; i++) {
            if (!isPresent(productInCart(product))) {
                return; // Product successfully removed
            }
            sleep(500); // Wait 500ms before checking again
        }
    }

    public boolean isProductRemoved(String product) {
        // Give it extra time to ensure removal is complete
        sleep(1000);

        // Check multiple times with retries
        int maxAttempts = 5;
        for (int i = 0; i < maxAttempts; i++) {
            if (!isPresent(productInCart(product))) {
                return true; // Product is removed
            }
            sleep(500); // Wait before next check
        }

        // Final check
        return !isPresent(productInCart(product));
    }

}