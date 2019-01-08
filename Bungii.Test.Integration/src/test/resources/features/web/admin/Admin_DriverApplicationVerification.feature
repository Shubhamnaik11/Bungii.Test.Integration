@web
Feature: Admin_DriverApplicationVerification

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I enter "valid" details on Signup page
    And I enter "unique" driver phone number on Signup page
    And I click "Signup button" on driver portal
    Then I should be directed to "phone verification page" on Driver portal
    When I enter "correct" verification code
    And I click "Submit verification code" on driver portal
    And I should see "Logged in user name" on Driver Registration
    And I click "Continue Registration" on driver portal
    And I enter "valid" data on Driver Details page
    And I click Next on "Driver Details" page
    And I enter "valid" data on Pickup Information page
    And I click Next on "Pickup Information" page
    And I enter "valid" data on Documentation page
    And I click Next on "Documentation" page
    And I enter "valid" data on Bank Details page
    And I click Next on "Bank Details" page
    And I click "I agree to the Terms and Conditions" on driver portal
    And I click Next on "Terms & Conditions" page
    And I click "I have viewed the entire video" on driver portal
    And I click Next on "Video Training" page
    And I click "Continue on Finish page" on driver portal
    And the driver logout from dashboard
    And I am logged in as Admin
    And there is a pending driver verification

  @sanity
  @regression
  Scenario: Admin_DriverApplicationApproval
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I check if each field has an "accept" option
    And I check if each field has an "reject" option
    And I check if the Save and cancel buttons are seen by default
    When I verify and approve all the verification fields
    And I click on the "Approve Application" Button
    And I confirm the "Driver Application Approval" action
    Then the status of the driver application should be marked as "Active"

  @regression
  Scenario: Admin_Driver_ApproveApplication_WithRejectedFields
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    When I verify and reject the invalid verification fields
    Then the "Approve Application" button is not visible

  @sanity
  @regression
  Scenario: Admin_DriverApplicationRejectionAfterVerification
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    When I click on "Reject Application" link
    And I confirm the "Driver Reject Application" action
    And I do not enter the reject reason
    And I click on the "Submit" Button
    Then the validation message "Please add reject reason" is displayed
    When I enter the reject reason
    And I click on the "Submit" Button
    Then the status of the driver application should be marked as "Rejected"

  @sanity
  @regression
  Scenario: Admin_DriverResendApplication
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    When I verify and reject the invalid verification fields
    And I click on the "Resend Application" Button
    And I confirm the "Driver Resend Application" action
    Then the status of the driver application should be marked as "Re-sent to Driver"

  @regression
  Scenario: Admin_DriverResendApplicationWithAllApprovedFields
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I verify and approve all the verification fields
    Then the "Resend Application" button is not visible

  @regression
  Scenario: Driver_Application_Reject_Cancelled
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    When I click on "Reject Application" link
    And I reject the "Driver Reject Application"confirm action
    And I click on the "Cancel" Button
    Then the status of the driver application should be marked as "Pending Verification"

  @regression
  Scenario: Driver_Application_Reject_WithAllFieldsApproved
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    When I verify and approve all the verification fields
    And I click on "Reject Application" link
    And I confirm the "Driver Reject Application" action
    And I enter the reject reason
    And I click on the "Submit" Button
    Then the status of the driver application should be marked as "Rejected"

  @regression
  Scenario: Admin_DriverVerifyStatusChange
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    When I verify and approve the "Driver Picture" field
    Then the status of the field changes to "accepted"
    When I verify and reject the "Driver Picture" field
    Then the status of the field changes to "rejected"
    When I click and reset the status of "Driver Picture" field
    Then the status of the field resets to default

