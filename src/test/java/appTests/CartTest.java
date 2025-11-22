package appTests;

import org.junit.Assert;
import org.junit.Test;
import pages.CartPage;
import pages.ProductPage;
import pages.SearchPage;
import util.ConfigReader;

import java.time.Duration;

public class CartTest extends BaseTest {

//    Objects:

    SearchPage searchPage = new SearchPage(driver, Duration.ofSeconds(20));
    ProductPage productPage = new ProductPage(driver, Duration.ofSeconds(20));
    CartPage cartPage = new CartPage(driver, Duration.ofSeconds(20));


//    Tests:


    @Test
    public void isProductInCartTest() {
        searchPage.search(ConfigReader.get("search.input"));
        searchPage.selectProduct(ConfigReader.get("product.title"));

        productPage.addToCart();
        productPage.clickOnCartButton();

        cartPage.isProductInCart(ConfigReader.get("product.title"));

        Assert.assertTrue("The selected product should be in cart", cartPage.isProductInCart(ConfigReader.get("product.title")));
    }

    @Test
    public void removeFromCartTest() {
        searchPage.search(ConfigReader.get("search.input"));
        searchPage.selectProduct(ConfigReader.get("product.title"));

        productPage.addToCart();
        productPage.clickOnCartButton();

        cartPage.removeFromCart();
        cartPage.clickOkToRemove();

        Assert.assertTrue("Product was NOT removed from the cart!", cartPage.isProductRemoved(ConfigReader.get("product.title")));
    }
}
