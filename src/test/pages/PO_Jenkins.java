package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Raik Yauheni on 09.12.2018.
 */
public class PO_Jenkins extends PO {

    @FindBy(xpath ="//*[@id='main-panel']/form[@name = 'delete']")
    private WebElement formWithText;

    @FindBy(xpath ="//span[@id = 'yui-gen1']")
    private WebElement submit;


    public PO_Jenkins (WebDriver driver) {
        super(driver);
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
