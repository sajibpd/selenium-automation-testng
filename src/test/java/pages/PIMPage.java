package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v125.page.model.WebAppManifest;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Utils;

import java.util.List;

public class PIMPage {
    @FindBy(className = "oxd-button--secondary")
    List<WebElement> button;
    @FindBy(css ="[type=submit]" )
    WebElement btnSubmit;
    WebDriver driver;
    @FindBy(className = "employee-image")
    WebElement imgProfileImage;
    @FindBy (css = "[name=firstName]")
    WebElement txtFirstName;
    @FindBy (css = "[name=lastName]")
    WebElement txtLastName;
    @FindBy(className = "oxd-switch-input")
    WebElement btnToggle;
    @FindBy(tagName ="input")
    List<WebElement> txtInput;
    @FindBy(className = "oxd-select-text-input")
    public List<WebElement> dropdowns;

    public PIMPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void createEmployee(String firstName, String lastName,String username, String password) throws InterruptedException {
        button.get(1).click();
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        Thread.sleep(3000);
        btnToggle.click();

        txtInput.get(7).sendKeys(username); // input username
        txtInput.get(10).sendKeys(password); // input password
        txtInput.get(11).sendKeys(password); // input confirm password
        btnSubmit.click();


      // Utils.waitForElements(driver,imgProfileImage,50); // implicit waiter works in a same page. But it'll work as it's changing the page
    }
}
