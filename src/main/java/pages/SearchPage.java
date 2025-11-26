package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public class SearchPage extends BasePage {

//    Locators:

    private By searchInputField = By.id("pretraga_rec");
    private By resultsPageHeadline = By.xpath("//*[@id='spisak-knjiga-knjige']//*[@class='naslov-sredina']");
    private By productTitle(String product) {
        return By.xpath("//*[@class='naslov' and contains(text(),'" + product + "')]");
    }


//    Constructor:

    public SearchPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Methods:

    public void search(String inputText) {
        type(searchInputField, inputText);
        find(searchInputField).submit();
    }

    public boolean isSearchOk() {
        return isDisplayed(resultsPageHeadline);
    }

    public void selectProduct(String product) {
        waitForVisible(productTitle(product));
        scrollToElement(productTitle(product));
        sleep(300);
        click(productTitle(product));
    }
}
