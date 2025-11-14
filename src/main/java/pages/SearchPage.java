package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class SearchPage extends BasePage {

//    Locators:

    private By resultsPageHeadlineLocator = By.xpath("//*[@id=\"spisak-knjiga-knjige\"]//*[@class=\"naslov-sredina\"]");
    private By productLocator = By.xpath("//*[@class=\"naslov\" and @title=\"na drini ćuprija laguna knjige\"]");


//    Constructor:

    public SearchPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Actions:

    public void addToCartFunctionality() {
        click(productLocator);
    }


}
