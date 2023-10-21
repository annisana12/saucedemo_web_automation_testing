package stepDef;

import helper.CartHelper;
import helper.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Checkout {
    WebDriver driver;
    double grossPrice = 0;

    @When("user click Checkout")
    public void user_click_Checkout() {
        // Set Driver
        driver = ScenarioContext.get().getDriver();

        // Calculate total gross price
        CartHelper cartHelper = new CartHelper();
        grossPrice = cartHelper.calcAllProductsPriceInCart(driver);

        // Click checkout
        driver.findElement(By.id("checkout")).click();

        // Check if user is redirected to buyer information form
        String pageTitle = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals("Checkout: Your Information", pageTitle);
    }

    @And("user enter valid buyer information")
    public void user_enter_valid_buyer_information() {
        // Enter first name, last name, postal code
        driver.findElement(By.id("first-name")).sendKeys("Annisa");
        driver.findElement(By.id("last-name")).sendKeys("Nabila");
        driver.findElement(By.id("postal-code")).sendKeys("63391");
    }

    @And("user click Continue")
    public void user_click_Continue() {
        driver.findElement(By.id("continue")).click();
    }

    @Then("user redirected to checkout overview page")
    public void user_redirected_to_checkout_overview_page() {
        // Check if user redirected to check out overview page
        String pageTitle = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals("Checkout: Overview", pageTitle);
    }

    @And("user see correct total price with tax")
    public void user_see_correct_total_price_with_tax() {
        String taxString = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[7]")).getText().replaceAll("[^0-9.]", "");
        double tax = Double.parseDouble(taxString);

        String actualTotalPriceStr = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]")).getText().replaceAll("[^0-9.]", "");
        double actualTotalPrice = Double.parseDouble(actualTotalPriceStr);
        double expectedTotalPrice = grossPrice + tax;
        boolean isCorrectTotal = actualTotalPrice == expectedTotalPrice;

        Assert.assertTrue(isCorrectTotal);
    }

    @When("user click Finish")
    public void user_click_Finish() {
        driver.findElement(By.id("finish")).click();
    }

    @Then("order successfully made")
    public void order_successfully_made() {
        String pageTitle = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        String successMessage = driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2")).getText();

        Assert.assertEquals("Checkout: Complete!", pageTitle);
        Assert.assertEquals("Thank you for your order!", successMessage);
    }
}
