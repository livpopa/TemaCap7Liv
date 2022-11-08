import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;
import static org.testng.Assert.*;


public class Appointment extends Base {
    @Test(groups = "without_login")
    public void makeAppointmentWithoutLogin(){
        makeAppointmentButton();
        assertTrue(driver.getCurrentUrl().contains("profile.php#login"),
                "Appointment failed without added Credentials first");
        assertFalse(driver.getCurrentUrl().contains("#appointment"), "Appointment Button redirected to Appointment page even not being Logged");
    }

    @Test(groups = "with_login")
    public void appointmentFormCompletedCorrectly(){
        Select facility = new Select(driver.findElement(By.id("combo_facility")));

        WebElement hospitalReadmission = driver.findElement(By.id("chk_hospotal_readmission"));
        List<WebElement> healthcareProgram = driver.findElements(By.cssSelector(".radio-inline"));

        WebElement selectDate = driver.findElement(By.id("txt_visit_date"));
        WebElement commentBox= driver.findElement(By.name("comment"));
        WebElement bookAppointmentButton = driver.findElement(By.id("btn-book-appointment"));
        facility.selectByIndex(2);
        hospitalReadmission.isSelected();
        healthcareProgram.get(2).click();
        selectDate.sendKeys("30/12/2022");
        commentBox.sendKeys("Feedback");
        bookAppointmentButton.click();
        assertTrue(driver.getCurrentUrl().contains("appointment.php#summary"),"Appointment unsuccessful");
        assertEquals(driver.findElement(By.cssSelector("div[class=\"col-xs-12 text-center\"]:first-child h2")).getText(), "Appointment Confirmation",
                "Message did not display");

        WebElement confirmationMesage =   driver.findElement(By.cssSelector("div[class=\"col-xs-12 text-center\"]:first-child h2"));
        System.out.println(confirmationMesage.getText());
    }

    @Test(groups = "with_login")
    public void appointmentFormWithoutSelectingADate() {
        Select facility = new Select(driver.findElement(By.id("combo_facility")));

        WebElement hospitalReadmission = driver.findElement(By.id("chk_hospotal_readmission"));
        List<WebElement> healthcareProgram = driver.findElements(By.cssSelector(".radio-inline"));
        WebElement commentBox = driver.findElement(By.name("comment"));
        WebElement bookAppointmentButton = driver.findElement(By.id("btn-book-appointment"));

        facility.selectByIndex(2);
        hospitalReadmission.isSelected();
        healthcareProgram.get(0).click();
        commentBox.sendKeys("Good Feedback");
        bookAppointmentButton.click();
        assertFalse(driver.getCurrentUrl().contains("appointment.php#summary"), "Appointment successful even did not choice a date");
        String message = driver.findElement(By.id("txt_visit_date")).getAttribute("validationMessage");
        System.out.println(message);
        assertEquals(message, "Please fill out this field.", "date confirmation Message did not display");
    }

    @Test(groups = "with_login")
    public void makeAppointmentWithLettersInDateField() {
        Select facility = new Select(driver.findElement(By.id("combo_facility")));

        WebElement hospitalReadmission = driver.findElement(By.id("chk_hospotal_readmission"));
        List<WebElement> healthcareProgram = driver.findElements(By.cssSelector(".radio-inline"));

        WebElement selectDate = driver.findElement(By.id("txt_visit_date"));
        WebElement commentBox = driver.findElement(By.name("comment"));
        WebElement bookAppointmentButton = driver.findElement(By.id("btn-book-appointment"));
        facility.selectByIndex(1);
        hospitalReadmission.isSelected();
        healthcareProgram.get(1).click();
        selectDate.sendKeys("Test negative failed");
        commentBox.sendKeys("Test to verify if letters can be added in data-field");
        bookAppointmentButton.click();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(isElementPresent(By.cssSelector("div[class=\"col-xs-12 text-center\"]:first-child h2")), "Confirmation Message displayed even  apare desi am introdus date gresite in datefield." );
        softAssert.assertFalse(driver.getCurrentUrl().contains("appointment.php#summary"), "filed with wrong details and redirected to appointment confirmation page");
        softAssert.assertTrue(driver.getCurrentUrl().contains("#appointment"), "Wrong details used in data filed and I have been redirected from appointment page");
        driver.quit();
        softAssert.assertAll();
    }
}