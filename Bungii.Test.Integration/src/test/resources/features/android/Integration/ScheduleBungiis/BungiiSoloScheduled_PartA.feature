@android
@SoloScheduled
@bungii
#These feature will run in kansas geofence
Feature: SoloScheduled
 # All Stable 2 Cases
  Background:
    
       #######################################################################################
  @regression
  Scenario: Verify Minimum Scheduled Time For Solo Trip in San Francisco Geofence
    Given I am on customer Log in page
    When I am logged in as "New" customer
    And I enter "San Francisco pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then I should see the minimum scheduled time for Solo Bungii displayed on the Estimate page
  
  @regression
       #stable
  Scenario:Verify When Bungii Is Not Started Driver Can Cancel Scheduled Bungii From The App :solo
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas1  | Accepted     | NEXT_POSSIBLE |
    
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "kansas driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    And I Switch to "driver" application on "same" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    When Bungii Driver "cancels Bungii request"
    Then user is alerted for "FOR EMERGENCY CONTACT SUPPORT LINE"
    And correct details should be displayed on the "SMS FOR CANCEL INCASE OF EMERGENCEY" app

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


#CORE-2753 : To verify that driver can successfully accept incoming Scheduled trip request during ongoing trip
#Sprint-58==CORE-3396 changes incorporated
  @ready
  Scenario:To verify that driver can successfully accept incoming Scheduled trip request during ongoing trip
    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I get TELET time of of the current trip
    And As a driver "Testdrivertywd_appleks_a_drvah Kansas_ah" perform below action with respective "SOLO SCHEDULED" trip
      | driver1 state |
      | Accepted      |
    And I wait for 1 minutes
    And As a driver "Testdrivertywd_appleks_a_drvah Kansas_ah" perform below action with respective "SOLO SCHEDULED" trip
      | driver1 state |
      | Enroute      |
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvah Kansas_ah" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    Then Bungii driver should see "Enroute screen"
    And I Switch to "customer" application on "same" devices
    And I am logged in as "valid kansas 3" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I enter "kansas very short trip location" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    When I confirm trip with following detail
      | Day | Trip Type |
      | 1   | SOLO      |
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I click "Done" button on "Success" screen

    And I Switch to "driver" application on "same" devices
    Then I should see a popup "New Bungii Request" displayed
    And I click on "View Request" button
    And I wait for 1 minutes
    And I click on "Accept" button
    And I click "Scheduled Bungiis" button on "update" screen
    And I should select the "valid kansas 3" customer on driver app
    Then Start button should not be shown
    And I click on device "BACK" button
    And I click on device "BACK" button
    Then Bungii driver should see "Enroute screen"
    And I slide update button on "EN ROUTE" Screen

    And I cancel all bungiis of customer
     | Customer Phone  | Customer2 Phone |
     | CUSTOMER1_PHONE |                 |

  #CORE-3381 :To verify that admin is unable to revive trips canceled by customer from customer
  @ready
  Scenario:To verify that admin is unable to revive trips canceled by customer from app
    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8877661052     | Testcustomertywd_BppleMarkBA LutherBA | Cci12345          |
    When I Switch to "customer" application on "same" devices
    And I am on customer Log in page
    And I am logged in as "valid kansas 4" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select already scheduled bungii
    Then I Cancel selected Bungii
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I wait for "2" minutes
    And I Select "trips" from admin sidebar
    When  I search the delivery using "Pickup Reference"
    Then The revive button should not be displayed

  #CORE-3381:To verify that admin/partner canceled revived deliveries are not displayed to driver on app
  @ready
  Scenario:To verify that admin/partner canceled revived deliveries are not displayed to driver on app
    When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8877661053     | Testcustomertywd_appleMarkBB LutherBB | Cci12345          |
    And I wait for "2" minutes
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkBB LutherBB" the customer
    And I Select "Cancel Trip" option
    And I enter cancellation fee and Comments
    And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
    And I click on "Cancel Bungii" button
    Then The "Pick up has been successfully canceled." message should be displayed
    And I wait for "2" minutes
    And I Select "trips" from admin sidebar
    When  I search the delivery using "Pickup Reference"
    And Revive button should be displayed beside the trip
    When I click on "Revive" button
    Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
    When I click on "Confirm" button on Revival Popup
    And I wait for 2 minutes
    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    When I am logged in as "Testdrivertywd_appledv_b_mattF Stark_dvOnEF" driver
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    Then The trip should not be present in available bungiis
    And I Switch to "customer" application on "same" devices
    And I am logged in as "valid denver8" customer
    And I tap on "Menu" > "MY BUNGIIS" link
    Then The trip should be present in my bungiis


# #CORE-3606 : Verify Customer Signature screen is shown on driver app for Partner trips
  @ready
  Scenario:Verify Customer Signature screen is shown on driver app for Partner trips
  When I request Partner Portal "Solo" Trip for "Cort Furniture" partner
    |Geofence| Bungii Time   | Customer Phone | Customer Name |
    | atlanta| NEXT_POSSIBLE | 8877661069 | Testcustomertywd_appleMarkBR LutherBR|
  And As a driver "Testdrivertywd_applega_a_steveG Stark_altOnEG" perform below action with respective "Solo Scheduled" Delivery
    | driver1 state|
    | Accepted     |
    | Enroute  |
    | Arrived |
    | Loading Item |
    | Driving To Dropoff |
    | Unloading Item |

    And I wait for 2 minutes
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I select the live trip for "Testcustomertywd_appleMarkBR LutherBR" customer
    And I edit the drop off address
    Then I change the drop off address to "100 Robin Road Extension"
    And I click on "VERIFY" button
    And the "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And the "Bungii Saved!" message is displayed
    When I click on "Close" button
    And I get the new pickup reference generated

    And I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_applega_a_steveG Stark_altOnEG" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I slide update button on "UNLOADING ITEMS" Screen
    When Bungii driver uploads "1" image
    When Bungii Driver "clicks More Options"
    And I click "Customer Signature" button on "update" screen
    Then I should see the "Customer signature" header "Displayed"
    And I click "Customer Signature" button on "update" screen
    Then I should see the customers name under the customer name field

    When I request "Solo" Bungii as a customer in "atlanta" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | 1_DAY_LATER | 9284174823       | Krishna Hoderker|
    And I wait for 2 minutes
    And I click on "View Request" button
    And I wait for 1 minutes
    Then I should see the trip details
    And I click on "Accept" button

    And I should be able to add the text "Signed By customer" in the signed by field
    And I should be able to add customer signature
    And I click on "Clear Signature" button
    And I should be able to add customer signature
    And I click "Submit Data" button on "update" screen
    And I slide update button on "UNLOADING ITEM" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen

    And I wait for 2 minutes
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "trips" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkBR LutherBR" the customer
    And I select "Admin Canceled" from the dropdown
    And I select "Customer initiated - other reason" as the reason from the reason dropdown
    And I click on "Confirm Status" button
    And I click on "Cancel Status" button
    And I wait for 2 minutes
    And I Select "trips" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkBR LutherBR" the customer
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then I should see the customer signature row "Present" in admin portal all delivery details page
    And The customer signature field is "Signature Present"

 #CORE-3606 : Verify Customer signature can be skipped on driver app
  @ready
  Scenario:Verify Customer signature can be skipped on driver app
    When I request Partner Portal "SOLO" Trip for "BestBuy2 service level" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |baltimore| NEXT_POSSIBLE | 8877661070 | Testcustomertywd_appleMarkBS LutherBS|
    And As a driver "TestDrivertywd_applemd_a_billE Stark_bltTwOE" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted     |
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
    When I Switch to "driver" application on "same" devices
    And I am logged in as "TestDrivertywd_applemd_a_billE Stark_bltTwOE" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I click on "Got It" button
    And I slide update button on "UNLOADING ITEMS" Screen
    When Bungii driver uploads "1" image
    And I slide update button on "UNLOADING ITEMS" Screen
    Then I should see the "Customer signature" header "Displayed"
    Then I should see the customers name under the customer name field
    And I click on "Skip Customer Signature" button
    And I slide update button on "UNLOADING ITEMS" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    And I wait for 2 minutes

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "trips" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkBS LutherBS" the customer
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then I should see the customer signature row "Present" in admin portal all delivery details page
    And The customer signature field is "N/A"

    #CORE-3606 : Verify customer signature screen is shown for only the control driver when he/she completes the trip first
  @ready @duo
  Scenario:Verify customer signature screen is shown for only the control driver when he/she completes the trip first
    When I request Partner Portal "Duo" Trip for "Cort Furniture" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      | atlanta| NEXT_POSSIBLE | 8877661071 | Testcustomertywd_appleMarkBT LutherBT|

    And As a driver "Testdrivertywd_applega_a_steveH Stark_altOnEH" and "Testdrivertywd_applega_a_steveI Stark_altOnEI" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I wait for 2 minutes

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And  I search the delivery using "Pickup Reference"
    When I click on the "Edit" button from the dropdown
    And I select the first driver
    And I click on "Remove Driver" button
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_applega_a_bryan Stark_altFour" for the trip
    And I click on "VERIFY" button
    And the "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And the "Bungii Saved!" message is displayed
    When I click on "Close" button
    And I get the new pickup reference generated
    And As a driver "Testdrivertywd_applega_a_steveI Stark_altOnEI" and "Testdrivertywd_applega_a_bryan Stark_altFour" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Driving To Drop-off       | Driving To Drop-off       |

    And I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_applega_a_steveI Stark_altOnEI" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    And I connect to "extra1" using "Driver2" instance
    When I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_applega_a_bryan Stark_altFour" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
   And I click on "Got It" button

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I click on "Got It" button

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "UNLOADING ITEMS" Screen
    When Bungii driver uploads "1" image
    And I slide update button on "UNLOADING ITEMS" Screen
    Then I should see the "Customer signature" header "Displayed"
    Then I should see the customers name under the customer name field
    And I should be able to add the text "Signed By customer" in the signed by field
    And I should be able to add customer signature
    And I click on "Clear Signature" button
    And I should be able to add customer signature
    And I click "Submit" button on "update" screen
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I should be navigated to "Rate duo teammate" screen

    When I Switch to "driver" application on "Driver2" devices
    Then I should see the "Customer signature" header "Not Displayed"
    And I should be navigated to "Rate duo teammate" screen

  #CORE-3606 :Verify customer signature screen is shown only for control driver , even when non control driver completes trip first
 @ready @duo
  Scenario:Verify customer signature screen is shown only for control driver , even when non control driver completes trip first
    When I request Partner Portal "Duo" Trip for "Cort Furniture" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      | atlanta| NEXT_POSSIBLE | 8877661072 | Testcustomertywd_BppleMarkBU LutherBU|
    And As a driver "Testdrivertywd_applega_a_steveJ Stark_altOnEJ" and "Testdrivertywd_applega_a_steveK Stark_altOnEK" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Driving To Drop-off  |  Driving To Drop-off  |
    And I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_applega_a_steveJ Stark_altOnEJ" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    And I connect to "extra1" using "Driver2" instance
    When I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_applega_a_steveK Stark_altOnEK" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I click on "Got It" button
    And I slide update button on "UNLOADING ITEM" Screen
    When Bungii driver uploads "1" image
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I should be navigated to "Rate duo teammate" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I click on "Got It" button
    And I slide update button on "UNLOADING ITEMS" Screen
    When Bungii driver uploads "1" image
    And I slide update button on "UNLOADING ITEMS" Screen
    Then I should see the "Customer signature" header "Displayed"
    Then I should see the customers name under the customer name field
    And I should be able to add the text "Signed By customer" in the signed by field
    And I should be able to add customer signature
    And I click "Submit" button on "update" screen
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I should be navigated to "Rate duo teammate" screen

  #CORE-3606:Verify driver app when admin completes the trip before signature is taken
  @ready
  Scenario:Verify driver app when admin completes the trip before signature is taken
    When I request Partner Portal "Solo" Trip for "Cort Furniture" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      | atlanta| NEXT_POSSIBLE | 8877661073 | Testcustomertywd_appleMarkBV LutherBV|
    And As a driver "Testdrivertywd_applega_a_drvaa Atlanta_aa" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted     |
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
    And I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_applega_a_drvaa Atlanta_aa" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkBV LutherBV" the customer
    And I click on "Edit" link beside live delivery
    And I click on "Edit Delivery Status" radiobutton
    And I click on "Delivery Completed" radiobutton
    And I enter delivery completion date and time as per geofence
    And I click on "CALCULATE COST" button
    Then Confirmation message on edit live delivery pop up should be displayed
    And I click on "CONFIRM CHANGES" button
    Then The "Pick up has been successfully updated." message should be displayed for live delivery
    And I click on "Close" button
    And I wait for 2 minutes
    And I Select "trips" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkBV LutherBV" the customer
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then I should see the customer signature row "Present" in admin portal all delivery details page
    And The customer signature field is "N/A"

    And I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And Bungii Driver "skips to rate customer"


#CORE-3606:Verify driver app when admin completes the trip after signature is taken
  @ready
  Scenario:Verify driver app when admin completes the trip after signature is taken
    When I request Partner Portal "Solo" Trip for "Cort Furniture" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      | atlanta| NEXT_POSSIBLE | 8877661074 | Testcustomertywd_appleMarkBW LutherBW|
    And As a driver "Testdrivertywd_applega_a_drvab Atlanta_ab" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted     |
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
    And I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_applega_a_drvab Atlanta_ab" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I slide update button on "UNLOADING ITEM" Screen
    When Bungii driver uploads "1" image
    And I slide update button on "UNLOADING ITEMS" Screen
    Then I should see the "Customer signature" header "Displayed"
    Then I should see the customers name under the customer name field
    And I should be able to add the text "Signed By customer" in the signed by field
    And I should be able to add customer signature
    And I click "Submit" button on "update" screen

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkBW LutherBW" the customer
    And I click on "Edit" link beside live delivery
    And I click on "Edit Delivery Status" radiobutton
    And I click on "Delivery Completed" radiobutton
    And I enter delivery completion date and time as per geofence
    And I click on "CALCULATE COST" button
    Then Confirmation message on edit live delivery pop up should be displayed
    And I click on "CONFIRM CHANGES" button
    Then The "Pick up has been successfully updated." message should be displayed for live delivery
    And I click on "Close" button
    And I wait for 2 minutes
    And I Select "trips" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkBW LutherBW" the customer
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then I should see the customer signature row "Present" in admin portal all delivery details page
    And The customer signature field is "Signature Present"

    And I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And Bungii Driver "skips to rate customer"