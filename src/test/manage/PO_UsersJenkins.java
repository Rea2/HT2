package manage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Raik Yauheni on 06.12.2018.
 */
public class PO_UsersJenkins {
    private WebDriverWait wait;
    private final WebDriver driver;

    @FindBy(partialLinkText = "Create User")
    private WebElement href_CreateUser;

//@FindBy (xpath = "//tbody/tr[last()]/td[last()]/a[@href = 'user/someuser/delete']")
//@FindBy (xpath = "//tbody/tr[last()]/td/*[text() = 'someuser']")
    @FindBy (xpath = "//tbody/tr[last()]/td[*/text() = 'someuser']")
    private WebElement deleteUserText;

    @FindBy (xpath = "//a[@href = 'user/someuser/delete']")
    private WebElement deleteUserLink;

    @FindBy (xpath = "//a[@href = 'user/admin/delete']")
    private WebElement deleteAdmin;


    public PO_UsersJenkins(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);

        // Провекрка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Users [Jenkins]")) ) {
            throw new IllegalStateException("Wrong site page!");
        }
    }


    public  WebElement getHref_CreateUser() {
        return  href_CreateUser;
    }

    public PO_UsersJenkins clickHrefCreateUser() {
        href_CreateUser.click();
        return this;
    }

    public String getHrefCreateUserDescription(){
        return href_CreateUser.getText();
    }

    // Удаление пользователя 'someuser'
    public PO_UsersJenkins deleteSomeuser(){
        deleteUserLink.click();
        return this;
    }

    // Проверяем, существует ли WebElement deleteUser2
    public boolean isDeletedUserHrefExist() {
        try {
            deleteUserLink.getText();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isDeletedUserTextExist(){
        try {
            deleteUserText.getText();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAdminHrefExist(){
        try {
            deleteAdmin.getText();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }




}
