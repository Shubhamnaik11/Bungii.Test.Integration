@ios
Feature: Customer Estimate screen
  As a Bungii customer
  when I request for Bungii
  I Should be navigated to Estimate screen


  Background:
    Given I am on Customer logged in Home page
  @FAILED2702
  @regression
  Scenario: Verify If Customer Cancels Ondemand Bungii While It Is In Searching driver State Then He Is Navigated To Home Screen - Also Pickup And Dropoff Location Of Previous Trip Is Not Reset
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    And I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
      | 30       |           |              | Now  | Default     | No             |
    Then I should be navigated to "SEARCHING" screen
    When I click "Cancel" button on "SEARCHING" screen
    Then user is alerted for "CANCEL BUNGII"
    And I should be navigated to "Home" screen
    And Trip Information should be correctly displayed on CUSTOMER HOME screen
  
  @regression
  Scenario: Verify Customer Selects Scheduled Bungii When There Are No Driver Available For Ondemand Request Then He Should Be Navigated To Estimate Screen With Prefilled Data
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Panjim bus stand  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    And I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 15       |           |              | Now  | Default     |
    Then I should be navigated to "SEARCHING" screen
    When I wait for SEARCHING screen to disappear
    Then I should be navigated to "DRIVER NOT AVAILABLE" screen
    When I click "Ok" button on "DRIVER NOT AVAILABLE" screen
    Then Alert message with SCHEDULE BUNGII OPTION text should be displayed
    When I click "Schedule Bungii" on alert message
    Then I should be navigated to "Estimate" screen
    And Estimate Screen should have element as per below table
      | Trip Distance    | Load/unload time | Promo Code | Total Estimate   | Payment Method | Time | Terms And Condition | REQUEST BUNGII |
      | {PREVIOUS VALUE} | SELECT           |            | ~$0.00 | **** 4242/**** 1117/**** 1881   |      | UNCHECK             | DISABLED       |
  
  @regression
  Scenario: Verify When Bungii Customer Cancels On Heads Up Alert Message Then He Stays On Estimate Screen And All Field Details Remains Unchanged
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 15       |           |              | Now  | Default     |
    And I store default card value
    And I click "REQUEST BUNGII" button on "Estimate" screen
    And I reject Alert message
    Then I should be navigated to "ESTIMATE" screen
    And Estimate Screen should have element as per below table
      | Trip Distance    | Load/unload time | Promo Code | Total Estimate   | Payment Method   | Time | Terms And Condition | REQUEST BUNGII |
      | {PREVIOUS VALUE} | 15 mins          |            | {PREVIOUS VALUE} | {PREVIOUS VALUE} | Now  | CHECK               | ENABLED        |

  @regression
  Scenario: Verify When Customer Cancels On Estimate Page Then He Is Navigated To Home Screen
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    And Trip Information should be correctly displayed on Estimate screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
      | 30       |           |              | Now  | Default     | No             |
    And I click "Cancel" button on "Estimate" screen
    Then I should be navigated to "Home" screen
    And Trip Information should be correctly displayed on CUSTOMER HOME screen
  
  @regression
  Scenario: Verify If The Information Icons Display Correct Information On Estimate Screen
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen

    Then I should be navigated to "Estimate" screen
    And Trip Information should be correctly displayed on Estimate screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage | Save Trip Info |
      | 30       |           |              | NEXT_POSSIBLE | Default     | No             |
    Then "Load/Upload Time" information icon should display correct information
    #removed as part of sprint 32
  #  And "Total estimate" information icon should display correct information
    And "Time" information icon should display correct information

  @regression
  Scenario: Verify Field Elements Of Estimate Screen
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    And Trip Information should be correctly displayed on Estimate screen
    And Estimate Screen should have element as per below table
      | Trip Distance | Load/unload time | Promo Code | Total Estimate | Payment Method | Time | Terms And Condition | REQUEST BUNGII |
      | <IN MILES>    | SELECT           | ADD        | <IN DOLLAR>    | **** 4242/**** 1117/**** 1881   | Now  | UNCHECK             | DISABLED       |


  @regression
  Scenario: Verify Load Unload Time Functionality And Verify If Estimate Cost Is Recalculated
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    And check if I have ability to select different load time and Estimate cost is re calculated



  @regression
  Scenario: Verify Estimate Value For The Bungii Should Be Correctly Displayed In Estimate Screen
    And I am on the "LOG IN" page
    And I am on Customer logged in Home page
    And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                | Geofence  |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa | goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 30       |           |              | Now  | Default     |
    Then Estimate value for trip should be properly displayed

  @regression
  Scenario: Verify Customer Is Prompted To Go To Add Payment Page If No Payment Exists
    Given I am on the "LOG IN" page
    When I enter Username :9999990216 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                | Geofence  |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa | goa |
    And I click "Get Estimate" button on "Home" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 30       |           |              | Now  | Default     |
    And I click "REQUEST BUNGII" button on "Estimate" screen
    Then user is alerted for "ADD CARD BEFORE REQUEST BUNGII"
    Then I should be navigated to "PAYMENT MODE" screen
    And "Add New Card" message should be displayed on "PAYMENT" page
    And "Add Image" should be present in "PAYMENT" screen
    And "ADD" should be present in "PAYMENT" screen
    When I Switch to "customer" application on "same" devices
    And I Select "LOGOUT" from Customer App menu
    
    @regression
    Scenario:Verify Customer Can Add Atleast One And Maximum Of Four Images Of Items On Estimate Screen
      When I request for  bungii for given pickup and drop location
        | Driver | Pickup Location | Drop Location                |
        | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
      And I click "Get Estimate" button on "Home" screen
      When I enter following details on "Estimate" screen
        | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
        | 30       |           |              | Now  | No image     | No             |
      And I click "REQUEST BUNGII" button on "Estimate" screen
      Then user is alerted for "ADD IMAGE OF ITEM"
      When i add "4 images" of pickup item
      And I click "REQUEST BUNGII" button on "Estimate" screen
      When I click "YES" on alert message
      Then I should be navigated to "SEARCHING" screen
      When I click "Cancel" button on "SEARCHING" screen
      Then user is alerted for "CANCEL BUNGII"
  @FAILED2702
  @regression
      Scenario: Verify When Duo Is Selected Then Time Is Selected To Next Available Scheduled Time For A Selected Geofence
      And I request for  bungii for given pickup and drop location
        | Driver | Pickup Location                 | Drop Location                                        | Geofence  |
        | Duo    |Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States | nashville |
    And I click "Get Estimate" button on "Home" screen
    Then correct details next available scheduled time should be displayed
