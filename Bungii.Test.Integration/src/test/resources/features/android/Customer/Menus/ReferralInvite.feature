@android
Feature: ReferralInvite

  Background:
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Home" link
    And I tap "Referral Invite link" on Home page
    Then I should see "Referral Code" on Invite Page


  @regression
  Scenario: Cust_Menu_ReferralInvite_TextMessage
    When I tap "Share" on Invite page
    And I tap "Share by Text Message" on Invite page
    Then I should see post "on text message app"

  @regression
  Scenario: Cust_Menu_ReferralInvite_Email
    When I tap "Share" on Invite page
    And I tap "Share by Email" on Invite page
    Then I should see post "on gmail app"
    And I Switch to "customer" application on "same" devices

  @regression

  Scenario: Cust_Menu_ReferralInvite_Twitter_AppNotInstalled
    Given I have "twitter" app "not installed"
    When I tap "Share" on Invite page
    And I tap "Share on Twitter" on Invite page
    Then I should see post "on Twitter in browser"
    And I Switch to "customer" application on "same" devices

  @notwitter
 # @regression
  Scenario: Cust_Menu_ReferralInvite_Twitter_AppInstalled
    Given I have "twitter" app "installed"
    When I tap "Share" on Invite page
    And I tap "Share on Twitter" on Invite page
    Then I should see post "Tweet Post in Twitter app"
    And I Switch to "customer" application on "same" devices


  @regression
  Scenario: As Bungii customer I go to Invite Page , Page with Proper info and promocode should be displayed
    Then I should see "all elements" on Invite Page
    When I tap "Back" on Invite page
    Then "Home" page should be opened

