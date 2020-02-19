@ios
Feature: Customer Estimate screen
  As a Bungii customer
  when I request for Bungii
  I Should be navigated to Estimate screen


  Background:
    Given I am on Customer logged in Home page

  @regression
  Scenario: If I cancel on Bungii while it is in Searching driver  page , I should be navigated to Home screen and Pickup and Drop location of previous trip should be current pickup and drop location
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
  Scenario: When there are no driver available for on demand Bungii , and Customer choose for Scheduled Bungii instead then he should be navigated to Estimate screen with fields having previous details
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
  Scenario: When Bungii Customer cancel on Head's Up Alert message, He should stay on Estimate Page . And all field details should remain unchanged
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
  Scenario: When I cancel on Estimate Page , I should be navigated to Home screen
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
  Scenario: To check if the information icons display correct information
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
  @FAILED
  @regression
  Scenario: To check the elements of Estimate page
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
  Scenario: Verify Load/unload time functionality . Check if Estimate cost is re calculated
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    And check if I have ability to select different load time and Estimate cost is re calculated



  @regression
  Scenario: Estimate value for the Bungii Should be correctly displayed in Estimate Page
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
  Scenario: To check that customer is prompted to go to Add payment page if No payment exists (on request Bungii)
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
    Scenario: To check that Customer is able to add at least one and maximum 4 images of Items
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
  @FAILED
  @regression
      Scenario: To check that when duo is selected, Time is selected to next available  scheduled time (correct Timezone)
      And I request for  bungii for given pickup and drop location
        | Driver | Pickup Location                 | Drop Location                                        | Geofence  |
        | Duo    |Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States | nashville |
    And I click "Get Estimate" button on "Home" screen
    Then correct details next available scheduled time should be displayed
