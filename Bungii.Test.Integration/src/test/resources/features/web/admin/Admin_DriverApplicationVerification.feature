@web
Feature: Admin_DriverApplicationVerification

  Background:
    Given I am logged in as Admin
    And there is a pending application for driver verification

  @sanity
  @regression
  @email
    #test data created in base
  Scenario: DriverApplication_Approval_NonFountainFlow
    When I click "Verify" button against the "John MwrB" applicant
    Then I should be directed to "Driver Verification Page"
    And I check if each field has an "accept" option
    And I check if each field has an "reject" option
    And I check if the Save and cancel buttons are seen by default
    When I verify and approve all the verification fields
    And I click on the "Approve Application" Button
    And I confirm the "Driver Application Approval" action
    Then the status of the driver application should be marked as "Active"
    And I should receive "BUNGII: Time to Hit the Road!" email

  @regression
    #test data created in base
  Scenario: DriverApplication_WithRejectedFields_NonFountainFlow
    When I click "Verify" button against the "John owPH" applicant
    Then I should be directed to "Driver Verification Page"
    When I verify and reject the invalid verification fields
    Then the "Approve Application" button is not visible

  @sanity
  @regression
    #test data created in base
  @email
  Scenario: DriverApplication_Rejection_NonFountainFlow
    When I click "Verify" button against the "John dMIk" applicant
    Then I should be directed to "Driver Verification Page"
    When I click on "Reject Application" link
    And I confirm the "Driver Reject Application" action
    And I do not enter the reject reason
    And I click on the "Submit" Button
    Then the validation message "Please add reject reason" is displayed
    When I enter the reject reason
    And I click on the "Submit" Button
    Then the status of the driver application should be marked as "Rejected"
    And I should receive "Your application has been rejected." email

  @sanity
  @regression
    #test data created in base
  Scenario: Driver_ResendApplication_NonFountainFlow
    When I click "Verify" button against the "John PxLK" applicant
    Then I should be directed to "Driver Verification Page"
    When I verify and reject the invalid verification fields
    And I click on the "Resend Application" Button
    And I confirm the "Driver Resend Application" action
    Then the status of the driver application should be marked as "Re-sent to Driver"

  @regression
    #test data created in base
  Scenario: DriverApplication_ResendButtonVisibility_WithAllApprovedFields_NonFountainFlow
    When I click "Verify" button against the "John Annie" applicant
    Then I should be directed to "Driver Verification Page"
    And I verify and approve all the verification fields
    Then the "Resend Application" button is not visible

  @regression
    #test data created in base
  Scenario: DriverApplication_Reject_Cancellation_NonFountainFlow
    When I click "Verify" button against the "John Johnie" applicant
    Then I should be directed to "Driver Verification Page"
    When I click on "Reject Application" link
    And I reject the "Driver Reject Application"confirm action
    And I click on the "Cancel" Button
    Then the status of the driver application should be marked as "Pending Verification"

  @regression
    #test data created in base
  Scenario: DriverApplication_Rejection_WithAllFieldsApproved_NonFountainFlow
    When I click "Verify" button against the "John Tony" applicant
    Then I should be directed to "Driver Verification Page"
    When I verify and approve all the verification fields
    And I click on "Reject Application" link
    And I confirm the "Driver Reject Application" action
    And I enter the reject reason
    And I click on the "Submit" Button
    Then the status of the driver application should be marked as "Rejected"

  @regression
        #test data created in base
  Scenario: Driver_ApplicationStatusChange_NonFountainFlow
    When I click "Verify" button against the "John Jamie" applicant
    Then I should be directed to "Driver Verification Page"
    When I verify and approve the "Driver Picture" field
    Then the status of the field changes to "accepted"
    When I verify and reject the "Driver Picture" field
    Then the status of the field changes to "rejected"
    When I click and reset the status of "Driver Picture" field
    Then the status of the field resets to default

  @regression
    #test data created in base
  Scenario: Driver_ApplicationStatusChange
    When I click "Verify" button against the "Melvin Johnson" applicant
    And I verify all the fields except "Date of Birth"
    And I click on the "Resend Application" Button
    And I confirm the "Driver Resend Application" action
    And I login to the driver portal as driver "Melvin Johnson"
    And I update the rejected "Date of Birth" field
    And I update the accepted "Social Security Number" field
    And I click on "Update" button
    And I submit the updated application
    And I logout of driver portal
    And I am logged in as Admin
    Then there is a pending application for driver verification
    When I click "Verify" button against the "Melvin Johnson" applicant
    Then The accepted tick is removed for "Social Security Number" field previously accepted by admin

