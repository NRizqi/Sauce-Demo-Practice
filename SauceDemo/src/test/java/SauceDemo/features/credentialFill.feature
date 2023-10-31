Feature: credential filling and finish buy

  @TC005
  Scenario Outline: user can fill their information and finish buy
    Given user login with registered username and password
    When user finish login
    And user add item
    And user click cart icon
    And user click checkout
    And user at information fill page
    And user fill <firstName>, <lastName>, and <postalCode>
    And user continue
    Then user credential fill is <status>

    Examples:
      |firstName  |lastName |postalCode|status    |
      |Abe        |Mono     |12345     |success   |
      |Abe        |Mono     |          |failed    |
      |Abe        |         |12345     |failed    |
      |           |Mono     |12345     |failed    |
      |           |         |12345     |failed    |