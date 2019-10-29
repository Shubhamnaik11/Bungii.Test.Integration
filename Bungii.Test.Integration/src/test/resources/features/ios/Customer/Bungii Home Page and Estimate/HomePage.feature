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