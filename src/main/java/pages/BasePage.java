package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class BasePage {

    //    Variables:

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;


    //    Constructor:

    public BasePage (WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }


    //    Page/JS/AJAX readiness
    public void waitForPageToLoad() {
        wait.until(d -> Objects.equals(js.executeScript("return document.readyState"), "complete"));
    }

    public void waitForPageLoadComplete() {
        wait.until(webDriver -> Objects.equals(js.executeScript("return document.readyState"), "complete"));
    }



    //    Navigation
    public void navigateTo(String url) {
        driver.get(url);
        waitForPageToLoad();
    }



    //    Core finders
    public WebElement find(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }



    //    Element actions (with built-in waits)
    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void type(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(text);
    }

    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public boolean isDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }



    //    Waits (explicit)
    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean waitForInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public boolean waitForText(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public boolean waitForTitleContains(String text) {
        return wait.until(ExpectedConditions.titleContains(text));
    }

    public boolean waitForUrlContains(String part) {
        return wait.until(ExpectedConditions.urlContains(part));
    }



    //    waitForJQueryIdle is a helper method (sometimes found in Selenium test frameworks) that makes your test script pause until all active jQuery Ajax requests finish.
    public void waitForJQueryIdle() {
        try {
            wait.until(d -> Boolean.TRUE.equals(js.executeScript("return (window.jQuery && jQuery.active === 0);")));
        } catch (JavascriptException | TimeoutException ignored) {
            // jQuery not present or timeout — just proceed
        }
    }



    //    Scrolling
    public void scrollToElement(By locator) {
        WebElement el = find(locator);
        js.executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});", el);
    }

    public void scrollByPixels(int x, int y) {
        js.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }

    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0);");
    }

    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void waitAndScrollToElement(By locator) {
        WebElement el = waitForVisible(locator);
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }



    //    JS helpers
//    It bypasses Selenium’s native click and forces the browser to click the element via JavaScript
    public void jsClick(By locator) {
        WebElement el = find(locator);
        js.executeScript("arguments[0].click();", el);
    }



    //    Actions (mouse/keyboard)
    public void hover(By locator) {
        actions.moveToElement(waitForVisible(locator)).perform();
    }

    public void moveToAndClick(By locator) {
        actions.moveToElement(waitForVisible(locator)).click().perform();
    }



    //    Select (dropdowns)
    public void selectByVisibleText(By locator, String text) {
        new Select(waitForVisible(locator)).selectByVisibleText(text);
    }

    public void selectByValue(By locator, String value) {
        new Select(waitForVisible(locator)).selectByValue(value);
    }

    public void selectByIndex(By locator, int index) {
        new Select(waitForVisible(locator)).selectByIndex(index);
    }



    //    Frames & Windows
    public void switchToFrame(By locator) {
        driver.switchTo().frame(waitForVisible(locator));
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }



    //    Alerts
    public String getAlertTextAndAccept() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public void alertAccept() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public void acceptAlert(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent())
                .accept();
    }

    public void resolveHtmlAlertIfPresent(WebDriver driver) {
        List<WebElement> okButtons = driver.findElements(By.xpath("//button[normalize-space(.)='OK' or normalize-space(.)='Ok' or normalize-space(.)='ok']"));
        if (!okButtons.isEmpty()) {
            WebElement okBtn = okButtons.get(0);
            if (okBtn.isDisplayed() && okBtn.isEnabled()) {
                okBtn.click();
            }
        }
    }

    public void dismissAlertIfPresent() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.dismiss();
        } catch (TimeoutException ignored) { }
    }

    public void handleAlert(WebDriver driver, boolean accept) {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            if (accept) {
                alert.accept();
            } else {
                alert.dismiss();
            }
        } catch (TimeoutException e) {
            System.out.println("No alert present.");
        }
    }

    // Resolve html "OK" button if present (safer)
    public void resolveHtmlAlertIfPresent() {
        List<WebElement> okButtons = driver.findElements(By.xpath("//button[normalize-space(.)='OK' or normalize-space(.)='Ok' or normalize-space(.)='ok']"));
        if (!okButtons.isEmpty()) {
            WebElement okBtn = okButtons.get(0);
            if (okBtn.isDisplayed() && okBtn.isEnabled()) {
                okBtn.click();
            }
        }
    }

    private By gdprBox = By.id("gdpr-box");
    private By gdprCloseButton = By.cssSelector(".gdpr-box .close-gdpr"); // adjust if needed

    public void closeGdprIfVisible() {
        try {
            WebElement box = driver.findElement(gdprBox);
            if (box.isDisplayed()) {
                box.findElement(gdprCloseButton).click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(gdprBox));
            }
        } catch (Exception ignored) {}
    }


    //    Uploads
    public void uploadFile(By inputLocator, String absolutePath) {
        // Works for <input type="file">
        waitForVisible(inputLocator).sendKeys(absolutePath);
    }

    public void retryingClick(By locator, int attempts, long sleepMs) {
        for (int i = 0; i < attempts; i++) {
            try {
                click(locator);
                return;
            } catch (WebDriverException e) {
                sleep(sleepMs);
            }
        }
        // Final attempt throws whatever happens
        click(locator);
    }

    protected void sleep(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ignored) { Thread.currentThread().interrupt(); }
    }

}