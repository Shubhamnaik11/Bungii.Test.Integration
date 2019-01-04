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

  @regression1
  Scenario: Admin_Driver_ApproveApplication
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I verify and approve all the verification fields
    And I click on "Approve Application" button
    And I confirm the "Driver Application Approval" action



  @reworkneeded
  Scenario: Admin_Driver_ApproveApplication_WithRejectedFields
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I verify and reject the invalid verification fields
    And I check if "Approve Application" button is visible

  @reworkneeded
  Scenario: Admin_Driver_ChangeStatusOfApproveApplication
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I verify and approve all the verification fields
    And I click on "Approve Application" button
    And I confirm the "Driver Application Approval" action
    And I check if I can change the driver application status

  @reworkneeded
  Scenario: Driver_Application_Reject_Sucessfully
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I click on "Reject Application" link
    And I confirm the "Driver Reject Application" action
    And I check the status of the Driver application

  @reworkneeded
  Scenario: Driver_Application_Reject_ConfirmationMessage
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I click on "Reject Application" link
    And I check if "Driver Reject Application" confirm action is shown

  @reworkneeded
  Scenario: Driver_Application_Reject_AfterVerification
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I verify and reject the invalid verification fields
    And I click on "Reject Application" link
    And I confirm the "Driver Reject Application" action
    And I check the status of the Driver application

  @reworkneeded
  Scenario: Driver_Application_Reject_Cancelled
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I click on "Reject Application" link
    And I reject the "Driver Reject Application"confirm action
    And I check the status of the Driver application

  @reworkneeded
  Scenario: Driver_Application_Reject_WithAllFieldsApproved
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I verify and approve all the verification fields
    And I click on "Reject Application" link
    And I confirm the "Driver Reject Application" action
    And I check the status of the Driver application

  @reworkneeded
  Scenario: Driver_Application_Reject_Without Reason
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I click on "Reject Application" link
    And I confirm the "Driver Reject Application" action
    And I do not enter the reject reason
    And I click on "Submit" Button
    And I check if a validation message "Please add reject reason" is shown

  @reworkneeded
  Scenario: Admin_Driver_ResendApplication
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I verify and reject the invalid verification fields
    And I click on "Resend Application" button
    And I confirm the "Driver Resend Application" action
    And I check the status of the Driver application

  @reworkneeded
  Scenario: Admin_Driver_ResendApplication_WithAllApprovedFields
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I verify and approve all the verification fields
    And I check if "Resend Application" button is visible

  @reworkneeded
  Scenario: Admin_Driver_VerifyFields
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I check if each field has an "accept" option
    And I check if each field has an "reject" option

  @reworkneeded
  Scenario: Admin_Driver_VerifyStatus
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I verify and approve the "Driver Picture" field
    And I check if the status has been changed to "accepted"
    And I verify and reject the "Birthday" field
    And I check if the status has been changed to "rejected"

  @reworkneeded
  Scenario: Admin_Driver_VerifyStatusChange
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I verify and approve the "Driver Picture" field
    And I check if the status has been changed to "accepted"
    And I change the status of the "Driver Picture" field
    And I check if the status of "Driver Picture" field has been changed to rejected

  @reworkneeded
  Scenario: Admin_Driver_VerifyStatus_AcceptedToReset
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I verify and approve the "Driver Picture" field
    And I check if the status has been changed to "accepted"
    And I click and reset the status of "Driver Picture" field
    And I verify that the status has been reset

  @reworkneeded
  Scenario: Admin_Driver_VerifyStatus_RejectedToReset
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I verify and reject the "Driver Picture" field
    And I check if the status of "Driver Picture" field has been changed to rejected
    And I click and reset the Rejected status of "Driver Picture" field
    And I verify that the status has been reset

  @reworkneeded
  Scenario: Admin_Driver_VerifyDefaultButtons
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I check if the Save and cancel buttons are seen by default

  @reworkneeded
  Scenario: Admin_Driver_VerifyConfirmCancel
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I verify and approve the "Driver Picture" field
    And I click on the "Cancel" button
    And I check if a Cancel confirmation message is shown

  @reworkneeded
  Scenario: Admin_Driver_VerifyConfirmCancelWhenNoChangesAreMade
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I click on the "Cancel" button
    And I check if a Cancel confirmation message is not shown

  @reworkneeded
  Scenario: Admin_Driver_VerifyAdminDirectstoDashboardOnCancel
    When I click "Verify" button against the applicant name
    Then I should be directed to "Driver Verification Page"
    And I click on the "Cancel" button
    And I check if admin gets directed to dashboard