@web
Feature: Admin_Login_Page

  Background:
    Given I am on the "Admin" Portal

    @ready
    Scenario: Verify updated text is displayed in "Earn Extra Cash" potential earnings on admin portal
      Then I should see updated text "$45" in "Earn Extra Cash" potential earnings on "admin" portal
      #	Core-4330 Verify terms and privacy policy is displayed on login page
      Then I check terms and privacy policy is displayed on login page