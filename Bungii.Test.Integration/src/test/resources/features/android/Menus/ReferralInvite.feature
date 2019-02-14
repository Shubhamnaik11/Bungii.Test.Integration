@android

Feature: ReferralInvite

  Background:
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Home" link
    When I tap "Referral Invite link" on Home page
    Then I should see "Referral Code" on Invite Page
    When I tap "Share" on Invite page

  @regression
  Scenario: Cust_Menu_ReferralInvite_TextMessage
    When I tap "Share by Text Message" on Invite page
    Then I should see post "on text message app"
    When I Switch to "customer" application on "same" devices

  @regression
  Scenario: Cust_Menu_ReferralInvite_Email
    When I tap "Share by Email" on Invite page
    Then I should see post "on gmail app"
    When I Switch to "customer" application on "same" devices

  @regression
  Scenario: Cust_Menu_ReferralInvite_Twitter_AppNotInstalled
    When I tap "Share on Twitter" on Invite page
    Then I should see post "on Twitter in browser"
    When I Switch to "customer" application on "same" devices
