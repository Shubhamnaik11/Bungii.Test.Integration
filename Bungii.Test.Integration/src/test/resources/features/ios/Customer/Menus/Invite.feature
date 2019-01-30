@ios
Feature: Customer	Referral Invite page
  As a Bungii customer I Should able to share my referral code

  Background: 
    Given I am on Customer logged in Home page

  @regression
  Scenario: As Bungii customer I go to Invite Page , Page with Proper info and promocode should be displayed
    When I Select "Home" from Customer App menu
    Then "Invite referrals" should be present in "Home" screen
    When I click "Invite referrals" button on "HOME" screen
    Then I should be navigated to "Invite" screen
    Then Invite Referral page should be properly displayed
    And I click "Done" button on "Invite" screen

  @regression
  Scenario: As   As Bungii customer I should able to share my code using Text messages
    When I Select "Home" from Customer App menu
    When I click "Invite referrals" button on "HOME" screen
    Then I should be navigated to "Invite" screen
    Then I get Invite Code
    When I click "SHARE" button on "INVITE" screen
    When I click "SHARE BY TEXT MESSAGE" button on "INVITE" screen
    Then I should see draft post in "Message" application
    And I click "Done" button on "Invite" screen

  @regression
  Scenario: As Bungii customer I should able to share my code using EMAIL
    When I Select "Home" from Customer App menu
    When I click "Invite referrals" button on "HOME" screen
    Then I should be navigated to "Invite" screen
    Then I get Invite Code
    When I click "SHARE" button on "INVITE" screen
    When I click "SHARE BY EMAIL" button on "INVITE" screen
    Then I should see draft post in "MAIL" application

  @regression
  Scenario: As Bungii customer I go to Invite Page , but should be alerted when I try to share Invite code using Twitter but no Application is installed
    When I Select "Home" from Customer App menu
    When I click "Invite referrals" button on "HOME" screen
    Then I should be navigated to "Invite" screen
    Then I get Invite Code
    When I click "SHARE" button on "INVITE" screen
    When I click "SHARE ON TWITTER" button on "INVITE" screen
    Then user is alerted for "No twitter installed"
