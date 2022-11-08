import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Login extends Base {
    @Test(groups = "without_login")
    public void loginWithValidCredentials(){
        WebElement menuButton= driver.findElement(By.id("menu-toggle")) ;
        WebElement login= driver.findElement(By.cssSelector("a[href='profile.php#login"));
        menuButton.click();
        login.click();
        loginSteps("John Doe", "ThisIsNotAPassword");
        assertTrue(driver.getCurrentUrl().contains("#appointment"),
                "After login the page is not redirected to AppointmentPage");
    }

    @Test(groups = "without_login")
    public void loginWithInvalidCredentials(){
        WebElement menuButton= driver.findElement(By.id("menu-toggle")) ;
        WebElement login= driver.findElement(By.cssSelector("a[href='profile.php#login"));
        menuButton.click();
        login.click();
        loginSteps("Liviu Popa", "Password");
        assertFalse(driver.getCurrentUrl().contains("#appointment"),
                "Added Wrong Credentials and page been redirected to Appointment");
        assertTrue(driver.getCurrentUrl().contains("profile.php#login"),
                "Redirected from Login Page after added invalid credentials");
        assertTrue(isElementPresent(By.cssSelector(".lead.text-danger")), "Fail attempt message not displayed after added invalid credentials");
    }
}
