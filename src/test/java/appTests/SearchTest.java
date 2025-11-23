package appTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.SearchPage;
import util.ConfigReader;


public class SearchTest extends BaseTest {

//    Objects:

    private SearchPage searchPage;

    private static final String SEARCH_INPUT = ConfigReader.get("search.input");

//    Setup:

    @Before
    public void setUp() throws Exception {
        super.setUp();
        searchPage = new SearchPage(driver, BaseTest.DEFAULT_TIMEOUT);
    }


//    Tests:

    @Test
    public void searchTest() {
        searchPage.search(SEARCH_INPUT);
        Assert.assertTrue("Search results headline should be visible!", searchPage.isSearchOk());
    }
}