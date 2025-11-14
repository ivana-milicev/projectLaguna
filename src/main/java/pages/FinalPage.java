package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class FinalPage extends BasePage {

//    Locators:

    private By finalPageHeadlineLocator = By.xpath("//*[text()\"Lični podaci korisnika\"]");


//    Constructor:

    public FinalPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

}
