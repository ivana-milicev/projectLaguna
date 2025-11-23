package appTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.*;
import util.ConfigReader;

import java.time.Duration;

public class BuyFlowTest extends BaseTest {

//    Objects:

    private SearchPage searchPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private LoginPage loginPage;
    private BuyerPage buyerPage;
    private PaymentPage paymentPage;
    private ConfirmationPage confirmationPage;
    private CheckoutPage checkoutPage;

    private static final String VALID_EMAIL = ConfigReader.get("valid.email");
    private static final String VALID_PASSWORD = ConfigReader.get("valid.password");
    private static final String SEARCH_INPUT = ConfigReader.get("search.input");
    private static final String PRODUCT_TITLE = ConfigReader.get("product.title");
    private static final String BUYER_NAME = ConfigReader.get("buyer.name");
    private static final String BUYER_EMAIL = ConfigReader.get("buyer.email");
    private static final String BUYER_COUNTRY = ConfigReader.get("buyer.country");
    private static final String BUYER_PHONE = ConfigReader.get("buyer.phone");
    private static final String BUYER_STREET = ConfigReader.get("buyer.street");
    private static final String BUYER_STREET_NUMBER = ConfigReader.get("buyer.streetNumber");
    private static final String BUYER_CITY = ConfigReader.get("buyer.city");


//    Setup:

    @Before
    public void setUp() throws Exception {
        super.setUp();
        searchPage = new SearchPage(driver, BaseTest.DEFAULT_TIMEOUT);
        productPage = new ProductPage(driver, BaseTest.DEFAULT_TIMEOUT);
        cartPage = new CartPage(driver, BaseTest.DEFAULT_TIMEOUT);
        loginPage = new LoginPage(driver, BaseTest.DEFAULT_TIMEOUT);
        buyerPage = new BuyerPage(driver, BaseTest.DEFAULT_TIMEOUT);
        paymentPage = new PaymentPage(driver, BaseTest.DEFAULT_TIMEOUT);
        confirmationPage = new ConfirmationPage(driver, BaseTest.DEFAULT_TIMEOUT);
        checkoutPage = new CheckoutPage(driver, BaseTest.DEFAULT_TIMEOUT);
    }


//    Tests:

    @Test
    public void buyFlowTest() {
        loginPage.login(VALID_EMAIL, VALID_PASSWORD);

        searchPage.search(SEARCH_INPUT);
        searchPage.selectProduct(PRODUCT_TITLE);

        productPage.addToCart();
        productPage.clickOnCartButton();

        cartPage.clickOnNextButton();

        buyerPage.buyerDataFillIn(BUYER_NAME, BUYER_EMAIL, BUYER_COUNTRY, BUYER_PHONE, BUYER_STREET, BUYER_STREET_NUMBER, BUYER_CITY);
        buyerPage.clickOnNextButton();

        paymentPage.clickOnCreditCard();
        paymentPage.clickOnNextButton();

        confirmationPage.acceptTerms();
        confirmationPage.acceptCosts();
        confirmationPage.clickOnPaymentButton();

        Assert.assertTrue(checkoutPage.isOnWSPayPage());
    }
}
