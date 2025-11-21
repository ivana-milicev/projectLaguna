package appTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.SearchPage;
import util.ConfigReader;

import java.time.Duration;

public class SearchTest extends BaseTest {

//    Objects:

    SearchPage searchPage;

    @Before
    public void initPages() {
        SearchPage searchPage = new SearchPage(driver, Duration.ofSeconds(20));
    }


//    Tests:

    @Test
    public void searchTest() {
        searchPage.search(ConfigReader.get("search.input"));
        Assert.assertTrue("Headline should contain 'Pretraga: ' + 'search.input'", searchPage.isSearchOk(ConfigReader.get("search.input")));
    }

}
