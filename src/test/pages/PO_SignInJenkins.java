package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Raik Yauheni on 06.12.2018.
 */
public class PO_SignInJenkins extends PO {

    // Подготовка элементов страницы.
    @FindBy(id = "j_username")
    private WebElement user_name;

    @FindBy(name = "j_password")
    private WebElement user_password;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement button_sign_in;


    public PO_SignInJenkins(WebDriver driver) {
        super(driver);

        // Провекрка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Sign in [Jenkins]")) ) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    // Авторизация
    // Заполнение имени
    public PO_SignInJenkins setName(String value) {
        user_name.clear();
        user_name.sendKeys(value);
        return this;
    }
    //  Заполнение пароля
    public PO_SignInJenkins setPassword(String value) {
        user_password.clear();
        user_password.sendKeys(value);
        return this;
    }

    // Заполнение имени и пароля
    public PO_SignInJenkins setFieldsLoginPass(String name, String password) {
        setName(name);
        setPassword(password);
        return this;
    }

    // Клик по кнопке и отправка данных
    public PO_SignInJenkins submitForm() {
        button_sign_in.click();
        return this;
    }

    // Отправка заполненной формы для входа в аккаунт
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











}
