@android
@SoloScheduled

#These feature will run in kansas geofence
Feature: SoloScheduled

  Background:

  @regression
  Scenario: Verify Customer Can Create A Scheduled Bungii - Also Verify Correct Contact Number Is Displayed On Call And SMS Option
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Accepted     | NEXT_POSSIBLE |
    When I Switch to "customer" application on "same" devices
    And I am logged in as "valid" customer
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
   # And I wait for Minimum duration for Bungii Start Time
    And Bungii Driver "Start Schedule Bungii" request
    And I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Enroute screen"

    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app
    And Trip Information should be correctly displayed on "EN ROUTE" status screen for customer

    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "Enroute screen"
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"
    And Trip Information should be correctly displayed on "EN ROUTE" status screen for driver

    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Arrived screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app
    And Trip Information should be correctly displayed on "ARRIVED" status screen for customer

    When I Switch to "driver" application on "same" devices
    And Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"
    And Trip Information should be correctly displayed on "ARRIVED" status screen for driver

    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Loading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Loading Item screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app
    And Trip Information should be correctly displayed on "LOADING ITEM" status screen for customer

    When I Switch to "driver" application on "same" devices
    And Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"
    And Trip Information should be correctly displayed on "LOADING ITEM" status screen for driver

    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Driving to DropOff screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app
    And Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for customer

    When I Switch to "driver" application on "same" devices
    And Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"
    And Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for driver

    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Unloading Item screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app
    And Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for customer

    When I Switch to "driver" application on "same" devices
    And Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"
    And Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for driver

    When Bungii Driver "slides to the next state"
    And I Switch to "customer" application on "same" devices
    Then Bungii customer should see "correct details" on Bungii completed page
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    And Bungii Driver "completes Bungii"
    And I Select "HOME" from driver App menu


  @sanity
  @regression
  Scenario: Verify Customer Can Create Scheduled Bungii
    Given I am logged in as "valid" customer
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I Select "HOME" from driver App menu
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "Home" link

    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I select Bungii Time as "next possible scheduled"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I check if the customer is on success screen
    And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

    And I Switch to "driver" application on "same" devices
    And I tap on "Available Trips link" on Driver Home page

    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
  #  And I wait for Minimum duration for Bungii Start Time

    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "Enroute screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Enroute screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Arrived screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Loading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Loading Item screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Driving to DropOff screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Unloading Item screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    And I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    And I Switch to "driver" application on "same" devices
    Then Bungii Driver "completes Bungii"
    And I Select "HOME" from driver App menu


  @regression
  Scenario: Verify When Bungii Is Cancelled By Admin It Is Removed From The Scheduled Trip List On Drivers App
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Accepted     | NEXT_POSSIBLE |
    When I Switch to "customer" application on "same" devices
    And I am logged in as "valid" customer

    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I Cancel Bungii with following details
      | Charge | Comments |
      | 0      | TEST     |
    Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
    And Bungii must be removed from the List

    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    Then Bungii must be removed from "MY BUNGIIS" screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  @regression
  Scenario: Verify Customer cannot Schedule A Bungii At A Same Time Equaling The Already Scheduled Bungii

    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Scheduled    | NEXT_POSSIBLE |
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I Switch to "customer" application on "same" devices
    And I am logged in as "valid" customer
    And I tap on "Menu" > "Home" link
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I select Bungii Time as "OLD BUNGII TIME"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then I tap on "ok on already scheduled bungii message" on Bungii estimate
    When I tap on "back" on Bungii estimate
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select already scheduled bungii
    Then I Cancel selected Bungii
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  @regression
  Scenario: Verify Customer Can Cancel The Scheduled Bungii
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Scheduled    | NEXT_POSSIBLE |
    When I am logged in as "valid" customer
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select already scheduled bungii
    And I Cancel selected Bungii
    And I tap on "Menu" > "MY BUNGIIS" link
    Then Bungii must be removed from "MY BUNGIIS" screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: Verify Status Of Scheduled Bungii Trip In Scheduled Bungiis Menu Screen When Required Drivers Have Not Accepted It
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Scheduled    | NEXT_POSSIBLE |
    When I am logged in as "valid" customer
    And I tap on "Menu" > "MY BUNGIIS" link
    Then The status on "MY BUNGIIS" should be displayed as "Contacting Drivers"
    And I select already scheduled bungii
    Then trips status on bungii details should be "driver 1 - contacting drivers"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  @regression
  Scenario: Verify Status Of Scheduled Bungii Trip In Scheduled Bungiis Menu Page When Required Drivers Have Not Accepted It - Scenario:DUO
    When I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I tap on "Menu" > "MY BUNGIIS" link
    Then The status on "MY BUNGIIS" should be displayed as "Contacting Drivers"
    And I select already scheduled bungii
    Then trips status on bungii details should be "driver 1 - contacting drivers"
    Then trips status on bungii details should be "driver 2 - contacting drivers"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |


  @regression

  Scenario:Verify Status In Scheduled Bungiis Screen When Only One Driver Accepts The Trip
    When I Switch to "driver" application on "same" devices
    Then As a driver "Testdrivertywd_appleks_ra_four Kent" I log in

    And I Switch to "customer" application on "same" devices
    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      |               |
    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I tap on "Menu" > "MY BUNGIIS" link
    Then The status on "MY BUNGIIS" should be displayed as "Contacting Drivers"
    And I select already scheduled bungii
    Then trips status on bungii details should be "driver1 name"
    Then trips status on bungii details should be "driver 2 - contacting drivers1"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |

  @regression
  @failed123
  Scenario: Verify Status On Customers Scheduled Bungiis Screen When Both Drivers Have Accepted Trip
    When I Switch to "driver" application on "same" devices
    Then As a driver "Testdrivertywd_appleks_ra_four Kent" I log in

    And I Switch to "customer" application on "same" devices
    When I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I tap on "Menu" > "MY BUNGIIS" link
    Then The status on "MY BUNGIIS" should be displayed as "estimated cost"
    And I select already scheduled bungii
    Then trips status on bungii details should be "driver1 name"
    Then trips status on bungii details should be "driver2 name"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |


  @regression
  Scenario: Verify If Customer Receives Notification Once Required Number Of Drivers Accepts The Scheduled Trip - Scenario:Solo
    When I Switch to "driver" application on "same" devices
    Then As a driver "Testdrivertywd_appleks_ra_four Kent" I log in

    And I Switch to "customer" application on "same" devices
    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I tap on "Menu" > "MY BUNGIIS" link
    When I Switch to "driver" application on "same" devices
    And As a driver "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "Solo Scheduled" trip
      | driver1 state |
      | Accepted      |
    Then I click on notification for "SCHEDULED PICKUP ACCEPTED"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |


  @regression
  Scenario: Verify If Customer Receive Notification Once Required Number Of Drivers Accepts Scheduled Trip - Scenario:DUO
    When I Switch to "driver" application on "same" devices
    Then As a driver "Testdrivertywd_appleks_ra_four Kent" I log in

    And I Switch to "customer" application on "same" devices
    And I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I tap on "Menu" > "MY BUNGIIS" link
    When I Switch to "driver" application on "same" devices
    And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    Then I click on notification for "SCHEDULED PICKUP ACCEPTED"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |


  @regression

  Scenario:  Verify Customer Cannot Schedule Bungii for A Time That Is Outside Working Hours - Scenario:SOLO
    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    When I try to schedule bungii for "today - after working hour" for "SOLO"
    Then User should see message "OUTSIDE BUISSNESS HOUR" text on the screen
    When I try to schedule bungii for "tommorow - before working hour" for "SOLO"
    Then User should see message "OUTSIDE BUISSNESS HOUR" text on the screen

  @regression
  Scenario: Verify Customer Cannot Schedule Bungii For A Time That Is Outside Working Hours - Scenario:DUO
    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    Then I should see "two drivers selected" on Bungii estimate
    Then I tap on "Get Estimate button" on Bungii estimate
    When I try to schedule bungii for "today - after working hour" for "DUO"
    Then User should see message "OUTSIDE BUISSNESS HOUR" text on the screen
    When I try to schedule bungii for "tommorow - before working hour" for "DUO"
    Then User should see message "OUTSIDE BUISSNESS HOUR" text on the screen

  @regression
  Scenario:  Verify Customer Can Schedule Bungii Only 5 Days Ahead Including Current Date - Scenario:SOLO
    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    When I confirm trip with following detail
      | Day | Trip Type |
      | 1   | SOLO      |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I click "Done" button on "Success" screen

    And I tap on "Menu" > "Home" link
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    When I confirm trip with following detail
      | Day | Trip Type |
      | 2   | SOLO      |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I click "Done" button on "Success" screen

    And I tap on "Menu" > "Home" link
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    When I confirm trip with following detail
      | Day | Trip Type |
      | 3   | SOLO      |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I click "Done" button on "Success" screen

    And I tap on "Menu" > "Home" link
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    When I confirm trip with following detail
      | Day | Trip Type |
      | 4   | SOLO      |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I click "Done" button on "Success" screen

    And I tap on "Menu" > "Home" link
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then I try to schedule bungii for "today+5"
      | Day | Trip Type |
      | 5   | SOLO      |
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |


  @regression
  Scenario:  Verify Customer Can Schedule Bungii Only 5 days ahead Including Current Date - Scenario:Duo
    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    Then I should see "two drivers selected" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    And I confirm trip with following detail
      | Day | Trip Type |
      | 1   | DUO       |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I click "Done" button on "Success" screen

    And I tap on "Menu" > "Home" link
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    Then I should see "two drivers selected" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    And I confirm trip with following detail
      | Day | Trip Type |
      | 2   | DUO       |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I click "Done" button on "Success" screen

    And I tap on "Menu" > "Home" link
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    Then I should see "two drivers selected" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    And I confirm trip with following detail
      | Day | Trip Type |
      | 3   | DUO       |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I click "Done" button on "Success" screen

    And I tap on "Menu" > "Home" link
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    Then I should see "two drivers selected" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    And I confirm trip with following detail
      | Day | Trip Type |
      | 4   | DUO       |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I click "Done" button on "Success" screen

    And I tap on "Menu" > "Home" link
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    Then I should see "two drivers selected" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    Then I try to schedule bungii for "today+5"
      | Day | Trip Type |
      | 5   | DUO       |
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |


  @regression
  Scenario: Verify Trip limit (150 miles) For Delivery
    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I enter "kansas pickup and dropoff locations less than 150 miles" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened

    And I click on device "Back" button
    And I enter "kansas pickup and dropoff locations equal to 150 miles" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened

    And I click on device "Back" button
    And I enter the "kansas pickup and dropoff locations greater than 150 miles" on the Bungii estimate
    Then Alert message with Oops! We focus on local deliveries within 150 miles of pickup. It looks like this trip is a little outside our scope. text should be displayed


  @regression
  Scenario: Verify Customer Cannot Schedule Bungii That Overlaps With Another Scheduled Trip TELET Time - Scenario:Solo
    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I get TELET time of of the current trip

    And I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I enter "kansas pickup and dropoff locations less than 150 miles" on Bungii estimate

    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened
    When I confirm trip with following details
      | Day | Trip Type | Time                |
      | 0   | SOLO      | <TIME WITHIN TELET> |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then Alert message with Hmm, it looks like you already have a Bungii scheduled. At this time, our system only allows one Bungii at a time. text should be displayed

    When I click on device "Back" button
    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened

    When I confirm trip with following details
      | Day | Trip Type | Time          |
      | 0   | SOLO      | <AFTER TELET> |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I click "Done" button on "Success" screen

    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |

  @regression
  Scenario: Verify Customer Cannot Schedule Bungii That Overlaps With Another Scheduled Trip TELET Time - Scenario:Duo
    When I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I get TELET time of of the current trip

    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    Then I should see "two drivers selected" on Bungii estimate

    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened
    And I confirm trip with following details
      | Day | Trip Type | Time                |
      | 0   | DUO       | <TIME WITHIN TELET> |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then Alert message with Hmm, it looks like you already have a Bungii scheduled. At this time, our system only allows one Bungii at a time. text should be displayed

    When I click on device "Back" button
    And I should see "two drivers selected" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened

    When I confirm trip with following details
      | Day | Trip Type | Time          |
      | 0   | DUO       | <AFTER TELET> |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I click "Done" button on "Success" screen

    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |

  #@DUO_SCH_DONOT_ACCEPT
  @regression
  Scenario:Verify If Customer Receieve Notification After Admin Researches For Drivers And Both Drivers Accepts It
  #  Given I have already scheduled bungii with "DUO_SCH_DONOT_ACCEPT" label
    When I Switch to "driver" application on "same" devices
    Then As a driver "Testdrivertywd_appleks_ra_four Kent" I log in

    And I Switch to "customer" application on "same" devices
    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                    |
      | now         | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test |
    When I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
    Then I wait for "3" mins
    When I Switch to "driver" application on "same" devices
    When I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I verify status and researches Bungii with following details
      | label                | Status of Trip      |
      | DUO_SCH_DONOT_ACCEPT | Driver(s) Not Found |

    And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    When I Switch to "driver" application on "ORIGINAL" devices
    Then I click on notification for "SCHEDULED PICKUP ACCEPTED"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |

  #@regression
  @ready
  Scenario:Verify Alert Message Is Displayed When Customer Tries To Contact Driver Who Is Currently Has A Ongoing Bungii.
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time     |
      | Kansas   | Accepted     | 0.75 hour ahead |
    And I Open "customer" application on "same" devices
    When I am on customer Log in page
    When I am logged in as "valid" customer
    And I Open "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    Then I click "Go Online" button on Home screen on driver app
    And I Switch to "customer" application on "same" devices

    When I request "Solo Ondemand" Bungii as a customer in "kansas" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                    | Customer label |
      | now         | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test | 2              |
    And I Switch to "customer" application on "same" devices
    And I click on notification for "Driver" for "on demand trip"
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" on the alert message
    And I click "ACCEPT" button on "Bungii Request" screen

    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select 1st trip from scheduled bungii
    When I wait for 1 hour for Bungii Schedule Time
    When I try to contact driver using "call driver1"
    Then user is alerted for "driver finishing current bungii"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8805368840      |

  @regression
  Scenario:Verify Alert Message Is Displayed When Customer Tries To Contact Driver More Than One Hour From Scheduled Time
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Accepted     | 1 hour ahead |

    And I Switch to "customer" application on "same" devices
    When I am on customer Log in page
    And I am logged in as "valid" customer
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select already scheduled bungii
    When I try to contact driver using "sms driver1"
    Then user is alerted for "more than 1 hour from scheduled time"
    And correct support details should be displayed to customer on "SMS" app
    When I try to contact driver using "call driver1"
    Then user is alerted for "more than 1 hour from scheduled time"
    And correct support details should be displayed to customer on "SMS" app
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  #@regression
  @ready
  Scenario: Verify Customer Can Contact Controlled Driver When Non-control Driver Starts the trip

    When I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_five Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
      |               | Enroute       |
    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select already scheduled bungii
    When I try to contact driver using "call driver2"
    Then correct details should be displayed to driver on "Calling" app
    When I try to contact driver using "call driver1"
    Then correct details should be displayed to driver on "Calling" app
    When I try to contact driver using "sms driver1"
    Then correct details should be displayed to driver on "SMS" app
    When I try to contact driver using "sms driver2"
    Then correct details should be displayed to driver on "SMS" app
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |

  #@regression
  @ready
  Scenario: Verify Customer Can See Text Stating That Driver Can Be Contacted On The Bungii Details Screen Only When The Trip Has Been Accepted By Required Number Of Drivers
    When I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I tap on "Menu" > "MY BUNGIIS" link
    When I Switch to "driver" application on "same" devices
    And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I Switch to "customer" application on "same" devices
    When I am on customer Log in page
    And I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select already scheduled bungii
    Then I verify that text "You will have the ability to contact your drivers when the Bungii begins" is displayed
    Then I cancel all bungiis of customer

      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |

  #@regression
  @ready
  Scenario: Verify Scheduled Bungii Notification Info Is Correct (Estimaged earnings - Date etc.)
    When I clear all notification
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    Then I click "Go Online" button on Home screen on driver app
    When I Switch to "customer" application on "same" devices

    And I request "Solo Scheduled" Bungii as a customer in "Kansas" geofence
      | Bungii Time | Customer Phone | Customer Name                    | Customer Password |
      | now         | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    Then I click on notification for the "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then I should be navigated to "SCHEDULED BUNGII" screen
    And "correct scheduled trip details" should be displayed on Bungii request screen
    When I click "ACCEPT" button on SCHEDULED BUNGII screen
    Then I should be navigated to "SCHEDULED BUNGIIS" screen
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |

  @regression

  Scenario:Verify Driver Recieves Scheduled Bungii Request While In Offline State
    When I clear all notification
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    Then I click "Go Offline" button on Home screen on driver app
    When I Switch to "customer" application on "same" devices
    And I request "Solo Scheduled" Bungii as a customer in "Kansas" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                    |
      | now         | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test |
    Then I click on notification for the "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then I should be navigated to "SCHEDULED BUNGII" screen
    When I click "REJECT" button on SCHEDULED BUNGII screen
    When I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
    And I Switch to "customer" application on "same" devices
    And I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                    |
      | now         | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test |
    And I click on notification for the "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then I should be navigated to "SCHEDULED BUNGII" screen
    When I click "REJECT" button on SCHEDULED BUNGII screen
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |


  @regression
  Scenario:Verify Driver Is Not Able To Accept The Request If Trip Is Already Accepted By The Required Number Of Drivers
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |

    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip

    And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |

    And I click "ACCEPT" button on Bungii Request screen
    Then user is alerted for "PICKUP REQUEST NO LONGER AVAILABLE"
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |


  #@regression
@ready
  Scenario: Verify If Driver receives More Than One Requests He Is Not Able To Accept The Bungii If He Has Already Accepted A Bungii whos TELET Time Overlaps - Scenario:Solo
    Given I Switch to "customer" application on "same" devices
    #trip 1
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Scheduled    | 15 min ahead |
     #trip 2
    Given I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    | Customer label |
      | NEXT_POSSIBLE | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test | 2              |
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I Select "AVAILABLE TRIPS" from driver App menu
    Then I should able to see "two" available trip
    And I Select Trip from available trip
    And I click "ACCEPT" button on Bungii Request screen
    Then I should be navigated to "AVAILABLE TRIPS" screen
    Then I should able to see "zero" available trip

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8805368840      |

  #@regression
  @ready
  Scenario: Verify If Driver receives More Than One Requests He Is Not Able To Accept The Bungii If He Has Already Accepted A Bungii whos TELET Time Overlaps - Scenario:duo
    Given I Switch to "customer" application on "same" devices
    #trip 1
    Given I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time  | Customer Phone | Customer Password | Customer Name                    |
      | 15 min ahead | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test |
     #trip 2
    Given I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                     | Customer label |
      | NEXT_POSSIBLE | 8888888881     | Cci12345          | Testcustomertywd_appleRicha1 Test | 2              |
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I Select "AVAILABLE TRIPS" from driver App menu
    Then I should able to see "two" available trip
    And I Select Trip from available trip
    And I click "ACCEPT" button on Bungii Request screen
    Then I should be navigated to "AVAILABLE TRIPS" screen
    Then I should able to see "zero" available trip

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8888888881      |


  @regression
  Scenario: Verify Status Of Scheduled Bungii In the Scheduled Trip Screen When Only One Driver Accepts The Trip
    And I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |

    And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      |               |

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :8888881019 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    Then trips status should be "Contacting Other Driver"
    And I Select Trip from driver scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    And "correct duo scheduled trip details" should be displayed on Bungii request screen
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |


  @regression

  Scenario:Verify Details In The Bungii Details Screen When Required Number Of Drivers Accept The Trip

    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |

    And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :8888881019 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    Then trips status should be "estimated cost of duo trip"
    And I Select Trip from driver scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    And "correct duo scheduled trip details" should be displayed on Bungii request screen
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |

  #@regression
  @ready
  Scenario: Verify Customer Receives Notification When Control Driver Starts Duo Bungii
    When I clear all notification
    And I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    Then I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I tap on "Menu" > "MY BUNGIIS" link
    When I Switch to "driver" application on "same" devices
    And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Enroute       | Accepted      |
    When I Switch to "customer" application on "same" devices
    And I click on notification for "Customer" for "DRIVERS ARE ENROUTE"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |

  #@regression
  @ready
  Scenario:  Verify Customer Receives Notification When Control Driver Starts Solo Bungii
    When I clear all notification
    And I request "Solo Scheduled" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    Then I Switch to "customer" application on "same" devices
    Given  I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I tap on "Menu" > "MY BUNGIIS" link
    When I Switch to "driver" application on "same" devices
    And As a driver "Testdrivertywd_appleks_rathree Test" perform below action with respective "Solo Scheduled" trip
      | driver1 state |
      | Accepted      |
      | Enroute       |
    When I Switch to "customer" application on "same" devices
    And I click on notification for "Customer" for "DRIVERS ARE ENROUTE"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |

  @regression
  Scenario: Verify Driver Doesnt Receive Scheduled Trip Request If His Home Is Over 30 Mins Away From Pickup Location
    When I clear all notification
    When I Switch to "customer" application on "same" devices
    Given  I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :8888881019 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I Switch to "customer" application on "same" devices

    And I enter "kansas pickup and dropoff locations greater than 30mins" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then I should be navigated to "Estimate" screen
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I select Bungii Time as "next possible scheduled"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I check if the customer is on success screen
    And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
    And I Switch to "customer" application on "same" devices
    When  I am on customer Log in page
    And I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I tap on "Menu" > "MY BUNGIIS" link
    Then Bungii must be removed from "MY BUNGIIS" screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8805368840 |                 |

  #@regression
  @ready
  Scenario: Re-searched trip request should show Urgent Notification text if admin re-searches less than one hour from scheduled trip time or for trip time between 24 hours prior to current time
    When I clear all notification
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Accepted     | NEXT_POSSIBLE |
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    When I Switch to "customer" application on "same" devices
    Then I wait for "2" mins
    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I remove current driver and researches Bungii
    When I switch to "ORIGINAL" instance
    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    When I Switch to "customer" application on "same" devices
    And Notification for "driver" for "URGENT SCHEDULED PICKUP AVAILABLE" should be displayed
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario:Verify TELET Is Calculated Correctly (Initial Request Time +  (Estimated Duration(1.5)) + 30 Minutes) For Solo Trip
    When I request "Solo Scheduled" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I get TELET time of of the current trip
    Then Telet time of current trip should be correctly calculated

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression

  Scenario: Verify Re-searched Trip Request Doesnt Show Urgent Notification Text If Is More Than One Hour From The Scheduled Trip Time
    When I clear all notification
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Accepted     | 2 hour ahead |
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    When I Switch to "customer" application on "same" devices
    Then I wait for "2" mins
    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I remove current driver and researches Bungii
    When I switch to "ORIGINAL" instance
    And I should not get notification for "driver" for "URGENT SCHEDULED PICKUP AVAILABLE"
    When I Switch to "customer" application on "same" devices
    Then Notification for "driver" for "SCHEDULED PICKUP AVAILABLE" should be displayed
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: Verify TELET Is Calculated Correctly (Initial Request Time +  (Estimated Duration(1.5)) + 30 Minutes) For Duo Trip
    When I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I get TELET time of of the current trip
    Then Telet time of current trip should be correctly calculated

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  #@regression
  @ready
  Scenario: Verify Validation Message Is Shown If Driver Tries To Start Bungii More Than 60 Mins Before The Scheduled Time
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Accepted     | 1 hour ahead |
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    Then User should see message "60 MINS BEFORE SCHEDULE TRIP TIME" text on the screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: Verify Customer Cannot Schedule Bungii That Overlaps With Another Scheduled Trips TELET Time - Scenario:Duo
    When I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I get TELET time of of the current trip

    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate

    And I tap on "Get Estimate button" on Bungii estimate
    And I confirm trip with following details
      | Day | Trip Type | Time                |
      | 0   | DUO       | <TIME WITHIN TELET> |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then Alert message with Hmm, it looks like you already have a Bungii scheduled. At this time, our system only allows one Bungii at a time. text should be displayed

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  #@regression
  @ready
  Scenario: Verify Driver Receives Alert Stating That The Trip Has Already Been Accepted By Him If He Receives Request Notification After Accepting The Trip From Available Trips
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :8888881019 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    Given I Switch to "customer" application on "same" devices

    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test |

    Then I wait for "1" mins
    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip
    When I click "ACCEPT" button on Bungii Request screen
    And I Switch to "customer" application on "same" devices
    And I click on notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then user is alerted for "PICKUP ALREADY ACCEPTED BY YOU"
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |

  @regression
  Scenario: Verify Driver Can Start Bungii Within 60 Mins Of The Scheduled Time If Required Number Of Drivers Accepts It
    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      |               |
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :9999999991 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And I start selected Bungii
    Then I should see "REQUIRED DRIVER NOT ACCEPTED" on screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  #@regression
  @ready
  Scenario:Verify Driver Can Start Bungii If The Customer Is Currently In An Ongoing Trip - Scenario:Solo
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Accepted     | 1 hour ahead |
    Given that ondemand bungii is in progress
      | geofence | Bungii State | Driver label | Trip Label |
      | Kansas   | Enroute      | driver 2     | 2          |
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver

    And I Select "SCHEDULED BUNGIIS" from driver App menu
    When I wait for 1 hour for Bungii Schedule Time
    And I Select Trip from driver scheduled trip
    And I start selected Bungii
    Then I should see "CUSTOMER HAS ONGOING BUNGII" on screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  #@regression
  @ready
  Scenario:Verify Driver Can Start Bungii If The Customer Is Currently In An Ongoing Trip - Scenario: Duo
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  | Customer        | Driver1         | Driver2         |
      | Kansas   | Accepted     | 1 hour ahead | Kansas customer | Kansas driver 1 | Kansas driver 2 |
    Given that ondemand bungii is in progress
      | geofence | Bungii State | Driver label | Trip Label |
      | Kansas   | Enroute      | driver 2     | 2          |
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Kansas driver 1" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    When I wait for 1 hour for Bungii Schedule Time
    And I Select Trip from driver scheduled trip
    And I start selected Bungii
    Then I should see "CUSTOMER HAS ONGOING BUNGII" on screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: Verify If Incoming Ondemand Trip TELET Overlaps Scheduled Trip Telet Then Request Should Not Be Sent To driver.
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time    |
      | kansas   | Accepted     | 0.5 hour ahead |
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I tap on "Go Online button" on Driver Home page
    When I request "Solo Ondemand" Bungii as a customer in "kansas" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                    | Customer label |
      | now         | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test | 2              |

    Then I should not get notification for ON DEMAND TRIP
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |

  #@regression
  @ready
  Scenario: Verify If TELET Of Re-searched Trip
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Accepted     | NEXT_POSSIBLE |
    And I get TELET time of of the current trip
    Then Telet time of current trip should be correctly calculated
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    Then I wait for "1" mins

    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I remove current driver and researches Bungii
    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    Then Telet time of research trip should be not be same as previous trips
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression

  Scenario: Verify If Incoming Scheduled Trip Request TELET (Trip A) Overlaps Start Time Of Previously Scheduled Trip (Trip B) Then Driver Doesnt Receive Notification Or Offline SMS

    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Accepted     | NEXT_POSSIBLE |
    And I get TELET time of of the current trip

    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    When I enter customers "9871450107" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate

    And I tap on "Get Estimate button" on Bungii estimate
    And I confirm trip with following details
      | Day | Trip Type | Time                              |
      | 0   | DUO       | <TIME WITHIN TELET OF CUSTOMER 1> |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
    Then I should not get notification for SCHEDULED PICKUP AVAILABLE
    And I Switch to "driver" application on "same" devices
    And I tap on "Available Trips link" on Driver Home page
    Then I should able to see "zero" available trip
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone      |
      | CUSTOMER1_PHONE | CUSTOMER_PHONE_EXTRA |


  #@regression
  @ready
  Scenario: Verify If Incoming Scheduled Trip Request Start Time (Trip A) Overlaps TELET Of Previously Scheduled Trip (Trip B) Then Driver Doesnt Receive Notification Or offline SMS

    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Accepted     | NEXT_POSSIBLE |
    And I get TELET time of of the current trip

    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid" customer
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate

    And I tap on "Get Estimate button" on Bungii estimate
    And I confirm trip with following details
      | Day | Trip Type | Time                                               |
      | 0   | DUO       | <TELET TIME OVERLAP WITH START TIME OF CUSTOMER 1> |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
    Then I should not get notification for SCHEDULED PICKUP AVAILABLE
    And I Switch to "driver" application on "same" devices
    And I tap on "Available Trips link" on Driver Home page
    Then I should able to see "zero" available trip
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone      |
      | CUSTOMER1_PHONE | CUSTOMER_PHONE_EXTRA |

  @regression

  Scenario: Verify If Incoming On-demend Trip Request TELET (Trip A) Overlaps Start Time Of Previously Scheduled Trip (Trip B) Then Driver Doesnt Receive Notification Or offline SMS
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Accepted     | NEXT_POSSIBLE |
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I tap on "Go Online button" on Driver Home page
    When I request "Solo Ondemand" Bungii as a customer in "kansas" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                    | Customer label |
      | now         | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test | 2              |

    Then I should not get notification for ON DEMAND TRIP
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |


  #@regression
  @ready
  Scenario:Verify Customer Notification - 2 hours before scheduled trip
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time    |
      | kansas   | Accepted     | 1.5 hour ahead |
    And I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid" customer
    And I wait for Minimum duration for current Bungii to be T-2 hours
    And I Switch to "driver" application on "same" devices
    Then I click on notification for "SCHEDULED PICKUP ACCEPTED"

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  #@regression
  @ready
  Scenario: Verify If Control Driver Is Allowed To Complete The Trip And Proper Summary Is Shown
    Given that duo schedule bungii is in progress
      | geofence | Bungii State    | Bungii Time   | Customer        | Driver1         | Driver2         |
      | Kansas   | unloading items | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |

    And I Switch to "customer" application on "same" devices
    When I am on customer Log in page
    And I am logged in as "valid kansas" customer

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "kansas driver 1" driver
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"

    When I Switch to "customer" application on "same" devices
    Then Bungii customer should see "correct details for duo trip" on Bungii completed page
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate

    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details for duo trip" on Bungii completed page
    And I click "On To The Next One" button on the "Bungii Completed" screen


  @regression
  Scenario: Verify If Non control Driver Completes The Trip First Then He Is Shown With Waiting Screen Till The Control Driver Completes And The Correct Summary Is Shown Thereafter
    When I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state  | driver2 state  |
      | Unloading Item | Unloading Item |

    And I Switch to "customer" application on "same" devices
    When I am on customer Log in page
    And I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :9999999991 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"
    Then non control driver should see "waiting for other driver" screen

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Unloading Item screen"
	#control driver complete bungii
    And As a driver "Testdrivertywd_appleks_rathree Test" perform below action with respective "Duo Scheduled" trip
      | driver1 state    |
      | Bungii Completed |
    Then Bungii customer should see "correct details for duo trip" on Bungii completed page
    And I tap on "OK on complete" on Bungii estimate
    And I click "I DON'T LIKE FREE MONEY" button on the "Promotion" screen

    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details for duo trip" on Bungii completed page
    And I click "On To The Next One" button on the "Bungii Completed" screen

  #@regression
  @ready
  Scenario:Verify If Incoming Scheduled Request Start Time (Trip 3) Overlaps With TELET Of Accepted Stacked Request (Trip 2) Then Driver Doesn't Receive Scheduled Notification Or offline SMS
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | kansas   | Enroute      |
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I Switch to "customer" application on "same" devices

    When I request "Solo Ondemand" Bungii as a customer in "kansas" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                    | Customer label |
      | now         | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test | 2              |

    Then I click on notification for "STACK TRIP"
    And Bungii Driver "view stack message" request
    And I tap on the "ACCEPT" Button on Bungii Request screen
    And I get TELET time of currrent trip of customer 2

    And I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    When I enter customers "9999990069" Phone Number

    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I confirm trip with following details
      | Day | Trip Type | Time                              |
      | 0   | SOLO      | <TIME WITHIN TELET OF CUSTOMER 2> |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
    Then I should not get notification for SCHEDULED PICKUP AVAILABLE
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8805368840      |

  @regression

  Scenario: Verify Minimum Scheduled Time For Solo Trip
    Given I am on customer Log in page
    When I am logged in as "New" customer
    And I enter "San Francisco pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then I should see the minimum scheduled time for Solo Bungii displayed on the Estimate page

#####################################################################################################################
  #@regression
  @ready
  Scenario: Verify Customer Can Cancel Through SMS To Admin after 2 hour processing is over (Irrespective Of No. Of Required Drivers Have Accepted Or Not)
    When I Switch to "driver" application on "same" devices
    Then As a driver "Testdrivertywd_appleks_ra_four Kent" I log in

    And I Switch to "customer" application on "same" devices
    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    When I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I tap on "Menu" > "MY BUNGIIS" link
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
    Then I wait for "2" mins
    And I select already scheduled bungii
    When I Cancel selected Bungii
    Then correct details should be displayed on the "ADMIN-SMS" app

    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I Cancel Bungii with following details
      | Charge | Comments |
      | 0      | TEST     |
    Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
    And Bungii must be removed from the List
    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    Then Bungii must be removed from "MY BUNGIIS" screen

  @regression
  Scenario:Verify Customer Can Cancel Through SMS To Admin If No driver Accepts And Processing Gets Over - Scenario:Solo
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas1  | Scheduled    | NEXT_POSSIBLE |

    When I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid kansas" customer
    And I tap on "Menu" > "MY BUNGIIS" link
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
    Then I wait for "2" mins
    And I select already scheduled bungii
    When I Cancel selected Bungii
    Then correct details should be displayed on the "ADMIN-SMS" app
 #   And I click "TOP BACK" button on "Bungii Details" screen

    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I Cancel Bungii with following details
      | Charge | Comments |
      | 0      | TEST     |
    Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
    And Bungii must be removed from the List
    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    Then Bungii must be removed from "MY BUNGIIS" screen

  @regression
  Scenario: Verify Customer Can Cancel Through SMS To Admin If No driver Accepts And Processing Gets Over - Scenario:Duo
    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8888888881     | Testcustomertywd_appleRicha Test | Cci12345          |

    When I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid kansas" customer
    And I tap on "Menu" > "MY BUNGIIS" link
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
    Then I wait for "2" mins
    And I select already scheduled bungii
    When I Cancel selected Bungii
    Then correct details should be displayed on the "ADMIN-SMS" app

    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I Cancel Bungii with following details
      | Charge | Comments |
      | 0      | TEST     |
    Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
    And Bungii must be removed from the List
    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    Then Bungii must be removed from "MY BUNGIIS" screen

  #@regression
  @ready
  Scenario: Verify Customer Can Cancel Through SMS To Admin If Only One Driver Accepts And Processing Gets Over
    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8888888881     | Testcustomertywd_appleRicha Test | Cci12345          |
    And As a driver "Testdrivertywd_appleks_rathree Test" perform below action with respective "Duo Scheduled" trip
      | driver1 state |
      | Accepted      |

    When I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid kansas" customer
    And I tap on "Menu" > "MY BUNGIIS" link
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
    Then I wait for "2" mins
    And I select already scheduled bungii
    When I Cancel selected Bungii
    Then correct details should be displayed on the "ADMIN-SMS" app

    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I Cancel Bungii with following details
      | Charge | Comments |
      | 0      | TEST     |
    Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
    And Bungii must be removed from the List

    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    Then Bungii must be removed from "MY BUNGIIS" screen

  #@regression
  @ready
  Scenario: Verify Customer Can Cancel Through SMS To Admin If Required Number Of Drivers Have Accepted The Trip  - scenario : duo
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time     | Customer        | Driver1         | Driver2         |
      | kansas   | Accepted     | 0.75 hour ahead | Kansas customer | Kansas driver 1 | Kansas driver 2 |
    When I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid kansas" customer
    And I tap on "Menu" > "MY BUNGIIS" link
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
    Then I wait for "2" mins
    And I select already scheduled bungii
    When I Cancel selected Bungii
    Then correct details should be displayed on the "ADMIN-SMS" app

    And I Cancel Bungii with following details
      | Charge | Comments |
      | 0      | TEST     |
    Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
    And Bungii must be removed from the List

    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    Then Bungii must be removed from "MY BUNGIIS" screen

   #Date: 22-01-2020
  @regression

  Scenario:Verify When Bungii Is Not Started Driver Can Cancel Scheduled Bungii From The App - Scenario:solo
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas1  | Accepted     | NEXT_POSSIBLE |
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "kansas driver 1" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    When Bungii Driver "cancels Bungii request"
    Then user is alerted for "FOR EMERGENCY CONTACT SUPPORT LINE"
    And correct details should be displayed on the "SMS FOR CANCEL INCASE OF EMERGENCEY" app

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression

  Scenario:Verify When Bungii Is Not Started Driver Can Cancel Scheduled Bungii From The App - Scenario:duo
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | kansas   | Accepted     | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |

    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Kansas driver 1" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    When Bungii Driver "cancels Bungii request"
    Then user is alerted for "FOR EMERGENCY CONTACT SUPPORT LINE"
    And correct details should be displayed on the "SMS FOR CANCEL INCASE OF EMERGENCEY" app

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: Verify Control Driver Can Cancel Duo Bungii From The App In The First Two States Of Started Bungii - Scenario:enroute
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | kansas   | enroute      | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |
    When I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid kansas 2" customer
    Then for a Bungii I should see "Enroute screen"

    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Kansas driver 1" driver
    Then Bungii driver should see "Enroute screen"

    And I click the "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on the alert message
    And I Select "HOME" from driver App menu
    Then Bungii driver should see "Home screen"

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then "Home" page should be opened

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: Verify Control Driver Can Cancel Duo Bungii From The App In The First Two States Of Started Bungii - Scenario:arrived
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | kansas   | arrived      | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |
    When I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid kansas 2" customer
    Then for a Bungii I should see "Arrived screen"

    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Kansas driver 1" driver
    Then Bungii driver should see "Arrived screen"
    And I click the "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on the alert message
    And I Select "HOME" from driver App menu
    Then Bungii driver should see "Home screen"

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then "Home" page should be opened

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: Verify Non-Control Driver Can Cancel Duo Bungii From The App In The First Two States Of Started Bungii - Scenario:enroute
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | kansas   | enroute      | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |
    When I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid kansas" customer
    Then for a Bungii I should see "Enroute screen"

     #non control driver
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Kansas driver 2" driver
    Then Bungii driver should see "Enroute screen"
    And I click the "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on the alert message
    And I Select "HOME" from driver App menu
    Then Bungii driver should see "Home screen"

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then "Home" page should be opened

  @regression
  Scenario: Verify Non-Control Driver Can Cancel Duo Bungii From The App In The First Two States Of Started Bungii - Scenario:arrived
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | kansas   | arrived      | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |
    When I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid kansas" customer
    Then for a Bungii I should see "Arrived screen"

    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Kansas driver 2" driver
    Then Bungii driver should see "Arrived screen"
    And I click the "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on the alert message
    And I Select "HOME" from driver App menu
    Then Bungii driver should see "Home screen"

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then "Home" page should be opened

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: Verify If Customer Can Rate Driver For The Duo Trip
    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8888888881     | Testcustomertywd_appleRicha Test | Cci12345          |
    Given I am on customer Log in page
    And I am logged in as "valid kansas" customer
    And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state    | driver2 state    |
      | Bungii Completed | Bungii Completed |
    When I Switch to "customer" application on "same" devices
    And Bungii customer should see "correct rating detail for duo" on Bungii completed page
    When I select "3" Ratting star for duo "Driver 1"
    And I select "5" Ratting star for duo "Driver 2"
    Then I tap on "OK" on Bungii Complete
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8888888881 |                 |

  @regression
  Scenario: Verify If Re-searched Driver Can Cancel Trip After Starting The Scheduled Solo Trip
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | kansas1  | Accepted     | 15 min ahead |

    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I remove current driver and researches Bungii
    And As a driver "Testdrivertywd_appleks_rathree Test" perform below action with respective "Solo Scheduled" trip
      | driver1 state |
      | Accepted      |

    When I switch to "ORIGINAL" instance

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "kansas driver 1" driver

    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    And I click the "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on the alert message
    Then Bungii driver should see "Scheduled Bungii screen"

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: Verify If Re-searched Driver Can Cancel Trip After Starting The Scheduled Duo Trip
    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |

    And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |

    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I remove current driver and researches Bungii
    And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |

    When I switch to "ORIGINAL" instance

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "kansas driver 2" driver

    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    And I click the "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on the alert message
    Then Bungii driver should see "Scheduled Bungii screen"

    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |

  #@regression
  @ready
  Scenario: Verify Driver Notification - 30 Mins Before Scheduled Trip
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Scheduled    | NEXT_POSSIBLE |

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I tap on "Available Trips link" on Driver Home page
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    When I Switch to "customer" application on "same" devices

    And I wait for Minimum duration for Bungii Start Time
    Then I click on notification for "TAP NOTIFICATION TO ACTIVATE BUNGII"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

     #keep this scenario at last
#CMA 1513: delete card once trip is cancel
  #@regression
  @ready
  Scenario Outline: Verify Customer Cannot Delete Payment Method Linked To Any Ongoing Or Scheduled Trip
    Given I am on Sign up page
    When I enter "unique" customer phone number on Signup Page
    And I enter "valid test" data in mandatory fields on Signup Page
    And I enter "ValidPercent" promo code on Signup Page
  #  And I enter "Referral" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    Then The user should be logged in

    When I tap on "Menu" > "Payment" link
    And I get the number of cards present
    And I tap on "Add" on Payment page
    And I tap on "Credit or Debit Card" on Payment page
    And I enter "<Card Detail>" on Card Details page
    And I enter "<Card Expiry>" on Card Details page
    And I enter "<CVV>" on Card Details page
    And I enter "<Postal Code>" on Card Details page

    And I tap on "Add Card" on Payment page
    Then I should see "the card has been added" on Payment page


    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone  | Customer Name | Customer Password |
      | NEXT_POSSIBLE | NEW_USER_NUMBER |               | Cci12345          |

    Given I am on customer Log in page
    And I am logged in as "new test customer" customer
    When I tap on "Menu" > "PAYMENT" link
    Then "Payment" page should be opened
    When I swipe "default" card on the payment page
    And I tap on "Delete" on Payment page
  #  Then Alert message with Delete Warning text should be displayed
    And I should see "Payment Method is already associated to a trip" on Payment page
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | NEW_USER_NUMBER |                 |
    Given I am on customer Log in page
    And I am logged in as "new test customer" customer
    When I tap on "Menu" > "PAYMENT" link
    When I swipe "default" card on the payment page
    And I tap on "Delete" on Payment page
    Then I should see "message when no payment methods exist" on Payment page
    Examples:
      | Scenario       | Card Detail                | Card Expiry       | CVV       | Postal Code       |
      | VALID_discover | valid discover card number | valid expiry date | valid cvv | valid postal code |

      #@regression
  @ready
  Scenario: Verify Driver Doesnt Receive Scheduled Request If The Request Is Sent Outside Of The Time That Is Set For Trip Alert Settings
    When I clear all notification
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :8888881019 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    When I Select "TRIP ALERT SETTINGS" from driver App menu
    And I update kansas driver todays trip alert setting to outside current time
    When I Switch to "customer" application on "same" devices
    When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"

    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    Then I should be navigated to "AVAILABLE TRIPS" screen
    And I should able to see "zero" available trip
    And I Select "TRIP ALERT SETTINGS" from driver App menu
    And I update trip setting of "TODAY" to "12:00 AM" to "11:59 PM"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |


  @regression
  Scenario: Verify that error message on android and iOS when driver accepts a trip1 through push notification and admin assign trip2 for another customer through portal such that trip1 TELET overlaps start time of trip2, then error message is shown to the driver when he starts either of the trips
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time     |
      | Kansas   | Accepted     | 0.75 hour ahead |

    When I Switch to "customer" application on "same" devices
    And I am logged in as "valid kansas" customer
    And I tap on "Menu" > "Home" link
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I select Bungii Time as "next possible scheduled"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I check if the customer is on success screen
    Then I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

    
    @regression
    Scenario: Verify That Solo Scheduled Bungii can be started 1 hour before the Scheduled start time
      When I Open "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "valid" driver
      Then I click "Go Online" button on Home screen on driver app

      When that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
        | geofence | Bungii State | Bungii Time  |
        | Kansas   | Scheduled    | 1 hour ahead |

      And I Open "customer" application on "same" devices
      When I am on customer Log in page
      When I am logged in as "Testcustomertywd_appleand_A Android" customer

      When I Switch to "driver" application on "same" devices
      And I Select "AVAILABLE TRIPS" from driver App menu
      And I Select Trip from driver available trip
      And I tap on "ACCEPT" on driver Trip details Page
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from driver scheduled trip
      And Bungii Driver "Start Schedule Bungii" request
      Then Bungii driver should see "Enroute screen"
      When Bungii Driver "slides to the next state"
      And Bungii Driver "slides to the next state"
      And Bungii Driver "slides to the next state"
      And Bungii Driver "slides to the next state"
      And Bungii Driver "slides to the next state"

      And I Switch to "customer" application on "same" devices
      And I tap on "OK on complete" on Bungii estimate
      And I tap on "No free money" on Bungii estimate
      And I Switch to "driver" application on "same" devices
      Then Bungii Driver "completes Bungii"

  @regression
  Scenario: Verify That a Solo scheduled Bungii can be started 30 mins before the scheduled Trip start time
    When I Open "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    Then I click "Go Online" button on Home screen on driver app

    When that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Scheduled    | 0.5 hour ahead |

    And I Open "customer" application on "same" devices
    When I am on customer Log in page
    When I am logged in as "Testcustomertywd_appleand_A Android" customer

    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "Enroute screen"
    When Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"

    And I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    And I Switch to "driver" application on "same" devices
    Then Bungii Driver "completes Bungii"