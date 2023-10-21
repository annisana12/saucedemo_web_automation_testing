Feature: Checkout products
  Scenario: checkout all products in cart
    Given the user is on the cart page with these products displayed:
      | Sauce Labs Bike Light   |
      | Sauce Labs Backpack     |
      | Sauce Labs Bolt T-Shirt |
    When user click Checkout
    And user enter valid buyer information
    And user click Continue
    Then user redirected to checkout overview page
    And user see correct total price with tax
    When user click Finish
    Then order successfully made