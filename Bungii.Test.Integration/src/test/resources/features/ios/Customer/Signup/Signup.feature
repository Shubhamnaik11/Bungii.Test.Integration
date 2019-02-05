@ios
Feature: As a new customer I should be allowed to Sign up on Bungii Customer applicatrion


  Background:
    Given I am on the "SIGN UP" page


  @regression
  Scenario Outline: Referral source should be incremented by 1 if a customer registered by selecting Referral source. Scenario :<Scenario>

    When I open new "Chrome" browser for "ADMIN_PORTAL"
    When I navigate to admin portal
    And I log in to admin portal
    When I Select "Referral Source" from admin sidebar
    Then I get Referral Source info for "<Source>"
    When I switch to "ORIGINAL" instance
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then Alert message with NO PROMO CODE text should be displayed
    And I reject Alert message
    Then I should be navigated to "VERIFICATION" screen
    And I Get SMS CODE for new "Customer"
    When I enter "valid" Verification code
#    Then I should be navigated to "TERMS AND CONDITION" screen
#    Then I accept Term and Condition agreement
    Then I should be navigated to "Home" screen
    When I switch to "ADMIN_PORTAL" instance
    When I Select "Referral Source" from admin sidebar
    Then account created info for "<Source>" should be "increase by 1"

    Examples:
      | Scenario      | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source |
      | Source :OTHER | Vishal     | Test      | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 |               | OTHER  |

  @regression
  Scenario Outline:As a new Bungii Customer I should submit registration form with Promo code
    When I open new "Chrome" browser for "ADMIN"
    When I navigate to admin portal
    And I log in to admin portal
    When I Select "Promo Code" from admin sidebar
    Then I get promo code for "<Referral Code>"
    When I switch to "ORIGINAL" instance
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Enter "<Referral Code>" value in "Referral code" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then I should be navigated to "VERIFICATION" screen
    And I Get SMS CODE for new "Customer"
    When I enter "valid" Verification code
  #  Then I should be navigated to "TERMS AND CONDITION" screen
  #  Then I accept Term and Condition agreement
    Then I should be navigated to "Home" screen
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    Then I should able to see expected promo code in available promo code

    Examples:
      | Scenario | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source |
      | VALID    | Vishal     | Test      | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 | Promo         | OTHER  |

  @regression
  Scenario Outline: As a new Bungii Customer I should submit registration form with out Promo code
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen

    Then Alert message with NO PROMO CODE text should be displayed
    And I reject Alert message
    Then I should be navigated to "VERIFICATION" screen

    And I Get SMS CODE for new "Customer"

    When I enter "valid" Verification code
  #  Then I should be navigated to "TERMS AND CONDITION" screen
  #  Then I accept Term and Condition agreement
    Then I should be navigated to "Home" screen


    Examples:
      | Scenario | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source   |
      | VALID    | Vishal     | Test      | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 |               | Facebook |


  @regression
  Scenario Outline: If I try to submit my registration form with invalid details then I should be Alerted for it . Scenario : <Scenario>
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then user is alerted for "<Expected Message>"

    Examples:
      | Scenario           | First Name | Last Name       | Email ID                        | Phone Number | Password | Referral Code | Source   | Expected Message              |
      | EMPTY SIGNUP FIELD | {BLANK}    | {BLANK}         | {BLANK}                         | {BLANK}      | {BLANK}  |               | {BLANK}  | EMPTY SIGNUP FIELD            |
      | Invalid_EMAIL      | test       | {RANDOM_STRING} | ss@dd                           | 9403960188   | Cci12345 |               | facebook | INVALID EMAIL WHILE SIGNUP    |
      | Invalid_EMAIL      | test       | {RANDOM_STRING} | Vishal.bagi@creativecapsule.com | 9403960188   | Cci      |               | facebook | INVALID PASSWORD WHILE SIGNUP |

  @regression
  Scenario Outline: If I try to submit my registration form with invalid Phone number then I should be Alerted for it . Scenario : <Scenario>
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then Alert message with NO PROMO CODE text should be displayed
    And I reject Alert message
    Then user is alerted for "<Expected Message>"

    Examples:
      | Scenario            | First Name | Last Name       | Email ID                        | Phone Number | Password | Referral Code | Source   | Expected Message           |
      | Already Existing No | Vishal     | {RANDOM_STRING} | vishal.bagi@creativecapsule.com | {VALID USER} | Cci12345 |               | facebook | EXISTING USER              |
      | InValid_Phone       | Vishal     | Bagi            | vishal.bagi@creativecapsule.com | 12345        | Cci12345 |               | facebook | INVALID PHONE WHILE SIGNUP |

  @regression
  Scenario Outline: If I try to submit my registration form with invalid Promo code then I should be Alerted for it .
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Referral Code>" value in "Referral code" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then user is alerted for "<Expected Message>"
    Then I should be navigated to "SIGN UP" screen

    Examples:
      | Scenario      | First Name | Last Name | Email ID                        | Phone Number | Password | Referral Code | Source   | Expected Message           |
      | InValid_Phone | Vishal     | Bagi      | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 | XX            | facebook | INVALID PROMO WHILE SIGNUP |
