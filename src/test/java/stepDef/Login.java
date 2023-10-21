package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("user at login page")
    public void user_at_login_page() {
        WebDriverManager.chromedriver().setup(); // setup chrome driver automatically using web driver manager

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @When("user enter {string} in username input field")
    public void user_enter_in_username_input_field(String username) {
        driver.findElement(By.id("user-name")).sendKeys(username);
    }

    @And("user enter {string} in password input field")
    public void user_enter_in_password_input_field(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("user click login button")
    public void user_click_login_button() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("user see product list")
    public void user_see_product_list() {
        String title = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals("Products", title);
    }

    @Then("user see error message")
    public void user_see_error_message() {
        driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).isDisplayed();
    }
}
