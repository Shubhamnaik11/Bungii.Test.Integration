@web
Feature: Home Outlet

  Background:
    Given I navigate to "Partner" portal configured for "Home Outlet" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"


  @testAllan
  Scenario: working title

#    When I click on "Date" dropdown
    Then I check if the day is "Sunday" first time slot is "09:00 AM" and last time slot is "06:00 PM"
#      Then I check if the day is "Monday" first time slot is "09:00 AM" and last time slot is "06:00 PM"
#      Then I check if the day is "Tuesday" first time slot is "09:00 AM" and last time slot is "06:00 PM"
#      Then I check if the day is "Wednesday" first time slot is "09:00 AM" and last time slot is "06:00 PM"
#      Then I check if the day is "Thursday" first time slot is "09:00 AM" and last time slot is "06:00 PM"
#      Then I check if the day is "Friday" first time slot is "09:00 AM" and last time slot is "06:00 PM"
#      Then I check if the day is "Saturday" first time slot is "09:00 AM" and last time slot is "05:00 PM"
