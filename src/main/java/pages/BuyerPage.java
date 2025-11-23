package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class BuyerPage extends BasePage {

//    Locators:

    private By nameInputField = By.id("ime-k");
    private By emailInputField = By.id("email-k");

    private By countryDropdown = By.id("id_drzave-selectized");
    private By countryOption(String country) {
        return By.xpath("//div[contains(@class, 'selectize-dropdown')]//div[text()='" + country + "']");
    }
    private By telephoneInputField = By.id("telefon-k");
    private By streetInputField = By.id("ulica-pomoc");
    private By streetNumberInputField = By.id("broj-k");
    private By cityDropdown = By.cssSelector("#mesto-k-select-selectized");
    private By cityDropdownOption(String city) {
        return By.xpath("//div[@class='option' and contains(text(),'" + city + "')]");
    }
    private By nextButton = By.id("nastaviDalje");


//    Constructor:

    public BuyerPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }


//    Methods:

    public void buyerDataFillIn(String name, String email, String country, String phone, String street, String streetNumber, String city) {
        type(nameInputField, name);
        type(emailInputField, email);
        click(countryDropdown);
        click(countryOption(country));
        type(telephoneInputField, phone);
        type(streetInputField, street);
        type(streetNumberInputField, streetNumber);
        click(cityDropdown);
        click(cityDropdownOption(city));
    }

    public void clickOnNextButton() {
        closeGdprIfVisible();
        waitForClickable(nextButton);
        WebElement next = find(nextButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", next);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", next);
    }

}