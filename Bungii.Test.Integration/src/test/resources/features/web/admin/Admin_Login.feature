@web
Feature: Admin_Login_Page

  Background:
    Given I am on the "Admin" Portal

  @regression
    Scenario: Verify updated text is displayed in "Earn Extra Cash" potential earnings on admin portal
      Then I should see updated text "$45" in "Earn Extra Cash" potential earnings on "admin" portal
      #	Core-4330 Verify terms and privacy policy is displayed on login page
      Then I check terms and privacy policy is displayed on login page

 #CORE-4493:Reset password functionality is not working. "An Application Error Has Occurred"
  @ready
  Scenario:To verify Admin user can reset the password by sending verification code
    When I click on the "Forget Password" Button
    Then The "Forgot Password" "Header" should be displayed
    When I enter "valid" phone number
    And  I click on the "Send Verification Code" Button
    Then The "Verify your phone" "Header" should be displayed