@android
Feature: DriverMenu
  In Bungii Driver
  As a logged in driver
  I want to check all menu links

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    Given I am on Driver logged in Home page
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
  @regression
  Scenario: Verify Driver Menus - Leaderboard | Scheduled And Available Bungiis | Earnigs | Account |  Alert Settings | Store | Logout
    When I Select "LEADERBOARD" from driver App menu
    And the "LEADERBOARD" page is opened
    Then I should be able to see data on "LEADERBOARD" page
    When I Select "SCHEDULED BUNGIIS" from driver App menu
    And the "SCHEDULED BUNGIIS" page is opened
    Then I should be able to see data on "SCHEDULED BUNGIIS" page
    When I Select "AVAILABLE BUNGIIS" from driver App menu
    And the "AVAILABLE BUNGIIS" page is opened
    Then I should be able to see data on "AVAILABLE BUNGIIS" page
    When I Select "EARNINGS" from driver App menu
    And the "EARNINGS" page is opened
    Then I should be able to see data on "EARNINGS" page
    When I Select "ACCOUNT" from driver App menu
    And the "ACCOUNT" page is opened
    When I Select "ACCOUNT INFO" from ACCOUNT menu
    Then I should be able to see data on "ACCOUNT INFO" page
    Then I click on "Navigate Back" button on the "ACCOUNT INFO" page
    And I Select "ALERT SETTINGS" from ACCOUNT menu
    #When I Select "ALERT SETTINGS" from driver App menu
    And the "ALERT SETTINGS" page is opened
    Then I should be able to see data on "ALERT SETTINGS" page
    Then I click on "Navigate Back" button on the "ALERT SETTINGS" page
    When I Select "BUNGII STORE" from driver App menu
    And the "STORE" page is opened
    Then I should be able to see data on "BUNGII STORE" page
    When I Select "ACCOUNT" from driver App menu
    And the "ACCOUNT" page is opened
    When I Select "LOGOUT" from ACCOUNT menu
    #When I Select "LOGOUT" from driver App menu
    Then I should be able to see data on "LOGOUT" page
  
  @knownissue
  Scenario: Verify Driver Menus - FAQ
    When I Select "FAQ" from driver App menu
    And the "FAQ" page is opened
    Then I should be able to see data on "FAQ" page
    When I Select "ACCOUNT" from driver App menu
    And the "ACCOUNT" page is opened
    When I Select "LOGOUT" from ACCOUNT menu
    #When I Select "LOGOUT" from driver App menu
    Then I should be able to see data on "LOGOUT" page
    
#failing due to BCKD-1103
  #@regression
  Scenario: Verify Device Token De-registration Upon Driver Logout
    Then Driver active flag should be "1"
    When I Select "ACCOUNT" from driver App menu
    And the "ACCOUNT" page is opened
    When I Select "LOGOUT" from ACCOUNT menu
    #When I Select "LOGOUT" from driver App menu
    Then I should be able to see data on "LOGOUT" page
    Then Driver active flag should be "0"

  @regression
  Scenario: Verify Driver Can Access Trip Histoy Page Upon Clicking Itemised Earnings Hyperlink
    When I Select "EARNINGS" from driver App menu
    And the "EARNINGS" page is opened
    Then I should be able to see data on "EARNINGS" page
    When I click on "Click here to view itemized earnings" hyperlink
    Then I am redirected to "Trip History Page"
  
  # Covered in single scenario to reduce time of execution
  Scenario: Verify Driver FAQ Menu
    When I Select "FAQ" from driver App menu
    And the "FAQ" page is opened
    Then I should be able to see data on "FAQ" page
  
  Scenario:  Verify Driver LEADERBOARD Menu
    When I Select "LEADERBOARD" from driver App menu
    And the "LEADERBOARD" page is opened
    Then I should be able to see data on "LEADERBOARD" page
  
  Scenario:  Verify Driver SCHEDULED BUNGIIS Menu
    When I Select "SCHEDULED BUNGIIS" from driver App menu
    And the "SCHEDULED BUNGIIS" page is opened
    Then I should be able to see data on "SCHEDULED BUNGIIS" page
  
  Scenario: Verify Driver AVAILABLE BUNGIIS Menu
    When I Select "AVAILABLE BUNGIIS" from driver App menu
    And the "AVAILABLE BUNGIIS" page is opened
    Then I should be able to see data on "AVAILABLE BUNGIIS" page
  
  Scenario: Verify Driver EARNINGS Menu
    When I Select "EARNINGS" from driver App menu
    And the "EARNINGS" page is opened
    Then I should be able to see data on "EARNINGS" page
  
  Scenario: Verify Driver ACCOUNT Menu
    When I Select "ACCOUNT" from driver App menu
    And the "ACCOUNT" page is opened
    When I Select "ACCOUNT INFO" from ACCOUNT menu
    Then I should be able to see data on "ACCOUNT INFO" page

  Scenario:  Verify Driver ALERT SETTINGS Menu
    When I Select "ACCOUNT" from driver App menu
    And the "ACCOUNT" page is opened
    And I Select "ALERT SETTINGS" from ACCOUNT menu
    #When I Select "ALERT SETTINGS" from driver App menu
    And the "ALERT SETTINGS" page is opened
    Then I should be able to see data on "ALERT SETTINGS" page
  
  Scenario:  Verify Driver STORE Menu
    When I Select "BUNGII STORE" from driver App menu
    And the "STORE" page is opened
    Then I should be able to see data on "BUNGII STORE" page
  
  Scenario:  Verify Driver LOGOUT Menu
    When I Select "ACCOUNT" from driver App menu
    And the "ACCOUNT" page is opened
    When I Select "LOGOUT" from ACCOUNT menu
    #When I Select "LOGOUT" from driver App menu
    Then I should be able to see data on "LOGOUT" page
    