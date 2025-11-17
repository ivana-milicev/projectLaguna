package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class BuyerPage extends BasePage {

//    Locators:

    private By nameAndSurnameTextLocator = By.xpath("//*[text()=\"Ime i prezime\"]");
    private By nameInputFieldLocator = By.id("ime-k");
    private By getEmailInputFieldLocator = By.id("email-k");
    private By choseCountryButtonLocator = By.id("id_drzave-selectized");
    private By telephoneInputFieldLocator = By.id("telefon-k");
    private By streetInputFieldLocator = By.id("ulica-pomoc");
    private By streetNumberInputFieldLocator = By.id("broj");
    private By zipCodeInputFieldLocator = By.id("mesto-k-select-selectized");
    private By nextButtonLocator = By.xpath("//*[text()=\"Dalje\"]");


//    Constructor:

    public BuyerPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Actions:

    public void buyerDataFillIn(String name, String email, String country, String phone, String street, String streetNumber, String zipcode) {
        type(nameInputFieldLocator, "Bugs Bunny");
        type(getEmailInputFieldLocator, "email@example.com");
        type(choseCountryButtonLocator, "Srbija");
        driver.findElement(choseCountryButtonLocator).submit();
        type(telephoneInputFieldLocator, "641234567");
        type(streetInputFieldLocator, "My Street");
        type(streetNumberInputFieldLocator, "10");
        type(zipCodeInputFieldLocator, "21000");
        driver.findElement(zipCodeInputFieldLocator).submit();
        click(nextButtonLocator);

    }

}