@web
Feature: Partner Duo Scheduled Trips

  Background:
    Given I navigate to "Bungii Partner Portal" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"

  @regression
  Scenario: Verify that Partner can scheduled Duo bungii Trip
    When I request for "Duo" Bungii trip in partner portal in "washingtondc" geofence
      | Driver | Pickup_Address                                 | Delivery_Address                                        |Load_Unload_Time|
      | Duo   | 1735 Noriega St, San Francisco, CA, US, 94122  | 1600 Holloway Avenue, San Francisco, California 94132   |30 minutes      |
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

  @Inprogress
  Scenario: Verify the five future days shown in Pickup Date dropdown
    When I click on Pickup date
    Then I should see five future days including today

    @regression
  Scenario: Verify changing the pickup date for scheduled Duo bungii Trip
    When I request for "Duo" Bungii trip in partner portal
      | Driver | Pickup_Address                            | Delivery_Address                                        |Load_Unload_Time|
      | Duo   | Patto Centre, Panaji, India, Goa, 403001  | 1600 Holloway Avenue, San Francisco, California 94132   |30 minutes      |
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

  @regression
  Scenario: Verify Cancellation of Duo Scheduled Trips
    When I request for "Duo" Bungii trip in partner portal in "washingtondc" geofence
      | Driver | Pickup_Address                                 | Delivery_Address                                        |Load_Unload_Time|
      | Duo    | 1735 Noriega St, San Francisco, CA, US, 94122  | 1600 Holloway Avenue, San Francisco, California 94132   |30 minutes      |
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
