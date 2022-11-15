@web
Feature: Admin_ReferralSource

  Background:
    Given I am logged in as Admin
    When I click on "Marketing  > Referral Sources" Menu
    Then I should be directed to "Referral Source Page"

  @sanity
  @regression
    @sn11
  Scenario: Verify Referral Source Grid Calculations
    Then the "Percentage of total(Accounts Created)" should display accurate value for each Source
    And  the "Percentage of total(Trips Completed)" should display accurate value for each Source

  @regression
    @failed
  Scenario: Verify Referral Source Grid Sort ASC DESC
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

  @knownissue
  Scenario: Verify Field Validations On Referral Source Grid [CORE-1934]
    When I click on "Search" button with entering "From" and "To" date
    Then the "From date is required" message is displayed beside "From Date" field
    And the "To date is required" message is displayed beside "To Date" field
    When I enter "To Date" less than the "From Date"
    Then the "Invalid To Date" message is displayed beside "To Date" field
