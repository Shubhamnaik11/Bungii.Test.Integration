@ios
Feature: Log In
  As a Bungii Driver I should be allowed to login only using valid credential

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
  @FAILED2702
  @regression
  Scenario Outline: As Bungii driver I should not able login to application using invalid details.  Scenario:<Scenario>
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message> text should be displayed on driverApp
    And I accept Alert message on driverApp
    Then I should be navigated to "LOG IN" screen on driverApp

    Examples:
      | Scenario                | Username | Password | Expected Message |
      | INVALID PASSWORD        | {VALID}  | Cci1234  | INVALID_PASSWORD |
    #  | EMPTY PASSWORD          | {VALID}  | <BLANK>  | EMPTY_FIELD      |
  #    | EMPTY USERNAME PASSWORD | <BLANK>  | <BLANK>  | EMPTY_FIELD      |
   #   | EMPTY USERNAME          | <BLANK>  | Cci12345 | EMPTY_FIELD      |

  @sanity
  @regression
  Scenario: As Bungii driver . I should able to login to application using valid password
    When I enter phoneNumber :{VALID} and  Password :{VALID}
    And I click "Log In" button on "Log In" screen on driverApp
    Then I should be successfully logged in to the application


  @regression
  Scenario Outline: Driver canNot login on driver app before admin verification
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message> text should be displayed on driverApp
    And I accept Alert message on driverApp
    Then I should be navigated to "LOG IN" screen on driverApp
    Examples:
      | Scenario             | Username   | Password | Expected Message                                  | Login Button Status  |
      | PENDING VERIFICATION | 9823901494 | Cci12345 | Your account registration is still under process. | LOGIN BUTTON ENABLED |

    #Valid failed, driver should be locked
  @regression
  Scenario Outline: Alert should be displayed and driver should be locked when customer enters incorrect password 5 times.
    When I enter phoneNumber :<Username> and  Password :<InCorrectPassword>
    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message> text should be displayed on driverApp
    And I accept Alert message on driverApp

    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message> text should be displayed on driverApp
    And I accept Alert message on driverApp

    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message 3 Times> text should be displayed on driverApp
    And I accept Alert message on driverApp

    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message> text should be displayed on driverApp
    And I accept Alert message on driverApp

    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message 5 Times> text should be displayed on driverApp
    And I accept Alert message on driverApp
#driver should be locked, not allowed to log in
    When I enter phoneNumber :<Username> and  Password :<Correct Password>
    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message 5 Times> text should be displayed on driverApp
    And I accept Alert message on driverApp


    Examples:
      | Username   | InCorrectPassword | Correct Password | Expected Message | Expected Message 3 Times | Expected Message 5 Times |
      | 8888881010 | cci12345          | Cci12345         | INVALID_PASSWORD | INVALID_PASSWORD_3_TIMES | INVALID_PASSWORD_5_TIMES |


  @regression
  Scenario: New driver with payment status Inactive/Pending should Not be able to go Online
    When I enter phoneNumber :8989890909 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    Then I should be successfully logged in to the application
    When I click "Go Online" button on "Home" screen on driverApp
    Then Alert message with HICCUP MESSAGE text should be displayed on driverApp
    And I accept Alert message on driverApp
    When I Select "LOGOUT" from driver App menu

  @regression
  Scenario: Permission - iOS Driver - Turn off location permission - View alert on app
    Given I install Bungii Driver App again
    And I Switch to "driver" application on "same" devices
    When I enter phoneNumber :{VALID} and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    Then I should be navigated to "ALLOW NOTIFICATIONS" screen
    And I should see "all details" on allow notifications driver screen
    When I verify and allow access of Notification from Bungii driver application
    Then I should be navigated to "ALLOW LOCATION" screen
    And I should see "all details" on allow location driver screen
    When I verify and deny access of Location from Bungii driver application
    And I Switch to "driver" application on "same" devices
    Then user is alerted for "PLEASE ENABLE LOCATION SERVICES"
    Given I install Bungii Driver App again

  @regression
  Scenario: Permission - iOS Driver - Notifications
    Given I install Bungii Driver App again
    And I Switch to "driver" application on "same" devices
    When I enter phoneNumber :{VALID} and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    Then I should be navigated to "ALLOW NOTIFICATIONS" screen
    And I should see "all details" on allow notifications driver screen
    When I verify and allow access of Notification from Bungii driver application
    Then I should be navigated to "ALLOW LOCATION" screen
    And I should see "all details" on allow location driver screen
    When I verify and allow access of Location from Bungii driver application
    When I Select "LOGOUT" from driver App menu
    When I enter phoneNumber :{VALID} and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    Then I should be navigated to "Home" screen

