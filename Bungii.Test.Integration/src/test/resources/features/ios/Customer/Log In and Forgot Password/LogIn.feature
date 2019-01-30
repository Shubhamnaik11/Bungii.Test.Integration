@ios
Feature: Log In
  As a Bungii Customer I should be allowed to login only using valid credential

  Background:
    Given I am on the "LOG IN" page

  @regression
  Scenario Outline: As Bungii customer I should not able login to application using invalid details.  Scenario:<Scenario>
    And I enter Username :<Username> and  Password :<Password>
    And I click "Log In" button on "Log In" screen
    Then Alert message with <Expected Message> text should be displayed
    And I accept Alert message
    Then I should be navigated to "LOG IN" screen

    Examples:
      | Scenario                | Username | Password | Expected Message |
      | INVALID PASSWORD        | {VALID}  | Cci1234  | INVALID_PASSWORD |
      | EMPTY PASSWORD          | {VALID}  | <BLANK>  | EMPTY_FIELD      |
      | EMPTY USERNAME PASSWORD | <BLANK>  | <BLANK>  | EMPTY_FIELD      |
      | EMPTY USERNAME          | <BLANK>  | Cci12345 | EMPTY_FIELD      |

  @regression
  Scenario: As Bungii customer , I should able to login to application using valid password
    When I enter Username :{VALID}  and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    Then User should be successfully logged in to the application