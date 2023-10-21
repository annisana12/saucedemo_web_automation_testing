package helper;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CartHelper {
    public void addProductToCart(String product, WebDriver driver) {
        String id = "add-to-cart-" + product.toLowerCase().replace(" ", "-");
        driver.findElement(By.id(id)).click();
    }

    public int getTotalProductsInCart(WebDriver driver) {
        // Selenium will wait for up to 3 seconds for elements to appear when using findElements
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        List<WebElement> elements = driver.findElements(By.className("shopping_cart_badge"));

        // After finding the elements, we reset the implicit wait to avoid affecting subsequent operations.
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        if (elements.size() != 0) {
            WebElement span = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));
            return Integer.parseInt(span.getText());
        } else {
            return 0;
        }
    }

    public boolean checkProductInCart(String searchProduct, WebDriver driver) {
        List<WebElement> elements = driver.findElements(By.className("inventory_item_name"));

        if (elements.size() != 0) {
            for (WebElement element : elements) {
                String displayedProduct = element.getText();

                if (displayedProduct.equals(searchProduct)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void addProductsAndDisplayCart(List<String> products, WebDriver driver) {
        if (products.size() != 0) {
            for (String product : products) {
                addProductToCart(product, driver);
            }
        }

        // Move to cart page
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();

        // Make sure user at cart page
        String title = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals("Your Cart", title);
    }

    public double calcAllProductsPriceInCart(WebDriver driver) {
        List<WebElement> qtyElements = driver.findElements(By.className("cart_quantity"));
        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));

        double total = 0;

        if (priceElements.size() != 0) {
            for (int i = 0; i < priceElements.size(); i++) {
                String priceString = priceElements.get(i).getText().replaceAll("[^0-9.]", "");
                double price = Double.parseDouble(priceString);

                String qtyString = qtyElements.get(i).getText();
                double qty = Double.parseDouble(qtyString);

                total += price * qty;
            }
        }

        return total;
    }
}
