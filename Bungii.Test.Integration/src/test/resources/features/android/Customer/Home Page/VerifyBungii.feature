@android

#These feature will run in kansas geofence
Feature: VerifyBungii

  Background:
    Given I am on customer Log in page
    And I am logged in as "Testcustomertywd_appleand_B Android" customer
  @regression1
  Scenario: Verify that correct trip details are displayed on the grey bar of the Estimate screen.
    When I Switch to "customer" application on "same" devices
    And I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    Then I should see "all elements" on Bungii estimate
    
  @regression1
  Scenario: Verify that Estimated Cost value reads $0.00 as default initially before selection of load + unload time.
    When I Switch to "customer" application on "same" devices
    And I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    Then I should see "zero estimated cost" on Bungii estimate

  @regression1
  Scenario: Verify that the Estimated cost on the grey bar is updated on updating load/unload time and promo code.
    When I Switch to "customer" application on "same" devices
    And I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "15 mins"
    And I tap on "Promo code value" on Estimate screen
    And I add "PROMOTER TYPE PROMO" PromoCode
    And I tap "Add" on Save Money page
    Then I should able to see expected promo code in available promo code
    And I tap on "Back" icon of page
    Then I should see "estimated cost" on Bungii estimate
    
  @regression1
  Scenario: Verify that four masked characters are displayed before the last four characters of Payment Mode.
    When I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "Payment" link
    Then I should see "masked card number" on Payment page

  @regression1
  Scenario: Verify that clicking on Details field on the Estimate screen opens a blank text box.
    When I Switch to "customer" application on "same" devices
    And I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I tap on "Details" on Estimate screen
    Then I should see blank textbox
    When I enter "text" in Additional Notes field
    Then the "remaining characters value" should change
    When I enter "500 characters" in Additional Notes field
    And I click on "ADD NOTE" button
    Then "Estimate" page should be opened
    When I enter "1 more character" in Additional Notes field
    Then the "remaining characters value= 0" should change
