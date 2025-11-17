package appTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.BasePage;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected BasePage basePage;


//    Annotations:

    @BeforeClass
    public static void beforeClass() throws Exception {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        //        max time to wait for a page load
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
        //        max time for asynchronous JS run
        basePage = new BasePage(driver, Duration.ofSeconds(10));
    }

    @AfterClass
    public static void afterClass() throws Exception {
        driver.quit();
    }

    @Before
    public void setUp() throws Exception {
        basePage.navigateTo("https://laguna.rs/");
    }

    @After
    public void tearDown() throws Exception {
        driver.manage().deleteAllCookies();
    }
}