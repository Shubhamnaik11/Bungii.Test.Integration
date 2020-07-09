@web
  Feature: Partner Solo Scheduled Trips

    Background:
    Given I navigate to "Bungii Partner Portal" URL
      And I enter "valid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      Then I should "be logged in"

    @regression
      Scenario: Verify that Partner can scheduled Solo bungii Trip
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
        When I enter following details on "Delivery Details" partner screen
          |Items_To_Deliver|Customer_Name|Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
          |Furniture       |Test Gopal   |9998881111     |Test Pickup        |9999999359          |
        And I Select "Customer Card" as Payment Method
        And I enter following Credit Card details on Partner Portal
          |CardNo   |Expiry |Postal_Code      |Cvv      |
          |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
        And I click "Schedule Bungii" button on Partner Portal
        Then I should "see Done screen"
        When I click "Track Deliveries" button on Partner Portal
        Then I should "see the trip in the Delivery List"
        And I should logout from Partner Portal

    @Inprogress
    Scenario: Verify Trips List Status Updation For Solo Scheduled Pickup on Partner Portal
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
      When I enter following details on "Delivery Details" partner screen
        |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
        |Furniture       |Testcustomer_Gopal   |9998881111     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see Done screen"
      When I click "Track Deliveries" button on Partner Portal
      Then I should "see the trip in the Delivery List"
      When I navigate to "Bungii Admin Portal in new tab" URL
      And I view the Scheduled Trips list on the admin portal
      Then I should be able to see the respective bungii partner portal trip with the below status
        | Status           |
        | Searching Drivers|
      And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" trip
        | driver1 state|
        | Accepted |
      And I view the Scheduled Trips list on the admin portal
      Then I should be able to see the respective bungii partner portal trip with the below status
        | Status    |
        | Scheduled |

    @Inprogress
    Scenario: Verify the five future days shown in Pickup Date dropdown
          When I click on Pickup date
          Then I should "see five future days including today"
          And I should logout from Partner Portal

    @regression
        Scenario: Verify changing the pickup date for scheduled Solo bungii Trip
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
          When I enter following details on "Delivery Details" partner screen
            |Items_To_Deliver|Customer_Name|Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
            |Furniture       |Test Gopal   |9998881111     |Test Pickup        |9999999359          |
          And I click "Back to Estimate" button on Partner Portal
          And I select Pickup Date and Pickup Time
            |PickUp_Date  |PickUp_Time          |
            |Today+2      |5th quarter          |
          And I click "GET ESTIMATE" button on Partner Portal
          Then I should see "Estimated Cost"
          And I should logout from Partner Portal

    @regression
    Scenario: Verify Cancellation of Solo Scheduled Trips
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
      When I enter following details on "Delivery Details" partner screen
        |Items_To_Deliver|Customer_Name|Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
        |Furniture       |Test Gopal   |9998881111     |Test Pickup        |9999999359          |
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
      And I should logout from Partner Portal


