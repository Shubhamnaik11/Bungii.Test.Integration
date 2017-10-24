Feature: CustomerSignup
	Sign up as a Customer on Bungii app
	
Background:
	Given I am on Sign up page

Scenario: Signup_NoPromo_Success_ReferralSourceCount
	Given I have existing details of "Referral Counts"
    When I enter "unique" customer phone number
	And I enter "valid" data in mandatory fields
    And I tap on the "Sign Up" button
	And I tap on the "No, Continue" button
    When I enter "valid" Verification code
	And I tap on the "Verification Continue" Link
	Then The user should be logged in
	And Admin portal should have updated value of "Referral Counts"

Scenario: Signup_AllFieldsBlank
	When I enter "blank" customer phone number
	And I enter "blank" data in mandatory fields
	Then the new user should see "sign up button disabled"

Scenario: Signup_InvalidDetails
	When I enter "invalid" customer phone number
	And I enter "invalid" data in mandatory fields
	Then the new user should see "validations for all fields"

Scenario: Signup_SuccessValidPromoCode
	When I enter "unique" customer phone number
	And I enter "valid" data in mandatory fields
	And I enter "ValidPercent" promo code
	And I tap on the "Sign Up" button
	And I enter "valid" Verification code
	And I tap on the "Verification Continue" Link
	Then The user should be logged in
	When I tap on "Menu" > "Save Money" link
	Then "ValidPercent" promo code should be displayed

Scenario: Signup_InvalidReferralCode-Yes
	When I enter "unique" customer phone number
	And I enter "valid" data in mandatory fields
	And I enter "invalid" promo code
	And I tap on the "Sign Up" button
	And I tap on the "Yes" button
	Then the new user should see "Signup page"

Scenario: Signup_ExistingPhoneNumber
	When I enter "existing" customer phone number
	And I enter "valid" data in mandatory fields
	And I tap on the "Sign Up" button
	And I tap on the "No, Continue" button
	Then the new user should see "snackbar validation message"
	And the new user should see "Signup page"