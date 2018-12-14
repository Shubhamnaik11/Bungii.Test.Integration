Feature: Admin_DriverRejection

  Background:
    Given I am logged in as Admin
    And there is a pending driver verification


  Scenario: Driver_Application_Reject_Sucessfully
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I click on "Reject Application" link
    And I confirm the "Driver Reject Application" action
    And I check the status of the Driver application

  Scenario: Driver_Application_Reject_ConfirmationMessage
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I click on "Reject Application" link
    And I check if "Driver Reject Application" confirm action is shown

  Scenario: Driver_Application_Reject_AfterVerification
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I verify and reject the invalid verification fields
    And I click on "Reject Application" link
    And I confirm the "Driver Reject Application" action
    And I check the status of the Driver application

  Scenario: Driver_Application_Reject_Cancelled
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I click on "Reject Application" link
    And I reject the "Driver Reject Application"confirm action
    And I check the status of the Driver application

  Scenario: Driver_Application_Reject_WithAllFieldsApproved
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I verify and approve all the verification fields
    And I click on "Reject Application" link
    And I confirm the "Driver Reject Application" action
    And I check the status of the Driver application

  Scenario: Driver_Application_Reject_Without Reason
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I click on "Reject Application" link
    And I confirm the "Driver Reject Application" action
    And I do not enter the reject reason
    And I click on "Submit" Button
    And I check if a validation message "Please add reject reason" is shown


