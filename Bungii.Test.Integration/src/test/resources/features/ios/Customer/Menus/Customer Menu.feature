@ios
Feature: Customer	Menu
  As a Bungii customer I want to check all menu links

  Background: 
    Given I am on Customer logged in Home page
  @sanity
  @FAILED2702
  @regression
  Scenario: Verify Customer App Menu Links
    When I Select "Home" from Customer App menu
    Then I should be navigated to "Home" screen
    When I Select "FAQ" from Customer App menu
    Then I should be navigated to "FAQ" screen
    When I Select "ACCOUNT" from Customer App menu
    Then I should be navigated to "ACCOUNT" screen
    When I Select "MY BUNGIIS" from Customer App menu
    Then I should be navigated to "MY BUNGIIS" screen
    When I Select "PAYMENT" from Customer App menu
    Then I should be navigated to "PAYMENT" screen
    When I Select "SUPPORT" from Customer App menu
    Then I should be navigated to "SUPPORT" screen
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    When I Select "DRIVE WITH BUNGII" from Customer App menu
    Then I should be navigated to "bungii.com" screen
    When I Switch to "customer" application on "same" devices
    When I Select "LOGOUT" from Customer App menu
    Then I should be navigated to "LOG IN" screen

  @regression
  Scenario: Verify Customer Support Menu
    When I Select "SUPPORT" from Customer App menu
    Then I should be navigated to "SUPPORT" screen
    And "Support Question" should be present in "SUPPORT" screen
    And "Bungii Customer Logo" should be present in "SUPPORT" screen
    When I Enter "{RANDOM_STRING}" value in "Support Textbox" field in "SUPPORT" Page
    And I click "SEND" button on "SUPPORT" screen
    Then user is alerted for "SUPPORT QUESTION SUBMITTED"

  @regression
  Scenario: Verify Support Menu Validations For Blank Questions
    When I Select "SUPPORT" from Customer App menu
    Then I should be navigated to "SUPPORT" screen
    When I Enter "{EMPTY}" value in "Support Textbox" field in "SUPPORT" Page
    And I click "SEND" button on "SUPPORT" screen
    Then user is alerted for "EMPTY SUPPORT QUESTION"

    #Social media link are not visible now
    # By default 1 answer is collapsed and displayed.
  @ready
  Scenario: Verify Customer FAQ Menu
    When I Select "FAQ" from Customer App menu
    Then I should be navigated to "FAQ" screen
    And I should see "faq image" on FAQ page
#    When I tap on "first question" on FAQ page
    Then I should see "first answer dropdown open" on FAQ page
    When I tap on "first question" on FAQ page
    Then I should see "first answer dropdown close" on FAQ page
 #   And I should see "social media links" on FAQ page
  @regression
  Scenario: Verify Get More Money Link Redirects To Invite Screen
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    When I click "GET MORE MONEY" button on "PROMOS" screen
    Then I should be navigated to "Invite" screen

  @regression
  Scenario: Verify Save Money Button Redirects To Invite Screen
    When I Select "MY BUNGIIS" from Customer App menu
    When I click "SAVE MONEY" button on "MY BUNGIIS" screen
    Then I should be navigated to "Invite" screen
  @failed
  #failing due to BCKD-1103
  @ready
  Scenario: Verify Device Token Deregistration Upon Customer Logout
    Then I customers active flag should be "1"
    When I Select "LOGOUT" from Customer App menu
    Then I customers active flag should be "0"

    #enable restriction on iphone , disable safari. Not to be run with normal regression
  @safaridisabled
  Scenario: Verify DRIVE WITH BUNGII Menu Link With No Safari Browser
    When I Select "DRIVE WITH BUNGII" from Customer App menu
    Then user is alerted for "PLEASE INSTALL A BROWSER"