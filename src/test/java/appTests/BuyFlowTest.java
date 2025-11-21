package appTests;

import org.junit.Assert;
import org.junit.Test;
import pages.*;
import util.ConfigReader;

import java.time.Duration;

public class BuyFlowTest extends BaseTest {

//    Objects:

    SearchPage searchPage = new SearchPage(driver, Duration.ofSeconds(20));
    ProductPage productPage = new ProductPage(driver, Duration.ofSeconds(20));
    CartPage cartPage = new CartPage(driver, Duration.ofSeconds(20));
    LoginPage loginPage = new LoginPage(driver, Duration.ofSeconds(20));
    BuyerPage buyerPage = new BuyerPage(driver, Duration.ofSeconds(20));
    PaymentPage paymentPage = new PaymentPage(driver, Duration.ofSeconds(20));
    ConfirmationPage confirmationPage = new ConfirmationPage(driver, Duration.ofSeconds(20));
    CheckoutPage checkoutPage = new CheckoutPage(driver, Duration.ofSeconds(20));


//    Tests:


    @Test
    public void buyFlowTest() {
        loginPage.login(ConfigReader.get("valid.email"), ConfigReader.get("valid.password"));

        searchPage.search(ConfigReader.get("search.input"));
        searchPage.selectProduct();

        productPage.addToCart();
        productPage.clickOnCartButton();

        cartPage.clickOnNextButton();

        buyerPage.buyerDataFillIn(ConfigReader.get("buyer.name"), ConfigReader.get("buyer.email"), ConfigReader.get("buyer.country"), ConfigReader.get("buyer.phone"), ConfigReader.get("buyer.street"), ConfigReader.get("buyer.streetNumber"), ConfigReader.get("buyer.city"));
        buyerPage.clickOnNextButton();

        paymentPage.clickOnCreditCard();
        paymentPage.clickOnNextButton();

        confirmationPage.acceptTerms();
        confirmationPage.acceptCosts();
        confirmationPage.clickOnPaymentButton();

        Assert.assertTrue(checkoutPage.isOnWSPayPage());
    }
}
