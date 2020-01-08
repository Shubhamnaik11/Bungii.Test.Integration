@android
Feature: CustomerApplicationLinks
  Check the basic User information is available.

  Background:
    Given I newly installed "Bungii Customer" app

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

    @regression1
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
      When I tap on "Menu" > "PROMOS" link
      Then "PROMOS" page should be opened
      When I click on "GET MORE MONEY" button
      Then "INVITE" page should be opened

      @regression1
      Scenario: Customer canNot delete payment method linked to any on-going/scheduled trips
        Given I am logged in as "valid" customer
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "valid" driver
        And I Select "HOME" from driver App menu
        And I Switch to "customer" application on "same" devices
        And I tap on "Menu" > "Home" link

        And I enter "kansas pickup and dropoff locations" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add "1" photos to the Bungii
        And I add loading/unloading time of "30 mins"
        And I select Bungii Time as "next possible scheduled"
        And I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
        When I tap on "Menu" > "PAYMENT" link
        Then "Payment" page should be opened
        When I swipe "default" card on the payment page
        And I tap on "Delete" on Payment page
        Then Alert message with Delete Warning text should be displayed
        And I should see "Payment Method is already associated to a trip" on Payment page
