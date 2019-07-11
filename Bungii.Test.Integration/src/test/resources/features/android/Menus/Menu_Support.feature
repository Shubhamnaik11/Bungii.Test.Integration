@android
@S29READY
@update
Feature: Menu_Support
  Test scenarios related to Support page

  Background:
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Support" link

  @regression
  Scenario:As Bungii customer I want to Ask question using support menu
    Then "Support" page should be opened
    And The user should see "support question" on Support page
    When I enter "valid" text in Support field
    And I tap "Send" on Support page
    Then The user should see "snackbar validation" on Support page

  @regression
  Scenario: Bungii customer I dont enter any question and click on send button
    And I enter "space" text in Support field
    Then The user should see "Send button disabled" on Support page
    And The user should see "validation message" on Support page