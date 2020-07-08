@web
  Feature: Partner_Delivery Details

    Background:
    Given I logged in as Partner User on Partner Portal
      When I request for  bungii for given pickup and drop location
        | Driver | Pickup Address  | Delivery Address             |Load Unload Time|
        | Solo   | Margoa Railway  | Patto Centre,Panjim          |15 minutes      |
      And I select <Pickup Date> and <Pickup Time>
      And I click "Get Estimate" button on "Get Estimate" screen
      Then I should see "Estimated Cost" on "Get Estimate" screen
      And I click on "Continue" button on "Get Estimate" screen

      Scenario: Verify all the fields on Delivery Details Screen
        When I see the "Delivery Details" screen
        And I enter following details on "Delivery Details" screen
          |Items To Deliver|Customer Name|Customer Mobile|Pickup Contact Name|Pickup Contact Phone|
          |Furniture       |Test Gopal   |9899999359     |Test Pickup        |9999999359          |
        And I Select Customer Card as Payment Method
        And I enter Card No:<CardNo> and Expiry :<Expiry> on Card Details page
        And I enter postal code :<Postal Code> and Cvv: <Cvv> on Card Details page
        And I click on "Schedule Bungii" button
        Then I should see bungii trip success message on "Done" screen

      Scenario: Verify that "Items To Deliver" is mandatory field on Delivery Details Screen
        When I see the "Delivery Details" screen
        And I click on "Schedule Bungii" button
        Then I should "see validations message for blank Items To Deliver field"

      Scenario: Verify that "Customer Name" is mandatory field on Delivery Details Screen
        When I see the "Delivery Details" screen
        And I click on "Schedule Bungii" button
        Then I should "see validations message for blank Customer Name field"

      Scenario: Verify that "Customer Mobile" is mandatory field on Delivery Details Screen
        When I see the "Delivery Details" screen
        And I click on "Schedule Bungii" button
        Then I should "see validations message for blank Customer Mobile field"

    Scenario: Verify that "Pickup Contact Name" is mandatory field on Delivery Details Screen
      When I see the "Delivery Details" screen
      And I click on "Schedule Bungii" button
      Then I should "see validations message for blank Pickup Contact Name field"

    Scenario: Verify that "Pickup Contact Phone" is mandatory field on Delivery Details Screen
      When I see the "Delivery Details" screen
      And I click on "Schedule Bungii" button
      Then I should "see validations message for blank Pickup Contact Phone field"