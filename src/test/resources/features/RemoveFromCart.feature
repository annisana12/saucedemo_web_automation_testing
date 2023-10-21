Feature: Remove product from cart
  Scenario: Remove one product from cart page
    Given the user is on the cart page with these products displayed:
      | Sauce Labs Bike Light   |
      | Sauce Labs Backpack     |
      | Sauce Labs Bolt T-Shirt |
    When user remove "Sauce Labs Bike Light" from cart
    Then "Sauce Labs Bike Light" is no longer displayed in the cart
    And total items in cart icon should be updated