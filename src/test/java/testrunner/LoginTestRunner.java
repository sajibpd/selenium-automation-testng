package testrunner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;
    @Test(priority = 1, description = "User can do login if provides wrong creds")
    public void doLoginWithInvalidCreds(){
         loginPage = new LoginPage(driver);
         String messageActual=loginPage.doLoginWithInvalidCreds("admin","wrongpass");
         String messageExpected="Invalid credentials";
        Assert.assertTrue(messageActual.contains(messageExpected));

    }

    @Test(priority = 2, description = "User can do login successfully")
    public void doLogin() throws IOException, ParseException {
        loginPage=new LoginPage(driver);

        // if we give input from gradle it will take it, otherwise it will get it from JSON values
        if(System.getProperty("username") !=null && System.getProperty("password") !=null){
            String username= System.getProperty("username");
            String password= System.getProperty("password");
            loginPage.doLogin(username,password);
        }
        else{
            JSONObject userObject= Utils.loadJSONFile("./src/test/resources/User.json");
            String username= (String) userObject.get("username");
            String password= (String) userObject.get("password");
            loginPage.doLogin(username,password);
        }




        WebElement headerText=driver.findElement(By.tagName("h6"));
        String actual_header=headerText.getText();
        String expected_header="Dashboard";
        SoftAssert softAssert= new SoftAssert();
        softAssert.assertEquals(actual_header,expected_header);
        WebElement pprofileImage= driver.findElement(By.className("oxd-userdropdown-img"));
        softAssert.assertTrue(pprofileImage.isDisplayed());
        softAssert.assertAll();
    }

}
