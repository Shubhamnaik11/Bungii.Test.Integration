@DONE
@SIGNUP_ALL
Feature: Title of your feature
  I want to use this template for my feature file

Background:
 Given I am on the "SIGN UP" page

  @SIGNUP_1
  Scenario Outline: Empty Field
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then user is alerted for "<Expected Message>"

    Examples: 
      | Scenario | First Name | Last Name | Email ID | Phone Number | Password | Referral Code | Source  | Expected Message   |
      | Valid    | {BLANK}    | {BLANK}   | {BLANK}  | {BLANK}      | {BLANK}  |               | {BLANK} | EMPTY SIGNUP FIELD |
      
      
        @SIGNUP_2
  Scenario Outline: Already Existing User
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
      | Scenario | First Name | Last Name       | Email ID                        | Phone Number | Password | Referral Code | Source   | Expected Message |
      | Valid    | Vishal     | {RANDOM_STRING} | vishal.bagi@creativecapsule.com |   9403960188 | Cci12345 |               | facebook | EXISTING USER    |
    
      @SIGNUP_3
  Scenario Outline: Invalid Email
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then user is alerted for "<Expected Message>"

    Examples: 
      | Scenario      | First Name | Last Name       | Email ID | Phone Number | Password | Referral Code | Source   | Expected Message           |
      | Invalid_EMAIL | test       | {RANDOM_STRING} | ss@dd    |   9403960188 | Cci      |               | facebook | INVALID EMAIL WHILE SIGNUP |

  @SIGNUP_4
  Scenario Outline: Invalid Password
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then user is alerted for "<Expected Message>"

    Examples: 
      | Scenario      | First Name | Last Name       | Email ID                        | Phone Number | Password | Referral Code | Source   | Expected Message              |
      | Invalid_EMAIL | test       | {RANDOM_STRING} | Vishal.bagi@creativecapsule.com |   9403960188 | Cci      |               | facebook | INVALID PASSWORD WHILE SIGNUP |

  @SIGNUP_5
  Scenario Outline: Invalid Phone Number
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then Alert message with NO PROMO CODE text should be displayed
    And I reject Alert message
    Then user is alerted for "<Expected Message>"

    Examples: 
      | Scenario      | First Name | Last Name | Email ID                        | Phone Number | Password | Referral Code | Source   | Expected Message           |
      | InValid_Phone | Vishal     | Bagi      | vishal.bagi@creativecapsule.com |        12345 | Cci12345 |               | facebook | INVALID PHONE WHILE SIGNUP |

  @SIGNUP_6
  Scenario Outline: Invalid Promo code
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
      | InValid_Phone | Vishal     | Bagi      | vishal.bagi@creativecapsule.com | {RANDOM_NUM} | Cci12345 | XX            | facebook | INVALID PROMO WHILE SIGNUP |
      

  @SIGNUP_7
  Scenario Outline: Sign up using refferal souce and verify count in admin panel
  
    When I open new "Chrome" browser for "ADMIN" instance
    When I naviagate to admin portal
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
    When I switch to "ADMIN" instance
    When I Select "Referral Source" from admin sidebar
    Then account created info for "Other" should be "increase by 1"

    Examples: 
      | Scenario | First Name | Last Name | Email ID                        | Phone Number | Password | Referral Code | Source |
      | VALID    | Vishal     | Test      | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 |               | OTHER  |





  @SIGNUP_8
  Scenario Outline: Valid Promo code
    When I open new "Chrome" browser for "ADMIN" instance
    When I naviagate to admin portal
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
    Then I should be navigated to "Home" screen
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    Then I should able to see expected promo code in available promo code

    Examples: 
      | Scenario | First Name | Last Name | Email ID                        | Phone Number | Password | Referral Code | Source |
      | VALID    | Vishal     | Test      | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 | Promo         | OTHER  |
      
      
        @SIGNUP_9
  Scenario Outline: Valid flow with out Promo code
 
 
   
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
   Then I should be navigated to "TERMS AND CONDITION" screen
    Then I accept Term and Condition agreement
    Then I should be navigated to "Home" screen


    Examples: 
      | Scenario | First Name | Last Name | Email ID                        | Phone Number | Password | Referral Code | Source |
      | VALID    | Vishal     | Test      | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 |          | Facebook  |


