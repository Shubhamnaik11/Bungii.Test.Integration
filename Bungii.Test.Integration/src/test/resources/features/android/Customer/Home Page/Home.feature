@android
Feature: Customer Home screen

  Background:
    Given I am on Customer logged in Home page
  @regression
  Scenario: To Verify clear text button on Pick up and Drop location
    When I tap on "Menu" > "Home" link
    And I select "Pick up" location
    Then "Pick up" address should be displayed in text box
    And I tap "Pick Up Clear Text" on Home page
    And I tap "My location" on Home page
    Then current location should be present as pickup location
    When I select "Pick up" location
    Then "Pick up" address should be displayed in text box
    When I select "Drop" location
    Then "Drop" address should be displayed in text box
    When I tap "Drop Clear Text" on Home page
    Then "Drop" address should be empty
    When I select "Drop" location
    Then "Drop" address should be displayed in text box
    When I tap "Pick Up Clear Text" on Home page
    And I tap "My location" on Home page
    Then current location should be present as pickup location
    And "Drop" address should be empty