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

    private static final String VALID_EMAIL = ConfigReader.get("valid.email");
    private static final String VALID_PASSWORD = ConfigReader.get("valid.password");
    private static final String INVALID_EMAIL = ConfigReader.get("invalid.email");
    private static final String INVALID_PASSWORD = ConfigReader.get("invalid.password");


//    Setup:

    @Before
    public void setUp() throws Exception {
        super.setUp();
        loginPage = new LoginPage(driver, BaseTest.DEFAULT_TIMEOUT);
        }


//    Tests:

    @Test
    public void validLoginTest() {
        loginPage.login(VALID_EMAIL, VALID_PASSWORD);
        Assert.assertTrue("Valid login proof should contain text 'Odjava'", loginPage.validLoginProofCheck());
    }

    @Test
    public void invalidLoginTest() {
        loginPage.login(INVALID_EMAIL, INVALID_PASSWORD);
        Assert.assertTrue("Invalid login proof should contain text 'Prijava'", loginPage.invalidLoginProofCheck());
    }

    @Test
    public void emptyFieldsLoginTest() {
        loginPage.login("", "");
        Assert.assertTrue("Failed login proof should contain text 'Prijava' when fields are empty", loginPage.invalidLoginProofCheck());
    }
}
