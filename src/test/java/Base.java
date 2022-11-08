import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
public abstract class Base {

    protected ChromeDriver driver = null;

    @BeforeMethod(onlyForGroups = {"with_login"})
    public void beforeMethodWithLogin() {
        driver = BrowserManager.getChromeDriver();
        driver.get("https://katalon-demo-cura.herokuapp.com");
        WebElement menuButton= driver.findElement(By.id("menu-toggle")) ;
        WebElement login= driver.findElement(By.cssSelector("a[href='profile.php#login"));
        menuButton.click();
        login.click();
        loginSteps("John Doe", "ThisIsNotAPassword");

    }
    @BeforeMethod(onlyForGroups = {"without_login"})
    public void beforeMethodWithoutLogin() {
        driver = BrowserManager.getChromeDriver();
        driver.get("https://katalon-demo-cura.herokuapp.com");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void loginbutton(){
        WebElement menuButton= driver.findElement(By.id("menu-toggle")) ;
        WebElement login= driver.findElement(By.cssSelector("a[href='profile.php#login"));
        menuButton.click();
        login.click();
    }
    protected void homeButoon(){
        WebElement menuButton= driver.findElement(By.id("menu-toggle")) ;
        WebElement home = driver.findElement(By.xpath("//a[normalize-space()='Home']"));
        menuButton.click();
        home.click();
    }

    protected void historyButton(){
        WebElement menuButton= driver.findElement(By.id("menu-toggle")) ;
        WebElement history= driver.findElement(By.cssSelector("a[href='history.php#history']")) ;
        menuButton.click();
        history.click();
    }
    protected void profileButton(){
        WebElement menuButton= driver.findElement(By.id("menu-toggle")) ;
        WebElement profile= driver.findElement(By.cssSelector("a[href='profile.php#profile']")) ;
        menuButton.click();
        profile.click();
    }
    protected void logoutButton(){
        WebElement menuButton= driver.findElement(By.id("menu-toggle")) ;
        WebElement logout= driver.findElement(By.cssSelector("a[href='authenticate.php?logout']")) ;
        menuButton.click();
        logout.click();
    }
    protected void makeAppointmentButton(){
        WebElement makeAppointment = driver.findElement(By.id("btn-make-appointment"));
        makeAppointment.click();
    }

    protected void  loginSteps(String username, String password) {
        driver.findElement(By.id("txt-username")).sendKeys(username);
        driver.findElement(By.id("txt-password")).sendKeys(password);
        driver.findElement(By.id("btn-login")).click();
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
