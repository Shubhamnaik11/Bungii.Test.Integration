@web
  Feature: Partner_Estimate

    Background:
      Given I am logged in as Partner User on Partner Portal

      Scenario:Verify If Partner User Cancel OnDemand Bungii After Entering Delivery Details Then He Is Navigated back To Get Estimate Screen
        When I request for  bungii for given pickup and drop location
          | Driver | Pickup Address  | Delivery Address             |Load Unload Time|
          | Solo   | Margoa Railway  | Patto Centre,Panjim          |15 minutes      |
        And I click "Get Estimate" button on "Get Estimate" screen
        Then I should see "Estimated Cost" on "Get Estimate" screen
        And I click on "Continue" button on "Get Estimate" screen
        Then I should be navigated to "Delivery Details" screen
        When I enter following details on "Delivery Details" screen
        |Items To Deliver|Customer Name|Customer Mobile|Pickup Contact Name|Pickup Contact Phone|
        |Furniture       |Test Gopal   |9899999359     |Test Pickup        |9999999359          |
        And I Select Customer Card as Payment Method
        And I enter Card No:<CardNo> and Expiry :<Expiry> on Card Details page
        And I enter postal code :<Postal Code> and Cvv: <Cvv> on Card Details page
        And I click on "Back to Estimate" button
        And Trip Information should be correctly displayed on "Request Bungii" screen

          | CardNo        | Expiry | Postal Code       | Cvv       |
          | VISA CARD     | 12/22  | VALID POSTAL CODE | VALID CVV |


    Scenario:Verify If Partner User Cancel OnDemand Bungii Before Entering Delivery Details Then He Is Navigated back To Get Estimate Screen
      When I request for  bungii for given following details
        | Driver | Pickup Address  | Delivery Address             |Load Unload Time|
        | Solo   | Margoa Railway  | Patto Centre,Panjim          |15 minutes      |
      And I click "Get Estimate" button on "Get Estimate" screen
      Then I should see "Estimated Cost" on "Get Estimate" screen
      And I click on "Continue" button on "Get Estimate" screen
      Then I should be navigated to "Delivery Details" screen
      And I click on "Back to Estimate" button
      And Trip Information should be correctly displayed on "Request Bungii" screen

    Scenario:Verify Validation message is display for Mandatory fields on "Get Estimate" screen
      When I click "Get Estimate" button on "Get Estimate" screen
      Then I should see "see validation message for mandatory fields"

    Scenario: Verify information icon display correct information on "Get Estimate" screen
       When I click on "WHAT’S NEEDED?" information icon
       Then I should see correct information for "WHAT’S NEEDED?"
       And I click on "Delivery Address" information icon
       Then I should see correct information for "Delivery Address"
       And I click on "Load/Unload Time" information icon
       Then I should see correct information for "Load/Unload Time"
       And I click on "PickUp Date" information icon
       Then I should see correct information for "PickUp Date"

    Scenario:Verify that Get Estimate cost get recalculate on changing the Load/Upload Time
      When I request for  bungii for given following details
        | Driver | Pickup Address  | Delivery Address             |Load Unload Time|
        | Solo   | Margoa Railway  | Patto Centre,Panjim          |15 minutes      |
      And I click "Get Estimate" button on "Get Estimate" screen
      Then I should see "Estimated Cost" on "Get Estimate" screen
      Then I change the "Load Unload Time"
      And I click "Get Estimate" button on "Get Estimate" screen
      Then Estimate Cost price should get recalculate

    Scenario: Verify that Get Estimate cost get recalculated on changing the Delivery Address
      When I request for  bungii for given following details
        | Driver | Pickup Address  | Delivery Address             |Load Unload Time|
        | Solo   | Margoa Railway  | Patto Centre,Panjim          |15 minutes      |
      And I click "Get Estimate" button on "Get Estimate" screen
      And I change the Delivery Address on "Get Estimate" screen
      Then Estimate Cost price should get recalculate

    Scenario:Verify that clearing Pickup address clears all other fields on "Get Estimate" screen
      When I request for  bungii for given following details
        | Driver | Pickup Address  | Delivery Address             |Load Unload Time|
        | Solo   | Margoa Railway  | Patto Centre,Panjim          |15 minutes      |
      And I click "Get Estimate" button on "Get Estimate" screen
      And I change the Pickup Address on "Get Estimate" screen
      Then Check that all fields on "Get Estimate" screen get clear
