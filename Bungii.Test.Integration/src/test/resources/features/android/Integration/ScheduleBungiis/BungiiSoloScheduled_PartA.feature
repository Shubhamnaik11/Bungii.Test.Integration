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