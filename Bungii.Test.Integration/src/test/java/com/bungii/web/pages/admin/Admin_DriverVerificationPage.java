package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_DriverVerificationPage extends PageBase {

    public WebElement Title_DriverVerificationPage (boolean...ignoreException) { return findElement("//*[@id='verification-content']/h4", LocatorType.XPath,ignoreException); }

    public WebElement Verify_Approve_DriverPic () { return findElement("//*[@id='btnok_16']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverFirstName () { return findElement("//*[@id='btnok_0']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverLastName () { return findElement("//*[@id='btnok_1']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverStreetAddress () { return findElement("//*[@id='btnok_4']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverCity () { return findElement("//*[@id='btnok_5']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverState () { return findElement("//*[@id='btnok_6']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverZip () { return findElement("//*[@id='btnok_7']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverSSN () { return findElement("//*[@id='btnok_8']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverBirthday () { return findElement("//*[@id='btnok_9']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverPickupImages () { return findElement("//*[@id='btnok_17']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverPickupMake () { return findElement("//*[@id='btnok_10']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverPickupModel () { return findElement("//*[@id='btnok_11']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverPickupYear () { return findElement("//*[@id='btnok_12']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverPickupLicense () { return findElement("//*[@id='btnok_13']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverLicenseImage () { return findElement("//*[@id='btnok_19']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverLicenseNumber () { return findElement("//*[@id='btnok_20']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverLicenseExpiration () { return findElement("//*[@id='btnok_21']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverInsuranceImage () { return findElement("//*[@id='btnok_22']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverInsurationExpiration () { return findElement("//*[@id='btnok_23']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverRoutingNumber () { return findElement("//*[@id='btnok_15']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverAccountNumber () { return findElement("//*[@id='btnok_14']", LocatorType.XPath); }

    public WebElement Button_DriverApproveApplication () { return findElement("//*[@id='btnapprove']", LocatorType.XPath, true); }

    public WebElement Button_DriverConfirmApproval () { return findElement("//*[@id='btnapproveagree']", LocatorType.XPath); }

    public WebElement Verify_Reject_DriverPicture () { return findElement("//*[@id='btnremove_16']", LocatorType.XPath); }

    public WebElement Verify_Reject_FirstName () { return findElement("//*[@id='btnremove_0']", LocatorType.XPath); }

    public WebElement Verify_Reject_LastName () { return findElement("//*[@id='btnremove_1']", LocatorType.XPath); }

    public WebElement Verify_Reject_StreetAddress () { return findElement("//*[@id='btnremove_4']", LocatorType.XPath); }

    public WebElement Verify_Reject_City () { return findElement("//*[@id='btnremove_5']", LocatorType.XPath); }

    public WebElement Verify_Reject_State () { return findElement("//*[@id='btnremove_6']", LocatorType.XPath); }

    public WebElement Verify_Reject_ZipCode () { return findElement("//*[@id='btnremove_7']", LocatorType.XPath); }

    public WebElement Verify_Reject_SSN () { return findElement("//*[@id='btnremove_8']", LocatorType.XPath); }

    public WebElement Verify_Reject_Birthday () { return findElement("//*[@id='btnremove_9']", LocatorType.XPath); }

    public WebElement Verify_Reject_DriverPickupImages () { return findElement("//*[@id='btnremove_17']", LocatorType.XPath); }

    public WebElement Verify_Reject_PickupMake () { return findElement("//*[@id='btnremove_10']", LocatorType.XPath); }

    public WebElement Verify_Reject_PickupModel () { return findElement("//*[@id='btnremove_11']", LocatorType.XPath); }

    public WebElement Verify_Reject_PickupYear () { return findElement("//*[@id='btnremove_12']", LocatorType.XPath); }

    public WebElement Verify_Reject_PickupLicenseNumber () { return findElement("//*[@id='btnremove_13']", LocatorType.XPath); }

    public WebElement Verify_Reject_LicenseImage () { return findElement("//*[@id='btnremove_19']", LocatorType.XPath); }

    public WebElement Verify_Reject_LicenseNumber () { return findElement("//*[@id='btnremove_20']", LocatorType.XPath); }

    public WebElement Verify_Reject_LicenseExpiration () { return findElement("//*[@id='btnremove_21']", LocatorType.XPath); }

    public WebElement Verify_Reject_InsuranceImage () { return findElement("//*[@id='btnremove_22']", LocatorType.XPath); }

    public WebElement Verify_Reject_InsuranceExpiration () { return findElement("//*[@id='btnremove_23']", LocatorType.XPath); }

    public WebElement Verify_Reject_RoutingNumber () { return findElement("//*[@id='btnremove_15']", LocatorType.XPath); }

    public WebElement Verify_Reject_AccountNumber () { return findElement("//*[@id='btnremove_14']", LocatorType.XPath); }

    public WebElement Status_Accepted () { return findElement("//*[@id='DriverDetails_DriverVerification_DriverVerificationDetails_16__Comment']", LocatorType.XPath); }

    public WebElement Textinput_ReasonforRejection_Birthday () { return findElement("//*[@id='DriverDetails_DriverVerification_DriverVerificationDetails_9__Comment']", LocatorType.XPath); }

    public WebElement Textinput_ReasonforRejection_DriverPicture () { return findElement("//*[@id='DriverDetails_DriverVerification_DriverVerificationDetails_16__Comment']", LocatorType.XPath); }

    public WebElement Textinput_ReasonforRejection_PickupImages () { return findElement("//*[@id='DriverDetails_DriverVerification_DriverVerificationDetails_17__Comment']", LocatorType.XPath); }

    public WebElement Button_DriverResentButton () { return findElement("//*[@id='btnresend']", LocatorType.XPath, true); }

    public WebElement Button_DriverConfirmResend () { return findElement("//*[@id='btnresendagree']", LocatorType.XPath); }

    public WebElement Link_RejectApplication () { return findElement("//*[@id='rejectApplication']/a", LocatorType.XPath); }

    public WebElement Popup_RejectApplication () { return findElement("//*[@id='rejectApp']//p[2]", LocatorType.XPath); }

    public WebElement Button_DriverConfirmReject_Yes () { return findElement("//*[@id='btnagree']", LocatorType.XPath); }

    public WebElement Button_DriverConfirmReject_No () { return findElement("//*[@id='rejectApp']//button[1]", LocatorType.XPath); }

    public WebElement Textinput_ReasonforRejectDriverApplication () { return findElement("//*[@id='DriverDetails_DriverVerification_CancelReason']", LocatorType.XPath); }

    public WebElement Button_Submit () { return findElement("//*[@id='submitcancelreason']", LocatorType.XPath); }

    public WebElement Validation_Message_PleaseAddRejectionReason () { return findElement("//*[@id='rejectreason-errormsg']", LocatorType.XPath); }

    public WebElement Button_Save () { return findElement("//*[@id='btnsave']", LocatorType.XPath); }

    public WebElement Button_Cancel () { return findElement("//*[@id='btncancel']", LocatorType.XPath); }

    public WebElement Popup_ConfirmCancelDriverVerificationApplication () { return findElement("//*[@id='cancelApp']//p[2]", LocatorType.XPath); }

    public WebElement Button_VerifySSN () { return findElement("//td[text()='Social Security Number']/following-sibling::td/div/button[starts-with(@id,'btnok')]", LocatorType.XPath); }

    public WebElement Textbox_SSNComment () { return findElement("//input[@id='DriverDetails_DriverVerification_DriverVerificationDetails_8__Comment']", LocatorType.XPath); }
}
