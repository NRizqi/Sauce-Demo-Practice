Feature: Login
  @TC001
  Scenario Outline: login status
   Given user at login page
   When user input registered <username> and <password>
   And user click login button
   Then User login is <status>

   Examples:
   |username      |password        |status   |
   |standard_user |secret_sauce    |success  |
   |standard_user |secret_password |failed   |
   |normal_user   |secret_sauce    |failed   |
   |normal_user   |secret_password |failed   |
