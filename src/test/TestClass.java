import pages.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Arrays;
/**
 * Created by Raik Yauheni on 06.12.2018.
 */
public class TestClass {
    String base_url = "http://localhost:8080/";
    StringBuffer verificationErrors = null;
    WebDriver driver = null;

    // Переменная для передачи объекта из локальной переменной testdeleteUser() в testAbsentAdmin()
    PO_UsersJenkins transmittedPO = null;

    // Логин и пароль учетной записи  для работы с приложением Jenkins
    String user_name = "rea1";
    String user_password = "kickme22";

    String chromedriver_path = "E:\\Coding\\EPAM Training\\HT2\\resources\\chromedriver.exe";
    final String confirmationDeletingUser = "Are you sure about deleting the user from Jenkins?";

    @BeforeClass
    public void beforeClass() {
        // Создаем экземляр chromedriver
        System.setProperty("webdriver.chrome.driver", chromedriver_path);
        ChromeOptions options = new ChromeOptions();
        options.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));
        driver = new ChromeDriver(options);

        //  Открываем стартовую страницу
        driver.get(base_url);
        PO_SignInJenkins page = PageFactory.initElements(driver, PO_SignInJenkins.class);

        //  Быстрая проверка наличия формы для регистрации
        Assert.assertTrue(page.isFormPresent(), "No suitable forms found!");

        //  Запоняем в форму  логин, пароль и отправляем на сервер
        page.submitFilledForm(user_name, user_password);
        Assert.assertEquals(driver.getTitle(), "Dashboard [Jenkins]", "Incorrect login or password!");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @BeforeMethod
    public void beforeTest() {
        verificationErrors = new StringBuffer();
    }

    @AfterMethod
    public void afterTest() {
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }

    /**
     * 1. После клика по ссылке «Manage Jenkins» на странице появляется элемент dt с текстом «Manage Users» и
     * элемент dd с текстом «Create/delete/modify users that can log in to this Jenkins».
     */
    @Test(priority = 1)
    public void testManageJenkins() {

        // Открываем старницу  "
        driver.get(base_url);
        PO_DashBoard page1 = PageFactory.initElements(driver, PO_DashBoard.class);
        page1.clickHrefManage_Jenkins();

        // Проверяем, что выполнен переход на новую страницу
        PO_ManageJenkins page2 = PageFactory.initElements(driver, PO_ManageJenkins.class);

        if (!page2.isDtExist()) {
            verificationErrors.append("Element dt with text \"Manage Users\" haven\'t found\n");
        }
        if (!page2.isDdExist()) {
            verificationErrors.append("Element dd with text \"Create/delete/modify users that" +
                    " can log in to this Jenkins\" haven\'t found\n");
        }
    }

    /**
     * 2. После клика по ссылке, внутри которой содержится элемент dt с текстом «Manage Users»,
     * становится доступна ссылка «Create User».
     */
    @Test(priority = 2)
    public void testUsersJenkins() {
        // Открываем старницу
        driver.get(base_url + "manage");
        PO_ManageJenkins page1 = PageFactory.initElements(driver, PO_ManageJenkins.class);
        page1.getLinkWithDt_ManageUsers().click();
        PO_UsersJenkins page2 = PageFactory.initElements(driver, PO_UsersJenkins.class);
        Assert.assertTrue(page2.isHref_CreateUserExist(), "Link Create User is not available");
    }

    /**
     * 3. После клика по ссылке «Create User» появляется форма с тремя полями типа text и двумя полями
     * типа password, причём все поля должны быть пустыми.
     */
    @Test(priority = 3)
    public void testCreateUserJenkins() {
        driver.get(base_url + "securityRealm/");
        PO_UsersJenkins page1 = PageFactory.initElements(driver, PO_UsersJenkins.class);
        page1.clickHref_CreateUser();
        Assert.assertEquals(driver.getTitle(), "Create User [Jenkins]", "Wrong site page!");
        //Открываем новую страницу
        driver.get(base_url + "securityRealm/addUser");
        PO_CreateUserJenkins page2 = PageFactory.initElements(driver, PO_CreateUserJenkins.class);

        // Проверяем существоватьние нужной формы
        Assert.assertTrue(page2.isFormPresentForReal(), "No suitable forms found!");
        // Проверяем наличие текстовых полей на странице
        verificationErrors.append(page2.getErrorOnTextAbsence("Username"));
        verificationErrors.append(page2.getErrorOnTextAbsence("Password"));
        verificationErrors.append(page2.getErrorOnTextAbsence("Confirm password"));
        verificationErrors.append(page2.getErrorOnTextAbsence("Full name"));
        verificationErrors.append(page2.getErrorOnTextAbsence("E-mail address"));

        // Проверяем, что каждое из полей не заполнено
        verificationErrors.append(page2.getErrorOnInputTextNotEmpty(page2.getUsername()));
        verificationErrors.append(page2.getErrorOnInputTextNotEmpty(page2.getPassword1()));
        verificationErrors.append(page2.getErrorOnInputTextNotEmpty(page2.getPassword2()));
        verificationErrors.append(page2.getErrorOnInputTextNotEmpty(page2.getFullname()));
        verificationErrors.append(page2.getErrorOnInputTextNotEmpty(page2.getEmail()));
    }

    /**
     * 4. После заполнения полей формы («Username» = «someuser», «Password» = «somepassword»,
     * «Confirm password» = «somepassword», «Full name» = «Some Full Name», «E-mail address» = «some@addr.dom»)
     * и клика по кнопке с надписью «Create User» на странице появляется строка таблицы (элемент tr), в которой
     * есть ячейка (элемент td) с текстом «someuser».
     */
    @Test(priority = 4)
    public void testFillCreateUserForm() {
        //Открываем новую страницу
        driver.get(base_url + "securityRealm/addUser");
        PO_CreateUserJenkins page1 = PageFactory.initElements(driver, PO_CreateUserJenkins.class);
        // Проверяем существование нужной формы
        Assert.assertTrue(page1.isFormPresentForReal(), "No suitable forms found!");
        page1.submitFilledForm("someuser", "somepassword",
                "somepassword", "Some Full Name", "some@addr.dom");

        //Открываем новую страницу со списком пользователей
        PO_UsersJenkins page2 = PageFactory.initElements(driver, PO_UsersJenkins.class);

        //Проверяем, что на странице появляется строка таблицы (элемент tr), в которой
        //      есть ячейка (элемент td) с текстом «someuser».
        Assert.assertTrue(page2.isСreatedUserExist(),"Can not find elements of table with text \"someuser\"");
    }

    /**
     * 5. После клика по ссылке с атрибутом href равным «user/someuser/delete» появляется текст «Are you sure
     * about deleting the user from Jenkins?».
     */
    @Test(priority = 5)
    public void testConfirmationDeletingUser() {
        driver.get(base_url + "securityRealm");
        PO_UsersJenkins page1 = PageFactory.initElements(driver, PO_UsersJenkins.class);
        page1.deleteSomeuser();
        PO_Jenkins page2 = PageFactory.initElements(driver, PO_Jenkins.class);
        Assert.assertTrue(page2.isFormIncludeText(confirmationDeletingUser), "Page " + driver.getTitle()
                + " does not consist text: " + confirmationDeletingUser);
    }

    /**
     * 6. После клика по кнопке с надписью «Yes» на странице отсутствует строка таблицы (элемент tr),
     * с ячейкой (элемент td) с текстом «someuser». На странице отсутствует ссылка с атрибутом href
     * равным «user/someuser/delete».
     */
    @Test(priority = 6)
    public void testdeleteUser() {
        driver.get(base_url + "securityRealm/user/someuser/delete");
        PO_Jenkins page1 = PageFactory.initElements(driver, PO_Jenkins.class);

        // Кликаем на кнопку "yes" и подверждаем удаление пользователя
        page1.submitConfirmationDelete();

        // Создаем объект новой страцицы
        PO_UsersJenkins page2 = PageFactory.initElements(driver, PO_UsersJenkins.class);

        //Проверяем отсутствие ссылка с атрибутом href равным «user/someuser/delete»
        if (page2.isDeletedUserHrefExist()) {
            verificationErrors.append("Error! <a> with href = \"user/someuser/delete\" is still available.\n");
        }

        if (page2.isDeletedUserTextExist()) {
            verificationErrors.append("Error! Table's cell with the text \"someuser\" is still" +
                    " available. \"someuser\" might not have been deleted.");
        }

        //Передаем страницу для выполнения тест-кейса п.7, т.к. так в п.6 задания  есть указание
        //{На той же странице, без выполнения каких бы то ни было действий}
        transmittedPO = page2;
    }

    /**
     * 7. {На той же странице, без выполнения каких бы то ни было действий}. На странице отсутствует ссылка с
     * атрибутом href равным «user/admin/delete»
     */
    @Test(priority = 7)
    public void testAbsentAdmin() {
        Assert.assertTrue(!transmittedPO.isAdminHrefExist(), "Error! <a> with href =" +
                " \"user/admin/delete\" is available. ");
    }
}





