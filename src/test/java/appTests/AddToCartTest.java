//package appTests;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import pages.CartPage;
//import pages.ProductPage;
//import pages.SearchPage;
//import util.ConfigReader;
//
//import java.time.Duration;
//
//public class AddToCartTest extends BaseTest {
//
////    Objects:
//
//     SearchPage searchPage = new SearchPage(driver, Duration.ofSeconds(20));
//     ProductPage productPage = new ProductPage(driver, Duration.ofSeconds(20));
//     CartPage cartPage = new CartPage(driver, Duration.ofSeconds(20));
//
//
////    Tests:
//
//    @Test
//    public void isProductSelectedTest() {
//        searchPage.search(ConfigReader.get("search.input"));
//        searchPage.selectProduct();
//
//        Assert.assertTrue("Headline should contain the name of selected product", productPage.isProductSelected(ConfigReader.get("product.name")));
//    }
//
//    @Test
//    public void addToCartTest() {
//        searchPage.search(ConfigReader.get("search.input"));
//        searchPage.selectProduct();
//
//        productPage.addToCart();
//        productPage.clickOnCartButton();
//
//        Assert.assertTrue("Result should contain the name of selected product 'Na Drini ćuprija'", cartPage.isProductInCart(ConfigReader.get("product.name")));
//    }
//}
