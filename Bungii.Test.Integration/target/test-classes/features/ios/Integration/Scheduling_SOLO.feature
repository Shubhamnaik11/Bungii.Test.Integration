
@tag
Feature: To Test Solo - Scheduling Bungii
  I want to use request Scheduling Bungii with Solo type
  Assume customer is logged in

  #Background: List of steps run before each of the scenarios
  @positiveCompletesolo
  Scenario: Positive Scenario

 #   When I connect to "device2" using "customer app" instance

    When I Select "ACCOUNT" from Customer App menu
    Then I get customer account details
    When I Select "Home" from Customer App menu
      When I request for  bungii for given pickup and drop location
        | Driver | Pickup Location | Drop Location                |
        | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    Then Trip Information should be correctly displayed on "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | PayMentCard | Time          | PickUpImage |
      |       30 |           |             | NEXT_POSSIBLE | Default     |
    Then I should be navigated to "Success" screen
    Then Bungii Posted message should be displayed
    And I click "Done" button on "Success" screen
    Then I Select "Home" from Customer App menu
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip
    Then I should be navigated to "TRIP DETAILS" screen
#    Then Trip Information should be correctly displayed on "TRIP DETAILS" screen
    When I accept selected Bungii
#    When I Switch to "driver" application on "same" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    When I wait for Minimum duration for Bungii Start Time
    #TODO: verify bungii detail page
   # Then If Alerted I ""
    When I start selected Bungii
   Then I should be navigated to "EN ROUTE" trip status screen
    When I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" trip status screen
    When I Switch to "customer" application on "same" devices
    Then Customer should be naviagated to "ARRIVED" trip status screen
    When I Switch to "driver" application on "same" devices
    When I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" trip status screen
    When I Switch to "customer" application on "same" devices
    Then Customer should be naviagated to "LOADING ITEM" trip status screen
    When I Switch to "driver" application on "same" devices
    When I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
    When I Switch to "customer" application on "same" devices
    Then Customer should be naviagated to "DRIVING TO DROP OFF" trip status screen
    When I Switch to "driver" application on "same" devices
    When I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" trip status screen
    When I Switch to "customer" application on "same" devices
    Then Customer should be naviagated to "UNLOADING ITEM" trip status screen
    When I Switch to "driver" application on "same" devices
    When I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    And I click "On To The Next One" button on "Bungii Completed" screen
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    When I rate Bungii Driver  with following details and Press "OK" Button
      | Ratting | Tip |
      |       5 |   5 |
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

  Scenario: To verify ETA , Location text box header . To Verify clear text button is enabled once location is selected
    When I Select "Home" from Customer App menu
    Then "Invite referrals" should be present in "Home" screen
    Then current location should be present as pickup location
    Then "PICK UP" box header and ETA bar header should be correctly displayed
    When I select "Pick up" location
    Then "Pick up" address should be displayed in text box
    Then "Drop" box header and ETA bar header should be correctly displayed
    Then Clear Button should be enabled for "Pick up" box
    When I select "Drop" location
    Then "Drop" address should be displayed in text box
    Then Clear Button should be enabled for "Drop" box

  Scenario: To Verify clear text button on Pick up and Drop location
    When I Select "Home" from Customer App menu
    When I select "Pick up" location
    Then "Pick up" address should be displayed in text box
    And I click "Pick Up Clear Text" button on "Home" screen
    Then current location should be present as pickup location
    When I select "Pick up" location
    Then "Pick up" address should be displayed in text box
    When I select "Drop" location
    Then "Drop" address should be displayed in text box
    When I click "Drop Clear Text" button on "Home" screen
    Then "Drop" address should be empty
    When I select "Drop" location
    Then "Drop" address should be displayed in text box
    When I click "Pick Up Clear Text" button on "Home" screen
    Then current location should be present as pickup location
    Then "Drop" address should be empty

  Scenario: To check if the information icons display correct information
    When I Select "Home" from Customer App menu
    And I request for  bungii
      | Driver | Distance |
      | Solo   | Long     |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    Then Trip Information should be correctly displayed on "Estimate" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | PayMentCard | Time          | PickUpImage |
      |       30 |           |             | NEXT_POSSIBLE | Default     |
    Then "Load/Upload Time" information icon should display correct information
    Then "Total estimate" information icon should display correct information
    Then "Time" information icon should display correct information

  @Solo_Scheduling
  Scenario: To check the elements of Bungii-Solo Estimate page
    #When I Switch to "customer" application on "same" devices
    When I Select "Home" from Customer App menu
    And I request for  bungii
      | Driver | Distance |
      | Solo   | Long     |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    Then Trip Information should be correctly displayed on "Estimate" screen
    Then Estimate Screen should have element as per below table
      | Trip Distance | Load/unload time | Promo Code | Total Estimate | Payment Method | Time | Terms And Condition | REQUEST BUNGII |
      | <IN MILES>    | SELECT           | ADD        | <IN DOLLAR>    | x4242          | Now  | UNCHECK             | DISABLED       |

  @Solo_Scheduling
  Scenario: Verify Load/unload time fuctionality . Check if Estimate cost is re calculated
    When I Switch to "customer" application on "same" devices
    When I Select "Home" from Customer App menu
    And I request for  bungii
      | Driver | Distance |
      | Solo   | Long     |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    Then check if I have ability to select different load time and Estimate cost is re calculated

  @testloadTime
  Scenario: Verify Load/unload time fuctionality . Check if Estimate cost is re calculated
    When I Switch to "customer" application on "same" devices
    When I select Bungii time as per table
      | Time          | Date  |
      | 2 hour before | today |

  @Solo_Scheduling
  Scenario: To check that Customer cannot schedule a Bungii at same time as an already scheduled bungii
    When I Switch to "customer" application on "same" devices
    When I Select "Home" from Customer App menu
    And I request for  bungii
      | Driver | Distance |
      | Solo   | Long     |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | PayMentCard | Time          | PickUpImage |
      |       30 |           |             | NEXT_POSSIBLE | Default     |
    Then I should be navigated to "Success" screen
    Then Bungii Posted message should be displayed
    And I click "Done" button on "Success" screen
    Then I Select "Home" from Customer App menu
    And I request for  bungii
      | Driver | Distance |
      | Solo   | Long     |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | PayMentCard | Time              | PickUpImage |
      |       30 |           |             | <OLD BUNGII TIME> | Default     |
    Then user is alerted for "already scheduled bungii"
    And I click "Cancel" button on "Estimate" screen
    When I Select "SCHEDULED BUNGIIS" from Customer App menu
    When I select already scheduled bungii
    Then I Cancel selected Bungii

  Scenario: Customer cancel bungii , Verify trip details in Bungii Details
    When I Select "ACCOUNT" from Customer App menu
    Then I get customer account details
    When I Select "Home" from Customer App menu
    And I request for  bungii
      | Driver | Distance |
      | Solo   | Long     |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | PayMentCard | Time          | PickUpImage |
      |       30 |           |             | NEXT_POSSIBLE | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    When I Select "SCHEDULED BUNGIIS" from Customer App menu
    When I select already scheduled bungii
    Then Trip Information should be correctly displayed on "BUNGII DETAILS" screen
    Then I Cancel selected Bungii
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen

  @Solo_SchedulingTEST
  Scenario: Cancel Bungii from Admin Panel , verify trip is gone from scheduled trip in app
    When I Select "ACCOUNT" from Customer App menu
    Then I get customer account details
    When I Select "Home" from Customer App menu
    And I request for  bungii
      | Driver | Distance |
      | Solo   | Long     |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | PayMentCard | Time          | PickUpImage |
      |       30 |           |             | NEXT_POSSIBLE | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    When I open new "Chrome" browser for "ADMIN"
    When I naviagate to admin portal
    And I log in to admin portal
    When I Select "Scheduled Trip" from admin sidebar
    # 			Then I Select Bungii from "Scheduled Trip" List
    When I Cancel Bungii with following details
      | Charge | Comments |
      |     15 | TEST     |
    Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
    Then Bungii must be removed from the List
    When I switch to "ORIGINAL" instance
    When I Switch to "customer" application on "same" devices
    When I Select "SCHEDULED BUNGIIS" from Customer App menu
    #     And I Select Trip from scheduled trip
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen
