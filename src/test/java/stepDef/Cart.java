package stepDef;

import helper.CartHelper;
import helper.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class Cart {
    WebDriver driver;
    int initalTotalItems = 0;
    int totalProductsToAdd = 0;
    int totalProductsToRemove = 0;

    @When("user add {string} to cart")
    public void user_add_to_cart(String product) {
        // Set Driver
        driver = ScenarioContext.get().getDriver();

        // Cek initial total products in cart
        CartHelper cartHelper = new CartHelper();
        initalTotalItems = cartHelper.getTotalProductsInCart(driver);

        // Add product to cart
        cartHelper.addProductToCart(product, driver);
        totalProductsToAdd = 1;
    }

    @When("user add this products to cart:")
    public void user_add_this_products_to_cart(List<String> products) {
        // Set Driver
        driver = ScenarioContext.get().getDriver();

        // Cek initial total products in cart
        CartHelper cartHelper = new CartHelper();
        initalTotalItems = cartHelper.getTotalProductsInCart(driver);

        // Add product to cart
        if (products.size() != 0) {
            for (String product : products) {
                cartHelper.addProductToCart(product, driver);
            }
        }

        totalProductsToAdd = products.size();
    }

    @Then("the total items in cart icon should be updated")
    public void the_total_items_in_cart_icon_should_be_updated() {
        CartHelper cartHelper = new CartHelper();
        int actualTotalItems = cartHelper.getTotalProductsInCart(driver);
        int expectedTotalItems = initalTotalItems + totalProductsToAdd;

        // Assertion
        Assert.assertEquals(expectedTotalItems, actualTotalItems);
    }

    @When("user clicks the cart icon")
    public void user_clicks_the_cart_icon() {
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
    }

    @Then("{string} should be displayed in the cart")
    public void should_be_displayed_in_the_cart(String product) {
        // Make sure user at cart page
        String title = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals("Your Cart", title);

        // Make sure selected product displayed in cart
        CartHelper cartHelper = new CartHelper();
        boolean isProductDisplayed = cartHelper.checkProductInCart(product, driver);
        Assert.assertTrue(isProductDisplayed);
    }

    @Then("all this products should be displayed in the cart:")
    public void all_this_products_should_be_displayed_in_the_cart(List<String> products) {
        // Make sure user at cart page
        String title = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals("Your Cart", title);

        // Make sure selected product displayed in cart
        CartHelper cartHelper = new CartHelper();
        boolean isAllProductDisplayed = true;

        if (products.size() != 0) {
            for (String product : products) {
                isAllProductDisplayed = cartHelper.checkProductInCart(product, driver);
            }
        }

        Assert.assertTrue(isAllProductDisplayed);
    }

    @When("user remove {string} from cart")
    public void user_remove_from_cart(String product) {
        // Set Driver
        driver = ScenarioContext.get().getDriver();

        // Cek initial total products in cart
        CartHelper cartHelper = new CartHelper();
        initalTotalItems = cartHelper.getTotalProductsInCart(driver);

        // Remove product
        String id = "remove-" + product.toLowerCase().replace(" ", "-");
        driver.findElement(By.id(id)).click();

        totalProductsToRemove = 1;
    }

    @Then("{string} is no longer displayed in the cart")
    public void is_no_longer_displayed_in_the_cart(String product) {
        CartHelper cartHelper = new CartHelper();
        boolean isProductDisplayed = cartHelper.checkProductInCart(product, driver);

        Assert.assertFalse(isProductDisplayed);
    }

    @And("total items in cart icon should be updated")
    public void total_items_in_cart_icon_should_be_updated() {
        CartHelper cartHelper = new CartHelper();
        int actualTotalItems = cartHelper.getTotalProductsInCart(driver);
        int expectedTotalItems = initalTotalItems - totalProductsToRemove;

        // Assertion
        Assert.assertEquals(expectedTotalItems, actualTotalItems);
    }
}
