package testrunner;

import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.security.Key;

public class PIMTestRunner extends Setup {
    DashboardPage dashboardPage;
    LoginPage loginPage;
    PIMPage pimPage;

    @BeforeTest
    public void doLogin() throws IOException, ParseException, InterruptedException {
        loginPage= new LoginPage(driver);
        System.out.println("Enter");
        JSONObject userObject= Utils.loadJSONFile("./src/test/resources/User.json");
        System.out.println("Json paisi");
        String username= (String) userObject.get("username");
        String password= (String) userObject.get("password");
        System.out.println(password);
        Thread.sleep(3000);
        loginPage.doLogin(username,password);
    }

    @Test(priority = 2, description = "User can view existing employee list", enabled = true)
    public void searchEmployeeInfo() throws InterruptedException {
        dashboardPage= new DashboardPage(driver);
        Thread.sleep(3000);
        dashboardPage.menus.get(1).click();  // click on PIM menu takes it to PIM apge
        Thread.sleep(3000);
        String isUserFound= driver.findElements(By.className("oxd-text--span")).get(12).getText();
        Thread.sleep(3000);
        Assert.assertTrue(isUserFound.contains("Records Found"));

    }
    @Test(priority = 3, description = "User can search Employee by employee status", enabled = true)
    public void selectEmployeeStatus() throws InterruptedException {
        pimPage = new PIMPage(driver);
        pimPage.dropdowns.get(0).click();
        System.out.println("No DrpDown");
        pimPage.dropdowns.get(0).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        pimPage.dropdowns.get(0).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        pimPage.dropdowns.get(0).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("[type=submit]"));
        Utils.doScroll(driver,200);

    }
    @Test(priority = 4, description = "Create New Employee", enabled = true)
    public void addEmployee() throws InterruptedException, IOException, ParseException {
        pimPage= new PIMPage(driver);
        String username="test"+Utils.generateRandomNumber(1000,9999);
        String password="P@ssword1234";
        Faker faker= new Faker();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();

        pimPage.createEmployee(firstName,lastName,username,password);

        Thread.sleep(8000);
        String header_actual=driver.findElement(By.className("orangehrm-main-title")).getText();
        String header_expected="Personal Details";
        Assert.assertTrue(header_actual.contains(header_expected));

        Utils.addJsonArray(firstName,lastName,username,password);

    }

}
