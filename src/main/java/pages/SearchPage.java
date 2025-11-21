package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class SearchPage extends BasePage {

//    Locators:

//    private By loginButton = By.xpath("//*[normalize-space(.)=\"Prijava\"]");
    private By searchInputField = By.id("pretraga_rec");
    private By resultsPageHeadline = By.xpath("//*[@id=\"spisak-knjiga-knjige\"]//*[@class=\"naslov-sredina\"]");
    private By product = By.xpath("//*[@class=\"naslov\" and @title=\"na drini ćuprija laguna knjige\"]");


//    Constructor:

    public SearchPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Methods:

//    public void clickOnLoginButton() {
//        click(loginButton);
//    }

    public void search(String inputText) {
        type(searchInputField, inputText);
        find(searchInputField).submit();
    }

    public boolean isSearchOk(String searchInput) {
        return isDisplayed(resultsPageHeadline);
    }

    public void selectProduct() {
        click(product);
    }

}
