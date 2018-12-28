

Feature: Driver_Login

  Background:
    Given I navigate to "Bungii Driver URL"
    When I click "LOG IN link" on driver portal
    Then I should be directed to "LOG IN tab" on Driver portal
  @Web
  Scenario: Driver_Login_Success
    When I enter "valid" driver Phone Number on Driver portal
    And I enter "valid" driver Password on Driver portal
    And I click "LOG IN button" on driver portal
    Then the driver should "be logged in"
    Then the driver logout from dashboard
  @Web
  Scenario: Driver_Login_Blank
    When I click "LOG IN button" on driver portal
    Then the driver should "see validation message for blank fields"
  @Web
  Scenario: Driver_Login_InvalidPhone
    When I enter "invalid" driver Phone Number on Driver portal
    And I click "LOG IN button" on driver portal
    Then the driver should "see validation message for invalid phone field"
  @Web
  Scenario: Driver_Login_IncorrectCredentials
    When I enter "valid" driver Phone Number on Driver portal
    And I enter "invalid" driver Password on Driver portal
    And I click "LOG IN button" on driver portal
    Then the driver should "see validation message for incorrect credentials"