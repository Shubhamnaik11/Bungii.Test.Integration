@ios

Feature: Customer Home screen
# 1 valid test case fail
  @FAILED2802
    #test case getting failed , Known issue
  @regression
  Scenario:Verify If ETA Bar Remains On Map When Pickup Address Is Cleared
    Given I am on Customer logged in Home page
    Then "Pick up" address should be displayed in text box
    When I click "Pick Up Clear Text" button on "Home" screen
    And "PICK UP" box header and ETA bar header should be correctly displayed
  @FAILED2802
  @FAILED0203
  @regression
  Scenario: Verify ETA And Location Textbox Header - Also Verify Clear Text Button Is Enabled Once Location Is Selected
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
  @FAILED0203
  @regression
  Scenario: Verify Clear Text Button On Pickup And Dropoff Location
    Given I am on Customer logged in Home page
    And I Select "Home" from Customer App menu
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

  @FAILED2802
  @regression
  Scenario:Verify That Dropoff Field Is Displayed Only When Pickup Address Is Set
    Given I am on Customer logged in Home page
    And I open "customer" application on "same" devices
    Then "Pick up" address should be displayed in text box
    Then drop off field should be "displayed"
    When I click "Pick Up Clear Text" button on "Home" screen
    Then drop off field should be "not be displayed"



  @regression
  Scenario:Verify If Driver ETA Is Displayed When There Are Drivers Present In 30 Min Radius Of Pickup Location
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    When I Switch to "customer" application on "same" devices
    Given I am on Customer logged in Home page
    Then driver eta should be "less than 30 mins"

  @regression
  Scenario:Verify Customer Can Set Pickup And Dropoff Locations When No Driver ETA Is Found (Within Geofence)
    Given I am on Customer logged in Home page

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
  Scenario: Verify Long Haul(>150 miles) Alert Is Shown When Distance between Pickup And Dropoff Should Be >150 Miles)
    Given I am on Customer logged in Home page

    And I enter pickup location
      | Driver | Pickup Location |
      | Solo   | Margoa Railway  |
    And I enter drop location
      | Driver | Drop Location                   |
      | Solo   | Bangalore international airport |
    Then user is alerted for "LONG HAUL"


  @regression
  Scenario: Verify ETA Box When Geofence Is Not Active
    Given I am on Customer logged in Home page

    And I enter pickup location
      | Driver | Pickup Location |
      | Solo   | Kolhapur Airport  |
    Then driver eta should be "not be displayed"
    Then geofence not active message should be displayed
    When I click "Request your city" button on "Home" screen
    Then I should be navigated to "bungii.com" screen
