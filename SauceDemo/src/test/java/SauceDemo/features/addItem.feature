Feature: add item

  @TC002
  Scenario Outline: user can add item to cart
    Given user login using valid <username> and <password>
    When user already at homepage
    And user click add to cart button
    And add button change to remove button
    And count number show on cart button
    Then add item is success

    Examples:
      |username     |password     |
      |standard_user|secret_sauce |

  @TC003
  Scenario Outline: user can add item to cart
    Given user login using valid <username> and <password>
    When user already at homepage
    And user click any item name
    And user directed to item page
    And user click add to cart button
    And add button change to remove button
    And count number show on cart button
    Then add item is success

    Examples:
      |username     |password     |
      |standard_user|secret_sauce |
