@web
Feature: Partner Integration with Admin and Driver
  
  Background:
    Given I navigate to "Partner" portal configured for "normal" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
  
  @regression
  @sanity
    #stable
  Scenario: Delivery List Status Updation For Solo Scheduled Pickup on Partner Portal
    When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" for "normal" on partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |Testpartner A      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD3|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Searching Drivers|
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Accepted |
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Scheduled |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Scheduled      |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Enroute |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Trip Started |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Arrived |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Driver(s) Arrived |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Loading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      |  Status |
      | Loading Items |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Driving To Dropoff |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Driving To Dropoff |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Unloading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Unloading Items |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Bungii Completed |
    And I view the Deliveries list on the admin portal
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Completed    |
  
  @regression
    #stable
  Scenario: Verify Cancelling Partner Portal Solo Scheduled trip from Admin Portal
    When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" for "normal" on partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |Testpartner B      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD4|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Searching Drivers|
    When I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
    And I click on "Submit" button
    #And I view the Scheduled Trips list on the admin portal
    And I view the Deliveries list on the admin portal
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Canceled       |
  
  @regression
    #Stable
  Scenario: Verify Cancelling Partner Portal Solo Scheduled trip from Driver
    When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" for "normal" on partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |Testpartner C      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD3|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Searching Drivers|
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Accepted |
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Scheduled |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Scheduled      |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Enroute |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status       |
      | Trip Started |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state   |
      | Driver Canceled |
    And I view the Deliveries list on the admin portal
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status    |
      | Canceled       |
  
  @ready
  Scenario: Verify Cancelling Partner Portal Duo Scheduled trip by control Driver
    When I request "Duo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" for "normal" on partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |Testpartner D     |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD4|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    #When I navigate to "Bungii Admin Portal in new tab" URL
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Searching Drivers|
    When As a driver "Testdrivertywd_appledc_a_ronny James" and "Testdrivertywd_appledc_a_mate Gate" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Scheduled |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Scheduled      |
    When As a driver "Testdrivertywd_appledc_a_ronny James" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state |
      | Enroute       |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status       |
      | Trip Started |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    When As a driver "Testdrivertywd_appledc_a_ronny James" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state   |
      | Driver Canceled |
    And I view the Deliveries list on the admin portal
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Canceled       |
  
  @ready
  Scenario: Verify Cancelling Partner Portal Duo Scheduled trip by Non control Driver
    When I request "Duo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" for "normal" on partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |Testpartner E      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD3|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    #When I navigate to "Bungii Admin Portal in new tab" URL
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Searching Drivers|
    When As a driver "Testdrivertywd_appledc_a_ronny James" and "Testdrivertywd_appledc_a_mate Gate" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Scheduled |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Scheduled      |
    When As a driver "Testdrivertywd_appledc_a_ronny James" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state |
      | Enroute       |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status       |
      | Trip Started |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    When As a driver "Testdrivertywd_appledc_a_mate Gate" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state   |
      | Driver Canceled |
    And I view the Deliveries list on the admin portal
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Canceled       |
  
  @regression
  Scenario: Verify Cancelling Partner Portal Solo Scheduled trip manually by Admin
    When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" for "normal" on partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |Testpartner F      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |MASTER CARD2|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    #When I navigate to "Bungii Admin Portal in new tab" URL
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Searching Drivers|
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Accepted     |
      | Enroute      |
      | Arrived      |
      | Loading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      |  Status |
      | Loading Items |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And I view the Live Deliveries list on the admin portal
    #And I click on Partner Portal Bungii delivery
    And I open the live delivery details in admin portal
    And I click on "Manually End Bungii" link
    And Enter the End Date and Time
    And Click on "Calculate Cost" button
    Then the amount is calculated and shown to admin
    And Click on "Confirm" button
    And I view the Deliveries list on the admin portal
    #Then The Delivery List page should display the delivery in "Payment Successful" state
    Then I should be able to see the respective partner portal trip with "Payment Successful" state
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Completed      |
  
  
  @ready
  Scenario: Verify Solo Scheduled trip cannot cancel in Partner portal once the Trip started
    When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" for "normal" on partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |Testpartner G      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD4|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    #When I navigate to "Bungii Admin Portal in new tab" URL
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Searching Drivers|
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Accepted |
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Scheduled |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Scheduled      |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Enroute |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status       |
      | Trip Started |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state   |
      | Bungii Canceled |
    And I view the Live Deliveries list on the admin portal
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
  
  @ready
  Scenario: Verify Driver Est. Earning for Fixed Pricig Partner Portal Trip
    When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                  |Load_Unload_Time|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | Legacy granite, 7730 Oak St, Falls Church, VA 22043, United States|30 minutes      |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" for "normal" on partner screen
      |Items_To_Deliver|Customer_Name      |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |Testpartner H      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD3|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    #When I navigate to "Bungii Admin Portal in new tab" URL
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Accepted |
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Scheduled |
    And I select the partner portal scheduled trip on scheduled delivery
    Then I view the correct Driver Est. Earnings for geofence based pricing model
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Enroute |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Trip Started |
    And I select the scheduled trip on live delivery
    Then I view the correct Driver Est. Earnings for geofence based pricing model
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Arrived |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Driver(s) Arrived |
    And I select the scheduled trip on live delivery
    Then I view the correct Driver Est. Earnings for geofence based pricing model
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Loading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      |  Status |
      | Loading Items |
    And I select the scheduled trip on live delivery
    Then I view the correct Driver Est. Earnings for geofence based pricing model
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Driving To Dropoff |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Driving To Dropoff |
    And I select the scheduled trip on live delivery
    Then I view the correct Driver Est. Earnings for geofence based pricing model
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Unloading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Unloading Items |
    And I select the scheduled trip on live delivery
    Then I view the correct Driver Est. Earnings for geofence based pricing model
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Bungii Completed |
    And I view All Deliveries list on the admin portal
    Then I should be able to see the respective partner portal trip with "Payment Successful" state
    #Then The Delivery List page should display the delivery in "Payment Successful" state
    And I select the scheduled trip on All Deliveries
    Then I view the correct Driver Earnings for geofence based pricing model