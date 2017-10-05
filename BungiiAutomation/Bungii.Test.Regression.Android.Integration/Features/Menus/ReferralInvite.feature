Feature: ReferralInvite
	
Scenario: Menu_SaveMoney_ReferralInvite_Facebook_AppInstalled
	Given I am logged in as "existing" customer
	When I tap on "Menu" > "Save Money" link
	And I tap "Get More Money" on Save Money page
	Then I should see "all elements" on Invite Page
	When I tap "Share" on Invite page
	And I tap "Share on Facebook" on Invite page
	And I share on "Facebook with app installed"
	Then I should see post "Facebook"

Scenario: Menu_ReferralInvite_Facebook_AppInstalled
	Given I am logged in as "existing" customer
	When I tap "Referral Invite link" on Invite page