@web
Feature: Partner Integration with Admin and Driver

  Background:
    Given I navigate to "Bungii Partner Portal" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"

  @ready
  Scenario: Verify Trips List Status Updation For Solo Scheduled Pickup on Partner Portal
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
    When I enter following details on "Delivery Details" partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |TestPP Customer      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    When I navigate to "Bungii Admin Portal in new tab" URL
    And I view the all Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Searching Drivers|
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Accepted |
    And I view the all Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Scheduled |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Scheduled      |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Enroute |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Trip Started |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Arrived |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Driver(s) Arrived |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Loading Item |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      |  Status |
      | Loading Items |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Driving To Dropoff |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Driving To Dropoff |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Unloading Item |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Unloading Items |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Bungii Completed |
    And I view the Trips list on the admin portal
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Completed    |

  @ready
  Scenario: Verify Cancelling Partner Portal Solo Scheduled trip from Admin Portal
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
    When I enter following details on "Delivery Details" partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |TestPP Customer      |9998881111     |Test Pickup        |9999999359          |
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
    When I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
    And I click on "Submit" button
    And I view the Scheduled Trips list on the admin portal
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Canceled       |

  @ready
  Scenario: Verify Cancelling Partner Portal Solo Scheduled trip from Driver
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
    When I enter following details on "Delivery Details" partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |TestPP Customer      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    When I navigate to "Bungii Admin Portal in new tab" URL
    #And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Searching Drivers|
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Accepted |
    And I view the all Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Scheduled |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Scheduled      |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Enroute |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status       |
      | Trip Started |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state   |
      | Driver Canceled |
    And I view the Trips list on the admin portal
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status    |
      | Canceled       |

  @ready
  Scenario: Verify Cancelling Partner Portal Duo Scheduled trip by control Driver
    When I request "Duo" Bungii trip in partner portal in "washingtondc" geofence
      | Driver | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
      | Duo    | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |TestPP Customer      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    When I navigate to "Bungii Admin Portal in new tab" URL
    #And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Searching Drivers|
    When As a driver "Testdrivertywd_appledc_a_ronny James" and "Testdrivertywd_appledc_a_mate Gate" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    #And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Scheduled |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Scheduled      |
    When As a driver "Testdrivertywd_appledc_a_ronny James" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state |
      | Enroute       |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status       |
      | Trip Started |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    When As a driver "Testdrivertywd_appledc_a_ronny James" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state   |
      | Driver Canceled |
    And I view the Trips list on the admin portal
    And I navigate to partner portal and view the Trip status with below status
        | Partner_Status |
        | Canceled       |

  @ready
  Scenario: Verify Cancelling Partner Portal Duo Scheduled trip by Non control Driver
    When I request "Duo" Bungii trip in partner portal in "washingtondc" geofence
      | Driver | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
      | Duo    | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |TestPP Customer      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    When I navigate to "Bungii Admin Portal in new tab" URL
    #And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Searching Drivers|
    When As a driver "Testdrivertywd_appledc_a_ronny James" and "Testdrivertywd_appledc_a_mate Gate" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    #And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Scheduled |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Scheduled      |
     When As a driver "Testdrivertywd_appledc_a_ronny James" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state |
      | Enroute       |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status       |
      | Trip Started |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
     When As a driver "Testdrivertywd_appledc_a_mate Gate" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state   |
      | Driver Canceled |
    And I view the Trips list on the admin portal
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Canceled       |

  @ready
  Scenario: Verify Cancelling Partner Portal Solo Scheduled trip manually by Admin
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
    When I enter following details on "Delivery Details" partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |TestPP Customer      |9998881111     |Test Pickup        |9999999359          |
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
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Accepted     |
      | Enroute      |
      | Arrived      |
      | Loading Item |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      |  Status |
      | Loading Items |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And I view the Live Trips list on the admin portal
    And I click on Partner Portal Bungii trip
    And I click on "Manually End Bungii" link
    And Enter the End Date and Time
    And Click on "Calculate Cost" button
    Then the amount is calculated and shown to admin
    And Click on "Confirm" button
    And I view the Trips list on the admin portal
    Then The Trip List page should display the partner portal trip in "Payment Successful" state
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Completed      |


  @ready
  Scenario: Verify Solo Scheduled trip cannot cancel in Partner portal once the Trip started
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
    When I enter following details on "Delivery Details" partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |TestPP Customer      |9998881111     |Test Pickup        |9999999359          |
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
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Accepted |
    #And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Scheduled |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Scheduled      |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Enroute |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status       |
      | Trip Started |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state   |
      | Bungii Canceled |
    And I view the Live Trips list on the admin portal
    And I navigate to partner portal
    And I select the Scheduled Bungii from Delivery List
    Then I should "see the trip details"
    And I click "Cancel Delivery link" button on Partner Portal
    Then I should "see the cancel delivery warning message"
    And I click "Cancel Delivery" button on Partner Portal
    Then I should "see Delivery cancellation failed message"
    And I click "OK on Delivery Cancellation Failed" button on Partner Portal
    And I close the Trip Delivery Details page
    And I should logout from Partner Portal