@web
Feature: Driver_Details

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I click on "Login" link
    #Driver James niZq
    And I enter driver Phone number as "9999995409" and valid password
    And I click "LOG IN button" on driver portal

  #@ready
  @knownissue
  Scenario: Verify Driver Application Details Form - Invalid Data Validations On Exisiting Non Fountain Application
    When I click Next on "Driver Details" page
     Then I should see blank fields validation on "Driver Details" page
     When I enter "invalid" data on Driver Details page
    And I click Next on "Driver Details" page
     Then I should see individual field validations on "Driver Details" page
