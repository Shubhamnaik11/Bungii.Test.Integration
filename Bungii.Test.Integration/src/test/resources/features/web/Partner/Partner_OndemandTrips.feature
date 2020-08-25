@web
  Feature: Partner Ondemand Trips

    Background:
      Given I am logged in as Partner User on Partner Portal

    @regression
    @sanity
    Scenario: Verify that Partner can create Solo ondemand bungii Trip
      When I request for  bungii for given pickup and drop location
        | Driver | Pickup Address  | Delivery Address             |Load Unload Time|
        | Solo   | Margoa Railway  | Patto Centre,Panjim          |15 minutes      |
      And I select <Pickup Date> and <Pickup Time>
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
      And I click on "Schedule Bungii" button
      Then I should see bungii trip success message on "Done" screen
  
    @regression
    @sanity
    Scenario: Verify that Partner can create Duo ondemand bungii Trip
      When I request for  bungii for given pickup and drop location
        | Driver | Pickup Address  | Delivery Address             |Load Unload Time|
        | Duo   | Margoa Railway  | Patto Centre,Panjim          |15 minutes      |
      And I select <Pickup Date> and <Pickup Time>
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
      And I click on "Schedule Bungii" button
      Then I should see bungii trip success message on "Done" screen

      Scenario: Verify Start Over functionality for Bungii Trip
        When I request for  bungii for given pickup and drop location
          | Driver | Pickup Address  | Delivery Address             |Load Unload Time|
          | Duo   | Margoa Railway  | Patto Centre,Panjim          |15 minutes      |
        And I click on "Start Over" button
        Then I should see all values get reset to default

