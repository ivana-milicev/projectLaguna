package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class CartPage extends BasePage {

//    Locators:

//    private By cartContentButton = By.xpath("//*[text()=\"1. Sadržaj korpe\"]");
    private By productInCart = By.xpath("//*[@class=\"naslov\" and text()=\"Na Drini ćuprija\"]");
    private By removeItemButton = By.cssSelector(".uklanjanje");
    private By quantityDropdown = By.cssSelector("select[name='kolicina']");
    private By nextButton = By.xpath("//*[text()=\"Dalje\"]");


//    Constructor:

    public CartPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Methods:

    public boolean isProductInCart(String productName) {
        return isDisplayed(productInCart);
    }

    public void clickOnNextButton() {
        closeGdprIfVisible();
        WebElement next = find(nextButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", next);
        click(nextButton);
    }

    public void removeItem() {
        closeGdprIfVisible();
        click(removeItemButton);
    }

//    // Example additional method (optional)
//    public void changeQuantity(String value) {
//        closeGdprIfVisible();
//        selectFromDropdown(quantityDropdown, value);
//    }

}