package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Raik Yauheni on 10.12.2018.
 */
public class PO {
    protected WebDriverWait wait;
    protected final WebDriver driver;

    public PO(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);
    }

    protected boolean isWebElementExist (WebElement webElement) {
        try {
            webElement.isEnabled();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
