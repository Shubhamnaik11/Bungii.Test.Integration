@web
Feature: Admin_ReferralSource

  Background:
    Given I am logged in as Admin
    When I click on "Marketing  > Referral Source" Menu
    Then I should be directed to "Referral Source Page"

  @sanity
  @regression
  Scenario: Admin_ReferralSourcegrid_Calculations
    Then the "Percentage of total(Accounts Created)" should display accurate value for each Source
    And  the "Percentage of total(Trips Completed)" should display accurate value for each Source

  @regression
  Scenario: Admin_ReferralSourcegrid_Sort
    When I click on "Source" header "Ascending" on "Referral Source" grid
    Then the "Referral Source" list should be sorted by "Ascending" order of "Source"
    When I click on "Source" header "Descending" on "Referral Source" grid
    Then the "Referral Source" list should be sorted by "Descending" order of "Source"
    When I click on "Accounts Created" header "Ascending" on "Referral Source" grid
    Then the "Referral Source" list should be sorted by "Ascending" order of "Accounts Created"
    When I click on "Accounts Created" header "Descending" on "Referral Source" grid
    Then the "Referral Source" list should be sorted by "Descending" order of "Accounts Created"
    When I click on "Percentage of total(Accounts Created)" header "Ascending" on "Referral Source" grid
    Then the "Referral Source" list should be sorted by "Ascending" order of "Percentage of total(Accounts Created)"
    When I click on "Percentage of total(Accounts Created)" header "Descending" on "Referral Source" grid
    Then the "Referral Source" list should be sorted by "Descending" order of "Percentage of total(Accounts Created)"
    When I click on "Trips Completed" header "Ascending" on "Referral Source" grid
    Then the "Referral Source" list should be sorted by "Ascending" order of "Trips Completed"
    When I click on "Trips Completed" header "Descending" on "Referral Source" grid
    Then the "Referral Source" list should be sorted by "Descending" order of "Trips Completed"
    When I click on "Percentage of total(Trips Completed)" header "Ascending" on "Referral Source" grid
    Then the "Referral Source" list should be sorted by "Ascending" order of "Percentage of total(Trips Completed)"
    When I click on "Percentage of total(Trips Completed)" header "Descending" on "Referral Source" grid
    Then the "Referral Source" list should be sorted by "Descending" order of "Percentage of total(Trips Completed)"

  @regression
  Scenario: Admin_ReferralSourcegrid_FieldValidations
    When I click on "Search" button with entering "From" and "To" date
    Then the "From date is required" message is displayed beside "From Date" field
    And the "To date is required" message is displayed beside "To Date" field
    When I enter "To Date" less than the "From Date"
    Then the "Invalid To Date" message is displayed beside "To Date" field
