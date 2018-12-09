//package users_jenkins;
//
//
//import users_jenkins.PO_SignInJenkins;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//import java.util.Arrays;
//
///**
// * Created by Raik Yauheni on 06.12.2018.
// */
//public class TestClass {
//    String base_url = "http://localhost:8080/securityRealm/";
//    StringBuffer verificationErrors = new StringBuffer();
//    WebDriver driver = null;
//    String user_name = "rea1";
//    String user_password = "kickme22";
//    String chromedriver_path = "E:\\Coding\\EPAM Training\\HT2\\resources\\chromedriver.exe";
//
//    @BeforeClass
//    public void beforeClass() throws Exception {
//        System.setProperty("webdriver.chrome.driver", chromedriver_path);
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));
//        driver = new ChromeDriver(capabilities);
//    }
//
//    @AfterClass
//    public void afterClass() {
//        driver.quit();
//        String verificationErrorString = verificationErrors.toString();
//        if (!"".equals(verificationErrorString)) {
//            Assert.fail(verificationErrorString);
//        }
//    }
//
//    @Test
//    public void sampleTest() throws InterruptedException {
//        // Открываем старницу  "
//        driver.get(base_url);
//        Thread.sleep(100);
//        PO_SignInJenkins page = PageFactory.initElements(driver, PO_SignInJenkins.class);
//        page.submitFilledForm(user_name,user_password  );
//        Thread.sleep(100);
//
//        //1. Кликаем по ссылке «Manage Jenkins»
//        Assert.assertTrue(page.isManageJenkinsExist(),
//                "Link \"Manage Jenkins\" hasn't been found on the page \"" + driver.getTitle()+ "\"");
//        page.clickHrefManageJenkins();
//        Assert.assertTrue(driver.getTitle().equals("Manage Jenkins [Jenkins]"), "Wrong site page!");
//        Thread.sleep(2000);
//        page.clickHrefManageUsers();
//        Assert.assertTrue(driver.getTitle().equals("Users [Jenkins]"), "Wrong site page!");
//        Assert.assertTrue(page.isSelectedHrefManageUsers(), "Link \"Create User\" is not avaliable");
//
//
//
//
//
//
//
//
//
//
//
//
//
//    }
//
//}
//
//
