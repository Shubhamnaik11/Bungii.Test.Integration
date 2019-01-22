@IOS
Feature: Customer	Referal Invite page
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
      | {PREVIOUS VALUE} | SELECT           | ADD        | {PREVIOUS VALUE} | x4242          |      | UNCHECK             | DISABLED       |

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
    And I click "REQUEST BUNGII" button on "Estimate" screen
    And I reject Alert message
    Then I should be navigated to "ESTIMATE" screen
    Then Estimate Screen should have element as per below table
      | Trip Distance    | Load/unload time | Promo Code | Total Estimate   | Payment Method | Time | Terms And Condition | REQUEST BUNGII |
      | {PREVIOUS VALUE} | 30 mins          | ADD        | {PREVIOUS VALUE} | x4242          | Now  | CHECK               | ENABLED        |

  @regression
  Scenario: When I cancel on Estimate Page , I should be navigated to Home screen
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    Then Trip Information should be correctly displayed on Estimate screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 30       |           |              | Now  | Default     |
    And I click "Cancel" button on "Estimate" screen
    Then I should be navigated to "Home" screen
    Then Trip Information should be correctly displayed on CUSTOMER HOME screen
