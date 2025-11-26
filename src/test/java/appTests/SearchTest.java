package appTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.SearchPage;
import util.ConfigReader;


public class SearchTest extends BaseTest {

//    Objects:

    private SearchPage searchPage;

//    Setup:

    @Before
    public void setUp() throws Exception {
        super.setUp();
        searchPage = new SearchPage(driver, BaseTest.DEFAULT_TIMEOUT);
    }


//    Tests:

    @Test
    public void searchTest() {
        String searchInput = ConfigReader.get("search.input");
        searchPage.search(searchInput);
        Assert.assertTrue("Search results headline should be visible!", searchPage.isSearchOk());
    }
}