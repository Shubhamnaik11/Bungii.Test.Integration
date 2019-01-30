@ios
Feature: Payment page
  As a Bungii customer
  I Should able to add/remove/change payment card

  Background:
    Given I am on Customer logged in Home page

  @regression
  Scenario Outline: As Bungii customer I should not be able to add invalid card , <Scenario> Scenario
    When I Select "PAYMENT" from Customer App menu
    Then I should be navigated to "PAYMENT" screen
    When I click "Add new" button on "PAYMENT" screen
    And I enter <CardNo> and <Expiry> on Card Details page
    Then I should see <Expected Message> on Payment page
    And I click "Cancel" button on "PAYMENT" screen

    Examples:
      | Scenario       | CardNo           | Expiry | Expected Message |
      | INVALID_EXPIRY | 4242424242424242 | 12/02  | "invalid expiry" |
      | INVALID_CARD   | 1111111111111111 | 12/22  | "invalid card"   |

  @regression
  Scenario Outline: As Bungii customer I should able to add New Card , <Scenario> Scenario
    When I Select "PAYMENT" from Customer App menu
    Then I should be navigated to "PAYMENT" screen
    Then PAYMENT page should be properly displayed
    When I click "Add new" button on "PAYMENT" screen
    And I enter <CardNo> and <Expiry> on Card Details page
    And I click "ADD PAYMENT METHOD" button on "PAYMENT" screen
    Then I should see "new card" on Payment page

    Examples:
      | Scenario           | CardNo           | Expiry |
      | ValidCard Discover | 6011111111111117 | 12/22  |
      | ValidCard Visa     | 4242424242424242 | 12/22  |

  @regression
  Scenario: As Bungii customer I should able to delete card
    When I Select "PAYMENT" from Customer App menu
    Then I should be navigated to "PAYMENT" screen
    When I swipe "other" card on the payment page
    And I tap on "Delete" on Payment page
    Then Alert message with Delete Warning text should be displayed
    And I accept Alert message
    Then I should see "the card has been deleted" on Payment page


  @regression
  Scenario: As Bungii customer I should able to change default card
    When I Select "PAYMENT" from Customer App menu
    Then I should be navigated to "PAYMENT" screen
    Then I get "current" default card
    And I tap on "Other card" on Payment page
    And I tap on "Checkbox" on Payment page
    When I click "SAVE" button on "PAYMENT" screen
    Then I should see "new default card" on Payment page

  @regression
  Scenario Outline:  As new Bungii customer without payment card , Payment page should display add Payment card message and Image .
    When I Select "LOGOUT" from Customer App menu
    Then I should be navigated to "LOG IN" screen
    And I enter Username :<Username> and  Password :<Password>
    And I click "Log In" button on "Log In" screen
    Then User should be successfully logged in to the application
    When I Select "PAYMENT" from Customer App menu
    Then "Add New Card" message should be displayed on "PAYMENT" page
    Then "Add Image" should be present in "PAYMENT" screen
    Then "ADD" should be present in "PAYMENT" screen
    When I Select "LOGOUT" from Customer App menu

    Examples:
      | Scenario          | Username       | Password       |
      | New_Register_User | {with no card} | {with no card} |
