import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Helpers {

    private static WebDriver driver;

    public Helpers() {
    }

    public static void  init() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\61491\\sdettesting\\Softwares\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    public static void waitDriver(String elem,int duration){
        WebDriverWait wait = new WebDriverWait(getDriver(),duration);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elem)));
    }

    private static WebDriver getDriver() {
        if (driver == null)
            init();
        return driver;
    }

    public static void navigateToUrl(String url){
        getDriver().get(url);
    }

    public static WebElement find(By locator) {
        WebElement we = null;
        if (getDriver().findElement(locator).isEnabled()) {
            we = getDriver().findElement(locator);
        }
        return we;
    }
    public static void JsExecutorClick(By locator) {
            WebElement element = find(locator);
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click();", element);

    }
    public static void quit() {

        getDriver().quit();
    }
}
