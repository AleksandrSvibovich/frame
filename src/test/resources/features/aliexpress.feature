Feature: simple case on aliexpress

  Scenario: 1. Check that main aliexpress page is opened and search is work well
    Given User is on Aliexpress home page
    And User enter "Laser free shipping" value in to "Search Field" field
    When User clicks on "Search Button" element
    Then Verify that second "Product" contains "Laser" text
    Then Verify that "Product" contains "Laser" text

  Scenario: 2. Check that main aliexpress page is opened and search is work well
    Given User is on Aliexpress home page
    And User enter "Iphone" value in to "Search Field" field
    When User clicks on "Search Button" element
    Then Verify that second "Product" contains "iPhone" text
    Then Verify that "Product" contains "iPhone" text

  Scenario: 3. Check that main aliexpress page is opened and search is work well test
    Given User is on Aliexpress home page