import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSeleniumTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void init(){
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void destruct(){
        driver.quit();
    }

    @Test
    void successfulLoginTest() {

        driver.get("https://niffler.qa.guru");

        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameInput.sendKeys("stas");
        passwordInput.sendKeys("12345");
        loginButton.click();

        WebElement spendingsHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("spendings")));
        String headerText = spendingsHeader.getText();

        assertTrue(headerText.contains("History of Spendings"), "Expected text not found!");
    }
}