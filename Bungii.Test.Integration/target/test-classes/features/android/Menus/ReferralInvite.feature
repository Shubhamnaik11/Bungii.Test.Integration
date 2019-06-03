Feature: ReferralInvite

  Background:
    Given I am logged in as "existing" customer
    When I tap "Referral Invite link" on Invite page
    Then I should see "Referral Code" on Invite Page
    When I tap "Share" on Invite page
  @Android
  Scenario: Cust_Menu_ReferralInvite_TextMessage
    When I tap "Share by Text Message" on Invite page
    Then I should see post "on text message app"
  @Android
  Scenario: Cust_Menu_ReferralInvite_Email
    When I tap "Share by Email" on Invite page
    Then I should see post "on gmail app"
  @Android
  Scenario: Cust_Menu_ReferralInvite_Twitter_AppNotInstalled
    When I tap "Share on Twitter" on Invite page
    Then I should see post "on Twitter in browser"