package manage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;
/**
 * Created by Raik Yauheni on 06.12.2018.
 */
public class TestClass {
    String base_url = "http://localhost:8080/";
    StringBuffer verificationErrors = new StringBuffer();
    WebDriver driver = null;
    PO_UsersJenkins po = null;
    String user_name = "rea1";
    String user_password = "kickme22";
    String chromedriver_path = "E:\\Coding\\EPAM Training\\HT2\\resources\\chromedriver.exe";
    final String confirmationDeletingUser = "Are you sure about deleting the user from Jenkins?";

    @BeforeClass
    public void beforeClass() throws Exception {
        System.setProperty("webdriver.chrome.driver", chromedriver_path);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));
        driver = new ChromeDriver(capabilities);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @AfterMethod
    public void afterTest() {
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
        verificationErrors = new StringBuffer();
    }

    /**
     * 1. После клика по ссылке «Manage Jenkins» на странице появляется элемент dt с текстом «Manage Users» и
     * элемент dd с текстом «Create/delete/modify users that can log in to this Jenkins».
     */
    @Test(priority = 1)
    public void testManageJenkins() throws InterruptedException {
        // Открываем старницу  "
        driver.get(base_url+"manage");
        PO_SignInJenkins page = PageFactory.initElements(driver, PO_SignInJenkins.class);
        Assert.assertTrue(page.isFormPresent(), "No suitable forms found!");
        page.submitFilledForm(user_name,user_password);

    //1. Проверяем что ссылка для перехода есть
    Assert.assertTrue(page.isManageJenkinsExist(),
            "Link \"Manage Jenkins\" hasn't been found on the page \"" + driver.getTitle()+ "\"");

    page.clickHrefManage_Jenkins();

    String error_dt = "Element dt with text \"Manage Users\" haven\'t found";
    String error_dd = "Element dd with text \"Create/delete/modify users that" +
            " can log in to this Jenkins\" haven\'t found";

    try {
        Assert.assertTrue(page.isDtExist());

    } catch (NoSuchElementException e) {
        verificationErrors.append(error_dt);
    }

    try {
        Assert.assertTrue(page.isDdExist());
    } catch (NoSuchElementException e) {
        verificationErrors.append(error_dd);
    }

    Thread.sleep(200);
    Assert.assertTrue(page.getHref_Manage_Users().isEnabled());
    page.clickHrefManage_Users();
    Assert.assertEquals(driver.getTitle(), "Users [Jenkins]", "Wrong site page!");
    Thread.sleep(200);
    }

    /**
     * 2. После клика по ссылке, внутри которой содержится элемент dt с текстом «Manage Users»,
     * становится доступна ссылка «Create User».
     */
    @Test(priority = 2)
    public void testUsersJenkins() throws InterruptedException {
        // Открываем старницу  "
        driver.get(base_url+"securityRealm/");

        PO_UsersJenkins page = PageFactory.initElements(driver, PO_UsersJenkins.class);
        Assert.assertTrue(page.getHref_CreateUser().isEnabled(), "Link Create User is not available");
        page = null;
    }

    /**
     * 3. После клика по ссылке «Create User» появляется форма с тремя полями типа text и двумя полями
     * типа password, причём все поля должны быть пустыми.
     */
    @Test(priority = 3)
    public void testCreateUserJenkins()  {
        driver.get(base_url+"securityRealm/");
        PO_UsersJenkins page1 = PageFactory.initElements(driver, PO_UsersJenkins.class);
        page1.getHref_CreateUser().click();
        Assert.assertEquals(driver.getTitle(), "Create User [Jenkins]", "Wrong site page!");
//         Открываем новую страницу
        driver.get(base_url+"securityRealm/addUser");
        PO_CreateUserJenkins page2 = PageFactory.initElements(driver, PO_CreateUserJenkins.class);

        // Проверяем существоватьние нужной формы
        Assert.assertTrue(page2.isFormPresentForReal(), "No suitable forms found!");
        // Проверяем наличие полей
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
        driver.get(base_url+"securityRealm/addUser");
        PO_CreateUserJenkins page = PageFactory.initElements(driver, PO_CreateUserJenkins.class);
        // Проверяем существоватьние нужной формы
        Assert.assertTrue(page.isFormPresentForReal(), "No suitable forms found!");
        page.submitFilledForm("someuser", "somepassword",
                "somepassword", "Some Full Name", "some@addr.dom");
        Assert.assertEquals(page.getCreatedUser().getText(), "someuser", "Unable element \"someuser\"");
    }

    /**
     *5. После клика по ссылке с атрибутом href равным «user/someuser/delete» появляется текст «Are you sure
     *  about deleting the user from Jenkins?».
     */
    @Test(priority = 5)
    public void testConfirmationDeletingUser() {
        driver.get(base_url + "securityRealm");
        PO_UsersJenkins page1 = PageFactory.initElements(driver, PO_UsersJenkins.class);

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        page1.deleteSomeuser();
        Assert.assertEquals(driver.getTitle(), "Jenkins", "Wrong site page!");
        driver.get(base_url  + "securityRealm/user/someuser/delete");
        PO_Jenkins page2  = PageFactory.initElements(driver, PO_Jenkins.class);
        Assert.assertTrue(page2.isFormIncludeText(confirmationDeletingUser), "Page "+driver.getTitle()
                +" does not consist text: " + confirmationDeletingUser);
    }
    /**
     * 6. После клика по кнопке с надписью «Yes» на странице отсутствует строка таблицы (элемент tr),
     * с ячейкой (элемент td) с текстом «someuser». На странице отсутствует ссылка с атрибутом href
     * равным «user/someuser/delete».
     */
    @Test(priority = 6)
    public void testdeleteUser() {
        driver.get(base_url  + "securityRealm/user/someuser/delete");
        PO_Jenkins page1  = PageFactory.initElements(driver, PO_Jenkins.class);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Кликаем на кнопку и подверждаем удаление пользователя
        page1.submitConfirmationDelete();

        // Проверяем что браузер отображает другую страницу
        Assert.assertEquals(driver.getTitle(), "Users [Jenkins]","Wrong site page!" );

        // Создаем объект новой страцицы
        driver.get(base_url  + "securityRealm/");
        PO_UsersJenkins page2  = PageFactory.initElements(driver, PO_UsersJenkins.class);

        //Проверяем отсутствие ссылка с атрибутом href равным «user/someuser/delete»
        Assert.assertTrue(!page2.isDeletedUserHrefExist(), "Error! " +
                "<a> with href = \"user/someuser/delete\" is still available. "+
                "\"someuser\" might not have been deleted." );

        //Проверяем отсутствие строки таблицы (элемент tr), с ячейкой (элемент td) с текстом «someuser».
        Assert.assertTrue(!page2.isDeletedUserTextExist(), "Error! " +
                "Table's cell with the text \"someuser\" is still available. " +
                " \"someuser\" might not have been deleted." );

        //Передаем страницу для выполнения тест-кейса п.7, т.к. там есть указание
        //"{На той же странице, без выполнения каких бы то ни было действий}"
        po = page2;

    }

    /**
     *7. {На той же странице, без выполнения каких бы то ни было действий}. На странице отсутствует ссылка с
     *  атрибутом href равным «user/admin/delete».
     *
     */
    @Test(priority = 7)
    public void testAbsentAdmin() {
        Assert.assertTrue(!po.isAdminHrefExist(), "Error! " +
                "<a> with href = \"user/admin/delete\" is still available. " +
                "\"someuser\" might not have been deleted.");
    }


}


