Feature: password recovery test

  Scenario: user is able to recover his password using 10minute email
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
    When user recovers his password to: 10minutemail.info
    And user opens 10minutemail.info page
    And user opens received email in 10minutemail.info
    Then user should be able to set his new password to following:
      | password | *generated* |
    And user logs out
    And user logs in with login: testuser322 and new password
    And user closes the browser

  Scenario: user is able to recover password using inbox.lv
    Given user opens sportsdirect.com page
    And user recovers his password to: inbox.lv
    And user logs in to inbox.lv via login: testuser322 and password: test12345
    And user opens received email in inbox.lv
    Then user should be able to set his new password to following:
      | password | *generated* |
    And user logs out
    When user logs in with login: testuser322@inbox.lv and new password
    Then user is logged in
    And user closes the browser

#  Write web test for password recovery on sportsdirect.com (here you      need email box, create one).