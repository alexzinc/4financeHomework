Feature: password recovery test

  Scenario: user is able to recover his password
    Given user opens 10minutemail.info page
    And user gets an email
    Given user opens sportsdirect.com page
    And user signs up with following credentials:
      | email        | *email*     |
      | repeat email | *email*     |
      | title        | Mr          |
      | age          | 21          |
      | firstname    | *generated* |
      | lastname     | *generated* |
      | password     | *default*   |
      | password     | *default*   |
    And user logs out
    When user recovers his password
    Then user should receive his forgotten password
    And user is able to log in
    And user closes the browser


#  Write web test for password recovery on sportsdirect.com (here you      need email box, create one).