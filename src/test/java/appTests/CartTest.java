package appTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.CartPage;
import pages.ProductPage;
import pages.SearchPage;
import util.ConfigReader;

import java.time.Duration;

public class CartTest extends BaseTest {

//    Objects:

    private SearchPage searchPage;
    private ProductPage productPage;
    private CartPage cartPage;

//    Setup:

    @Before
    public void setUp() throws Exception {
        super.setUp();
        searchPage = new SearchPage(driver, BaseTest.DEFAULT_TIMEOUT);
        productPage = new ProductPage(driver, BaseTest.DEFAULT_TIMEOUT);
        cartPage = new CartPage(driver, BaseTest.DEFAULT_TIMEOUT);
    }


//    Tests:

    @Test
    public void isProductInCartTest() {
        String searchInput = ConfigReader.get("search.input");
        String productTitle = ConfigReader.get("product.title");

        searchPage.search(searchInput);
        searchPage.selectProduct(productTitle);

        int cartCountBefore = productPage.getCartCount();

        productPage.addToCart();

        Assert.assertTrue("Product was not added to cart - count didn't increase!", productPage.wasItemAddedToCart(cartCountBefore + 1));

        productPage.clickOnCartButton();

        Assert.assertTrue("The selected product should be in cart", cartPage.isProductInCart(productTitle));
    }

    @Test
    public void removeFromCartTest() {
        String searchInput = ConfigReader.get("search.input");
        String productTitle = ConfigReader.get("product.title");

        searchPage.search(searchInput);
        searchPage.selectProduct(productTitle);

        productPage.addToCart();
        productPage.clickOnCartButton();

        cartPage.removeFromCart();
        cartPage.clickOkToRemove(productTitle);

        Assert.assertTrue("Product was NOT removed from the cart!", cartPage.isProductRemoved(productTitle));
    }
}