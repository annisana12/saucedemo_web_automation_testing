package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SortHelper {
    public boolean compareProducts(String category, String order, WebDriver driver) {
        String className = "inventory_item_name";

        if (category.equals("price")) {
            className = "inventory_item_price";
        }

        List<WebElement> elements = driver.findElements(By.className(className));
        String prevName = "";
        double prevPrice = 0;
        boolean isInOrder = true;

        if (elements.size() != 0) {
            for (WebElement element : elements) {
                String currentData = element.getText();

                if (category.equals("price")) {
                    String priceString = currentData.replace("$", "");
                    double currentPrice = Double.parseDouble(priceString);

                    if (prevPrice == 0) {
                        prevPrice = currentPrice;
                        continue;
                    }

                    if ((order.equals("ascending") && prevPrice > currentPrice)
                            || (order.equals("descending") && prevPrice < currentPrice)
                    ) {
                        isInOrder = false;
                        break;
                    }
                } else {
                    if (prevName.isEmpty()) {
                        prevName = currentData;
                        continue;
                    }

                    int compareResult = prevName.compareTo(currentData);

                    if ((order.equals("ascending") && compareResult > 0)
                            || (order.equals("descending") && compareResult < 0)
                    ) {
                        isInOrder = false;
                        break;
                    }
                }
            }
        }

        return isInOrder;
    }
}
