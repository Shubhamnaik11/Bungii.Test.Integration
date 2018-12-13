Feature: Admin_DriverVerification

Background: 
Given I am logged in as Admin 
And there is a pending driver verification


Scenario: Admin_Driver_VerifyFields
	When I click "Verify" button against the applicant name
	Then I should be directed to "Driver Verification Page"  
	And I check if each field has an "accept" option
	And I check if each field has an "reject" option

Scenario: Admin_Driver_VerifyStatus
	When I click "Verify" button against the applicant name
	Then I should be directed to "Driver Verification Page"  
	And I verify and approve the "Driver Picture" field
	And I check if the status has been changed to "accepted"
	And I verify and reject the "Birthday" field
	And I check if the status has been changed to "rejected"

Scenario: Admin_Driver_VerifyStatusChange
	When I click "Verify" button against the applicant name
	Then I should be directed to "Driver Verification Page"  
	And I verify and approve the "Driver Picture" field
	And I check if the status has been changed to "accepted"
	And I change the status of the "Driver Picture" field
	And I check if the status of "Driver Picture" field has been changed to rejected

Scenario: Admin_Driver_VerifyStatus_AcceptedToReset
	When I click "Verify" button against the applicant name
	Then I should be directed to "Driver Verification Page"  
	And I verify and approve the "Driver Picture" field
	And I check if the status has been changed to "accepted"
	And I click and reset the status of "Driver Picture" field
	And I verify that the status has been reset 

Scenario: Admin_Driver_VerifyStatus_RejectedToReset
	When I click "Verify" button against the applicant name
	Then I should be directed to "Driver Verification Page"  
	And I verify and reject the "Driver Picture" field
	And I check if the status of "Driver Picture" field has been changed to rejected
	And I click and reset the Rejected status of "Driver Picture" field
	And I verify that the status has been reset 

Scenario: Admin_Driver_VerifyDefaultButtons
	When I click "Verify" button against the applicant name
	Then I should be directed to "Driver Verification Page"  
	And I check if the Save and cancel buttons are seen by default

Scenario: Admin_Driver_VerifyConfirmCancel
	When I click "Verify" button against the applicant name
	Then I should be directed to "Driver Verification Page"  
	And I verify and approve the "Driver Picture" field
	And I click on the "Cancel" button
	And I check if a Cancel confirmation message is shown 

Scenario: Admin_Driver_VerifyConfirmCancelWhenNoChangesAreMade
	When I click "Verify" button against the applicant name
	Then I should be directed to "Driver Verification Page"  
	And I click on the "Cancel" button
	And I check if a Cancel confirmation message is not shown 	

Scenario: Admin_Driver_VerifyAdminDirectstoDashboardOnCancel
	When I click "Verify" button against the applicant name
	Then I should be directed to "Driver Verification Page"  
	And I click on the "Cancel" button
	And I check if admin gets directed to dashboard
	 
	

	