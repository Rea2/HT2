package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Raik Yauheni on 06.12.2018.
 */
public class PO_UsersJenkins extends PO {

    @FindBy(xpath = "//a[text() = 'Create User']")
    private WebElement href_CreateUser;

    @FindBy (xpath = "//tbody/tr[last()]/td[*/text() = 'someuser']")
    private WebElement deleteUserText;

    @FindBy (xpath = "//a[@href = 'user/someuser/delete']")
    private WebElement deleteUserLink;

    @FindBy (xpath = "//a[@href = 'user/admin/delete']")
    private WebElement deleteAdmin;

    @FindBy (xpath = "//tbody/tr/td[*/text() = 'someuser']")
    private WebElement createdUser;

    public PO_UsersJenkins(WebDriver driver) {
        super(driver);

        // Провекрка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Users [Jenkins]")) ) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    public  boolean  isHref_CreateUserExist() {
        return  isWebElementExist(href_CreateUser);
    }

    public PO_UsersJenkins clickHref_CreateUser() {
        href_CreateUser.click();
        return this;
    }

    public  boolean  isСreatedUserExist() {
        return  isWebElementExist(createdUser);
    }

    // Удаление пользователя 'someuser'
    public PO_UsersJenkins deleteSomeuser(){
        deleteUserLink.click();
        return this;
    }


    // Проверяем, существует ли WebElement deleteUser2
    public boolean isDeletedUserHrefExist() {
       return isWebElementExist(deleteUserLink);
    }

    public boolean isDeletedUserTextExist(){
       return isWebElementExist(deleteUserText);
    }

    public boolean isAdminHrefExist(){
        return isWebElementExist(deleteAdmin);
    }

    public WebElement getHref_CreateUser() {
        return href_CreateUser;
    }
}
