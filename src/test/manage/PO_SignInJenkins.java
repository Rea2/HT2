package manage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Raik Yauheni on 06.12.2018.
 */
public class PO_SignInJenkins {
    private WebDriverWait wait;
    private final WebDriver driver;

    // Подготовка элементов страницы.
    @FindBy(id = "j_username")
    private WebElement user_name;

    @FindBy(name = "j_password")
    private WebElement user_password;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement button_sign_in;

    @FindBy(linkText = "Manage Jenkins")
    private WebElement href_Manage_Jenkins;

    @FindBy(xpath = "//*/dt[text()= 'Manage Users']")
    private WebElement dt;

    @FindBy(xpath = "//*/dd[text()= 'Create/delete/modify users that can log in to this Jenkins']")
    private WebElement dd;

    @FindBy(partialLinkText = "Manage Users" )
    private WebElement href_Manage_Users;

    public PO_SignInJenkins(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);

        // Провекрка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Sign in [Jenkins]")) ) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    // Автологирование
    //1.1 Заполнение имени
    public PO_SignInJenkins setName(String value) {
        user_name.clear();
        user_name.sendKeys(value);
        return this;
    }
    //1.2 Заполнение пароля
    public PO_SignInJenkins setPassword(String value) {
        user_password.clear();
        user_password.sendKeys(value);
        return this;
    }

    //1.3 Заполнение имени и пароля
    public PO_SignInJenkins setFieldsLoginPass(String name, String password) {
        setName(name);
        setPassword(password);
        return this;
    }

    //1.4 Клик по кнопке и отправка данных
    public PO_SignInJenkins submitForm() {
        button_sign_in.click();
        return this;
    }

    // 1.5 Отправка заполненной формы для входа в аккаунт
    public PO_SignInJenkins submitFilledForm(String name, String password) {
        setFieldsLoginPass(name, password);
        return submitForm();
    }

    // Упрощённый поиск формы.
    public boolean isFormPresent() {
        if ((user_name != null) && (user_password != null)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isManageJenkinsExist(){
        if (href_Manage_Jenkins != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isManageUsersExist(){
        if (href_Manage_Jenkins != null) {
            return true;
        } else {
            return false;
        }
    }

    public PO_SignInJenkins clickHrefManage_Jenkins() {
        href_Manage_Jenkins.click();
        return this;
    }
    public PO_SignInJenkins clickHrefManage_Users() {
        href_Manage_Users.click();
        return this;
    }

    public boolean isDtExist()throws NoSuchElementException {
        return (dt.isEnabled());
    }

    public boolean isDdExist() throws NoSuchElementException {
        return (dd.isEnabled());
    }

    public boolean isHrefManageUsersExist()  {
        return (href_Manage_Users.isSelected());
    }

    public WebElement getHref_Manage_Users (){
        return href_Manage_Users;
    }









}
