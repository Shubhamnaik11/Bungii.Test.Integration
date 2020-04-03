@android
Feature: CustomerMenu
  In Bungii Customer
  As a logged in customer
  I want to check all menu links

  Background:
    Given I am on Customer logged in Home page

  @regression
  Scenario: Verify Customer App Menu Item Navigations
    When I tap on "Menu" > "Home" link
    Then "Home" page should be opened
    When I tap on "Menu" > "SIGN UP TO DRIVE" link
    Then "bungii.com" page should be opened
    When I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "Logout" link
    Then "Login" page should be opened
#failing due to BCKD-1103
  #@regression
  Scenario: Verify Device Token De-Registration On Customer Logout
    Then Customer active flag should be "1"
    And I tap on "Menu" > "Logout" link
    Then Customer active flag should be "0"

  @regression
  Scenario: Verify Customer FAQ Menu
    When I tap on "Menu" > "FAQ" link
    Then "FAQ" page should be opened
  #  When I tap on "first question" on FAQ page
    Then I should see "first answer dropdown open" on FAQ page
    When I tap on "expanded first question" on FAQ page
    Then I should see "first answer dropdown close" on FAQ page
    And I should see "last question" on FAQ page
  #  And I should see "social media links" on FAQ page

  @regression
  Scenario: Verify Customer Account Menu
    Given I am logged in as "valid" customer
    When I tap on "Menu" > "Account" link
    Then "Account" page should be opened
    And logged in Customer details should be displayed

  @regression
  Scenario: Verify Customer Payment Menu
    When I tap on "Menu" > "Payment" link
    Then "Payment" page should be opened

  @regression
  Scenario: Verify Customer Support Menu
    When I tap on "Menu" > "Support" link
    Then "Support" page should be opened

  @regression
  Scenario:Verify Customer Promos Menu
    When I tap on "Menu" > "Promos" link
    Then "Promos" page should be opened

    @regression1
    Scenario: Verify My Bungiis Menu
      When I tap on "Menu" > "My Bungiis" link
      Then "MY BUNGIIS" page should be opened
      And I click on "Scheduled" tab
      And I should see "Scheduled info" message displayed
      And I click on "Past" tab
      And I should see "Past info" message displayed