@android
@S29READY

Feature: Menu_Payment
Scenarios on Payment Methods

  @regression
  Scenario: Cust_Menu_Payment_NoPaymentMethodExists
    Given I am on customer Log in page
    And I am logged in as "newly registered" customer
    When I tap on "Menu" > "Payment" link
    Then I should see "message when no payment methods exist" on Payment page
    And I tap on "Menu" > "Logout" link

  @regression
  Scenario: As Bungii Customer I should able to change my default payment card
    Given I am on customer Log in page
    And I am logged in as "valid" customer
    When I tap on "Menu" > "Payment" link
    And I tap on "the 2nd payment method" on Payment page
    And I tap on "Set as default payment mode" on Payment page
    And I tap on "Save" on Payment page
    Then I should see "default payment set" on Payment page
    And I tap on "Menu" > "Logout" link

  @regression
  Scenario:  As Bungii Customer I should able to delete my payment card except default card
    Given I am logged in as "valid" customer
    When I tap on "Menu" > "Payment" link
    And I swipe "default" card on the payment page
    Then I should see "no delete button" on Payment page
    When I swipe "2nd" card on the payment page
    And I tap on "Delete" on Payment page
    Then I should see "the card has been deleted" on Payment page
    And I tap on "Menu" > "Logout" link


  @regression
  Scenario Outline:  As Bungii Customer I should able to add payment card
    Given I am logged in as "valid" customer
    When I tap on "Menu" > "Payment" link
    And I tap on "Add New" on Payment page
    And I tap on "Credit or Debit Card" on Payment page
    And I enter "<Card Detail>" on Card Details page
    And I enter "<Card Expiry>" on Card Details page
    And I tap on "Add Card" on Payment page
    Then I should see "the card has been added" on Payment page
    And I tap on "Menu" > "Logout" link
    Examples:
      | Scenario       | Card Detail                | Card Expiry       |
      | VALID_discover | valid discover card number | valid expiry date |
      | VALID_visa     | valid visa card number     | valid expiry date |

  @regression
  Scenario:  As Bungii Customer I should not able to add invalid payment Card
    Given I am logged in as "valid" customer
    When I tap on "Menu" > "Payment" link
    And I tap on "Add New" on Payment page
    And I tap on "Credit or Debit Card" on Payment page
    And I enter "invalid card number" on Card Details page
    Then I should see "invalid card error" on Payment page


  @regression
  Scenario:  As Bungii Customer I should not be able to add invalid expiry
    Given I am logged in as "valid" customer
    When I tap on "Menu" > "Payment" link
    And I tap on "Add New" on Payment page
    And I tap on "Credit or Debit Card" on Payment page
    And I enter "valid card number" on Card Details page
    Then I should see "no option to add previous year" on Payment page

