Feature: Item filtering test

  Scenario: user is able to filter items
    Given user opens lv.sportsdirect.com page
    And user selects currency: EUR
    When user selects mens in main category
    And user selects footwear in sub category
    And user selects shoes from subcategory list
    And user selects Firetrap brand
    And user selects Skechers brand
    And user selects a price range between 30 and 60
    Then selected brand items in price range of 30 and 60 should be displayed
    And user closes the browser


#  Create web test which would check whether item filter on
#  sportsdirect.com is working properly. Open mens shoes section and filter
#  by brands "Skechers" and "Firetrap" and price in range      30 - 60 EUR. Check items are correctly filtered!