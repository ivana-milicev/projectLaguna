package appTests;

import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;
import util.ConfigReader;

import java.time.Duration;

public class LoginTest extends BaseTest {

//    Objects:

    LoginPage loginPage = new LoginPage(driver, Duration.ofSeconds(20));


//    Tests:

    @Test
    public void validLoginTest() {
        loginPage.login(ConfigReader.get("valid.email"), ConfigReader.get("valid.password"));
        Assert.assertTrue("Valid login proof should contain text 'Odjava'", loginPage.validLoginProofCheck());
    }

    @Test
    public void invalidLoginTest() {
        loginPage.login(ConfigReader.get("invalid.email"), ConfigReader.get("invalid.password"));
        Assert.assertTrue("Invalid login proof should contain text 'Prijava'", loginPage.invalidLoginProofCheck());
    }

    @Test
    public void emptyFieldsLoginTest() {
        loginPage.login("", "");
        Assert.assertTrue("Failed login proof should contain text 'Prijava' when fields are empty", loginPage.invalidLoginProofCheck());
    }
}
