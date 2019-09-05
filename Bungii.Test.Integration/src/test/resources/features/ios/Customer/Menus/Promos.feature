@ios
@NONBUNGII
@S30READYExcept1
Feature: Promos
  As a Bungii customer
  I Should able to add new promo code

  Background:
    Given I am on Customer logged in Home page

  @regression
  Scenario Outline:As a existing bungii customer , I should not be allowed to use First time only Promo code
    When I logged in Customer application using  "existing" user
    And I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    When I Enter "<Promo>" value in "Promo Code" field in "Promo" Page
    And I click "ADD" button on "PROMOS" screen
    Then user is alerted for "<Expected Message>"
    And I Select "LOGOUT" from Customer App menu

#added promo code in
    Examples:
      | Senario | Promo           | Expected Message      |
      | Invalid | first time only | FIRST TIME ONLY PROMO |

  @regression
  Scenario Outline: As a Bungii Customer , I should be alert while adding invalid promo code
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    When I Enter "<Promo>" value in "Promo Code" field in "Promo" Page
    And I click "ADD" button on "PROMOS" screen
    Then user is alerted for "<Expected Message>"

    Examples:
      | Scenario | Promo   | Expected Message |
      | Invalid | AAAAAAA | Invalid Promo    |

  @notready
  @regression
  Scenario Outline: As a Bungii Customer , I should not able to add Referral promo code after creating account . I Should be alerted that Referral code are for new customer only

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal

    And I log in to admin portal
    And I Select "Promo Code" from admin sidebar
    Then I get promo code for "referral"
    And I switch to "ORIGINAL" instance

    And I logged in Customer application using  "<User Type>" user
    And I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    And I Enter "Referral" value in "Promo Code" field in "Promo" Page
    When I click "ADD" button on "PROMOS" screen
    Then user is alerted for "<Expected Message>"
    When I Select "LOGOUT" from Customer App menu
    Examples:
      | Scenario                           | User Type | Expected Message      |
      | User already having REFERRAL code | referral  | REFERRAL FOR NEW USER |
      | New user (with out REFERRAL code)  | new       | REFERRAL FOR NEW USER |

  @regression
  Scenario: As a Bungii Customer , I should be alerted while added used one off promo code
    When I open new "Chrome" browser for "ADMIN PORTAL"
    When I navigate to admin portal
    And I log in to admin portal
    When I Select "Promo Code" from admin sidebar
    Then I get promo code for "USED ONE OFF"
    When I switch to "ORIGINAL" instance
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    And I Enter "USED ONE OFF" value in "Promo Code" field in "Promo" Page
    When I click "ADD" button on "PROMOS" screen
    Then user is alerted for "Invalid Promo"

  @regression
  Scenario: As a Bungii Customer , I should be alerted while adding already existing code
    When I open new "Chrome" browser for "ADMIN PORTAL"
    When I navigate to admin portal
    And I log in to admin portal
    When I Select "Promo Code" from admin sidebar
    Then I get promo code for "VALID"
    When I switch to "ORIGINAL" instance
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    When I add "VALID" PromoCode
    When I click "ADD" button on "PROMOS" screen
    Then I should able to see expected promo code in available promo code
    When I add Same Promo Code again
    When I click "ADD" button on "PROMOS" screen
    Then user is alerted for "Already Existing Code"

  @regression
  Scenario: When i try to enter expired promo code I should be alerted for Expired Promo code message
    When I open new "Chrome" browser for "ADMIN PORTAL"
    When I navigate to admin portal
    And I log in to admin portal
    When I Select "Promo Code" from admin sidebar
    Then I get promo code for "expired"
    When I switch to "ORIGINAL" instance
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    And I Enter "expired" value in "Promo Code" field in "Promo" Page
    When I click "ADD" button on "PROMOS" screen
    Then user is alerted for "EXPIRED PROMO"

  @regression
  Scenario: When i try to share my promo code , via twitter but there is no application installed then I should be alerted for No twitter Installed message
    Given I have "twitter" app "not installed"
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    When I click "GET MORE MONEY" button on "PROMOS" screen
    Then I should be navigated to "Invite" screen
    Then I get Invite Code
    When I click "SHARE" button on "INVITE" screen
    When I click "SHARE ON TWITTER" button on "INVITE" screen
    Then user is alerted for "No twitter installed"
    Then I should be navigated to "Invite" screen

  @regression
  Scenario: Menu_SaveMoney_ReferralInvite_Facebook_AppInstalled
    Given I have "facebook" app "installed"
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    When I click "GET MORE MONEY" button on "PROMOS" screen
    Then I should be navigated to "Invite" screen
    Then I get Invite Code
    When I click "SHARE" button on "INVITE" screen
    When I click "SHARE ON FACEBOOK" button on "INVITE" screen
    Then I should see "popup to post" Overlay Facebook screen
    When I enter "valid data" on Overlay Facebook screen
    When I tap "Next" button on Overlay Facebook screen
 #   Then I should see "promo server url" Overlay Facebook screen
 #   When I tap "Share" button on Overlay Facebook screen
    Then I should be navigated to "Invite" screen