package com.bungii.ios.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.RandomGeneratorUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.ScheduledTripsPage;
import com.bungii.ios.pages.customer.*;
import com.bungii.ios.pages.driver.BungiiCompletedPage;
import com.bungii.ios.pages.driver.BungiiRequestPage;
import com.bungii.ios.pages.driver.DriverBungiiDetailsPage;
import com.bungii.ios.pages.driver.TripDetailsPage;
import com.bungii.ios.pages.other.NotificationPage;
import com.bungii.ios.stepdefinitions.customer.HomeSteps;
import com.bungii.ios.stepdefinitions.customer.LogInSteps;
import com.bungii.ios.stepdefinitions.customer.VerificationSteps;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;


public class CommonSteps extends DriverBase {
	private static LogUtility logger = new LogUtility(VerificationSteps.class);

	private EstimatePage estimatePage;
	private HomePage homePage;
	private com.bungii.ios.pages.driver.HomePage driverHomePage;
	private BungiiDetails customerbungiiDetails;
	private DriverBungiiDetailsPage driverbungiiDetailspage;
	private SuccessPage successPage;
	private LoginPage loginPage;
	private SignupPage signupPage;
	private TripDetailsPage tripDetails;
	private com.bungii.ios.pages.driver.UpdateStatusPage driverUpdateStatusPage;
	private com.bungii.ios.pages.customer.UpdateStatusPage customerUpdateStatusPage;

	private TripDetailsPage tripDetailsPage;
	private BungiiCompletedPage driverBungiiCompletedPage;
	private BungiiCompletePage customerBungiiCompletePage;
	private PromotionPage promotionPage;
	private ScheduledTripsPage scheduledTripsPage;
	private ScheduledBungiiPage customerScheduledBungiiPage;
	private com.bungii.ios.pages.driver.ScheduledBungiiPage driverScheduledBungiiPage;
	private FaqPage faqPage;
	private PaymentPage paymentPage;
	private SupportPage supportPage;
	private PromosPage promosPage;
	private AccountPage accountPage;
	private ScheduledBungiiPage scheduledBungiiPage;
	private InvitePage invitePage;
	private ForgotPasswordPage forgotPasswordPage;
	private VerificationPage verificationPage;
	private SearchingPage searchingPage;
	private DriverNotAvailablePage driverNotAvailablePage;
	private NotificationPage notificationPage;
	private BungiiRequestPage bungiiRequestPage;
	private BungiiAcceptedPage bungiiAcceptedPage;
	private TermsAndConditionPage termsAndConditionPage;
	ActionManager action = new ActionManager();
	public CommonSteps(FaqPage faqPage, ScheduledBungiiPage scheduledBungiiPage, AccountPage accountPage,
                       PaymentPage paymentPage, SupportPage supportPage, PromosPage promosPage, EstimatePage estimatePage,
                       HomePage homePage, LoginPage loginPage, SignupPage signupPage,
                       ScheduledBungiiPage customerScheduledBungiiPage,
                       com.bungii.ios.pages.driver.ScheduledBungiiPage driverScheduledBungiiPage,BungiiDetails bungiiDetails,
                       TripDetailsPage tripDetails, DriverBungiiDetailsPage driverbungiiDetailspage,
					   com.bungii.ios.pages.driver.UpdateStatusPage updateStatusPage, SuccessPage successPage, TripDetailsPage tripDetailsPage,
                       BungiiCompletedPage bungiiCompletedPage, BungiiCompletePage customerBungiiCompletePage,
                       PromotionPage promotionPage, ScheduledTripsPage scheduledTripsPage, InvitePage invitePage,
                       ForgotPasswordPage forgotPasswordPage, SearchingPage searchingPage,
                       DriverNotAvailablePage driverNotAvailablePage, NotificationPage notificationPage,
                       BungiiRequestPage bungiiRequestPage, com.bungii.ios.pages.customer.UpdateStatusPage customerUpdateStatusPage,
                       BungiiAcceptedPage bungiiAcceptedPage, com.bungii.ios.pages.driver.HomePage driverHomePage,
                       VerificationPage verificationPage, TermsAndConditionPage termsAndConditionPage) {
		this.estimatePage = estimatePage;
		this.homePage = homePage;
		this.loginPage = loginPage;
		this.signupPage = signupPage;
		this.customerbungiiDetails = bungiiDetails;
		this.tripDetails = tripDetails;
		this.driverbungiiDetailspage = driverbungiiDetailspage;
		this.driverUpdateStatusPage = updateStatusPage;
		this.successPage = successPage;
		this.tripDetails = tripDetails;
		this.driverBungiiCompletedPage = bungiiCompletedPage;
		this.customerBungiiCompletePage = customerBungiiCompletePage;
		this.promotionPage = promotionPage;
		this.scheduledTripsPage = scheduledTripsPage;
		this.customerScheduledBungiiPage = customerScheduledBungiiPage;
		this.driverScheduledBungiiPage = driverScheduledBungiiPage;
		this.faqPage = faqPage;
		this.paymentPage = paymentPage;
		this.supportPage = supportPage;
		this.promosPage = promosPage;
		this.accountPage = accountPage;
		this.scheduledBungiiPage = scheduledBungiiPage;
		this.invitePage = invitePage;
		this.forgotPasswordPage = forgotPasswordPage;
		this.searchingPage = searchingPage;
		this.driverNotAvailablePage = driverNotAvailablePage;
		this.notificationPage = notificationPage;
		this.bungiiRequestPage = bungiiRequestPage;
		this.customerUpdateStatusPage = customerUpdateStatusPage;
		this.bungiiAcceptedPage = bungiiAcceptedPage;
		this.driverHomePage = driverHomePage;
		this.termsAndConditionPage = termsAndConditionPage;
		this.verificationPage = verificationPage;
	}
	String Image_Solo = "bungii_type-solo";

	public void recoverScenario() {

	}

	@Then("^\"([^\"]*)\" message should be displayed on \"([^\"]*)\" page$")
	public void something_message_should_be_displayed_on_something_page(String messageElement, String screen) {
		try {
			boolean messageDisplayed = false;

			switch (messageElement.toUpperCase()) {
			case "BUNGII CANCEL":
				messageDisplayed =scheduledTripsPage.isElementEnabled(scheduledTripsPage.Text_Success()) && scheduledTripsPage.Text_Success().getText().equals(PropertyUtility.getMessage("admin.cancel.sucess"));
				break;
			case "ADD NEW CARD":
				messageDisplayed= action.getValueAttribute(paymentPage.Text_AddInfo()).equals(PropertyUtility.getMessage("customer.payment.add")) && paymentPage.isElementEnabled(paymentPage.Text_AddHeader());
				break;
			case "FORGOT PASSWORD INFORMATION":
				messageDisplayed = action.getValueAttribute(forgotPasswordPage.Text_Info())
						.equals(PropertyUtility.getMessage("customer.forgotpassword.info"));
				break;
			default:
				error("UnImplemented Step or incorrect button name", "UnImplemented Step");
				break;
			}
			testStepVerify.isTrue(messageDisplayed,
										messageElement + " should be displayed", messageElement + " Message is Displayed",
					messageElement + " Message is not Displayed");
		} catch (Throwable e) {
			logger.error("Error performing step" + e.getMessage());
			error( "Step  Should be sucessfull",
					"Error performing step,Error", true);
		}
	}

	@Then("^\"([^\"]*)\" should be present in \"([^\"]*)\" screen$")
	public void something_should_be_present_in_something_screen(String button, String screen) {

		try {
			boolean isFound = false;
			switch (button.toUpperCase()) {
			case "GET ESTIMATE":
				// homePage.clickEstimateButton();
				break;
			case "INVITE REFERRALS":
				isFound = homePage.isElementEnabled((homePage.Button_Invite()));
				break;
			case "BUNGII CUSTOMER LOGO":
				isFound =supportPage.isElementEnabled(supportPage.Image_CustLogo());
				break;
			case "SUPPORT QUESTION":
				isFound = action.getValueAttribute(supportPage.Text_SupportQuestion()).equals(PropertyUtility.getMessage("customer.support.question"));
				break;
			case "ADD IMAGE":
				isFound = paymentPage.isElementEnabled(paymentPage.Image_Add());
				break;
			case "ADD":
				isFound = paymentPage.isElementEnabled(paymentPage.Button_ADD());
				break;
			default:
				error( "UnImplemented Step or incorrect button name",
						"UnImplemented Step");
				break;
			}

			testStepVerify.isTrue(isFound,
					button + "should be present in " + screen + " screen",
					button + "is present in " + screen + " screen", button + "is not present in " + screen + " screen");
		} catch (Throwable e) {
			logger.error("Error performing step" + e.getMessage());
			error( "Step  Should be sucessfull",
					"Error performing step,Error", true);
		}
	}

	@And("^I click \"([^\"]*)\" button on \"([^\"]*)\" screen$")
	public void iClickButtonOnScreen(String button, String screen) {
		try {

			switch (button.toUpperCase()) {
			case "GET ESTIMATE":
				action.click(homePage.Button_GetEstimate());
				break;
			case "INVITE REFERRALS":
				action.click(homePage.Button_Invite());
				break;
			case "SHARE":
				action.click(invitePage.Button_Share());
				break;
			case "LOG IN":
				if (screen.equalsIgnoreCase("log in"))
					action.click(loginPage.Button_Login());
				else if (screen.equalsIgnoreCase("sign up"))
					action.click(signupPage.Button_Login());
				else
					System.err.println("test");
				// none, error
				break;
			case "SIGN UP":
				if (screen.equalsIgnoreCase("SIGN UP")) {
					action.swipeUP();
					action.click(signupPage.Button_Signup());
				}
				else
					action.click(loginPage.Button_SignUp());
				break;
			case "DONE":
				if (screen.equalsIgnoreCase("invite"))
					action.click(invitePage.Button_Done());
				else{
					action.swipeUP();
				action.click(successPage.Button_Done());}
				break;
			case "ON TO THE NEXT ONE":
				action.click(driverBungiiCompletedPage.Button_NextTrip());
				break;
			case "I DON'T LIKE FREE MONEY":
				takeActionOnPromotion("REJECT");
				break;
			case "PICK UP CLEAR TEXT":
				action.click(homePage.Button_ClearPickup());
				break;
			case "DROP CLEAR TEXT":
				action.click(homePage.Button_ClearDrop());
				break;
			case "CANCEL":
				if (screen.equalsIgnoreCase("payment"))
					action.click(paymentPage.Button_Cancel());
				else if (screen.equalsIgnoreCase("update"))
					action.click(driverUpdateStatusPage.Button_Cancel());
				else
					action.click(estimatePage.Button_Cancel());
				break;
			case "SEND":
				if (screen.equalsIgnoreCase("forget password"))
					action.click(forgotPasswordPage.Button_Send());
				else
					action.click(supportPage.Button_Send());
				break;
			case "SMS":
				if (screen.equalsIgnoreCase("customer-Update"))
					action.click(customerUpdateStatusPage.Button_Sms());
				else
					action.click(driverUpdateStatusPage.Button_Sms());
				break;
			case "CALL":
				if (screen.equalsIgnoreCase("customer-Update"))
					action.click(customerUpdateStatusPage.Button_Call());
				else
					action.click(driverUpdateStatusPage.Button_Call());
				break;
			case "ADD":
				action.click(promosPage.Button_Add());
				break;
			case "GET MORE MONEY":
				action.click(promosPage.Button_GetMoreMoney());
				break;
			case "ADD NEW":
				action.click(paymentPage.Button_AddNew());
				break;
			case "ADD PAYMENT METHOD":
				action.click(paymentPage.Button_AddPayment());
				break;
			case "SAVE":
				action.click(paymentPage.Button_Save());
				break;
			case "FORGOT PASSWORD":
				action.click(loginPage.Button_ForgotPassword());
				break;
			case "CONTINUE":
				action.click(forgotPasswordPage.Button_Continue());
				break;
			case "BACK":
				action.click(forgotPasswordPage.Button_Back());
				break;
			case "REQUEST BUNGII":
				action.click(estimatePage.Button_RequestBungii());
				break;
			case "OK":
				if (screen.equalsIgnoreCase("BUNGII ACCEPTED"))
					//bungiiAcceptedPage.clickOkButton();
					bungiiAcceptedPage.Button_Ok().click();
				else
					action.click(driverNotAvailablePage.Button_OK());
				break;
			case "ACCEPT":
				action.click(bungiiRequestPage.Button_Accept());
				break;
			case "REJECT":
				action.click(bungiiRequestPage.Button_Reject());
				break;
			case "SHARE ON FACEBOOK":
			case "SHARE ON TWITTER":
			case "SHARE BY EMAIL":
			case "SHARE BY TEXT MESSAGE":
				shareInviteCode(button);
				break;
			default:
				error( "UnImplemented Step or incorrect button name",
						"UnImplemented Step");
				break;
			}
			log( "Click " + button + " button ",
					"Clicked " + button + " button", true);
		} catch (Throwable e) {
			logger.error("Error performing step" + e.getMessage());
			e.printStackTrace();
			error( "Step  Should be sucessfull",
					"Error performing step,Error", true);
		}
	}
	/**
	 * Accept or reject on promotion page
	 * @param actions Take action on promotion page
	 * @return if action is taken or nots
	 */
	public boolean takeActionOnPromotion(String actions){
		boolean isClicked=false;
		switch (actions.toUpperCase()) {
			case "ACCEPT":
				promotionPage.Button_AcceptPromo().click();
				isClicked=true;
				break;
			case "REJECT":
				promotionPage.Button_IdontLikePromo().click();
				isClicked=true;
				break;
			default:
				break;
		}

		return isClicked;
	}
	/**
	 * Click on Invite code button for social media platform
	 * @param target Identifer for share option
	 */
	public void shareInviteCode(String target){
		if(target.contains("FACEBOOK")){
			action.click(invitePage.Button_Facebook());
		}else if (target.contains("TWITTER")) {
			action.click(invitePage.Button_Twitter());
		}else if (target.contains("EMAIL")) {
			action.click(invitePage.Button_Email());
		}else if (target.contains("TEXT")) {
			action.click(invitePage.Button_TextMessage());
		}
	}

	@Then("^I should be navigated to \"([^\"]*)\" screen$")
	public void i_should_be_naviagated_to_something_screen(String screen) {
		try {
			boolean isCorrectPage=false;

			GeneralUtility utility = new GeneralUtility();
			isCorrectPage=utility.verifyPageHeader(screen);
			testStepVerify.isTrue(isCorrectPage, "I should be naviagated to " + screen + " screen",
					"I should be navigated to " + screen, "I was not navigated to " + screen + "screen ");

		} catch (Throwable e) {
			logger.error("Error performing step" + e.getMessage());
			e.printStackTrace();
			error( "Step  Should be sucessfull",
					"Error performing step,Error", true);
		}
	}


	@And("^I Get SMS CODE for new \"([^\"]*)\"$")
	public void i_get_sms_code_for_new_something(String strArg1) {
		try {

			String phone = (String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER");
			// TODO remove this
			Thread.sleep(20000);
			String smsCode = DbUtility.getVerificationCode(phone);

			cucumberContextManager.setScenarioContext("SMS_CODE", smsCode);
			testStepAssert.isFalse(smsCode.equals(""),
					"I should able to fetch value for sms code", "SMS CODE for " + strArg1 + " is " + smsCode,
					"Not able to fetch sms code for " + strArg1);
		} catch (Throwable e) {
			logger.error("Error performing step" + e.getMessage());
			error( "Step  Should be sucessfull", "Error performing step,Error",
					true);
		}
	}


	@Given("^I am on the \"([^\"]*)\" page$")
	public void i_am_on_the_something_page(String screen) {
		try {
			String NavigationBarName = action.getNameAttribute(homePage.Text_NavigationBar());
			switch (screen.toUpperCase()) {
			case "LOG IN":
				goToLogInPage(NavigationBarName);
				break;
			case "SIGN UP":
				goToSignUpPage(NavigationBarName);
				break;
			case "HOME":
				break;
			default:
				throw new Exception(" UNIMPLEMENTED STEP");
			}
		} catch (Throwable e) {
			logger.error("Error performing step" + e.getMessage());
			error( "Step  Should be sucessfull", "Error performing step,Error", true);

		}
	}

	public void goToLogInPage(String navigationBarName) throws Throwable {
		HomeSteps homeSteps = new HomeSteps(homePage);

		if (!navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.login"))) {

			if (navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.signup")))
				iClickButtonOnScreen("LOG IN", "sign up");
			else {
				homeSteps.i_select_something_from_customer_app_menu("LOGOUT");
			}
		}
	}

	public void goToSignUpPage(String navigationBarName) throws Throwable {
		HomeSteps homeSteps = new HomeSteps(homePage);

		if (!navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.signup"))) {

			if (navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.login")))
				iClickButtonOnScreen("SIGN UP", "sign up");
			else {
				homeSteps.i_select_something_from_customer_app_menu("LOGOUT");
				iClickButtonOnScreen("SIGN UP", "sign up");

			}
		}
	}

	@When("^I Switch to \"([^\"]*)\" application on \"([^\"]*)\" devices$")
	public void i_switch_to_something_application_on_something_devices(String appName, String device) throws Throwable {
		try {

			SetupManager.getObject().useDriverInstance("ORIGINAL");
			switch (appName.toUpperCase()) {
			case "DRIVER":
				action.switchApplication(PropertyUtility.getProp("bundleId_Driver"));
				break;
			case "CUSTOMER":
				action.switchApplication(PropertyUtility.getProp("bundleId_Customer"));
				break;
			default:
				error("UnImplemented Step or in correct app", "UnImplemented Step");
				break;
			}
			pass( "Switch to " + appName + " application",
					"Switch to " + appName + " application", true);
			cucumberContextManager.setFeatureContextContext("CURRENT_APPLICAION", appName.toUpperCase());

		} catch (Throwable e) {
			logger.error("Error performing step" + e.getMessage());
			error("Step  Should be sucessfull",
					"Error performing step,Error", true);

		}
	}

	// TODO change catch to error
	@Then("^Alert message with (.+) text should be displayed$")
	public void alert_message_with_text_should_be_displayed(String message) {
		try {
			action.waitForAlert();
			String actualMessage = action.getAlertMessage();
			String expectedMessage ;
			switch (message.toUpperCase()) {
			case "INVALID_PASSWORD":
				expectedMessage = PropertyUtility.getMessage("customer.error.invalidpassword");
				break;
			case "EMPTY_FIELD":
				expectedMessage = PropertyUtility.getMessage("customer.error.emptyfield");
				break;
			case "DELETE WARNING":
				expectedMessage = PropertyUtility.getMessage("customer.payment.delete");
				break;
			case "NO PROMO CODE":
				expectedMessage = PropertyUtility.getMessage("customer.signup.nopromo");
				break;
			case "SCHEDULE BUNGII OPTION":
				expectedMessage = PropertyUtility.getMessage("customer.driver.unavailable.schedule");
				break;
			case "ACCEPT BUNGII QUESTION":
				expectedMessage = PropertyUtility.getMessage("driver.bungii.request.ondemand.question");
				break;
			case "DRIVER CANCELLED":
				expectedMessage = PropertyUtility.getMessage("customer.alert.driver.cancel");
				break;
			case "DRIVER CANCEL BUNGII":
				expectedMessage = PropertyUtility.getMessage("driver.cancel.bungii");
				break;
			default:
				throw new Exception(" UNIMPLEMENTED STEP");
			}
			testStepVerify.isEquals(actualMessage, expectedMessage,
					"Alert with text" + expectedMessage + "should be displayed",
					"Alert with text ," + expectedMessage + " should be displayed",
					"Alert Message is not displayed, actual Message" + actualMessage + " Expected is "
							+ expectedMessage);
		} catch (Throwable e) {
			logger.error("Error performing step" + e.getMessage());
			fail( "Step  Should be sucessfull",
					"Error performing step,Error", true);
		}
	}

	@And("^I click \"([^\"]*)\" on alert message$")
	public void i_click_something_on_alert_message(String buttonLabel) throws Throwable {
		boolean clicked = action.clickAlertButton(buttonLabel);

		testStepAssert.isTrue(clicked,
				"Clicked on " + buttonLabel + " button", "Alert Message with " + buttonLabel + "button not displayed");
	}

	@And("^I accept Alert message$")
	public void iAcceptAlertMessage() {
		try {
			SetupManager.getDriver().switchTo().alert().accept();
			log( "Alert Message should be accepted", "Alert Message is accepted");
		} catch (Throwable e) {
			logger.error("Error performing step" + e.getMessage());
			error("Step  Should be sucessfull", "Error performing step,Error", true);
		}
	}

	@And("^I reject Alert message$")
	public void iRejectAlertMessage() {
		try {
			SetupManager.getDriver().switchTo().alert().dismiss();
			log("Alert Message should be reject", "Alert Message is reject");
		} catch (Throwable e) {
			logger.error("Error performing step" + e.getMessage());
			error( "Step  Should be sucessfull", "Error performing step,Error", true);
		}
	}

	@Then("^Trip Information should be correctly displayed on \"([^\"]*)\" screen$")
	public void trip_information_should_be_correctly_displayed_on_something_screen(String screen) throws Throwable {
		try {
			switch (screen.toUpperCase()) {
			case "ESTIMATE":
				verifyTripInformationOnEstimateScreen(screen);
				break;
			case "TRIP DETAILS":
				verifyTripInformationOnTripDetailsScreen(screen);
				break;
			case "BUNGII DETAILS":
				verifyTripInformationOnBungiiDetails(screen);
				break;
			case "CUSTOMER HOME":
				HomeSteps homeSteps = new HomeSteps(homePage);
				homeSteps.verifyTripInformationOnHome(screen);
				break;

			default:
				error(
						"UnImplemented Step or in correct app", "UnImplemented Step");
				break;
			}
		} catch (Throwable e) {
			logger.error("Error performing step" + e.getMessage());
			e.printStackTrace();
			error("Step  Should be sucessfull", "Error performing step,Error", true);
		}

	}

	@When("^I logged in Customer application using  \"([^\"]*)\" user$")
	public void i_logged_in_customer_application_using_something_user(String key) {
		try {
			String NavigationBarName = action.getNameAttribute(homePage.Text_NavigationBar());
			String userName = "", password = "";
			switch (key.toLowerCase()) {
			case "existing":
				userName = PropertyUtility.getDataProperties("customer.user");
				password = PropertyUtility.getDataProperties("customer.password");
				break;
/*			case "new":
				userName = PropertyUtility.getDataProperties("new.customer.user");
				password = PropertyUtility.getDataProperties("new.customer.password");
				break;*/
			default:
				error("UnImplemented Step or in correct app", "UnImplemented Step");
				break;
			}
			goToLogInPage(NavigationBarName);

			LogInSteps logInSteps = new LogInSteps(loginPage);
			logInSteps.i_enter_valid_and_as_per_below_table(userName, password);
			iClickButtonOnScreen("Log In", "Log In");

		} catch (Throwable e) {
			logger.error("Error performing step" + e.getMessage());
			error("Step  Should be sucessfull",
					"Error performing step,Error", true);
		}
	}


	private void verifyTripInformationOnTripDetailsScreen(String screen) {
		String[] actualDetails =new String [4];
		actualDetails[0] = action.getValueAttribute(tripDetailsPage.Text_Distance());
		actualDetails[1] = action.getValueAttribute(tripDetailsPage.Text_EstimatedEarnings());
		actualDetails[2] = action.getValueAttribute(tripDetailsPage.Text_ScheduledDate());
		actualDetails[3] =action. getValueAttribute(tripDetailsPage.Text_ScheduledTime());
		//cucumberContextManager.setScenarioContext("BUNGII_CUSTOMER_ESTIMATE", actualDetails[1]);
		cucumberContextManager.setScenarioContext("BUNGII_DRIVER_ESTIMATE", actualDetails[1]);

		String expectedTripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
		String expectedTripDistance = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
		//Leading zero is not present in time, Check if zero is present and delete it
		String timeValue =expectedTripTime.split(",")[1];timeValue =timeValue.substring(0,1).equals("0")?timeValue.substring(1):timeValue;

		boolean isTimeCorrect = expectedTripTime.split(",")[0].trim().equals(actualDetails[2].trim())
				&& timeValue.trim().equals(actualDetails[3].trim());
		boolean isDistanceCorrect = expectedTripDistance.equals(actualDetails[0]);

		testStepVerify.isTrue(isTimeCorrect && isDistanceCorrect,
				"Trip Information should be correctly displayed on" + screen + " screen",
				"Trip Information should be correctly displayed ",
				expectedTripDistance + expectedTripTime + "" + actualDetails[0] + actualDetails[1] + actualDetails[2]);

	}

	// TODO ; UPDATE for driver status / Duo
	private void verifyTripInformationOnBungiiDetails(String screen) {
		String[] tripInfo = new String[5];
		tripInfo[0]=action.getValueAttribute(customerbungiiDetails.Text_PickUpLocation());
		tripInfo[1]=action.getValueAttribute(customerbungiiDetails.Text_DropLocation());
		tripInfo[2]=action.getValueAttribute(customerbungiiDetails.Text_Driver1Status());
		tripInfo[3]=action.getValueAttribute(customerbungiiDetails.Text_Time());
		tripInfo[4]=action.getValueAttribute(customerbungiiDetails.Text_TotalEstimate());
		String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME")),
				pickUpLocation = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION")),
				dropLocation = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION")),
				estimate = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE"));
		String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));

		boolean isPickUpAddressCorrect = tripInfo[0].equals(pickUpLocation),
				isDropAddressCorrect = tripInfo[1].equals(dropLocation), isTimeCorrect = tripInfo[3].equals(tripTime),
				isEstimateCorrect = tripInfo[4].equals(estimate);

		if (!tripNoOfDriver.toUpperCase().equals("SOLO")) {
			// customerbungiiDetails.getDriver2Status()
		}

		testStepVerify.isTrue(isPickUpAddressCorrect,
				"Pick up address should be " + pickUpLocation, "Pick up address is " + pickUpLocation,
				"Expected pickup address is " + pickUpLocation + ", but actual is" + tripInfo[0]);
		testStepVerify.isTrue(isDropAddressCorrect,
				"Drop address should be " + dropLocation, "Drop address is " + dropLocation,
				"Expected Drop address is " + dropLocation + ", but actual is" + tripInfo[1]);
		testStepVerify.isTrue(isTimeCorrect,
				"Trip time should be " + tripTime, "Trip time is " + tripTime,
				"Expected Trip time is " + tripTime + ", but actual is" + tripInfo[3]);
		testStepVerify.isTrue(isEstimateCorrect,
				"Trip Estimate should be " + estimate, "Trip time is " + estimate,
				"Expected Trip Estimate is " + estimate + ", but actual is" + tripInfo[4]);

	}
	//	@When("^I open new \"([^\"]*)\" browser for \"([^\"]*)\" instance$")
	@When("^I open new \"([^\"]*)\" browser for \"([^\"]*)\"$")
	public void i_open_new_something_browser_for_something_instance(String browser, String instanceName) {
		try {

			SetupManager.getObject().createNewWebdriverInstance(instanceName, browser);
			SetupManager.getObject().useDriverInstance(instanceName);
			log(
					"I open new " + browser + " browser for " + instanceName + " instance$",
					"I open new " + browser + " browser for " + instanceName + " instance$", true);

		} catch (Exception e) {
			logger.error("Error performing step" + e.getMessage());
			e.printStackTrace();
			error( "Step  Should be sucessfull",
					"Error performing step,Error", true);
		}
	}
	@When("^I connect to \"([^\"]*)\" using \"([^\"]*)\" instance$")
	public void i_connect_to_something_using_something_instance(String deviceId, String instanceName) {
		try {
			SetupManager.getObject().createNewAppiumInstance(instanceName, deviceId);
			SetupManager.getObject().useDriverInstance(instanceName);
		}catch (Exception e){
			logger.error("Error performing step" + e.getMessage());
			error( "Step  Should be sucessfull",
					"Error performing step,Error", true);
		}

	}
	@When("^I switch to \"([^\"]*)\" instance$")
	public void i_switch_to_something_instance(String instanceName) throws Throwable {
		try {
			SetupManager.getObject().useDriverInstance(instanceName);
			log("I switch to  " + instanceName + "instance",
					"I switch to  " + instanceName + "instance", true);

		} catch (Exception e) {
			logger.error("Error performing step" + e.getMessage());
			error( "Step  Should be sucessfull",
					"Error performing step,Error", true);
		}
	}

	@And("^I Select Trip from scheduled trip$")
	public void i_select_trip_from_scheduled_trip() {
		try {
			String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
			String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
			String currentApplication = (String) cucumberContextManager.getFeatureContextContext("CURRENT_APPLICAION");

			if (currentApplication.equalsIgnoreCase("CUSTOMER")) {
				//customerScheduledBungiiPage.selectBungiiFromList(tripNoOfDriver, tripTime);
				String imageTag = "";
				if (tripNoOfDriver.toUpperCase().equals("SOLO")) {
					imageTag = Image_Solo;
				}
				action.swipeDown();

				//	By Image_SelectBungii = MobileBy.xpath("//XCUIElementTypeStaticText[@name='" + bungiiTime+ "']/following-sibling::XCUIElementTypeImage[@name='" + imageTag + "']/parent::XCUIElementTypeCell");

				WebElement Image_SelectBungii=scheduledBungiiPage.findElement("//XCUIElementTypeStaticText[@name='" + tripTime+ "']/following-sibling::XCUIElementTypeImage[@name='" + imageTag + "']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath);
				action.click(Image_SelectBungii);
			} else {
				//driverScheduledBungiiPage.selectBungiiFromList(tripNoOfDriver, tripTime);
				String imageTag="";
				if(tripNoOfDriver.toUpperCase().equals("SOLO"))
				{
					imageTag=Image_Solo;
				}
				action.swipeDown();
				WebElement Image_SelectBungii= driverScheduledBungiiPage.findElement("//XCUIElementTypeStaticText[@name='"+tripTime+"']/following-sibling::XCUIElementTypeImage[@name='"+imageTag+"']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath);
				//By Image_SelectBungii = MobileBy.xpath();
				action.click(Image_SelectBungii);

			}

		} catch (Exception e) {
			logger.error("Error performing  step" + e.getMessage());
			e.printStackTrace();
			//logger.error("Error performing step" + e.printStackTrace());

			error( "Step  Should be sucessfull", "Error performing step,Error",
					true);
		}
	}

	@Given("^I am on Customer logged in Home page$")
	public void iAmOnCustomerLoggedInHomePage() {
		try {
			LogInSteps logInSteps = new LogInSteps(new LoginPage());
			HomeSteps homeSteps = new HomeSteps(homePage);
			String NavigationBarName =action.getNameAttribute(homePage.Text_NavigationBar());

			if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.login"))
					|| NavigationBarName.equals("SIGN UP")) {
				if (NavigationBarName.equals("SIGN UP"))
					iClickButtonOnScreen("LOG IN", "sign up");

				logInSteps.i_enter_valid_and_as_per_below_table(PropertyUtility.getDataProperties("customer.user"),
						PropertyUtility.getDataProperties("customer.password"));
				iClickButtonOnScreen("Log In", "Log In");
				//homeSteps.user_should_be_successfully_logged_in_to_the_system();
			} else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.home"))) {
				// do nothing
			} else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.searching"))) {
				iClickButtonOnScreen("CANCEL", "SEARCHING");
				iRejectAlertMessage();
			} else {
				homeSteps.i_select_something_from_customer_app_menu("HOME");
			}

		} catch (Exception e) {
			logger.error("Error performing step" + e.getMessage());
			error( "Step  Should be sucessfull", "Error performing step,Error",
					true);
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> getRefferalCode(String codeType) {
		List<String> code = new ArrayList<String>();
		switch (codeType.toLowerCase()) {
		case "referral":
			code = (List<String>) cucumberContextManager.getFeatureContextContext("REFERRAL");
			break;
		case "valid":
			code = (List<String>) cucumberContextManager.getFeatureContextContext("VALID");
			break;
		case "promo":
			code = (List<String>) cucumberContextManager.getFeatureContextContext("PROMO");
			break;

		case "expired":
			code = (List<String>) cucumberContextManager.getFeatureContextContext("EXPIRED");
			break;
		case "one off":
			code = (List<String>) cucumberContextManager.getFeatureContextContext("ONE_OFF");
			break;
		case "used one off":
			code = (List<String>) cucumberContextManager.getFeatureContextContext("USED_ONE_OFF");
			break;
		case "unused one off":
			code = (List<String>) cucumberContextManager.getFeatureContextContext("UNUSED_ONE_OFF");
			break;
		default:
			code.add(codeType);
			break;
		}
		return code;
	}

	public String generateMobileNumber() {

		String phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");
		while (!DbUtility.isPhoneNumberUnique(phoneNumber)) {
			phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");

		}
		return phoneNumber;
	}

	@When("^I Enter \"([^\"]*)\" value in \"([^\"]*)\" field in \"([^\"]*)\" Page$")
	public void iEnterValueInFieldInPage(String value, String field, String screen) {

		try {
			String inputValue = RandomGeneratorUtility.getData(value, 10);

			if (!value.equalsIgnoreCase("{RANDOM_PHONE_NUM}")) {
				inputValue = value.equalsIgnoreCase("{EMPTY}") ? "     " : inputValue;
				inputValue = value.equalsIgnoreCase("{BLANK}") ? "" : inputValue;
			} else {
				inputValue = generateMobileNumber();
			}

			switch (field.toUpperCase()) {
			case "SUPPORT TEXTBOX":
				action.waitClearEnterText(supportPage.TextBox_Support(),inputValue);
				break;
			case "FIRST NAME":
				action.waitClearEnterText(signupPage.Textfield_FirstName(),inputValue);
				break;
			case "LAST NAME":
				action.waitClearEnterText(signupPage.Textfield_LastName(),inputValue);action.hideKeyboard();
				break;
			case "EMAIL":
				action.waitClearEnterText(signupPage.Textfield_Email(),inputValue);action.hideKeyboard();
				break;
			case "PHONE NUMBER":
				if (screen.equalsIgnoreCase("FORGOT PASSWORD")) {
					inputValue=value.equalsIgnoreCase("{VALID USER}")?PropertyUtility.getDataProperties("new.customer.user"):inputValue;
					action.waitClearEnterText(forgotPasswordPage.Text_InputNumber(),inputValue);
					cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", inputValue);

				} else {
					action.waitClearEnterText(signupPage.Textfield_Phonenumber(),inputValue);
					cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", inputValue);
				}
				break;
			case "PASSWORD":
				action.waitClearEnterText(signupPage.Textfield_Password(),inputValue);
				break;
			case "REFERRAL CODE":
				List<String> inputValueList = getRefferalCode(inputValue);
				action.waitClearEnterText(signupPage.Textfield_PromoCode(),inputValueList.get(0));
				cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", inputValue);
				break;
			case "PROMO CODE":
				List<String> ValueList = getRefferalCode(inputValue);
				action.waitClearEnterText(promosPage.TextBox_EnterCode(),ValueList.get(0));
				cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", inputValue);
				break;
			case "SMS CODE":
				inputValue = inputValue.equalsIgnoreCase("valid") ? (String) cucumberContextManager.getScenarioContext("SMS_CODE")
						: "111";
				action.waitClearEnterText(forgotPasswordPage.Text_SmsCode(),inputValue);
				action.hideKeyboard();

				break;
			case "NEW PASSWORD":
				action.waitClearEnterText(forgotPasswordPage.Text_Password(),inputValue);

				break;

			default:
				error(
						"UnImplemented Step or in correct app", "UnImplemented Step");

				break;
			}
			log(
					"I should able to Enter " + value + " value in " + field + " field in " + screen + " Page",
					"I Entered " + inputValue + " in " + field + " field", true);

		} catch (Exception e) {
			logger.error("Error performing step" + e.getMessage());
			e.getStackTrace();
			error(
					"Step  Should be sucessfull", "Error performing step,Error", true);
		}
	}

	@Then("^user is alerted for \"([^\"]*)\"$")
	public void user_is_alerted_for_something(String key) {
		try {
			action.waitForAlert();
			String expectedText = "";
			switch (key.toUpperCase()) {
			case "ALREADY SCHEDULED BUNGII":
				expectedText = PropertyUtility.getMessage("customer.alert.alreadyscheduled");
				break;
			case "SUPPORT QUESTION SUBMITTED":
				expectedText = PropertyUtility.getMessage("customer.support.submitted");
				break;
			case "EMPTY SUPPORT QUESTION":
				expectedText = PropertyUtility.getMessage("customer.support.emptyfield");
				break;
			case "NO TWITTER INSTALLED":
				expectedText = PropertyUtility.getMessage("customer.invite.notwitter");
				break;
			case "EXPIRED PROMO":
				expectedText = PropertyUtility.getMessage("customer.promos.expired");
				break;
			case "INVALID PROMO":
				expectedText = PropertyUtility.getMessage("customer.promos.invalid");
				break;
			case "EMPTY SIGNUP FIELD":
				expectedText = PropertyUtility.getMessage("customer.signup.emptyfield");
				break;
			case "EXISTING USER":
				expectedText = PropertyUtility.getMessage("customer.signup.existinguser");
				break;
			case "INVALID EMAIL WHILE SIGNUP":
				expectedText = PropertyUtility.getMessage("customer.signup.invalidemail");
				break;
			case "INVALID PHONE WHILE SIGNUP":
				expectedText = PropertyUtility.getMessage("customer.signup.invalidphonenumber");
				break;
			case "INVALID PASSWORD WHILE SIGNUP":
				expectedText = PropertyUtility.getMessage("customer.signup.invalidpassword");
				break;
			case "INVALID PROMO WHILE SIGNUP":
				expectedText = PropertyUtility.getMessage("customer.signup.invalidpromo");
				break;
			case "REERRAL FOR NEW USER":
				expectedText = PropertyUtility.getMessage("customer.promos.referralerror");
				break;
			case "FIRST TIME ONLY PROMO":
				expectedText = PropertyUtility.getMessage("customer.promos.firsttimeerror");
				break;
			case "ALREADY EXISTING CODE":
				expectedText = PropertyUtility.getMessage("customer.promos.already.existing.code");
				break;
			case "FAILED TO SEND TOKEN":
				expectedText = PropertyUtility.getMessage("customer.forgotpassword.failed.reset");
				break;
			case "PASSWORD CHANGE SUCCESS":
				expectedText = PropertyUtility.getMessage("customer.forgotpassword.sucess");
				break;
			case "INVALID SMS CODE":
				expectedText = PropertyUtility.getMessage("customer.forgotpassword.invalid.code");
				break;
			case "INVALID PASSWORD WHILE RESET":
				expectedText = PropertyUtility.getMessage("customer.forgotpassword.invalid.password");
				break;
			case "CANCEL BUNGII":
				expectedText = PropertyUtility.getMessage("customer.alert.cancel.bungii");
				break;

			default:
				error( "UnImplemented Step or in correct app", "UnImplemented Step");

				break;
			}
			String alertText = SetupManager.getDriver().switchTo().alert().getText();
			testStepVerify.isEquals(alertText, expectedText, "user is alerted for " + key);
			SetupManager.getDriver().switchTo().alert().accept();
		} catch (Exception e) {
			logger.error("Error performing step" + e.getMessage());
			e.getStackTrace();
			error( "Step  Should be sucessfull", "Error performing step,Error", true);
		}
	}


	private void verifyTripInformationOnEstimateScreen(String screen) {
		String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
		String pickUpLocation = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION"));
		String dropOffLocation = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION"));

		String[] tripLocation = new String[2];
		tripLocation[0] = action.getValueAttribute(estimatePage.Text_PickUpLocation());
		tripLocation[1] = action.getValueAttribute(estimatePage.Text_DropOffLocation());

		if (tripLocation[0].equals(pickUpLocation) && tripLocation[1].equals(dropOffLocation)) {
			pass(
					"Trip Information should be correctly displayed on " + screen + " screen",
					"Pick up location :" + pickUpLocation + " , Drop location: " + dropOffLocation
							+ "is correctly displayed on estimate screen ",
					true);

		} else {
			fail(
					"Trip Information should be correctly displayed on " + screen + " screen",
					"Pick up location on request screen is:" + pickUpLocation + " and on Estimate screen is"
							+ tripLocation[0] + " .Drop off location on request screen is:" + dropOffLocation
							+ " and on Estimate screen is" + tripLocation[1],
					true);
		}
	}
}
