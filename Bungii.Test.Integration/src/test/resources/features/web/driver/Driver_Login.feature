@web
Feature: Driver_Login

  Background:
    Given I navigate to "Bungii Driver URL"
    When I click "LOG IN link" on driver portal
    Then I should be directed to "LOG IN tab" on Driver portal

  @smoke
  @sanity
  @regression
  Scenario: Verify Driver Login With Valid Credentials
    When I enter "valid" driver Phone Number on Driver portal
    And I enter "valid" driver Password on Driver portal
    And I click "LOG IN button" on driver portal
    Then the driver should "be logged in"
    Then the driver logout from dashboard

  @regression
  @login
  Scenario: Verify Driver Login Validations With Blank Credentials
    When I click "LOG IN button" on driver portal
    Then the driver should "see validation message for blank fields"

  @regression
  Scenario: Verify Driver Login Validations With Invalid Phone Number
    When I enter "invalid" driver Phone Number on Driver portal
    And I click "LOG IN button" on driver portal
    Then the driver should "see validation message for invalid phone field"

  @regression
  Scenario: Verify Driver Login Validations With Invalid Password
    When I enter "valid" driver Phone Number on Driver portal
    And I enter "invalid" driver Password on Driver portal
    And I click "LOG IN button" on driver portal
    Then the driver should "see validation message for incorrect credentials"