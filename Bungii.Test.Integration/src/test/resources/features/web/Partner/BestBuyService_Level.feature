@web
Feature: Service Level

  Background:
    Given I navigate to "Partner" portal configured for "BestBuy service level" URL

    @regression
      #stable
  Scenario: Verify that NA is shown for Best buy service level on configured Partner portal site.
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    When I request "Solo" Bungii trip in partner portal configured for "BestBuy service level" in "Kansas" geofence
      | Pickup_Address                                 | Delivery_Address                                             |
      | 1735 Noriega St, San Francisco, CA, US, 94122  | 6800 Zoo Drive, Kansas City, United States, Missouri, 64132  |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    Then I should "see Delivery Cost: N/A"
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "BestBuy service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Order_Number|SoldBuy     |
      |5067400         |Handle with care   |Testpartner U |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |ON1         |Krishna|
    Then I should "see Delivery Cost: N/A on Delivery Details screen"
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I click on Filter and select check/unchecked all checkbox
    And I click on Apply button on Filter
    Then I should not able to see Filter screen
    And I select the Scheduled Bungii from Delivery List
    Then I should "see Delivery Cost: N/A on Delivery Details screen"

#CORE-3199-To verify that First 5 Partner portal deliveries are indicated on scheduled delivery page
 @ready
   Scenario: To verify that First 5 Partner portal deliveries are indicated on scheduled delivery page
    When I request Partner Portal "SOLO" Trip for "BestBuy" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |baltimore| NEXT_POSSIBLE | 8877661035 | Testcustomertywd_appleMarkAJ LutherAJ|
    And As a driver "TestDrivertywd_applemd_a_billC Stark_bltTwO" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And  I am logged in as Admin
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then In "Scheduled Deliveries" the trip should be  having a indicator with the text "New-1"
    When As a driver "TestDrivertywd_applemd_a_billC Stark_bltTwO" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
    And I wait for 2 minutes
    And I view the Live Deliveries list on  admin portal
    And I search the delivery using "Pickup Reference"
    Then In "Live Deliveries" the trip should be  having a indicator with the text "New-1"
    When As a driver "TestDrivertywd_applemd_a_billC Stark_bltTwO" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Bungii Completed |
    And I wait for 2 minutes
    And I view All Deliveries list on the admin portal
    And I search the delivery using "Pickup Reference"
    Then The "All Deliveries" page should display the delivery in "Payment Successful" form
    And In "All Deliveries" the trip should be  having a indicator with the text "New-1"


   When I request Partner Portal "SOLO" Trip for "BestBuy" partner
     |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |baltimore| NEXT_POSSIBLE | 8877661035 | Testcustomertywd_appleMarkAJ LutherAJ|
   When As a driver "TestDrivertywd_applemd_a_billC Stark_bltTwO" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
   And I wait for 2 minutes
   And  I am logged in as Admin
   And I view the all Scheduled Deliveries list on the admin portal
   And  I search the delivery using "Pickup Reference"
   Then In "Scheduled Deliveries" the trip should be  having a indicator with the text "New-2"
   When I click on the "Edit" button from the dropdown
   And I click on "Edit Trip Details" radiobutton
   And I edit the drop off address
   Then I change the drop off address to "Fells Point Dental, Fell Street"
   And I click on "Verify" button on Edit Scheduled bungii popup
   When I click on "Save" button on Edit Scheduled bungii popup
   Then "Bungii Saved!" message should be displayed
   And I get the new pickup reference generated
   And I wait for "2" mins
   And As a driver "TestDrivertywd_applemd_a_billC Stark_bltTwO" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
   And I wait for 2 minutes
   When I view the Live Deliveries list on  admin portal
   And I search the delivery using "Pickup Reference"
   Then In "Live Deliveries" the trip should be  having a indicator with the text "New-2"
   And As a driver "TestDrivertywd_applemd_a_billC Stark_bltTwO" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Bungii Completed |
   And I wait for 2 minutes
   And I view All Deliveries list on the admin portal
   And I search the delivery using "Pickup Reference"
   Then The "All Deliveries" page should display the delivery in "Payment Successful" form
   And In "All Deliveries" the trip should be  having a indicator with the text "New-2"

   When I request Partner Portal "SOLO" Trip for "BestBuy" partner
     |Geofence| Bungii Time   | Customer Phone | Customer Name |
     |baltimore| NEXT_POSSIBLE | 8877661035 | Testcustomertywd_appleMarkAJ LutherAJ|
   When I navigate to "Partner" portal configured for "BestBuy2 service level" URL
   And I enter "valid" password on Partner Portal
   And I click "SIGN IN" button on Partner Portal
   Then I should "be logged in"
   When I click the "Track Deliveries" button on Partner Portal
   And I click on the delivery based on customer name
   And I click "Cancel Delivery link" button on Partner Portal
   And I click "Cancel Delivery" button on Partner Portal
   Then I click "OK" button on Partner Portal
   When I navigate to "Admin" portal configured for "QA" URL
   And I wait for 2 minutes
   And I view All Deliveries list on the admin portal
   And  I search the delivery using "Pickup Reference"
   Then In "All Deliveries" the trip should be  having a indicator with the text "New-3"



   When I request Partner Portal "SOLO" Trip for "BestBuy" partner
     |Geofence| Bungii Time   | Customer Phone | Customer Name |
     |baltimore| NEXT_POSSIBLE | 8877661035 | Testcustomertywd_appleMarkAJ LutherAJ|
   And As a driver "TestDrivertywd_applemd_a_billC Stark_bltTwO" perform below action with respective "Solo Scheduled" partner portal trip
     | driver1 state   |
     | Accepted      |
     | Enroute       |
     | Driver Canceled |
   And I wait for 2 minutes
   And I view All Deliveries list on the admin portal
   And  I search the delivery using "Pickup Reference"
   Then In "All Deliveries" the trip should be  having a indicator with the text "New-4"

   When I request Partner Portal "SOLO" Trip for "BestBuy" partner
     |Geofence| Bungii Time   | Customer Phone | Customer Name |
     |baltimore| NEXT_POSSIBLE | 8877661035 | Testcustomertywd_appleMarkAJ LutherAJ|
   And I wait for 2 minutes
   And I view the all Scheduled Deliveries list on the admin portal
   And  I search the delivery using "Pickup Reference"
   And I click on the "Edit" button from the dropdown
   And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
   And I enter cancellation fee and Comments
   And I click on "Submit" button
   Then The "Pick up has been successfully canceled." message should be displayed
   And I wait for "2" mins
   When I view All Deliveries list on the admin portal
   And  I search the delivery using "Pickup Reference"
   Then In "All Deliveries" the trip should be  having a indicator with the text "New-5"
   And Revive button should be displayed beside the trip
   When I click on "Revive" button
   Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
   When I click on "Confirm" button on Revival Popup
   And I wait for 2 minutes
   And I view the all Scheduled Deliveries list on the admin portal
   And  I search the delivery using "Pickup Reference"
   Then I should be able to see the respective bungii with the below status
     |  Status |
     | Assigning Driver(s) |
   Then In "Scheduled Deliveries" the trip should be  having a indicator with the text "New-5"

    When I request Partner Portal "SOLO" Trip for "BestBuy" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |baltimore| NEXT_POSSIBLE | 8877661036 | Testcustomertywd_appleMarkAK LutherAK|
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The delivery should not be having indicator