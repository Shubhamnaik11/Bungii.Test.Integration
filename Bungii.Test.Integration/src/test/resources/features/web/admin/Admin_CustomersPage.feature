@web
Feature: Admin_CustomersPage

  Background:
    Given I am logged in as TestAdmin


  @regression
  Scenario: Verify Customer Search On Customers Page
    When I click on "Customers" Menu
    Then I should be directed to "Customers Page"
    When I enter "customers" "first name" in the "Customers search" box
    Then I should see "customer first name" listed on the "Customers" page

    When I enter "customers" "last name" in the "Customers search" box
    Then I should see "customer last name" listed on the "Customers" page

  @regression1
  Scenario: Verify Customer Search On Various Pages
    When I navigate to following pages one by one
      |Page |
      | Dashboard |
    And I enter "customers" "first name" in the "Dashboard search" box
    Then I should see "customer first name" listed on the "Dashboard" page
    When I enter "customers" "last name" in the "Dashboard search" box
    Then I should see "customer last name" listed on the "Dashboard" page

    When I navigate to following pages one by one
      |Page |
      | Trips |
    And I enter "customers" "first name" in the "Trips search" box
    Then I should see "customer first name" listed on the "Trips" page
    When I enter "customers" "last name" in the "Trips search" box
    Then I should see "customer last name" listed on the "Trips" page


@test
  Scenario: Verify First and Last Name of customer and driver On Scheduled Trips and Live Trips page
    When I navigate to following pages one by one
      |Page |
      | Scheduled Trips |
    And I enter "customers" "first name" in the "Scheduled Trips search" box
    Then I should see "customer first name" listed on the "Scheduled Trips" page
    When I enter "customers" "last name" in the "Scheduled Trips search" box
    Then I should see "customer last name" listed on the "Scheduled Trips" page

    When I navigate to following pages one by one
      |Page |
      | Live Trips |
    And I enter "customers" "first name" in the "Live Trips search" box
    Then I should see "customer first name" listed on the "Live Trips" page
    When I enter "customers" "last name" in the "Live Trips search" box
    Then I should see "customer last name" listed on the "Live Trips" page