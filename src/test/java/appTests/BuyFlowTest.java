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
    private BuyerPage buyerPage;
    private PaymentPage paymentPage;
    private ConfirmationPage confirmationPage;
    private CheckoutPage checkoutPage;


//    Setup:

    @Before
    public void setUp() throws Exception {
        super.setUp();
        searchPage = new SearchPage(driver, BaseTest.DEFAULT_TIMEOUT);
        productPage = new ProductPage(driver, BaseTest.DEFAULT_TIMEOUT);
        cartPage = new CartPage(driver, BaseTest.DEFAULT_TIMEOUT);
        buyerPage = new BuyerPage(driver, BaseTest.DEFAULT_TIMEOUT);
        paymentPage = new PaymentPage(driver, BaseTest.DEFAULT_TIMEOUT);
        confirmationPage = new ConfirmationPage(driver, BaseTest.DEFAULT_TIMEOUT);
        checkoutPage = new CheckoutPage(driver, BaseTest.DEFAULT_TIMEOUT);
    }


//    Tests:

    @Test
    public void buyFlowTest() {
        String searchInput = ConfigReader.get("search.input");
        String productTitle = ConfigReader.get("product.title");
        String buyerName = ConfigReader.get("buyer.name");
        String buyerEmail = ConfigReader.get("buyer.email");
        String buyerCountry = ConfigReader.get("buyer.country");
        String buyerPhone = ConfigReader.get("buyer.phone");
        String buyerStreet = ConfigReader.get("buyer.street");
        String buyerStreetNumber = ConfigReader.get("buyer.streetNumber");
        String buyerCity = ConfigReader.get("buyer.city");

        searchPage.search(searchInput);
        searchPage.selectProduct(productTitle);

        productPage.addToCart();
        productPage.clickOnCartButton();

        cartPage.clickOnNextButton();

        buyerPage.buyerDataFillIn(buyerName, buyerEmail, buyerCountry, buyerPhone, buyerStreet, buyerStreetNumber, buyerCity);
        buyerPage.clickOnNextButton();

        paymentPage.clickOnCreditCard();
        paymentPage.clickOnNextButton();

        confirmationPage.acceptTerms();
        confirmationPage.acceptCosts();
        confirmationPage.clickOnPaymentButton();

        Assert.assertTrue(checkoutPage.isOnWSPayPage());
    }
}