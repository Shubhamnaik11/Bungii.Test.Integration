@web
Feature: Floor and Decore Service Level

  Background:
    Given I navigate to "Partner" portal configured for "FloorDecor service level" URL

  @regression
  Scenario: Verify that 1pallet and 2pallets are shown instead of solo and duo for floor and decor partner portal
    When I enter "valid" password on Partner Portal
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
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I select the Scheduled Bungii from Delivery List
    Then I should "see the service name"
    Then I close the Trip Delivery Details page
    When I navigate to "Admin" portal configured for "QA" URL
    #When I navigate to "Bungii Admin Portal in new tab" URL
    #And I view the Deliveries list on the admin portal
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|

#    @testWeb
#    Scenario: Verify that 1pallet and 2pallets are shown instead of solo and duo for floor and decor partner portal
#      When I request Partner Portal "SOLO" Trip for "Equip-bid" partner
#        |Geofence| Bungii Time   | Customer Phone | Customer Name |
#        |kansas| NEXT_POSSIBLE | 8877661036 | Testcustomertywd_appleMarkAK LutherAK|
