package configs;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class browserRules extends ExternalResource {

    private WebDriver driver;

    @Override
    protected void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
//        driver = new SafariDriver();
    }

    @Override
    protected void after() {
        driver.quit();
    }

    public WebDriver driver() {
        return driver;
    }

}
