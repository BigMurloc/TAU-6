import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuckDuckGoTest {
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
        driver.get("https://www.duckduckgo.com");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void shouldClickOnFirstSearchResultWhichShouldBeStackOverflowCom() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_form_input_homepage")));
        WebElement searchInput = driver.findElement(By.id("search_form_input_homepage"));
        searchInput.sendKeys("Stack Overflow");
        searchInput.submit();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid=\"result-title-a\"]")));
        List<WebElement> links = driver.findElements(By.cssSelector("[data-testid=\"result-title-a\"]"));
        links.get(0).click();
        wait.until(ExpectedConditions.urlToBe("https://stackoverflow.com/"));
        assertEquals(driver.getCurrentUrl(), "https://stackoverflow.com/");
    }

    @Test
    public void shouldClickOnThirdSearchResultWhichShouldBeWikipediaPageAboutStackOverflow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_form_input_homepage")));
        WebElement searchInput = driver.findElement(By.id("search_form_input_homepage"));
        searchInput.sendKeys("Stack Overflow");
        searchInput.submit();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid=\"result-title-a\"]")));
        List<WebElement> links = driver.findElements(By.cssSelector("[data-testid=\"result-title-a\"]"));
        links.get(2).click();
        wait.until(ExpectedConditions.urlToBe("https://pl.wikipedia.org/wiki/Stack_Overflow"));
        assertEquals(driver.getCurrentUrl(), "https://pl.wikipedia.org/wiki/Stack_Overflow");
    }

    @Test
    public void shouldClickOnFirstSearchResultWhichShouldBeStackOverflowComUsingJavascriptExecutor() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_form_input_homepage")));
        WebElement searchInput = driver.findElement(By.id("search_form_input_homepage"));
        searchInput.sendKeys("Stack Overflow");
        searchInput.submit();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Stack Overflow - Where Developers Learn, Share, & Build Careers")));
        List<WebElement> links = driver.findElements(By.linkText("Stack Overflow - Where Developers Learn, Share, & Build Careers"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", links.get(0));
        wait.until(ExpectedConditions.urlToBe("https://stackoverflow.com/"));
        assertEquals(driver.getCurrentUrl(), "https://stackoverflow.com/");
    }

    @Test
    public void shouldClickOnFirstSearchResultWhichShouldBeStackOverflowComUsingActions() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("search__input--adv")));
        WebElement searchInput = driver.findElement(By.className("search__input--adv"));
        searchInput.sendKeys("Stack Overflow");
        searchInput.submit();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid=\"result-title-a\"]")));
        List<WebElement> links = driver.findElements(By.cssSelector("[data-testid=\"result-title-a\"]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(links.get(0)).click().perform();
        wait.until(ExpectedConditions.urlToBe("https://stackoverflow.com/"));
        assertEquals(driver.getCurrentUrl(), "https://stackoverflow.com/");
    }
}
