package appTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import util.ConfigReader;

import java.time.Duration;

public class LoginTest extends BaseTest {

//    Objects:

    private LoginPage loginPage;

//    Setup:

    @Before
    public void setUp() throws Exception {
        super.setUp();
        loginPage = new LoginPage(driver, BaseTest.DEFAULT_TIMEOUT);
    }


//    Tests:

    @Test
    public void validLoginTest() {
        String validEmail = ConfigReader.get("valid.email");
        String validPassword = ConfigReader.get("valid.password");

        loginPage.login(validEmail, validPassword);
        Assert.assertTrue("Valid login proof should contain text 'Odjava'", loginPage.validLoginProofCheck());
    }

    @Test
    public void invalidLoginTest() {
        String invalidEmail = ConfigReader.get("invalid.email");
        String invalidPassword = ConfigReader.get("invalid.password");

        loginPage.login(invalidEmail, invalidPassword);
        Assert.assertTrue("Invalid login proof should contain text 'Prijava'", loginPage.invalidLoginProofCheck());
    }

    @Test
    public void emptyFieldsLoginTest() {
        loginPage.login("", "");
        Assert.assertTrue("Failed login proof should contain text 'Prijava' when fields are empty", loginPage.invalidLoginProofCheck());
    }
}