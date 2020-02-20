@android
Feature: Menu_Payment
Scenarios on Payment Methods

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
  Scenario: Cust_Menu_Payment_NoPaymentMethodExists
    Given I am on customer Log in page
    And I am logged in as "newly registered" customer
    When I tap on "Menu" > "Payment" link
    Then I should see "message when no payment methods exist" on Payment page
    And I tap on "Menu" > "Logout" link

  @sanity
  #commented this due to base to auto data issue
  @regression

  Scenario Outline:  As Bungii Customer I should able to delete my payment
    Given I am on Sign up page
    When I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
   # And I enter "ValidPercent" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    Then The user should be logged in

    When I tap on "Menu" > "Payment" link
    And I get the number of cards present
    And I tap on "Add" on Payment page
    And I tap on "Credit or Debit Card" on Payment page
    And I enter "<Card Detail>" on Card Details page
    And I enter "<Card Expiry>" on Card Details page
    And I enter "<CVV>" on Card Details page
    And I enter "<Postal Code>" on Card Details page

    And I tap on "Add Card" on Payment page
    Then I should see "the card has been added" on Payment page

    When I swipe "default" card on the payment page
    And I tap on "Delete" on Payment page
    Then I should see "message when no payment methods exist" on Payment page
    And I tap on "Menu" > "Logout" link
    Examples:
      | Scenario       | Card Detail                | Card Expiry       |CVV|Postal Code|
      | VALID_discover | valid discover card number | valid expiry date |valid cvv|valid postal code|

  @regression

  Scenario Outline:  As Bungii Customer I should able to add payment card
    Given I am logged in as "valid" customer
    When I tap on "Menu" > "Payment" link
    And I get the number of cards present
    And I tap on "Add New" on Payment page
    And I tap on "Credit or Debit Card" on Payment page
    And I enter "<Card Detail>" on Card Details page
    And I enter "<Card Expiry>" on Card Details page
    And I enter "<CVV>" on Card Details page
    And I enter "<Postal Code>" on Card Details page

    And I tap on "Add Card" on Payment page
    Then I should see "the card has been added" on Payment page
    And I tap on "Menu" > "Logout" link
    Examples:
      | Scenario       | Card Detail                | Card Expiry       |CVV|Postal Code|
      | VALID_discover | valid discover card number | valid expiry date |valid cvv|valid postal code|
      | VALID_visa     | valid visa card number     | valid expiry date |valid cvv|valid postal code|

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

