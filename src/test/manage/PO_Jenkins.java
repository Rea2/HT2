package manage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Raik Yauheni on 09.12.2018.
 */
public class PO_Jenkins {
    private WebDriverWait wait;
    private final WebDriver driver;

    // Подготовка элементов страницы.
    @FindBy(xpath ="//*[@id='main-panel']/form[@name = 'delete']")
    private WebElement formWithText;

    @FindBy(xpath ="//span[@id = 'yui-gen1']")
    private WebElement submit;


    public PO_Jenkins (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);

        // Провекрка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Jenkins")) ) {
            throw new IllegalStateException("Wrong site page!");
        }
    }


    public boolean isFormIncludeText(String text){
       return formWithText.getText().contains(text);
    }

    public PO_Jenkins submitConfirmationDelete() {
        this.submit.click();
        return this;
    }




}
