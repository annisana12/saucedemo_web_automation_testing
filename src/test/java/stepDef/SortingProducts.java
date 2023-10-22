package stepDef;

import helper.ScenarioContext;
import helper.SortHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SortingProducts {
    WebDriver driver;

    @And("user click filter dropdown")
    public void user_click_filter_dropdown() {
        driver = ScenarioContext.get().getDriver();
        driver.findElement(By.className("product_sort_container")).click();
    }

    @And("user select (.*) option$")
    public void user_select_option_option(String option) {
        switch (option) {
            case "Name Z to A" ->
                    driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[2]")).click();
            case "Price low to high" ->
                    driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[3]")).click();
            case "Price high to low" ->
                    driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[4]")).click();
            default ->
                    driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[1]")).click();
        }
    }

    @Then("user see products sorted in (.*) order by its (.*)$")
    public void user_see_products_sorted_in_order_order_by_its_category(String order, String category) {
        SortHelper sortHelper = new SortHelper();
        boolean isInOrder = sortHelper.compareProducts(category, order, driver);
        Assert.assertTrue(isInOrder);
    }
}
