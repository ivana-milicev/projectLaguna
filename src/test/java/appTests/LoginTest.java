package appTests;

import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;
import pages.LoginPage;
import util.ConfigReader;

import java.time.Duration;

public class LoginTest extends BaseTest {

//    Objects:

    HomePage homePage = new HomePage(driver, Duration.ofSeconds(20));
    LoginPage loginPage = new LoginPage(driver, Duration.ofSeconds(20));


//    Tests:

    @Test
    public void validLoginTest() {
        homePage.clickOnLoginButton();
        loginPage.login(ConfigReader.get("valid.email"), ConfigReader.get("valid.password"));

        String validLoginProof = homePage.validLoginProofCheck();

        Assert.assertTrue("Valid login proof should contain text 'Odjava'", validLoginProof.contains("Odjava"));
    }

    @Test
    public void invalidLoginTest() {
        homePage.clickOnLoginButton();
        loginPage.login(ConfigReader.get("invalid.email"), ConfigReader.get("invalid.password"));

        String invalidLoginProof = homePage.invalidLoginProofCheck();

        Assert.assertTrue("Invalid login proof should contain text 'Prijava'", invalidLoginProof.contains("Prijava"));
    }

    @Test
    public void emptyFieldsLoginTest() {
        homePage.clickOnLoginButton();
        loginPage.login("", "");

        String invalidLoginProof = homePage.invalidLoginProofCheck();

        Assert.assertTrue("Login proof should contain text 'Prijava' when fields are empty", invalidLoginProof.contains("Prijava"));
    }
}
