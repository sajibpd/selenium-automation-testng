package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;

public class Utils {

    public static void doScroll(WebDriver driver, int heightPixel){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+heightPixel+")");
    }

    public static void waitForElements(WebDriver driver, WebElement webElement, int timeunit_sec){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeunit_sec)); // implicit waiter time 30s. Sp it'll extra take 20s
        wait.until(ExpectedConditions.visibilityOf(webElement));

    }

    public static int generateRandomNumber(int min, int max){
        return (int) Math.round(Math.random()*(max-min)+min);
    }

    public static JSONObject loadJSONFile(String fileLocation) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileLocation));
        return (JSONObject) obj;
    }

    public static void addJsonArray(String firstName, String lastName, String username, String password) throws IOException {
        String fileName = "./src/test/resources/NewUsers.json"; // File path to JSON
        JSONArray jsonArray = new JSONArray(); // Initialize with an empty array

            // Create a new JSON object for the user
        JSONObject userObj = new JSONObject();
        userObj.put("firstname", firstName);
        userObj.put("lastname", lastName);
        userObj.put("username", username);
        userObj.put("password", password);

        // Add the new user object to the JSON array
        jsonArray.add(userObj);

        // Write the updated JSON array back to the file
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(jsonArray.toJSONString()); // Convert array back to JSON and write
      //  System.out.println("User added successfully to the JSON file.");
        fileWriter.close();

    }



    public static void main(String[] args) throws IOException, ParseException {
        // Example usage
      //  addJsonArray("John", "Doe", "johndoe", "password123");
    }
}
