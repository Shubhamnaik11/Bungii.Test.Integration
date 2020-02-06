@ios

Feature: As a new customer I should be allowed to Sign up on Bungii Customer applicatrion


  Background:
    Given I am on the "SIGN UP" page

  @regression
  Scenario Outline: Referral source should be incremented by 1 if a customer registered by selecting Referral source. Scenario :<Scenario>

    When I open new "Chrome" browser for "ADMIN_PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Referral Source" from admin sidebar
    Then I get Referral Source info for "<Source>"
    When I switch to "ORIGINAL" instance
    And I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    #removed as part of sprint 32
  #  Then Alert message with NO PROMO CODE text should be displayed
  #  When I reject Alert message
    Then I should be navigated to "VERIFICATION" screen
    When I Get SMS CODE for new "Customer"
    And I enter "valid" Verification code
    Then I should be navigated to "Home" screen
    When I switch to "ADMIN_PORTAL" instance
    And I Select "Referral Source" from admin sidebar
    Then account created info for "<Source>" should be "increase by 1"

    Examples:
      | Scenario      | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source |
      | Source :OTHER | Mike       | Test      | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 |               | OTHER  |

  @regression
  Scenario Outline:As a new Bungii Customer I should submit registration form with Promo code
    When I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Promo Code" from admin sidebar
    And I get promo code for "<Referral Code>"
    And I switch to "ORIGINAL" instance
    And I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Enter "<Referral Code>" value in "Referral code" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then I should be navigated to "VERIFICATION" screen
    When I Get SMS CODE for new "Customer"
    And I enter "valid" Verification code
    Then I should be navigated to "Home" screen
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    And I should able to see expected promo code in available promo code
    And I click "INFO" button on "PROMOS" screen
    Then user is alerted for "MINIMUM COST STILL APPLIES"

    Examples:
      | Scenario | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source |
      | VALID    | Mike       | Test      | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 | Promo         | OTHER  |

  @regression
  Scenario Outline: As a new Bungii Customer I should submit registration form with out Promo code
    When I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
#removed as part of sprint 32
#    Then Alert message with NO PROMO CODE text should be displayed
#    When I reject Alert message
    Then I should be navigated to "VERIFICATION" screen
    When I Get SMS CODE for new "Customer"
    And I enter "valid" Verification code
    Then I should be navigated to "Home" screen


    Examples:
      | Scenario | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source   |
      | VALID    | Mike       | Test      | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 |               | Facebook |


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
      | Invalid_Password   | test       | {RANDOM_STRING} | Vishal.bagi@creativecapsule.com | 9403960188   | Cci      |               | facebook | INVALID PASSWORD WHILE SIGNUP |


  @regression
  Scenario Outline: If I try to submit my registration form with invalid Phone number then I should be Alerted for it . Scenario : <Scenario>
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
   # Then Alert message with NO PROMO CODE text should be displayed
  #  When I reject Alert message
    Then user is alerted for "<Expected Message>"

    Examples:
      | Scenario            | First Name | Last Name       | Email ID                        | Phone Number | Password | Referral Code | Source   | Expected Message           |
      | Already Existing No | Vishal     | {RANDOM_STRING} | vishal.bagi@creativecapsule.com | {VALID USER} | Cci12345 |               | facebook | EXISTING USER              |
      | InValid_Phone       | Mike       | tester          | vishal.bagi@creativecapsule.com | 12345        | Cci12345 |               | facebook | INVALID PHONE WHILE SIGNUP |

  @regression
  Scenario Outline: If I try to submit my registration form with invalid Promo code then I should be Alerted for it .
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Enter "<Referral Code>" value in "Referral code" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then user is alerted for "<Expected Message>"
    And I should be navigated to "SIGN UP" screen

    Examples:
      | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source   | Expected Message           |
      | Mike       | tester    | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 | XX            | facebook | INVALID PROMO WHILE SIGNUP |

  #promo code in example
  @regression
  Scenario Outline: Text on Promos page when first time promo code is added
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Enter "<Promo Code>" value in "Referral code" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen

    Then I should be navigated to "VERIFICATION" screen
    When I Get SMS CODE for new "Customer"
    And I enter "valid" Verification code
    Then I should be navigated to "Home" screen
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    And I should able to see expected promo code in available promo code
    Then I should see "first time code subtext" on Promos page
    And I click "INFO" button on "PROMOS" screen
    Then user is alerted for "FIRST TIME PROMO CODE"

    Examples:
      | First Name | Last Name | Email ID                        | Phone Number       | Password | Promo Code | Source   |
      | Ron        | testerr   | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 | ONETESTTIM | facebook |

    #used one off
  #Know issue, no alert
  @regression
  Scenario Outline: To check that validation is displayed on signing up with invalid/used One off promo codes
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Enter "<Referral Code>" value in "Referral code" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
  #  Then user is alerted for "<Expected Message>"
    And I should be navigated to "SIGN UP" screen
    Then I should be navigated to "VERIFICATION" screen
    When I Get SMS CODE for new "Customer"
    And I enter "valid" Verification code
    Then I should be navigated to "Home" screen

    Examples:
      | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source   | Expected Message           |
      | Mike       | tester    | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 | R1D2            | facebook | INVALID PROMO WHILE SIGNUP |

  @regression
  Scenario Outline: To check that Promoter type Promo codes active in future canNot be added by customer through sign up page
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Enter "<Promo Code>" value in "Referral code" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then Alert message with INACTIVE PROMO CODE MESSAGE text should be displayed

    Examples:
      | First Name              | Last Name | Email ID                        | Phone Number       | Password | Promo Code | Source   |
      | RandomTestcustomertywd_apple  | testerr   | richa.naik@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 | HECKNWWAU | facebook |