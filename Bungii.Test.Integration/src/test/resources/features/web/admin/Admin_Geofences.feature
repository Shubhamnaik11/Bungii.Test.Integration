@web
Feature: Admin_BusinessUsers

  Background:
    Given I am logged in as TestAdmin
    When I click on "Geofences  > Attributes" Menu
    Then I should be directed to "Attributes Page"

  @sanity
  @regression
  Scenario: Admin_VerifyDeaultGeofencesAttributes
    When I go to "Geofence Attributes" page
    Then I verify that the default settings are displayed
