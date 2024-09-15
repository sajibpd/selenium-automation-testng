package pages;

import org.junit.rules.Timeout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DashboardPage {
    @FindBy(css = ".oxd-userdropdown-tab")
    public WebElement btnProfileTab;
    @FindBy(partialLinkText = "Logout")
    public  WebElement linkLogOut;
    @FindBy(className = "oxd-main-menu-item--name")
    public List<WebElement> menus;
    @FindBy(className = "oxd-select-text-input")
    public List<WebElement> dropdowns;
    WebDriver driver;


    public DashboardPage(WebDriver driver){
        PageFactory.initElements(driver,this);

    }

    public void doLogout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(btnProfileTab));
        btnProfileTab.click();
        linkLogOut.click();
    }
}
