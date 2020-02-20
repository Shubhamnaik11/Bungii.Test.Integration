@android
Feature: CustomerApplicationLinks
  Check the basic User information is available.

  Background:
    Given I newly installed "Bungii Customer" app
  @sanity
  @regression
    #Following scenario also covered:
    #Tutorials should only be displayed on the app on first installation.
    #Tutorials - 5 pages
    #Verify text on Tutorials pages and swipe. Should be able to swipe back and forth between pages. (Completed PARTIALLY)
    #Dismiss Tutorials by tapping on Start
  Scenario: Tutorials should only be displayed on the app on first installation.
    When I tap on the "Log in" button on Signup Page
    And I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    Then "Terms and Conditions" page should be opened
    And I should see "all details" on Term and Condition agreement
    When I accept Term and Condition agreement
    Then "Tutorial" page should be opened
    And I check that "5" pages of turotial are present
    And I check that if i can swipe the pages
    And I tap the "START" button is present on last page
    When I tap on "Menu" > "Logout" link
    And I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I verify that the tutorial is displayed only once

    @regression

    Scenario: Scheduled Bungiis: Save Money Button redirect to invite page
      When I tap on the "Log in" button on Signup Page
      And I enter customers "8805368840" Phone Number
      And I enter customers "valid" Password
      And I tap on the "Log in" Button on Login screen
      Then "Terms and Conditions" page should be opened
      And I should see "all details" on Term and Condition agreement
      When I accept Term and Condition agreement
      And I check that if i can swipe the pages
      And I tap the "START" button is present on last page
      When I tap on "Menu" > "MY BUNGIIS" link
      Then "MY BUNGIIS" page should be opened
      When I click on "SAVE MONEY" button
      Then "INVITE" page should be opened
      When I tap on "back page" on Bungii estimate
      And I tap on "Menu" > "PROMOS" link
      Then "Promos" page should be opened
      When I click on "GET MORE MONEY" button
      Then "INVITE" page should be opened



        @nobrowser
        Scenario: Sign Up to Drive - when No browser is present on device
          When I tap on the "Log in" button on Signup Page
          And I enter customers "8805368840" Phone Number
          And I enter customers "valid" Password
          And I tap on the "Log in" Button on Login screen
          Then "Terms and Conditions" page should be opened
          And I should see "all details" on Term and Condition agreement
          When I accept Term and Condition agreement
          And I check that if i can swipe the pages
          And I tap the "START" button is present on last page
          When I tap on "Menu" > "SIGN UP TO DRIVE" link
          Then Alert message with Please install a browser in order to access this link. text should be displayed