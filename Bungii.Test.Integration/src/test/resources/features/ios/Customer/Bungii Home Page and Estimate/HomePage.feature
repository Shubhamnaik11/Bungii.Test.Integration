@ios

Feature: Customer Home screen

  Background:
  #  Given I am on Customer logged in Home page

  @regression
  Scenario: To verify ETA , Location text box header . To Verify clear text button is enabled once location is selected
    When I logged in Customer application using  "existing" user

    Then current location should be present as pickup location
    And "Invite referrals" should be present in "Home" screen
    And "PICK UP" box header and ETA bar header should be correctly displayed
    When I select "Pick up" location
    Then "Pick up" address should be displayed in text box
    And "Drop" box header and ETA bar header should be correctly displayed
    And Clear Button should be enabled for "Pick up" box
    When I select "Drop" location
    Then "Drop" address should be displayed in text box
    And Clear Button should be enabled for "Drop" box

  @regression
  Scenario: To Verify clear text button on Pick up and Drop location
    Given I am on Customer logged in Home page
    When I select "Pick up" location
    Then "Pick up" address should be displayed in text box
    When I click "Pick Up Clear Text" button on "Home" screen
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
    And "Drop" address should be empty

  @FAILED
  @regression
  Scenario:To check that drop off field is displayed only when pickup address is set.
    Given I am on Customer logged in Home page
    Then "Pick up" address should be displayed in text box
    Then drop off field should be "displayed"
    When I click "Pick Up Clear Text" button on "Home" screen
    Then drop off field should be "not be displayed"
  @FAILED
    #test case getting failed , Known issue
  @regression
  Scenario:To check if ETA bar/picker remains on map when pickup address has been cleared
    Given I am on Customer logged in Home page
    Then "Pick up" address should be displayed in text box
    When I click "Pick Up Clear Text" button on "Home" screen
    And "PICK UP" box header and ETA bar header should be correctly displayed


  @regression
  Scenario:To check if driver ETA is displayed when there are drivers present in 30 min radius of pickup location
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    When I Switch to "customer" application on "same" devices
    Given I am on Customer logged in Home page
    Then driver eta should be "less than 30 mins"
  @FAILED
  @regression
  Scenario:To check that customer is allowed to set pickup and drop off locations when  No driver ETA is found (within geofence)
    And I enter pickup location
      | Driver | Pickup Location     |
      | Solo   | cancona bus station |
    Then driver eta should be "not be displayed"
    And I enter drop location
      | Driver | Drop Location  |
      | Solo   | Margoa Railway |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 15       |           |              | Now  | Default     |
    Then I should be navigated to "SEARCHING" screen
    When I click "Cancel" button on "SEARCHING" screen
    Then user is alerted for "CANCEL BUNGII"


  @regression
  Scenario:Long Haul(>150 miles) alert shown (dist bet. pickup and drop off should be >150 miles)
    And I enter pickup location
      | Driver | Pickup Location |
      | Solo   | Margoa Railway  |
    And I enter drop location
      | Driver | Drop Location                   |
      | Solo   | Bangalore international airport |
    Then user is alerted for "LONG HAUL"


  @regression
  Scenario:To check ETA box when geofence Not active
    And I enter pickup location
      | Driver | Pickup Location |
      | Solo   | Kolhapur Airport  |
    Then driver eta should be "not be displayed"
    Then geofence not active message should be displayed
    When I click "Request your city" button on "Home" screen
    Then I should be navigated to "bungii.com" screen
