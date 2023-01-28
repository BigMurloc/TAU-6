import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AutomationPracticeDifferentWebDriversTest {


    @Test
    public void shouldCheckNumberOfLinksOnPageUsingFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.pl/index.php");

        List<WebElement> links = driver.findElements(By.xpath("//a"));
        int numberOfLinks = links.size();
        assertTrue(numberOfLinks > 0);
        System.out.println("Number of links: " + numberOfLinks);

        driver.quit();
    }

    @Test
    public void shouldCheckNumberOfImagesOnPageUsingChromeDriver() {
        System.setProperty("webdriver.gecko.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.pl/index.php");

        List<WebElement> images = driver.findElements(By.xpath("//img"));
        int numberOfImages = images.size();
        assertTrue(numberOfImages > 0);
        System.out.println("Number of images: " + numberOfImages);

        driver.quit();
    }

    @Test
    public void shouldNavigateToFormAndCheckNumberOfTextFieldsUsingEdgeDriver() {
        System.setProperty("webdriver.gecko.driver", "resources/msedgedriver.exe.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.pl/index.php");

        List<WebElement> forms = driver.findElements(By.xpath("//form"));
        WebElement form = forms.get(0);
        List<WebElement> textFields = form.findElements(By.xpath(".//input[@type='text']"));
        int numberOfTextFields = textFields.size();
        assertTrue(numberOfTextFields > 0);
        System.out.println("Number of text fields: " + numberOfTextFields);

        driver.quit();
    }

}
