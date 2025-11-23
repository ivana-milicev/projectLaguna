package appTests;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.BasePage;
import util.ConfigReader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest {

    protected static WebDriver driver;
    protected static BasePage basePage;

    protected static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(20);


    @Rule
    public TestName testName = new TestName();

    private boolean testFailed = false;

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            testFailed = true;
        }

        @Override
        protected void succeeded(Description description) {
            testFailed = false;
        }
    };


    @BeforeClass
    public static void beforeClass() throws Exception {
        String browser = ConfigReader.get("browser");

        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "chrome":
            default:
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(DEFAULT_TIMEOUT);
        driver.manage().timeouts().scriptTimeout(DEFAULT_TIMEOUT);
        basePage = new BasePage(driver, DEFAULT_TIMEOUT);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        driver.quit();
    }

    @Before
    public void setUp() throws Exception {
        basePage.navigateTo(ConfigReader.get("base.url"));
    }

    @After
    public void tearDown() throws Exception {
        if (testFailed) {
            takeScreenshot();
        }
        driver.manage().deleteAllCookies();
    }


    private void takeScreenshot() {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String currentTestName = testName.getMethodName();
            String fileName = currentTestName + "_" + timestamp + ".png";

            String screenshotFolder = "screenshots/";
            File folder = new File(screenshotFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File destination = new File(screenshotFolder + fileName);
            FileUtils.copyFile(source, destination);

            System.out.println("Screenshot saved: " + destination.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}