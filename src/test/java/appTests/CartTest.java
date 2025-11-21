//package appTests;
//
//import org.junit.Assert;
//import org.junit.Test;
//import pages.CartPage;
//import pages.ProductPage;
//import pages.SearchPage;
//import util.ConfigReader;
//
//import java.time.Duration;
//
//public class CartTest extends BaseTest {
//
////    Objects:
//
//        SearchPage searchPage = new SearchPage(driver, Duration.ofSeconds(20));
//        ProductPage productPage = new ProductPage(driver, Duration.ofSeconds(20));
//        CartPage cartPage = new CartPage(driver, Duration.ofSeconds(20));
//
//
////    Tests:
//
//
//    @Test
//    public void cartTest() {
//        searchPage.search(ConfigReader.get("search.input"));
//        searchPage.selectProduct();
//
//        productPage.addToCart();
//        productPage.clickOnCartButton();
//
//        Assert.assertTrue("Product should be in cart", cartPage.isProductInCart(ConfigReader.get("product.name")));
//        cartPage.clickOnNextButton();
//    }
//}
