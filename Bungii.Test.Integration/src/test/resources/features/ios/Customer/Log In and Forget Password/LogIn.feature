@LOGIN_ALL
Feature: Log In
  I want to use this template for my feature file

  Background: 
#    Given I am on the "LOG IN" page

  @LOGIN_1
  Scenario Outline: As Bungii customer , I should able to login to application using valid password
    And I enter valid <Username> and <Password> as per below table
 #   And I click "Log In" button on "Log In" screen
 #   Then User should be successfully logged in to the application

    Examples: 
      | Sr | Username   | Password |
      |  1 | 9403960188 | Cci12345 |

  @LOGIN_2
  Scenario Outline: As Bungii customer I should not able login to application using invalid details.  Scenario:<Scenario>
    And I enter valid <Username> and <Password> as per below table
    And I click "Log In" button on "Log In" screen
    Then Alert message with <Expected Message> text should be displayed
    And I accept Alert message
    Then I should be navigated to "LOG IN" screen
    
 #   And I click "SIGN UP" button on "Log In" screen

    Examples: 
      | Scenario                | Username   | Password | Expected Message |
      | INVALID PASSWORD        | 9403960188 | Cci1234  | INVALID_PASSWORD |
      | EMPTY PASSWORD          | 9403960188 | <BLANK>  | EMPTY_FIELD      |
      | EMPTY USERNAME PASSWORD | <BLANK>    | <BLANK>  | EMPTY_FIELD      |
      | EMPTY USERNAME          | <BLANK>    | Cci12345 | EMPTY_FIELD      |
