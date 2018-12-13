Feature: Admin_DriverResendApplication

Background: 
Given I am logged in as Admin 
And there is a pending driver verification


Scenario: Admin_Driver_ResendApplication
	When I click "Verify" button against the applicant name
	Then I should be directed to "Driver Verification Page"  
	And I verify and reject the invalid verification fields
	And I click on "Resend Application" button
	And I confirm the "Driver Resend Application" action
	And I check the status of the Driver application 

Scenario: Admin_Driver_ResendApplication_WithAllApprovedFields
	When I click "Verify" button against the applicant name
	Then I should be directed to "Driver Verification Page"  
	And I verify and approve all the verification fields
	And I check if "Resend Application" button is visible

