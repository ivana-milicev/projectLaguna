package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class CartPage extends BasePage {

//    Locators:

    private By cartContentButtonLocator = By.xpath("//*[text()=\"1. Sadržaj korpe\"]");
    private By productInCartLocator = By.xpath("//*[@class=\"naslov\" and text()=\"Na Drini ćuprija\"]");
    private By nextButtonLocator = By.xpath("//*[text()=\"Dalje\"]");


//    Constructor:

    public CartPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Actions:

    public void clickOnNextButton() {
        click(nextButtonLocator);
    }


}