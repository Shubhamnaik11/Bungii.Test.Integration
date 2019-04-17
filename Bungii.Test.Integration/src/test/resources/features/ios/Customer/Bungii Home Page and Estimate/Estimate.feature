@ios
@NONBUNGII
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
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
      | 30       |           |              | Now  | Default     | No             |
    Then I should be navigated to "SEARCHING" screen
    And I click "Cancel" button on "SEARCHING" screen
    Then user is alerted for "CANCEL BUNGII"
    Then I should be navigated to "Home" screen
    Then Trip Information should be correctly displayed on CUSTOMER HOME screen
  @RERUN1
  @regression
  Scenario: When there are no driver available for on demand Bungii , and Customer choose for Scheduled Bungii instead then he should be navigated to Estimate screen with fields having previous details
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 15       |           |              | Now  | Default     |
    Then I should be navigated to "SEARCHING" screen
    And I wait for SEARCHING screen to disappear
    Then I should be navigated to "DRIVER NOT AVAILABLE" screen
    And I click "Ok" button on "DRIVER NOT AVAILABLE" screen
    Then Alert message with SCHEDULE BUNGII OPTION text should be displayed
    And I click "Schedule Bungii" on alert message
    Then I should be navigated to "Estimate" screen
    Then Estimate Screen should have element as per below table
      | Trip Distance    | Load/unload time | Promo Code | Total Estimate   | Payment Method | Time | Terms And Condition | REQUEST BUNGII |
      | {PREVIOUS VALUE} | SELECT           | ADD        | {PREVIOUS VALUE} | x4242/x1117    |      | UNCHECK             | DISABLED       |
  @RERUN
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
    Then Estimate Screen should have element as per below table
      | Trip Distance    | Load/unload time | Promo Code | Total Estimate   | Payment Method   | Time | Terms And Condition | REQUEST BUNGII |
      | {PREVIOUS VALUE} | 15 mins          | ADD        | {PREVIOUS VALUE} | {PREVIOUS VALUE} | Now  | CHECK               | ENABLED        |

  @regression
  Scenario: When I cancel on Estimate Page , I should be navigated to Home screen
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    Then Trip Information should be correctly displayed on Estimate screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
      | 30       |           |              | Now  | Default     | No             |
    And I click "Cancel" button on "Estimate" screen
    Then I should be navigated to "Home" screen
    Then Trip Information should be correctly displayed on CUSTOMER HOME screen

  @regression
  Scenario: To check if the information icons display correct information
    When I Select "Home" from Customer App menu
#    And I request for  bungii
#      | Driver | Distance |
#      | Solo   | Long     |
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen

    Then I should be navigated to "Estimate" screen
    Then Trip Information should be correctly displayed on Estimate screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage | Save Trip Info |
      | 30       |           |              | NEXT_POSSIBLE | Default     | No             |
    Then "Load/Upload Time" information icon should display correct information
    Then "Total estimate" information icon should display correct information
    Then "Time" information icon should display correct information
  @RERUN
  @regression
  Scenario: To check the elements of Estimate page
    When I Select "Home" from Customer App menu
  #  And I request for  bungii
  #    | Driver | Distance |
  #    | Solo   | Long     |
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    Then Trip Information should be correctly displayed on Estimate screen
    Then Estimate Screen should have element as per below table
      | Trip Distance | Load/unload time | Promo Code | Total Estimate | Payment Method | Time | Terms And Condition | REQUEST BUNGII |
      | <IN MILES>    | SELECT           | ADD        | <IN DOLLAR>    | x4242/x1117    | Now  | UNCHECK             | DISABLED       |
  @RERUN
  @regression
  Scenario: Verify Load/unload time functionality . Check if Estimate cost is re calculated
    When I Select "Home" from Customer App menu
  #  And I request for  bungii
  #    | Driver | Distance |
  #    | Solo   | Long     |
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    Then check if I have ability to select different load time and Estimate cost is re calculated

# This is removed, Complete end to end scenario is created in OndemandBungii.feature
#  Scenario: Bungii_Estimate_ExistingUser_FixedPromoCode
#
#    When I open new "Chrome" browser for "ADMIN PORTAL"
#    When I navigate to admin portal
#    And I log in to admin portal
#    When I Select "Promo Code" from admin sidebar
#    Then I get promo code for "VALID"
#    When I switch to "ORIGINAL" instance
#    When I request for  bungii for given pickup and drop location
#      | Driver | Pickup Location | Drop Location                |
#      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
#    And I click "Get Estimate" button on "Home" screen
#    Then I should be navigated to "Estimate" screen
#    When I enter following details on "Estimate" screen
#      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
#      | 15       |           |              |      | Default     | No             |
#    When I tap "Promo code" on Estimate screen
#    Then I should be navigated to "PROMOS" screen
#    When I add "VALID" PromoCode
#    When I click "ADD" button on "PROMOS" screen
#    Then I should able to see expected promo code in available promo code
#    When I tap "Back" on Promos screen
#    Then I should be navigated to "Estimate" screen
  #  Then Estimate Screen should have element as per below table
  #    | Trip Distance | Load/unload time | Promo Code         | Total Estimate | Payment Method | Time | Terms And Condition | REQUEST BUNGII |
  #    |               | 15 mins          | <ADDED_PROMO_CODE> | <IN DOLLAR>    |                | Now  |                     |                |
  @RERUN1
  @regression
  Scenario: Estimate value for the Bungii Should be correctly displayed in Estimate Page
    When I Select "ACCOUNT" from Customer App menu
    Then I get customer account details
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 30       |           |             | Now  | Default     |
    Then Estimate value for trip should be properly displayed