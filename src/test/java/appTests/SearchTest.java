package appTests;

import org.junit.Assert;
import org.junit.Test;
import pages.SearchPage;
import util.ConfigReader;

import java.time.Duration;

public class SearchTest extends BaseTest {

//    Objects:

    SearchPage searchPage = new SearchPage(driver, Duration.ofSeconds(20));


//    Tests:

    @Test
    public void searchTest() {
        searchPage.search(ConfigReader.get("search.input"));
        Assert.assertTrue("Search results headline should be visible!", searchPage.isSearchOk());
    }
}