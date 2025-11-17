package appTests;

import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class LoginTest extends BaseTest {

//    Objects:

    HomePage homePage = new HomePage(driver, Duration.ofSeconds(20));
    LoginPage loginPage = new LoginPage(driver, Duration.ofSeconds(20));


//    Tests:


    @Test
    public void validLoginTest() {
        homePage.clickOnLoginButton();

        String email
        loginPage.login("ivanans90@hotmail.com", "Y4qvB.yVLLDrC7m");

        String validLoginProof = homePage.validLoginProofCheck();

        Assert.assertTrue("Valid login proof should contain text 'Odjava'", validLoginProof.contains("Odjava"));
    }

    @Test
    public void invalidLoginTest() {
        homePage.clickOnLoginButton();
        loginPage.login("ivanans90@hotmail.co", "Y4qvB.yVLLDrC7m");

        String invalidLoginProof = homePage.invalidLoginProofCheck();

        Assert.assertTrue("Valid login proof should contain text 'Prijava'", invalidLoginProof.contains("Prijava"));
    }

    @Test
    public void emptyFieldsLoginTest() {
        homePage.clickOnLoginButton();
        loginPage.login("", "");

        String invalidLoginProof = homePage.invalidLoginProofCheck();

        Assert.assertTrue("Valid login proof should contain text 'Prijava'", invalidLoginProof.contains("Prijava"));
    }
}
