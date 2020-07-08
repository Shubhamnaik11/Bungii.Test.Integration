@web
  Feature: Partner_Estimate

    Background:
      Given I navigate to "Bungii Partner Portal" URL
      And I enter "valid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      Then I should "be logged in"

    @regression
      Scenario:Verify If Partner User Cancel OnDemand Bungii After Entering Delivery Details Then He Is Navigated back To Get Estimate Screen
        When I request for "Solo" Bungii trip in partner portal
          | Driver | Pickup_Address                                 | Delivery_Address                                        |Load_Unload_Time|
          | Solo   | 1735 Noriega St, San Francisco, CA, US, 94122  | 1600 Holloway Avenue, San Francisco, California 94132   |30 minutes      |
        And I select Pickup Date and Pickup Time
          |PickUp_Date  |PickUp_Time          |
          |Today+1      |5th quarter          |
        And I click "GET ESTIMATE" button on Partner Portal
        Then I should see "Estimated Cost"
        And I click "Continue" button on Partner Portal
        Then I should "see Delivery Details screen"
        When I enter following details on "Delivery Details" partner screen
          |Items_To_Deliver|Customer_Name|Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
          |Furniture       |Test Gopal   |9975960666     |Test Pickup        |9999999359          |
        And I Select "Customer Card" as Payment Method
        And I enter following Credit Card details on Partner Portal
          |CardNo   |Expiry |Postal_Code      |Cvv      |
          |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
        And I click "Back to Estimate" button on Partner Portal
        Then I should "see Get Estimate screen"

    @regression
    Scenario:Verify If Partner User Cancel OnDemand Bungii Before Entering Delivery Details Then He Is Navigated back To Get Estimate Screen
      When I request for "Solo" Bungii trip in partner portal in "washingtondc" geofence
        | Driver | Pickup_Address                                 | Delivery_Address                                        |Load_Unload_Time|
        | Solo   | 1735 Noriega St, San Francisco, CA, US, 94122  | 1600 Holloway Avenue, San Francisco, California 94132   |30 minutes      |
      And I select Pickup Date and Pickup Time
        |PickUp_Date  |PickUp_Time          |
        |Today+1      |5th quarter          |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      And I click "Back to Estimate" button on Partner Portal
      And I should "see Get Estimate screen"

    @regression
    Scenario:Verify Validation message is display for Mandatory fields on "Get Estimate" screen
      When I clear the existing pickup address details
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "see validation message for mandatory fields"

    @regression
    Scenario: Verify information icon display correct information on "Get Estimate" screen
       When I click on "WHAT’S NEEDED?" information icon and verify its text contents
       And I click on "Delivery Address" information icon and verify its text contents
       And I click on "Load/Unload Time" information icon and verify its text contents
       And I click on "PickUp Date" information icon and verify its text contents

    @regression
    Scenario:Verify that Get Estimate cost get recalculate on changing the Load/Upload Time
      When I request for "Solo" Bungii trip in partner portal in "washingtondc" geofence
        | Driver | Pickup_Address                                 | Delivery_Address                                        |Load_Unload_Time|
        | Solo   | 1735 Noriega St, San Francisco, CA, US, 94122  | 1600 Holloway Avenue, San Francisco, California 94132   |30 minutes      |
      And I select Pickup Date and Pickup Time
        |PickUp_Date  |PickUp_Time          |
        |Today+1      |5th quarter          |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I change the "Load Unload Time" and click on Get Estimate button
        |Load_Unload_Time|
        |15 minutes      |
      Then Estimate Cost should get recalculate

    @regression
    Scenario: Verify that Get Estimate cost get recalculated on changing the Delivery Address
      When I request for "Solo" Bungii trip in partner portal in "washingtondc" geofence
        | Driver | Pickup_Address                                 | Delivery_Address                                        |Load_Unload_Time|
        | Solo   | 1735 Noriega St, San Francisco, CA, US, 94122  | 1600 Holloway Avenue, San Francisco, California 94132   |30 minutes      |
      And I select Pickup Date and Pickup Time
        |PickUp_Date  |PickUp_Time          |
        |Today+1      |5th quarter          |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I change the "Delivery Address" and click on Get Estimate button
        |Delivery_Address                                     |
        |1600 Holloway Avenue, San Francisco, California 94132|
      Then Estimate Cost should get recalculate

    @Inprogress
    Scenario:Verify that clearing Pickup address clears all other fields on "Get Estimate" screen
      When I request for  bungii for given following details
        | Driver | Pickup Address  | Delivery Address             |Load Unload Time|
        | Solo   | Margoa Railway  | Patto Centre,Panjim          |15 minutes      |
      And I click "Get Estimate" button on "Get Estimate" screen
      And I change the Pickup Address on "Get Estimate" screen
      Then Check that all fields on "Get Estimate" screen get clear
