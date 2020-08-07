@web
  Feature: Partner Solo Scheduled Trips

    Background:
    Given I navigate to "Bungii Partner Portal" URL
      And I enter "valid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      Then I should "be logged in"

    @regression
      Scenario: Verify that Partner can scheduled Solo bungii Trip
      When I request "Solo" Bungii trip in partner portal in "washingtondc" geofence
        | Driver | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
        | Solo   | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
      And I select Next Possible Pickup Date and Pickup Time
        |Trip_Time            |
        |NEXT_POSSIBLE        |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      And I confirm the trip details from Get Estimate
      When I enter all details on "Delivery Details" partner screen
        |Items_To_Deliver|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Receipt_Number|
        |Furniture       |Handle with care   |TestPP Customer |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see Done screen"
      When I click "Track Deliveries" button on Partner Portal
      Then I should "see the trip in the Delivery List"
      And I should logout from Partner Portal


    @regression
    Scenario: Verify the five future days shown in Pickup Date dropdown
      And I click on Pickup date
      Then I should see five future days including today
      And I should logout from Partner Portal

    @regression
    Scenario: Verify changing the pickup date for scheduled Solo bungii Trip
      When I request "Solo" Bungii trip in partner portal in "washingtondc" geofence
        | Driver | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
        | Solo   | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
      And I select Pickup Date and Pickup Time on partner portal
        |PickUp_Date  |PickUp_Time          |
        |Today+1      |09:30 AM             |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      When I enter following details on "Delivery Details" partner screen
        |Items_To_Deliver|Customer_Name     |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
        |Furniture       |TestPP Customer   |9998881111     |Test Pickup        |9999999359          |
      And I click "Back to Estimate" button on Partner Portal
      And I select Pickup Date and Pickup Time on partner portal
        |PickUp_Date  |PickUp_Time          |
        |Today+2      |10:30 PM             |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I should logout from Partner Portal

    @ready
    Scenario: Verify Cancellation of Solo Scheduled Trips
      When I request "Solo" Bungii trip in partner portal in "washingtondc" geofence
        | Driver | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
        | Solo   | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
      And I select Pickup Date and Pickup Time on partner portal
        |PickUp_Date  |PickUp_Time          |
        |Today+1      |11:30 AM             |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      When I enter following details on "Delivery Details" partner screen
        |Items_To_Deliver|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
        |Furniture       |TestPP Customer |9998881111     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see Done screen"
      When I click "Track Deliveries" button on Partner Portal
      Then I should "see the trip in the Delivery List"
      And I select the Scheduled Bungii from Delivery List
      Then I should "see the trip details"
      And I click "Cancel Delivery link" button on Partner Portal
      Then I should "see the cancel delivery warning message"
      And I click "Cancel Delivery" button on Partner Portal
      Then I should "see delivery has been cancelled message"
      And I click "OK" button on Partner Portal
      Then I should "see Canceled trip message"
      Then I close the Trip Delivery Details page
      And I should logout from Partner Portal


