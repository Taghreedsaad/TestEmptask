package BaseDate;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class Base {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://ecommerce.tealiumdemo.com/");
    }
    protected void acceptPrivacyIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            By acceptButton = By.cssSelector(".privacy_prompt_footer button");
            wait.until(ExpectedConditions.elementToBeClickable(acceptButton)).click();
        } catch (Exception e) {
            // popup not present – ignore
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

