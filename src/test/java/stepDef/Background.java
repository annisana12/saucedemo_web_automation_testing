package stepDef;

import helper.CartHelper;
import helper.LoginHelper;
import helper.ScenarioContext;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class Background {
    @Given("user at product list page")
    public void user_at_product_list_page() {
        LoginHelper loginHelper = new LoginHelper();
        WebDriver driver = loginHelper.success_login(ScenarioContext.get().getDriver());
        ScenarioContext.get().setDriver(driver);
    }

    @Given("the user is on the cart page with these products displayed:")
    public void the_user_is_on_the_cart_page_with_these_products_displayed(List<String> products) {
        LoginHelper loginHelper = new LoginHelper();
        WebDriver driver = loginHelper.success_login(ScenarioContext.get().getDriver());
        ScenarioContext.get().setDriver(driver);

        CartHelper cartHelper = new CartHelper();
        cartHelper.addProductsAndDisplayCart(products, driver);
    }
}
