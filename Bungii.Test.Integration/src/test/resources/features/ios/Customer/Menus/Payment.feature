@ios

Feature: Payment page
  As a Bungii customer
  I Should able to add/remove/change payment card

  Background:
    Given I am on Customer logged in Home page

  @regression
  Scenario Outline: As Bungii customer I should not be able to add invalid card . <Scenario> Scenario
    When I Select "PAYMENT" from Customer App menu
    Then I should be navigated to "PAYMENT" screen
    When I click "Add new" button on "PAYMENT" screen
    And I enter postal code :<Postal Code> and Cvv: <Cvv> on Card Details page
    And I enter Card No:<CardNo> and Expiry :<Expiry> on Card Details page
    Then I should see <Expected Message> on Payment page
    And I click "Cancel" button on "PAYMENT" screen

    Examples:
      | Scenario       | CardNo       | Expiry | Expected Message | Postal Code       | Cvv       |
      | INVALID_EXPIRY | VISA CARD    | 12/02  | "invalid expiry" | VALID POSTAL CODE | VALID CVV |
      | INVALID_CARD   | INVALID CARD | 12/22  | "invalid card"   | VALID POSTAL CODE | VALID CVV |
    
  @regression
  Scenario Outline: As Bungii customer I should able to add New Card . <Scenario> Scenario
    When I Select "PAYMENT" from Customer App menu
    Then I should be navigated to "PAYMENT" screen
    And PAYMENT page should be properly displayed
    When I click "Add new" button on "PAYMENT" screen
    And I enter Card No:<CardNo> and Expiry :<Expiry> on Card Details page
    And I enter postal code :<Postal Code> and Cvv: <Cvv> on Card Details page
    And I click "ADD PAYMENT METHOD" button on "PAYMENT" screen
    Then I should see "new card" on Payment page

    Examples:
      | Scenario           | CardNo        | Expiry | Postal Code       | Cvv       |
      | ValidCard Discover | DISCOVER CARD | 12/22  | VALID POSTAL CODE | VALID CVV |
      | ValidCard Visa     | VISA CARD     | 12/22  | VALID POSTAL CODE | VALID CVV |

  @FAILED
  @regression
  Scenario: As Bungii customer I should able to change default card
    When I Select "PAYMENT" from Customer App menu
    Then I should be navigated to "PAYMENT" screen
    When I get "current" default card
    And I tap on "Other card" on Payment page
    And I tap on "Checkbox" on Payment page
    When I click "SAVE" button on "PAYMENT" screen
    Then I should see "new default card" on Payment page

  #commented this due to base to auto data issue
    #From sprint30 , we can delete the default card
  @sanity
  @regression
  Scenario Outline: As Bungii customer I should able to delete card
    Given I am on the "SIGN UP" page
    When I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then I should be navigated to "VERIFICATION" screen
    When I Get SMS CODE for new "Customer"
    And I enter "valid" Verification code
    Then I should be navigated to "Home" screen
    When I Select "PAYMENT" from Customer App menu
    When I click "ADD" button on "PAYMENT" screen
    And I enter Card No:<CardNo> and Expiry :<Expiry> on Card Details page
    And I enter postal code :<Postal Code> and Cvv: <Cvv> on Card Details page
    And I click "ADD PAYMENT METHOD" button on "PAYMENT" screen
    Then I should see "new card" on Payment page
    When I swipe "other" card on the payment page
    And I tap on "Delete" on Payment page
    Then Alert message with Delete Warning text should be displayed
    When I accept Alert message
    Then I should see "the card has been deleted" on Payment page
    Examples:
      | Scenario | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source   |CardNo        | Expiry | Postal Code       | Cvv       |
      | VALID    | Mike       | Test      | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 |               | Facebook |VISA CARD     | 12/22  | VALID POSTAL CODE | VALID CVV |


  @FAILED
  @regression
  Scenario Outline:  As new Bungii customer without payment card . Payment page should display add Payment card message and Image .
    When I Select "LOGOUT" from Customer App menu
    Then I should be navigated to "LOG IN" screen
    When I enter Username :<Username> and  Password :<Password>
    And I click "Log In" button on "Log In" screen
    Then User should be successfully logged in to the application
    When I Select "PAYMENT" from Customer App menu
    Then "Add New Card" message should be displayed on "PAYMENT" page
    And "Add Image" should be present in "PAYMENT" screen
    And "ADD" should be present in "PAYMENT" screen
    And I Select "LOGOUT" from Customer App menu

    Examples:
      | Scenario          | Username       | Password       |
      | New_Register_User | {with no card} | {with no card} |

