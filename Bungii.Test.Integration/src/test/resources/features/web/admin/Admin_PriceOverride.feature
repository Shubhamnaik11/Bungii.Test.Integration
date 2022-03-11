@web
Feature: Admin_Price_Override

  @ready

  Scenario: Verify the Price override button is displayed, check save and cancel button functionality and Estimated Customer charge is updated on admin portal
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
    |Furniture       |Handle with care   |Testcustomertywd_appleNewQT Customer |9999999119    |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
    |CardNo   |Expiry |Postal_Code      |Cvv      |
    |DISCOVER CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    And I wait for "1" mins
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Searching Drivers|
    When I view the delivery details
    And I get the old values of "Customer price" for "Service level"
    And I get the old values of "Driver cut" for "Service level"
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I change the "Customer price"
    And I select Reason as "Custom Quote"
    And I change the "Driver cut"
    And I select Reason as "Driver Incentive"
    And I click on "Cancel" button on price override pop-up
    And I wait for "2" mins
    Then I check if values of "Customer price" and "Driver cut" remain unchanged
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I change the "Customer price"
    And I select Reason as "Custom Quote"
    And I change the "Driver cut"
    And I select Reason as "Driver Incentive"
    Then I click on "Save" button on price override pop-up
    And I click on "Ok" button on price override pop-up
    And I wait for "2" mins
    And I check the new values of "Estimated Charge" for "Service level"
    Then I check the new values of "Driver Fixed Earnings" for "Service level"


  @ready

    Scenario: Verify customer price override menu, driver cut price override menu and admin override multiple times on same delivery
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
      |Furniture       |Handle with care   |Testcustomertywd_appleNewQU Customer |9999999120    |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |DISCOVER CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    And I wait for "1" mins
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Searching Drivers|
    When I view the delivery details
    And I get the old values of "Customer price" for "Service level"
    And I get the old values of "Driver cut" for "Service level"
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I check if the "Customer price" menu is displayed
    And I change the "Customer price"
    And I select Reason as "Custom Quote"
    And I check if the "Driver cut" menu is displayed
    And I change the "Driver cut"
    And I select Reason as "Driver Incentive"
    Then I click on "Save" button on price override pop-up
    And I click on "Ok" button on price override pop-up
    And I wait for "2" mins
    And I check the new values of "Estimated Charge" for "Service level"
    Then I check the new values of "Driver Fixed Earnings" for "Service level"
    When I get the old values of "Customer price" for "Service level"
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I change the "Customer price"
    And I select Reason as "Custom Quote"
    Then I click on "Save" button on price override pop-up
    And I click on "Ok" button on price override pop-up
    And I wait for "2" mins
    Then I check the new values of "Estimated Charge" for "Service level"



  @ready

    Scenario: Verify change Pickup/Drop off address after override for driver earnings and customer cost before driver accepts, check if price override is reflected on partner portal and verify Driver cut is less than Customer price for Admin override
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
      |Furniture       |Handle with care   |Testcustomertywd_appleNewQV Customer |9999999121    |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |DISCOVER CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I am logged in as Admin
    And I view the partner portal Scheduled Trips list on the admin portal
    And I wait for "1" mins
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Searching Drivers|
    When I view the delivery details
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I get the old values of "Customer price" for "Service level"
    And I get the old values of "Driver cut" for "Service level"
    And I increase the "Driver cut" more than "Customer price"
    And I select Reason as "Driver Incentive"
    And I click on "Save" button on price override pop-up
    And "Delivery cost cannot be less than or equal to driver earnings." error message should be displayed
    Then I click on "Cancel" button on price override pop-up
    And I get the old values of "Customer price" for "Service level"
    And I get the old values of "Driver cut" for "Service level"
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I change the "Customer price"
    And I select Reason as "Custom Quote"
    And I change the "Driver cut"
    And I select Reason as "Driver Incentive"
    Then I click on "Save" button on price override pop-up
    And I click on "Ok" button on price override pop-up
    And I wait for "2" mins
    And I check the new values of "Estimated Charge" for "Service level"
    Then I check the new values of "Driver Fixed Earnings" for "Service level"
    And I navigate back to Scheduled Deliveries
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    Then I change the drop off address to "4400 Massachusetts Avenue Northwest"
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    When I navigate to "Partner" portal configured for "service level" URL
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I select the Scheduled Bungii from Delivery List
    Then I should "see the service name"
    And I check if the delivery cost is updated on partner portal
    Then I close the Trip Delivery Details page

  @ready

    Scenario: Verify Admin override is not present for Schedule Customer delivery and Live delivery
      When I am logged in as Admin
      And I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999122     | Testcustomertywd_appleNewQW Customer|
      And I view the all Scheduled Deliveries list on the admin portal
      And I should be able to see the respective bungii with the below status
      | Status           |
      | Searching Drivers|
      Then I click on "Edit" link beside live delivery
      When I view the delivery details
      And I check if "Price override" button is not present
      Then I navigate back to Scheduled Deliveries
      And As a driver "Testdrivertywd_appledc_a_webdd Testdriverdd" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Enroute       |
      | Arrived       |
      | Loading Item   |
      And I view the Live Deliveries list on the admin portal
      And I wait for "2" mins
      Then I should be able to see the respective bungii with the below status
      |  Status       |
      | Loading Items |
      When I click on "Edit" link beside live delivery
      When I view the delivery details
      Then I check if "Price override" button is not present

  @ready

      Scenario: Verify the estimated charge and driver earnings when service level is updated over a admin override functionality
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
        |Furniture       |Handle with care   |Testcustomertywd_appleNewQX Customer |9999999123   |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |DISCOVER CARD|12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see Done screen"
      When As a driver "Testdrivertywd_appledc_a_webdd Testdriverdd" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state |
        | Accepted      |
      When I am logged in as Admin
      And I view the partner portal Scheduled Trips list on the admin portal
      And I wait for "2" mins
      Then I should be able to see the respective bungii partner portal trip with the below status
        | Status           |
        | Scheduled |
      When I view the delivery details
      And I get the old values of "Customer price" for "Service level"
      And I get the old values of "Driver cut" for "Service level"
      And I check if "Price Override" button is displayed
      And I click on "Price Override" button on delivery details
      And I change the "Customer price"
      And I select Reason as "Custom Quote"
      And I change the "Driver cut"
      And I select Reason as "Driver Incentive"
      Then I click on "Save" button on price override pop-up
      And I click on "Ok" button on price override pop-up
      And I wait for "2" mins
      And I check the new values of "Estimated Charge" for "Service level"
      Then I check the new values of "Driver Fixed Earnings" for "Service level"
      When I navigate back to Scheduled Deliveries
      And I click on "Edit" link beside scheduled bungii
      And I click on "Edit Trip Details" radiobutton
      And I change the service level to "Threshold" in "Admin" portal
      And I click on "Verify" button on Edit Scheduled bungii popup
      And I click on "Save" button on Edit Scheduled bungii popup
      Then "Bungii Saved!" message should be displayed
      When I view the delivery details in admin portal
      And I get the old values of "Customer price" for "Service level"
      And I get the old values of "Driver cut" for "Service level"
      And I wait for "2" mins
      Then I check the new values of "Estimated Charge" and "Driver Fixed Earnings" for changed "Service level"

  @ready
   @testsweta
    Scenario: Verify fnd deliveries and driver app for change Service Level after override for driver earnings and customer cost  before driver accepts
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
        |20 boxes           |20X20X20  | 1570 |Handle with care   |Testartner T    |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |For decoration  |007         |FND166 |
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see Done screen"
      When I am logged in as Admin
      And I view the partner portal Scheduled Trips list on the admin portal
      And I wait for "2" mins
      Then I should be able to see the respective bungii partner portal trip with the below status
        | Status           |
        | Searching Drivers|
      When I view the delivery details
      And I get the old values of "Customer price" for "Service level - fnd"
      And I get the old values of "Driver cut" for "Service level - fnd"
      And I check if "Price Override" button is displayed
      And I click on "Price Override" button on delivery details
      And I change the "Customer price"
      And I select Reason as "Custom Quote"
      And I change the "Driver cut"
      And I select Reason as "Driver Incentive"
      Then I click on "Save" button on price override pop-up
      And I click on "Ok" button on price override pop-up
      And I wait for "2" mins
      And I check the new values of "Estimated Charge" for "Service level - fnd"
      Then I check the new values of "Driver Fixed Earnings" for "Service level - fnd"
      When I navigate back to Scheduled Deliveries
      And I click on "Edit" link beside scheduled bungii
      And I click on "Edit Trip Details" radiobutton
      And I change the service level to "Customer Return - First Threshold" in "Admin" portal
      And I click on "Verify" button on Edit Scheduled bungii popup
      And I click on "Save" button on Edit Scheduled bungii popup
      Then "Bungii Saved!" message should be displayed
      When I view the delivery details in admin portal
      And I get the old values of "Customer price" for "Service level - fnd"
      And I get the old values of "Driver cut" for "Service level - fnd"
      And I wait for "2" mins
      Then I check the new values of "Estimated Charge" and "Driver Fixed Earnings" for changed "Service level - fnd"
      When I click on "Edit" link beside scheduled bungii
      And I click on "Edit Trip Details" radiobutton
      And I click on "Add Driver" and add "Testdrivertywd_appledc_a_drvl WashingtonDC_l" driver
      And I click on "Verify" button on Edit Scheduled bungii popup
      When I click on "Save" button on Edit Scheduled bungii popup
      Then "Bungii Saved!" message should be displayed
      And I wait for "2" mins
      When I view the delivery details in admin portal
      Then I check the new values of "Estimated Charge" and "Driver Fixed Earnings" for changed "Service level - fnd"


  @ready

    Scenario: Verify fnd Portals, driver app for Price override for customer earnings only in Solo fixed pricing portals
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
        |20 boxes           |20X20X20  | 1570 |Handle with care   |Testartner T    |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |For decoration  |007         |FND166 |
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see Done screen"
      When I am logged in as Admin
      And I view the partner portal Scheduled Trips list on the admin portal
      And I wait for "2" mins
      Then I should be able to see the respective bungii partner portal trip with the below status
        | Status           |
        | Searching Drivers|
      When I view the delivery details
      And I get the old values of "Customer price" for "Service level - fnd"
      And I check if "Price Override" button is displayed
      And I click on "Price Override" button on delivery details
      And I change the "Customer price"
      And I select Reason as "Custom Quote"
      Then I click on "Save" button on price override pop-up
      And I click on "Ok" button on price override pop-up
      And I wait for "2" mins
      Then I check the new values of "Estimated Charge" for "Service level - fnd"