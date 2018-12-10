package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Raik Yauheni on 10.12.2018.
 */
public class PO_ManageJenkins extends PO {

    @FindBy(xpath = "//*/dt[text()= 'Manage Users']")
    private WebElement dt;

    @FindBy(xpath = "//*/dd[text()= 'Create/delete/modify users that can log in to this Jenkins']")
    private WebElement dd;

    @FindBy(xpath = "//a/following::dt[text() = 'Manage Users']/ancestor::a")
    private WebElement linkWithDt_ManageUsers;

    public PO_ManageJenkins(WebDriver driver) {
        super(driver);
        // Провекрка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Manage Jenkins [Jenkins]")) ) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    // Провекрка того факта, что мы на верной странице.
    public boolean isDtExist()throws NoSuchElementException {
        return (isWebElementExist(dt));
    }

    public boolean isDdExist() throws NoSuchElementException {
        return (isWebElementExist(dd));
    }

    public WebElement getLinkWithDt_ManageUsers() {
        return linkWithDt_ManageUsers;
    }
}
