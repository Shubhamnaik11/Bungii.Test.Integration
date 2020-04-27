@android
    #These feature will run in Goa geofence
Feature: VerifyBungiiDetails2

  @regression
  @new
  Scenario: Verify that the My Bungii Past trip is visible when admin manually ends bungii
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdriver_goa_a Android_test" driver
    And I tap on "Go Online button" on Driver Home page
    Then I Switch to "customer" application on "same" devices
    When I am on customer Log in page
    And I am logged in as "Testcustomertywd_appleand_A Android" customer
    When I enter "goa location in pickup and dropoff fields long distance" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I select Bungii Time as "NEW BUNGII TIME"
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "15 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I check if the customer is on success screen
    Then I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    Then I Select Trip from driver scheduled trip

    When Bungii Driver "Start Schedule Bungii" request
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"

    And I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    When I Switch to "driver" application on "same" devices
    Then Bungii Driver "completes Bungii"

    When I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    And "MY BUNGIIS" page should be opened
    And I click on "Past" tab
    Then I verify that the "completed solo" trip is displayed