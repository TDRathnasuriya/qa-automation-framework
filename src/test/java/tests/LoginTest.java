package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver and navigate to the login page
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // or any other driver
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void validLoginSucceeds() {
        // loginPage.login("standard_user", "secret_sauce");
        loginPage.login(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "Expected to land on inventory page after login");
    }

    @Test
    public void invalidLoginShowsError() {
        // loginPage.login("wrong_user", "wrong_pass");
        loginPage.login(ConfigReader.get("invalid.username"), ConfigReader.get("invalid.password"));
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Username and password do not match"),
                "Expected error message for invalid login");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
