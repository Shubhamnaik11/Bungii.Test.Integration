@android
@SoloScheduled

#These feature will run in kansas geofence
Feature: SoloScheduled

  Background:


  @regression
  Scenario: Validate That I am able to create Schedule  bungii. Also Validate that Correct contact number is displayed on Call and SMS Option
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
  Scenario: Validate That I am able to create Schedule  bungii.
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
  Scenario: Cancel Bungii from Admin Panel , verify trip is gone from scheduled trip in app
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


  @regression
  Scenario: To check that Customer cannot schedule a Bungii at same time as an already scheduled bungii

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


  @regression
  Scenario: Customer should able to cancel scheduled bungii
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

  @regression
  Scenario: To check status of Scheduled Bungii trip in Scheduled Bungiis menu page when required drivers have Not accepted it
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
  Scenario: To check status of Scheduled Bungii trip in Scheduled Bungiis menu page when required drivers have Not accepted it.Scenario:DUO
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

  Scenario: To check status in Scheduled Bungiis page when only one driver accepts trip
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
  Scenario: To check status on customer in Scheduled Bungiis page when both drivers have accepted trip
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
  Scenario: Check to see if customer receive Notification once both/required No of  drivers have accepted scheduled trip.Scenario:Solo
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
  Scenario: Check to see if customer receive Notification once both/required No of  drivers have accepted scheduled trip.Scenario:DUO
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
    Then I click on notification for "SCHEDULED PICKUP ACCEPTED"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |


  @regression

  Scenario:  To check that Customer cannot Schedule Bungii for a time that is outside working hours.Scenario:SOLO
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
  Scenario:  To check that Customer canNot Schedule Bungii for a time that is outside working hours.Scenario:DUO
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

  Scenario:  To check that Customer is able to Schedule Bungii only 5 days ahead including current date.Scenario:SOLO
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

  Scenario:  To check that Customer is able to Schedule Bungii only 5 days ahead including current date.Scenario:Duo
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
  Scenario:Trip limit (150 miles)
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
  Scenario: To check that Customer canNot Schedule bungii that overlaps with aNother Scheduled trip TELET time.Scenario:Solo
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
  Scenario: To check that Customer canNot Schedule bungii that overlaps with aNother Scheduled trip TELET time.Scenario:Duo
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
  Scenario:Check to see if customer receieve Notification after admin researches for drivers and both drivers accept.
  #  Given I have already scheduled bungii with "DUO_SCH_DONOT_ACCEPT" label
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

  @regression

  Scenario:Alert message should be displayed when customer tries to contact driver who is currently has a Bungii in progress.
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

  Scenario:Alert message should be displayed when customer tries to contact driver more than one hour from scheduled time.
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

  @regression

  Scenario:Customer should be able to contact control driver when Non-control driver has started the trip
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

  @regression

  Scenario: Customer should be able to see text stating that driver can be contacted on the Bungii Details page, only when the trip has been accepted by required number of drivers.
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

  @regression

  Scenario:Ensure shceduled Bungii notification info is correct (est. earnings, date)
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

  Scenario:To check if driver recieves Bungii scheduled request even while in the Offline state (assuming he does Not have Bungiis overlapping the TELET time)
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
  Scenario:To check that a driver is Not able to accept the request if the trip has already been accepted by the required number of drivers
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


  @regression

  Scenario: To check that if driver received more than one requests, he is not able to accept the Bungii if he has already accepted a Bungiis who's TELET time overlaps.Scenario:Solo
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

  @regression

  Scenario: To check that if driver received more than one requests, he is not able to accept the Bungii if he has already accepted a Bungiis who's TELET time overlaps.Scenario:duo
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

  Scenario: To check the status of scheduled Bungii in the scheduled trip page when only one driver has accepted
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

  Scenario:To check all details in the Bungii Details page when required number of drivers have accepted

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

  @regression

  Scenario: Check that customer received Notification when control driver starts bungii duo
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

  @regression

  Scenario: Check that customer received Notification when driver starts bungii solo
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
  Scenario: Check to see if a driver deosn't receive scheduled trip request if his home is over 30 mins away from PU location
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

  @regression

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

  @regression
  Scenario:check to ensure TELET is calculated correctly (Initial request time +  (Estimated Duration(1.5)) + 30 minutes).Solo
    When I request "Solo Scheduled" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I get TELET time of of the current trip
    Then Telet time of current trip should be correctly calculated

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression

  Scenario: Check that re-searched trip request does Not show Urgent Notification text if is more than one hour from the scheduled trip time
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

  @regression
  Scenario: check to ensure TELET is calculated correctly (Initial request time +  (Estimated Duration(1.5)) + 30 minutes).Duo
    When I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I get TELET time of of the current trip
    Then Telet time of current trip should be correctly calculated

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression

  Scenario: To check validation message shown if driver tries to start a Bungii more than 60 mins before the scheduled time
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Accepted     | 1 hour ahead |
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    Then User should see message "60 MINS BEFORE SCHEDULE TRIP TIME" text on the screen

  @regression

  Scenario: To check that Customer canNot Schedule bungii that overlaps with aNother Scheduled trip TELET time.Scenario:Duo
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


  @regression
  Scenario: Driver should Not receive scheduled request if the request is sent outside of the time that is set for Trip Alert settings.
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

  Scenario: Driver should receive alert stating that the trip has already been accepted by him, if he receives request Notification after accepting the trip from Available trips.
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
  Scenario: To check that driver is not allowed to start Bungii within 60 mins of the scheduled time if required number of Drivers have not accepted
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

  @regression

  Scenario:To check that driver is not allowed to start Bungii if the Customer is currently in an ongoing trip.Scenario .Solo
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

  @regression
  @fail
  Scenario:To check that driver is not allowed to start Bungii if the Customer is currently in an ongoing trip.Scenario .Duo
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

  Scenario: if incoming on demand trip TELET overlaps scheduled trip telet, then request should Not be sent to driver.
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

  @regression

  Scenario: check TELET of re-searched trip
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

  @regression

  Scenario: If incoming scheduled trip request TELET (Trip A) overlaps start time of previously scheduled trip (Trip B) = driver doesn't receive Notification or offline SMS

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


  @regression

  Scenario: If incoming scheduled trip request start time (Trip A) overlaps TELET of previously scheduled trip (Trip B) = driver doesn't receive Notification or offline SMS

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

  Scenario: If incoming on-demend trip request TELET (Trip A) overlaps start time of previously scheduled trip (Trip B) = driver doesn't receive Notification or offline SMS
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


  @regression

  Scenario:CUSTOMER: Notification - 2 hours before scheduled trip
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

  @regression

  Scenario: To check if control driver is allowed to complete the trip and proper summary is shown
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

  Scenario:  To check that if Non control driver completes the trip first, he is shown waiting page till the control driver completes and that the correct summary is shown thereafter
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
    And I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen

    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details for duo trip" on Bungii completed page
    And I click "On To The Next One" button on the "Bungii Completed" screen

  @regression

  Scenario:If incoming scheduled request start time (Trip 3), overlaps with TELET of accepted stacked request (Trip 2) = driver doesn't receive scheduled Notification or offline SMS
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

  Scenario: Check the Minimum scheduled time for Solo trip
    Given I am on customer Log in page
    When I am logged in as "New" customer
    And I enter "San Francisco pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then I should see the minimum scheduled time for Solo Bungii displayed on the Estimate page

#####################################################################################################################
  @regression

  Scenario: To check that Customer can request cancel scheduled trip via admin SMS after 2 hour processing is over (No. of required drivers accepted or Not)
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

  Scenario: To check that Customer can request cancel through SMS to ADMIN if No driver accepts but processing is over (cancellation on admin side).Scenario:Solo
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

  Scenario: To check that Customer can request cancel through SMS to ADMIN if No driver accepts but processing is over (cancellation on admin side).Scenario:Duo
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

  @regression

  Scenario: To check that Customer can request cancel through SMS to ADMIN even if one driver accepts but processing is over (Trip not started) (cancellation on admin side)
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

  @regression

  Scenario: To check that Customer can cancel through SMS to ADMIN if required no. of drivers have accepted (cancellation on admin side).scenario : duo
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

  Scenario:To check that when Bungii has Not yet started, Driver is Not able to cancel scheduled Bungii directly from app and that he is able to send SMS to cancel Bungii.Scenario.solo
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

  Scenario:To check that when Bungii has Not yet started, Driver is Not able to cancel scheduled Bungii directly from app and that he is able to send SMS to cancel Bungii.Scenario.duo
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
  Scenario: To check that Control Driver is able to cancel Duo Bungii directly from the app in the first two states after Bungii has been started.Scenario:enroute
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
  Scenario: To check that Control Driver is able to cancel Duo Bungii directly from the app in the first two states after Bungii has been started.Scenario:arrived
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
  Scenario: To check that Non-Control Driver is able to cancel Duo Bungii directly from the app in the first two states after Bungii has been started.Scenario:enroute
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
  Scenario: To check that Non-Control Driver is able to cancel Duo Bungii directly from the app in the first two states after Bungii has been started.Scenario:arrived
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
  Scenario: Check if customer is allowed to rate driver for duo trip
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

  @regression
  Scenario: check if re-searched driver can cancel trip after starting Solo
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

  Scenario: check if re-searched driver can cancel trip after starting Duo
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

    #keep this scenario at last
#CMA 1513: delete card once trip is cancel
  @regression

  Scenario Outline: Customer canNot delete payment method linked to any on-going/scheduled trips
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

  @regression

  Scenario: To check that Customer is able to view ongoing Bungii progress screens when trip is started by Control driver
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | Kansas   | Accepted     | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |

    And I Switch to "customer" application on "same" devices
    And I am logged in as "valid kansas" customer

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Kansas driver 1" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE" screen
    Then I check ETA of "control driver"

    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "EN ROUTE" screen
    Then "control driver" eta should be displayed to customer

    And I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid driver 2" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "ARRIVED" screen

    And I Switch to "driver" application on "Driver2" devices
    And I slide update button on "EN ROUTE" Screen


    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "ARRIVED" Screen
    Then I accept Alert message for "Reminder: both driver at pickup"
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "LOADING ITEM" screen

    And I Switch to "driver" application on "Driver2" devices
    And I slide update button on "ARRIVED" Screen
    Then I accept Alert message for "Reminder: both driver at pickup"
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" screen
    Then I check ETA of "control driver"

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "DRIVING TO DROP OFF" screen
    Then "control driver" eta should be displayed to customer

    And I Switch to "driver" application on "Driver2" devices
    And I slide update button on "LOADING ITEM" Screen


    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "UNLOADING ITEM" screen

    And I Switch to "driver" application on "Driver2" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen

    When I Switch to "customer" application on "same" devices
    Then I wait for "2" mins
    Then I should be navigated to "BUNGII COMPLETE" screen
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen

    And I Switch to "driver" application on "Driver2" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    When I click "On To The Next One" button on "Bungii Completed" screen

  @regression

  Scenario: DRIVER: Notification - 30 mins before scheduled trip
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