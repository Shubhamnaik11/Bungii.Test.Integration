Feature: Menu_Payment
Scenarios on Payment Methods
  @Android
  Scenario: Cust_Menu_Payment_NoPaymentMethodExists
    Given I am logged in as "newly registered" customer
    When I tap on "Menu" > "Payment" link
    Then I should see "message when no payment methods exist" on Payment page
  @Android
  Scenario: Cust_Menu_Payment_SetAsDefault
    Given I am logged in as "my" customer
    When I tap on "Menu" > "Payment" link
    And I tap on "the 2nd payment method" on Payment page
    And I tap on "Set as default payment mode" on Payment page
    And I tap on "Save" on Payment page
    Then I should see "default payment set" on Payment page
  @Android
  Scenario: Cust_Menu_Payment_DeletePaymentMethod
    Given I am logged in as "my" customer
    When I tap on "Menu" > "Payment" link
    When I swipe "2nd" card on the payment page
    And I tap on "Delete" on Payment page
    Then I should see "the card has been deleted" on Payment page
  @Android
  Scenario: Cust_Menu_Payment_AddPaymentMethod_Valid
    Given I am logged in as "newly registered" customer
    When I tap on "Menu" > "Payment" link
    When I tap on "Add" on Payment page
    And I tap on "Credit or Debit Card" on Payment page
    And I enter "valid card number" on Card Details page
    And I enter "valid expiry date" on Card Details page
    And I tap on "Add Card" on Payment page
    Then I should see "the card has been added" on Payment page
  @Android
  Scenario: Cust_Menu_Payment_AddPaymentMethod_Invalid
    Given I am logged in as "newly registered" customer
    When I tap on "Menu" > "Payment" link
    When I tap on "Add" on Payment page
    And I tap on "Credit or Debit Card" on Payment page
    And I enter "invalid card number" on Card Details page
    Then I should see "invalid card error" on Payment page