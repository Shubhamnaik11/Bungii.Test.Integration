@android
Feature: Log In
  As a Bungii Driver I should be allowed to login only using valid credential

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app


  @regression

  Scenario Outline: As Bungii driver I should not able login to application using invalid details.  Scenario:<Scenario>
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    Then I should see "<Expected Message>" on Log In screen on driver app
    And I should see "<Login Button Status>" on Log In screen on driver app

  #  Then I should be navigated to "LOG IN" screen on driverApp

    Examples:
      | Scenario                | Username | Password | Expected Message                          |Login Button Status  |
      | INVALID PASSWORD        | {VALID}  | Cc1234   | SNACK BAR VALIDATION FOR INVALID PASSWORD |LOGIN BUTTON ENABLED |
      | EMPTY PASSWORD          | {VALID}  | <BLANK>  | Empty Password Error                      |LOGIN BUTTON DISABLED|
      | EMPTY USERNAME PASSWORD | <BLANK>  | <BLANK>  | Empty Phone and Password Error            |LOGIN BUTTON DISABLED|
      | EMPTY USERNAME          | <BLANK>  | Cci12345 | Empty Phone Error                         |LOGIN BUTTON DISABLED|


  @sanity
  @regression

  Scenario: As Bungii driver , I should able to login to application using valid password
    When I enter phoneNumber :{VALID} and  Password :{VALID}
    And I click "Log In" button on Log In screen on driver app
    Then I should be navigated to Home screen on driver app

  @regression
  Scenario Outline: Driver canNot login on driver app before admin verification
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    Then I should see "<Expected Message>" on Log In screen on driver app
    And I should see "<Login Button Status>" on Log In screen on driver app
    Examples:
      | Scenario        | Username   | Password | Expected Message                                  | Login Button Status  |
      | PENDING PAYMENT | 9999991009 | Cci12345 | Your account registration is still under process. | LOGIN BUTTON ENABLED |

  @regression
  Scenario Outline: Alert should be displayed and driver should be locked when customer enters incorrect password 5 times.
    When I enter phoneNumber
    And I enter invalid password and click on "Log In" button for "3" times on Log In screen on driver app
    Then I should see "<Expected Message 3>" on Log In screen on driver app
    And I enter invalid password and click on "Log In" button for "2" times on Log In screen on driver app
    Then I should see "<Expected Message 5>" on Log In screen on driver app
    Examples:
      | Expected Message 5                                                                                                    |   Expected Message 3                                                                                    |
      | Invalid login credentials. Your account has been locked. Please use the Forgot Password option to reset your account. | Invalid login credentials. You have exhausted 3 out of 5 attempts of entering the correct credentials.  |

    @regression
      Scenario Outline: New driver with payment status Inactive/Pending should Not be able to go Online
      When I enter phoneNumber :<Username> and  Password :<Password>
      And I click "Log In" button on Log In screen on driver app
      And I tap on "Go Online button" on Driver Home page
      Then I should see "<Expected Message>" on Log In screen on driver app
      Examples:
        | Scenario        | Username   | Password | Expected Message                                  |
        | PENDING PAYMENT | 8989890909 | Cci12345 | It looks like we ran into a hiccup. Please contact support@bungii.com for more information. |

  @regression
  Scenario Outline: As a Bungii driver with Pending payment status, I should not be able to login to application using valid credentials
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    Then I should see "<Expected Message>" on Log In screen on driver app
    And I should see "<Login Button Status>" on Log In screen on driver app
    Examples:
      | Scenario        | Username   | Password | Expected Message                                  | Login Button Status  |
      | PENDING PAYMENT | 9999991009 | Cci12345 | Your account registration is still under process. | LOGIN BUTTON ENABLED |

  @regression

  Scenario Outline: As I enter wrong password 5 times, driver's account gets locked
    When I enter phoneNumber
    And I enter invalid password and click on "Log In" button for "5" times on Log In screen on driver app
    Then I should see "<Expected Message>" on Log In screen on driver app
    Examples:
      | Expected Message                                                                                                      |
      | Invalid login credentials. Your account has been locked. Please use the Forgot Password option to reset your account. |

  @regression

  Scenario: Permission - Android Driver - Location
    Given I have device which has location permission
    Given I install Bungii Driver App again
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    When I enter phoneNumber :{VALID} and  Password :{VALID}
    And I click "Log In" button on Log In screen on driver app
    Then "DRIVER's LOCATION" page should be opened
    And I should see "all details" on allow location driver screen
    When I verify and allow access of Location from Bungii driver application
    Then I should be navigated to Home screen on driver app
    When I Select "LOGOUT" from driver App menu
    When I enter phoneNumber :{VALID} and  Password :{VALID}
    And I click "Log In" button on Log In screen on driver app
    Then I should be navigated to Home screen on driver app
