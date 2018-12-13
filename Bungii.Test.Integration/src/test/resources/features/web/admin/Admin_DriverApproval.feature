Feature: Admin_Driver_Approval

Background: 
Given I am logged in as Admin 
And there is a pending driver verification


Scenario: Admin_Driver_ApproveApplication
	When I click "Verify" button against the applicant name
	Then I should be directed to "Driver Verification Page"  
	And I verify and approve all the verification fields
	And I click on "Approve Application" button
	And I confirm the "Driver Application Approval" action
	
Scenario: Admin_Driver_ApproveApplication_WithRejectedFields
	When I click "Verify" button against the applicant name
	Then I should be directed to "Driver Verification Page"  
	And I verify and reject the invalid verification fields
	And I check if "Approve Application" button is visible
	
Scenario: Admin_Driver_ChangeStatusOfApproveApplication
	When I click "Verify" button against the applicant name
	Then I should be directed to "Driver Verification Page"  
	And I verify and approve all the verification fields
	And I click on "Approve Application" button
	And I confirm the "Driver Application Approval" action
	And I check if I can change the driver application status 


		