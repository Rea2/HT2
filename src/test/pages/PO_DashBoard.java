package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Raik Yauheni on 10.12.2018.
 */
public class PO_DashBoard extends PO {

    @FindBy(linkText = "Manage Jenkins")
    private WebElement href_Manage_Jenkins;

    public PO_DashBoard(WebDriver driver) {
        super(driver);
        // Провекрка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Dashboard [Jenkins]")) ) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    public PO_DashBoard clickHrefManage_Jenkins() {
        href_Manage_Jenkins.click();
        return this;
    }

}
