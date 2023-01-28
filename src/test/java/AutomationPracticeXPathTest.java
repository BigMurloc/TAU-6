import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class AutomationPracticeXPathTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver() {
        System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("http://automationpractice.pl/index.php");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void shouldCheckNumberOfLinksOnPage() {
        List<WebElement> links = driver.findElements(By.xpath("//a"));
        int numberOfLinks = links.size();
        assertTrue(numberOfLinks > 0);
        System.out.println("Number of links: " + numberOfLinks);
    }

    @Test
    public void shouldCheckNumberOfImagesOnPage() {
        List<WebElement> images = driver.findElements(By.xpath("//img"));
        int numberOfImages = images.size();
        assertTrue(numberOfImages > 0);
        System.out.println("Number of images: " + numberOfImages);
    }

    @Test
    public void shouldNavigateToFormAndCheckNumberOfTextFields() {
        List<WebElement> forms = driver.findElements(By.xpath("//form"));
        WebElement form = forms.get(0);
        List<WebElement> textFields = form.findElements(By.xpath(".//input[@type='text']"));
        int numberOfTextFields = textFields.size();
        assertTrue(numberOfTextFields > 0);
        System.out.println("Number of text fields: " + numberOfTextFields);
    }


}
