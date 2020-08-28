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

  @regression
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
      | All Deliveries |
    And I enter "customers" "first name" in the "Deliveries search" box
    Then I should see "customer first name" listed on the "Deliveries" page
    When I enter "customers" "last name" in the "Deliveries search" box
    Then I should see "customer last name" listed on the "All Deliveries" page


@regression
  Scenario: Verify First and Last Name of customer and driver On Scheduled Trips and Live Trips page
    When I navigate to following pages one by one
      |Page |
      | Scheduled Deliveries |
    And I enter "customers" "first name" in the "Scheduled Deliveries search" box
    Then I should see "customer first name" listed on the "Scheduled Deliveries" page
    When I enter "customers" "last name" in the "Scheduled Deliveries search" box
    Then I should see "customer last name" listed on the "Scheduled Deliveries" page

    When I navigate to following pages one by one
      |Page |
      | Live Deliveries |
    And I enter "customers" "first name" in the "Live Deliveries search" box
    Then I should see "customer first name" listed on the "Live Deliveries" page
    When I enter "customers" "last name" in the "Live Deliveries search" box
    Then I should see "customer last name" listed on the "Live Deliveries" page