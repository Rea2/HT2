//package users_jenkins;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
///**
// * Created by Raik Yauheni on 06.12.2018.
// */
//public class PO_SignInJenkins {
//    private WebDriverWait wait;
//    private final WebDriver driver;
//
//    // Подготовка элементов страницы.
//    @FindBy(id = "j_username")
//    private WebElement user_name;
//
//    @FindBy(name = "j_password")
//    private WebElement user_password;
//
//    @FindBy(xpath = "//input[@type='submit']")
//    private WebElement button_sign_in;
//
//    @FindBy(linkText = "Manage Jenkins")
//    private WebElement href_Manage_Jenkins;
//
//    @FindBy(linkText = "Manage Users")
//    private WebElement href_Manage_Users;
//
//    @FindBy(linkText = "Create User")
//    private WebElement href_CreateUser;
//
//
//    public PO_SignInJenkins(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(this.driver, 30);
//
//        // Провекрка того факта, что мы на верной странице.
//        if ((!driver.getTitle().equals("Sign in [Jenkins]")) ) {
//            throw new IllegalStateException("Wrong site page!");
//        }
//    }
//
//    // Автологирование
//    //1.1 Заполнение имени
//    public PO_SignInJenkins setName(String value) {
//        user_name.clear();
//        user_name.sendKeys(value);
//        return this;
//    }
//    //1.2 Заполнение пароля
//    public PO_SignInJenkins setPassword(String value) {
//        user_password.clear();
//        user_password.sendKeys(value);
//        return this;
//    }
//
//    //1.3 Заполнение имени и пароля
//    public PO_SignInJenkins setFieldsLoginPass(String name, String password) {
//        setName(name);
//        setPassword(password);
//        return this;
//    }
//
//    //1.4 Клик по кнопке и отправка данных
//    public PO_SignInJenkins submitForm() {
//        button_sign_in.click();
//        return this;
//    }
//
//    // 1.5 Отправка заполненной формы для входа в аккаунт
//    public PO_SignInJenkins submitFilledForm(String name, String password) {
//        setFieldsLoginPass(name, password);
//        return submitForm();
//    }
//
//    // Упрощённый поиск формы.
//    public boolean isFormPresent() {
//        if ((user_name != null) && (user_password != null)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public boolean isManageJenkinsExist(){
//        if (href_Manage_Jenkins != null) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    // перед по ссылке
//    public PO_SignInJenkins clickHrefManageJenkins() {
//        href_Manage_Jenkins.click();
//        return this;
//    }
//
//    // перед по ссылке
//    public PO_SignInJenkins clickHrefManageUsers() {
//        href_Manage_Users.click();
//        return this;
//    }
//
//    public  boolean isSelectedHrefManageUsers() {
//        return href_CreateUser.isSelected();
//    }
//
//
//
//
//}
