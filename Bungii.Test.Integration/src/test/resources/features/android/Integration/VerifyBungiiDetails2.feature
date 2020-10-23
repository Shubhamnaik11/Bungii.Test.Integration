@android
@bungii
    #These feature will run in Goa geofence
Feature: VerifyBungiiDetails2

  @regression
  Scenario: Verify that the My Bungii Past trip is visible when admin manually ends bungii
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdriver_goa_a Android_test" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I tap on "Go Online button" on Driver Home page
    Then I Switch to "customer" application on "same" devices

    When I am on customer Log in page
    And I am logged in as "Testcustomertywd_appleand_A Android" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I enter "Goa pickup and dropoff locations" on Bungii estimate screen
    And I tap on "Get Estimate button" on Bungii estimate
    And I select Bungii Time as "NEW BUNGII TIME"
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "15 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I check if the customer is on success screen
    Then I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    Then I Select Trip from driver scheduled trip

    When Bungii Driver "Start Schedule Bungii" request
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    When bungii admin manually end bungii created by "CUSTOMER1"
    Then Bungii driver should see "summary" on Bungii completed page

    When I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    And "MY BUNGIIS" page should be opened
    And I click on "Past" tab
    And I open the trip for "Testdriver_goa_a Android_test" driver
    Then I verify the field "driver name"
    And I verify the field "pickup address"
    And I verify the field "dropoff address"
    And I verify the field "trip cost"

    @ready
    Scenario: Verify that the date and time displayed in edit Schedule bungii page against a drivers schedule list is proper timezone and not in UTC
      Given I am on the LOG IN page on driver app
      And I am logged in as "Testdriver_goa_a Android_test" driver
      And I tap on "Go Online button" on Driver Home page
      Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
        | geofence | Bungii State | Bungii Time   |
        | goa      | Accepted     | NEXT_POSSIBLE |
      And I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN_PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleand_A Android" the customer
      Then I check that time is not displayed in UTC

  @ready
  Scenario: Verify that Cancel button goes off once the solo scheduled Trip is cancelled
    Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_E Android"
      | geofence | Bungii State | Bungii Time   |
      | goa      | Accepted     | NEXT_POSSIBLE |
    When I Switch to "customer" application on "same" devices
    And I am logged in as "Testcustomertywd_appleand_E Android" customer

    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "testdriver_goa_e android_test" driver

    And I wait for "2" mins
    And I open Admin portal and navigate to "Scheduled Deliveries" page
    And I Cancel Bungii with following details
      | Charge | Comments | Reason                         |
      | 0      | TEST     | Outside of delivery scope      |
    Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
    And "Cancel button" should not be displayed

  @ready
  Scenario: Verify that for Duo trips if Admin portal displays Application error when one driver is accepted through push notification and other is assigned by ADMIN

    Given I am logged in as "Testcustomertywd_appleand_F Android" customer

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "testdriver_goa_a android_test" driver
    And I Select "Home" from driver App menu

    And I Open "customer" application on "ORIGINAL" devices
    And I enter "Goa pickup and dropoff location" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    Then I should see "two drivers selected" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    And I select Bungii Time as "next possible scheduled for duo"
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I check if the customer is on success screen
    And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

    And I Switch to "driver" application on "same" devices
    And I tap on "Available Trips link" on Driver Home page
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    Then I Select "SCHEDULED BUNGIIS" from driver App menu
  
    And I open Admin portal and navigate to "Scheduled Deliveries" page
    And I open the trip for "Testcustomertywd_appleand_F Android" customer
    And I Select "Edit Trip Details" option
    And I assign driver for the "Solo" trip
    And I click on "VERIFY" button
    And the "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And the "Bungii Saved!" message is displayed
    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select already scheduled bungii

  @ready
  Scenario: Verify that the Pickup note is not displayed as NULL or undefined when customer does not add a pickup note
    When I am on the LOG IN page on driver app
    And I am logged in as "Testdriver_goa_f Android_test" driver
    And I tap on "Go Online button" on Driver Home page

    When I Switch to "customer" application on "same" devices
    And I am logged in as "Testcustomertywd_appleand_B Android" customer
    And I enter "Goa pickup and dropoff location" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I select Bungii Time as "NEW BUNGII TIME"
    And I add loading/unloading time of "15 mins"
    Then I add "1" photos to the Bungii
    When I tap on "Details" on Estimate screen
    And I enter "text" in Additional Notes field
    And I click on "ADD NOTE" button
    Then "Estimate" page should be opened
    When I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I click "Done" button on "Success" screen

    And I click on notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then I should be able to see "No Note" Text
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 9999991020      |                 |

  @ready
  Scenario: Verify that if non control driver started the trip and control driver is removed by Admin and assigned a new driver, the non controller driver becomes new control driver
            Verify that a message/ alert is not displayed to ADMIN when no field on Edit schedule Trip is edited and Admin clicks Verify button
    When I request "duo" Bungii as a customer in "goa" geofence
      | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
      | NEXT_POSSIBLE | 9889889888     | Testcustomertywd_appleand_E Android | Cci12345          |
    And As a driver "Driver_goa_e Android_test" and "Driver_goa_f Android_test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
      |               | Enroute       |
    Then I wait for "2" mins
    When I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleand_E Android" the customer
    And I remove "control" driver and researches Bungii
    And I Select "Edit Trip Details" option
    And I check if a validation message "<string>" is shown
    And I assign driver for the "control" trip
    And I click on "VERIFY" button
    And the "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And the "Bungii Saved!" message is displayed
    And I verify that noncontrol driver becomes control driver

  @ready
  Scenario: Verify that Admin is not allowed to add multiple driver for solo bungii and more than 2 drivers for DUO
    When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
      | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
      | NEXT_POSSIBLE | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
    When I request "duo" Bungii as a customer in "goa" geofence
      | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
      | NEXT_POSSIBLE | 9999991020     | Testcustomertywd_appleand_B Android | Cci12345          |
    Then I wait for "2" mins
    When I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleand_A Android" the customer
    And I Select "Edit Trip Details" option
    And I assign driver for the "Solo" trip
    Then I am not allowed to assign more drivers
    And I click on "Close" button
    When I open the trip for "Testcustomertywd_appleand_B Android" the customer
    And I Select "Edit Trip Details" option
    And I assign driver for the "Duo" trip
    Then I am not allowed to assign more drivers

  @ready
  Scenario: Verify that application error is not thrown on re-search of trip with apostrophe in Customer notes
    Given I am on customer Log in page
    And I am logged in as "Testcustomertywd_appleand_B Android" customer

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Driver_goa_f Android_test" driver
    And I tap on "Go Online button" on Driver Home page
    And I Switch to "customer" application on "same" devices
    And I enter "Goa pickup and dropoff location" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "15 mins"
    Then I add "1" photos to the Bungii
    When I tap on "Details" on Estimate screen
    And I enter "Customer note should contain apostrophe in the note's." in Additional Notes field
    And I click on "ADD NOTE" button
    Then "Estimate" page should be opened
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate

    When I click on notification for "on demand trip"
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" button on alert message
    Then I should be able to see "Customer Note" Text
    And I wait for "1" mins

    When I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleand_A Android" the customer
    Then I remove current driver and researches Bungii