Feature: Sorting products
  Scenario Outline: Ensure products sorting functionality
    Given user at product list page
    And user click filter dropdown
    And user select <option> option
    Then user see products sorted in <order> order by its <category>

    Examples:
    | option                | order         | category  |
    | Name A to Z           | ascending     | name      |
    | Name Z to A           | descending    | name      |
    | Price low to high     | ascending     | price     |
    | Price high to low     | descending    | price     |




