@android

Feature: Menu_SaveMoney
  @regression
  Scenario: Menu_SaveMenu_FirstTimePromo_Not a first time user
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Promos" link
    And I add "first time" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar stating first time code is for new users" on Save Money page
  #  And I should see the "first time" PromoCode selected by default
    And I tap on "Menu" > "Logout" link
  @sanity
  @regression
  Scenario: Menu_SaveMoney_AddValid
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Promos" link
    And I add "valid" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "promocode added" on Save Money page
    And I tap on "Menu" > "Logout" link

  @regression
  Scenario: Menu_SaveMoney_AddInvalid
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Promos" link
    And I add "invalid" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar message for invalid code" on Save Money page
    And I tap on "Menu" > "Logout" link

  @regression
  Scenario: Menu_SaveMoney_AddExpired
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Promos" link
    And I add "expired" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar message for expired code" on Save Money page
    And I tap on "Menu" > "Logout" link

  @regression
  Scenario: Menu_SaveMenu_Add already added code
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Promos" link
    And I add "valid" PromoCode
    And I tap "Add" on Save Money page
    And I add "valid" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar message for already added code" on Save Money page
    And I tap on "Menu" > "Logout" link

  @regression
  Scenario: Menu_SaveMenu_Referral from SaveMoney page
    Given I am logged in as "newly registered" customer
    When I tap on "Menu" > "Promos" link
    And I add "referral" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar stating referrals are only for new users" on Save Money page
    And I tap on "Menu" > "Logout" link

  @regression
  Scenario: Menu_SaveMenu_OneOff code that has been used
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Promos" link
    And I add "used one off" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar message for used one off code" on Save Money page
    And I tap on "Menu" > "Logout" link

  @regression
  Scenario: Menu_SaveMenu_FirstTimePromo_User already has referral code
    Given I am logged in as "having referral code" customer
    And I tap on "Menu" > "Promos" link
    And I add "referral" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar message stating referral already exists" on Save Money page
    When I tap on the "i" icon
    Then I should see "Promo code for first Bungii selected by default" message on the Promos page
    When I add "valid" PromoCode
    And I tap "Add" on Save Money page
    And I select "different promo code when first time promo code is present" on the Promos page
    Then I should see "First time promo code not used" message on the Promos page
    And I tap on "Menu" > "Logout" link

  @regression
  Scenario: Menu_SaveMoney_ReferralInvite_Facebook_AppInstalled
    Given I have "facebook" app "installed"
    When I am logged in as "existing" customer
    And I tap on "Menu" > "Promos" link
    And I tap "Get More Money" on Save Money page
    Then I should see "all elements" on Invite Page
    When I tap "Share" on Invite page
    And I tap "Share on Facebook" on Invite page
    And I share on "Facebook with app installed"
#    Then I should see post "on Facebook app"

  @regression
  Scenario: When i try to share my promo code , via twitter but there is no application installed then I it should open in browser
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Promos" link
    And I tap "Get More Money" on Save Money page
    Then I should see "Referral Code" on Invite Page
    When I tap "Share" on Invite page
    And I tap "Share on Twitter" on Invite page
    Then I should see post "on Twitter in browser"
    And I Switch to "customer" application on "same" devices

  @regression
  Scenario: PromoCode_Unused_UponCancellation
    Given I am on customer Log in page
    When I am logged in as "no promocode" customer
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Promo Code" on Bungii estimate
    And I add "valid" PromoCode
    And I tap "Add" on Promos page
    And I select the added promo code
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"
    When I tap on "Cancel during search" on Bungii estimate
    Then for a Bungii I should see "Bungii Home page with locations"
    When I tap on "Menu" > "Promos" link
    Then I should see the unused promo code

  @regression
  Scenario: PromoCode_Used_ForRe-searchedBungii
    Given I am on customer Log in page
    When I am logged in as "no promocode" customer
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Promo Code" on Bungii estimate
    And I add "valid" PromoCode
    And I tap "Add" on Promos page
    And I select the added promo code
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"
    When I tap on "Cancel during search" on Bungii estimate
    Then for a Bungii I should see "Bungii Home page with locations"
    When I tap on "Get Estimate button" on Bungii estimate
    Then I should see the previously added promo code present for current Bungii request

  @regression
  Scenario: Expired_PromoCode_UsedForBungii
    Given I am on customer Log in page
    When I am logged in as "New" customer
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then I should see the "expired promo code" no more displayed on the estimates page