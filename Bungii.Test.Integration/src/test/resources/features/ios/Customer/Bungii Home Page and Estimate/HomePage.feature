@ios
@NONBUNGII

Feature: Customer Home screen

  Background:
    Given I am on Customer logged in Home page

@regression
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

@regression
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