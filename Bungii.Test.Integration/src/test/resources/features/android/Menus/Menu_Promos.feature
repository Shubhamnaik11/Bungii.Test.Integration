@android

Feature: Menu_SaveMoney

  @regression
  Scenario: Menu_SaveMoney_AddValid
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Promos" link
    When I add "valid" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "promocode added" on Save Money page
    When I tap on "Menu" > "Logout" link

  @regression
  Scenario: Menu_SaveMoney_AddInvalid
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Promos" link
    When I add "invalid" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar message for invalid code" on Save Money page
    When I tap on "Menu" > "Logout" link

  @regression
  Scenario: Menu_SaveMoney_AddExpired
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Promos" link
    When I add "expired" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar message for expired code" on Save Money page
    When I tap on "Menu" > "Logout" link

  @regression
  Scenario: Menu_SaveMenu_Add already added code
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Promos" link
    When I add "valid" PromoCode
    And I tap "Add" on Save Money page
    And I add "valid" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar message for already added code" on Save Money page
    When I tap on "Menu" > "Logout" link

  @regression
  Scenario: Menu_SaveMenu_Referral from SaveMoney page
    Given I am logged in as "newly registered" customer
    When I tap on "Menu" > "Promos" link
    When I add "referral" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar stating referrals are only for new users" on Save Money page
    When I tap on "Menu" > "Logout" link

  @regression
  Scenario: Menu_SaveMenu_FirstTimePromo_Not a first time user
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Promos" link
    When I add "first time" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar stating first time code is for new users" on Save Money page
    When I tap on "Menu" > "Logout" link

  @regression
  Scenario: Menu_SaveMenu_OneOff code that has been used
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Promos" link
    When I add "used one off" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar message for used one off code" on Save Money page
    When I tap on "Menu" > "Logout" link

  @regression
  Scenario: Menu_SaveMenu_FirstTimePromo_User already has referral code
    Given I am logged in as "having referral code" customer
    When I tap on "Menu" > "Promos" link
    When I add "referral" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar message stating referral already exists" on Save Money page
    When I tap on "Menu" > "Logout" link

  @regression
  Scenario: Menu_SaveMoney_ReferralInvite_Facebook_AppInstalled
    Given I have "facebook" app "installed"
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Promos" link
    And I tap "Get More Money" on Save Money page
    Then I should see "all elements" on Invite Page
    When I tap "Share" on Invite page
    And I tap "Share on Facebook" on Invite page
    And I share on "Facebook with app installed"
#    Then I should see post "on Facebook app"

  @new
  Scenario: When i try to share my promo code , via twitter but there is no application installed then I it should open in browser
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Promos" link
    And I tap "Get More Money" on Save Money page
    Then I should see "Referral Code" on Invite Page
    When I tap "Share" on Invite page
    When I tap "Share on Twitter" on Invite page
    Then I should see post "on Twitter in browser"
    When I Switch to "customer" application on "same" devices