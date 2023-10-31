Feature: Checkout

  @TC004
  Scenario Outline: user can checkout item
    Given user login using registered <username> and <password>
    When user already login
    And user add item they want
    And user click cart button
    And user see their item
    And user click continue
    Then user at your information page

    Examples:
      |username     |password     |
      |standard_user|secret_sauce |