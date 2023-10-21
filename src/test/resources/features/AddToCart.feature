Feature: Add Product To Cart
  Background:
    Given user at product list page

  Scenario: Add one product to cart
    When user add "Sauce Labs Backpack" to cart
    Then the total items in cart icon should be updated
    When user clicks the cart icon
    Then "Sauce Labs Backpack" should be displayed in the cart

  Scenario: Add more than one product
    When user add this products to cart:
      | Sauce Labs Bike Light   |
      | Sauce Labs Backpack     |
      | Sauce Labs Bolt T-Shirt |
    Then the total items in cart icon should be updated
    When user clicks the cart icon
    Then all this products should be displayed in the cart:
      | Sauce Labs Bike Light   |
      | Sauce Labs Backpack     |
      | Sauce Labs Bolt T-Shirt |