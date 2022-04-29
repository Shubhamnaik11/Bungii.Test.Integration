@web
@new
Feature: Admin_Reason_Code

  Background:
    Given I am logged in as Admin

  @ready

  Scenario: Verify Reason dropdown is displayed for Solo re-schedule delivery when no Driver accepts,initiated by Customer and Admin edits only time
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999106 | Testcustomertywd_appleNewQF Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on the "Time" and select future time
    And I click on "Reason" for change time
    And I click on "Customer initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    Then the updated time should be displayed on delivery details page

  @ready

  Scenario: Verify Reason dropdown from Duo re-schedule delivery when no Driver accepts,initiated by Customer and Admin edits only time
    When I request "Duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999107     | Testcustomertywd_appleNewQG Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on the "Time" and select future time
    And I click on "Reason" for change time
    And I click on "Customer initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    Then the updated time should be displayed on delivery details page

  @ready

    Scenario: Verify Reason dropdown is displayed for Customer SOLO re-scheduled delivery when NO Driver accepts and Admin edits only Date
      When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
        | Bungii Time   | Customer Phone | Customer Name |
        | NEXT_POSSIBLE | 9999999108 | Testcustomertywd_appleNewQH Customer|
      And I view the all Scheduled Deliveries list on the admin portal
      And I wait for "2" mins
      Then I should be able to see the respective bungii with the below status
        |  Status |
        | Assigning Driver(s) |
      When I click on "Edit" link beside scheduled bungii
      And I click on "Edit Trip Details" radiobutton
      And I click on the "Date" and select future time
      And I click on "Reason" for change time
      And I click on "Customer initiated" in the dropdown
      And I click on "Verify" button on Edit Scheduled bungii popup
      When I click on "Save" button on Edit Scheduled bungii popup
      Then "Bungii Saved!" message should be displayed
      And I wait for "2" mins
      Then the updated date should be displayed on delivery details page

  @ready

  Scenario: Verify Reason dropdown is displayed for Customer SOLO/ DUO  re-scheduled delivery  when NO Driver accepts and Admin edits both Date and Time
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999109 | Testcustomertywd_appleNewQI Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on the "Date" and select future time
    And I click on the "Time" and select future time
    And I click on "Reason" for change time
    And I click on "Customer initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    Then the updated date should be displayed on delivery details page


  @ready

  Scenario: Verify Reason dropdown is hidden when Admin does not edit date or time or both and verify reason dropdown values
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999110     | Testcustomertywd_appleNewQJ Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I check if the "Reason" field is hidden
    And I click on the "Time" and select future time
    And I click on "Reason" for change time and check reason dropdown values
    And I click on "Customer initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    Then the updated time should be displayed on delivery details page


  @ready

  Scenario: Verify Reason dropdown for Customer DUO re-scheduled delivery when Single Driver accepts
    When I request "Duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name  |
      | NEXT_POSSIBLE | 9999999111     | Testcustomertywd_appleNewQK Customer|
    And As a driver "Testdrivertywd_appledc_a_drvl WashingtonDC_l" perform below action with respective "Duo Scheduled" Delivery
      | driver1 state|
      |Accepted |
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on the "Date" and select future time
    And I click on the "Time" and select future time
    And I click on "Reason" for change time
    And I click on "Customer initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    Then the updated date should be displayed on delivery details page

  @ready

  Scenario: Verify Reason dropdown for Customer DUO re-scheduled delivery when  Both Drivers do not accept the delivery
    When I request "Duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999112     | Testcustomertywd_appleNewQL Customer|
    And I wait for "5" mins
    And I wait for "3" mins
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for "5" mins
    And I wait for "5" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on the "Time" and select future time
    And I click on "Reason" for change time
    And I click on "Customer initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    Then the updated date should be displayed on delivery details page

  @ready

  Scenario: Verify Reason is not mandatory when pickup/drop off address edited and driver is assigned by Admin for Customer delivery
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999113     | Testcustomertywd_appleNewQM Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    Then I change the drop off address to "4400 Massachusetts Avenue Northwest"
    And I check if the "Reason" field is hidden
    And I click on "Add Driver" and add "Testdrivertywd_appledc_a_drvl WashingtonDC_l" driver
    And I check if the "Reason" field is hidden
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I view the delivery details in admin portal
    Then the updated drop off address should be displayed on delivery details page

  @ready

  Scenario: Verify reschedule reason is not displayed for LIVE trips
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999114     | Testcustomertywd_appleNewQN Customer|
    And As a driver "Testdrivertywd_appledc_a_drvn WashingtonDC_n" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Enroute       |
      | Arrived       |
      | Loading Item   |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status       |
      | Loading Items |
    When I click on "Edit" link beside live delivery
    And I click on "Edit Trip Details" radiobutton
    And I check if the "Reason" field is present
    And I edit the drop off address
    Then I change the drop off address to "4400 Massachusetts Avenue Northwest"
    And I check if the "Reason" field is present
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I open the live delivery details in admin portal
    Then the updated drop off address should be displayed on delivery details page


  @ready

  Scenario: Verify Reason is not mandatory when Service Level edited  for Partner delivery
    When I navigate to "Partner" portal configured for "service level" URL
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    When I request "Solo" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                   |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002  |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Biglots" Alias
    And I change the service level to "Room of Choice" in "Partner" portal
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Receipt_Number|
      |Furniture       |Handle with care   |Testcustomertywd_appleNewQO Customer |9999999115    |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |DISCOVER CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I select the Scheduled Bungii from Delivery List
    Then I close the Trip Delivery Details page
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
    When I click on "Edit" link beside live delivery
    And I click on "Edit Trip Details" radiobutton
    And I check if the "Reason" field is hidden
    And I change the service level to "Threshold" in "Admin" portal
    And I check if the "Reason" field is hidden
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed


  @ready

  Scenario: Verify Reason is not mandatory when drop-off/pickup address edited and driver is assigned for Partner delivery
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas| NEXT_POSSIBLE | 9999999116 | Testcustomertywd_appleNewQP Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    And I check the price for delivery
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I check if the "Reason" field is hidden
    And I edit the drop off address
    Then I change the drop off address to "400 Speedway Boulevard"
    And I check if the "Reason" field is hidden
    And I click on "Add Driver" and add "Testdrivertywd_appleks_a_drvk Kansas_k" driver
    And I check if the "Reason" field is hidden
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I view the delivery details in admin portal
    Then the updated drop off address should be displayed on delivery details page


  @ready

  Scenario: Verify Reason dropdown for PARTNER SOLO(Weight based pricing) re-scheduled delivery when NO Driver accepts and Admin edits only Date when Partner Initiated
    When I navigate to "Partner" portal configured for "FloorDecor service level" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "see 1 pallet and 2 pallets"
    When I request "Solo" Bungii trip in partner portal configured for "FloorDecor service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837  |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Floor & Decor #240" Alias
    And I change the service level to "First Threshold" in "Partner" portal
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "FloorDecor service level" on partner screen
      |Product_Description|Dimensions|Weight|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Delivery_Purpose|Rb_Sb_Number|SoldBuy|
      |20 boxes           |20X20X20  | 1570 |Handle with care   |Testcustomertywd_appleNewQR Customer  |9999999117     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |For decoration  |007         |FND166 |
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on the "Date" and select future time
    And I click on "Reason" for change time
    And I click on "Partner initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    Then the updated time should be displayed on delivery details page


  @ready

  Scenario: Verify Reason dropdown for PARTNER DUO(Geofence based pricing) re-scheduled delivery when Both Driver do not accept and admin edits only time when Partner Initiated
    Given I navigate to "Partner" portal configured for "normal" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
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
      |Furniture       |Testcustomertywd_appleNewQS Customer   |9999999118     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD4|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    And I wait for "5" mins
    Then I should "see Done screen"
    And I wait for "4" mins
    When I navigate to "Admin" portal configured for "QA" URL
    And I wait for "4" mins
    And I view the partner portal Scheduled Trips list on the admin portal
    And I wait for "5" mins
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on the "Time" and select future time
    And I click on "Reason" for change time
    And I click on "Partner initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    Then the updated time should be displayed on delivery details page