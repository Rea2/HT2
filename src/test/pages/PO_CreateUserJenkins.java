package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Raik Yauheni on 08.12.2018.
 */
public class PO_CreateUserJenkins extends PO {

    @FindBy(xpath = "//body")
    private WebElement body;

    @FindBy(xpath = "//form[@action='/securityRealm/createAccountByAdmin']")
    private WebElement form;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(name = "password1")
    private WebElement password1;

    @FindBy(name = "password2")
    private WebElement password2;

    @FindBy(name = "fullname")
    private WebElement fullname;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy (id = "yui-gen1")
    private WebElement submitNewUser;

    public PO_CreateUserJenkins(WebDriver driver) {
        super(driver);

        // Провекрка того факта, что мы на верной странице.
        if ((!driver.getTitle().equals("Create User [Jenkins]")) ) {
            throw new IllegalStateException("Wrong site page!");
        }
    }

    // Надёжный поиск формы.
    public boolean isFormPresentForReal() {
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//html/body"), 1));
        Collection<WebElement> forms = driver.findElements(By.tagName("form"));
        if (forms.isEmpty()) {
            return false;
        }

        Iterator<WebElement> i = forms.iterator();
        WebElement form = null;

        while (i.hasNext()) {
            form = i.next();

            try {
                if ((form.findElement(By.name("username")).getAttribute("type").equalsIgnoreCase("text")) &&
                        (form.findElement(By.name("password1")).getAttribute("type").equalsIgnoreCase("password")) &&
                        (form.findElement(By.name("password2")).getAttribute("type").equalsIgnoreCase("password")) &&
                        (form.findElement(By.name("fullname")).getAttribute("type").equalsIgnoreCase("text")) &&
                        (form.findElement(By.name("email")).getAttribute("type").equalsIgnoreCase("text"))){
                return true;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return false;
    }

    // Проверка вхождения подстроки в текст страницы.
    public boolean pageTextContains(String search_string) {
        return body.getText().contains(search_string);
    }


    public String getErrorOnTextAbsence(String search_string) {
        if (!pageTextContains(search_string)) {
            return "No '" + search_string + "' is found inside page text!\n";
        } else {
            return "";
        }
    }
    // Проверка что поле для ввода текста не заполнено
    public String getErrorOnInputTextNotEmpty(WebElement webElement) {
        if (webElement.getAttribute("value").isEmpty())  {
            return "";
        } else {
            return (webElement.getText() + ": is not empty!\n");
        }
    }

    public PO_CreateUserJenkins setFields(String username, String password1,
                                        String password2, String fullName, String email) {
        setUsername(username);
        setPassword1(password1);
        setPassword2(password2);
        setFullname(fullName);
        setEmail(email);
        return this;
    }
    public PO_CreateUserJenkins submitForm() {
        submitNewUser.click();
        return this;
    }

    // Обёртка для упрощения отправки данных.
    public PO_CreateUserJenkins submitFilledForm(String username, String password1,
                                                 String password2, String fullName, String email) {
        setFields(username, password1, password2, fullName, email);
        return submitForm();
    }


    // Getters
    public WebElement getUsername() {
        return username;
    }

    public WebElement getPassword1() {
        return password1;
    }

    public WebElement getPassword2() {
        return password2;
    }

    public WebElement getFullname() {
        return fullname;
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getForm() {
        return form;
    }


    // Setters
    public PO_CreateUserJenkins setUsername(String value) {
        username.clear();
        username.sendKeys(value);
        return this;
    }

    public PO_CreateUserJenkins setPassword1(String value) {
        password1.clear();
        password1.sendKeys(value);
        return this;
    }

    public PO_CreateUserJenkins setPassword2(String value) {
        password2.clear();
        password2.sendKeys(value);
        return this;
    }

    public PO_CreateUserJenkins setFullname(String value) {
        fullname.clear();
        fullname.sendKeys(value);
        return this;
    }

    public PO_CreateUserJenkins setEmail(String value) {
        email.clear();
        email.sendKeys(value);
        return this;
    }


}

