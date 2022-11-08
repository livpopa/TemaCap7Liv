import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
public class Home extends Base {

    @Test(groups = {"without_login"})
    public void HomeButtonTest(){

        loginbutton();
        assertTrue(driver.getCurrentUrl().contains("profile.php#login"),
                "Click on LogIn Page and I am not redirected to LogIn Page");

        homeButoon();
        assertTrue(driver.getCurrentUrl().equals("https://katalon-demo-cura.herokuapp.com/"),
                "Click on Home Button and I am not redirected to Home Page from Login Page");

        loginbutton();
        loginSteps("John Doe", "ThisIsNotAPassword");

        assertTrue(driver.getCurrentUrl().contains("#appointment"),
                "After LogIn the page is not redirected to appointment");
        homeButoon();
        assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/",
                "Click on Home Page and I am not redirected to Home Page");

        historyButton();
        assertTrue(driver.getCurrentUrl().contains("history.php#history"),
                "Click on History Button  and I am not redirected to History Page from menu");
        homeButoon();
        assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/",
                "Click on Home Page and I am not redirected to Home Page");

        profileButton();
        assertTrue(driver.getCurrentUrl().contains("profile.php#profile"),
                "Click on Profile Button and I am not redirected to Profile Page");
        homeButoon();
        assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/",
                "Click on Home Page and I am not redirected to Home Page");

        logoutButton();
        assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/",
                "Click on LogOut and I am not redirected to Home Page");
    }
}
