package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;

public class Admin_DriverVerificationPage extends PageBase {

    public WebElement Title_DriverVerificationPage (boolean...ignoreException) { return findElement("//h4[contains(text(),'Driver verification')]", LocatorType.XPath,ignoreException); }

    public WebElement getElementDriverVerification(String field,boolean status,boolean...ignoreException){
        String var = "glyphicon glyphicon-remove";
        if(status){
            var = "glyphicon glyphicon-ok";
        }
        return findElement("//td[text()='"+field+"']/following-sibling::td[2]/div/button/span[@class='"+var+"']", LocatorType.XPath,ignoreException);
        //return findElement("//td[contains(text(),'"+field+"')]/following-sibling::td[2]/div/button/span[@class='"+var+"']", LocatorType.XPath,ignoreException);
    }

    public WebElement Verify_Approve_DriverPic () { return getElementDriverVerification("Driver Picture",true); }

    public WebElement Verify_Approve_DriverFirstName () { return getElementDriverVerification("First Name",true); }

    public WebElement Verify_Approve_DriverLastName () { return getElementDriverVerification("Last Name",true); }

    public WebElement Verify_Approve_DriverStreetAddress () { return getElementDriverVerification("Street address",true); }

    public WebElement Verify_Approve_DriverCity () { return getElementDriverVerification("City",true); }

    public WebElement Verify_Approve_DriverState () { return getElementDriverVerification("State",true); }

    public WebElement Verify_Approve_DriverZip () { return getElementDriverVerification("Zip code",true); }

    //public WebElement Verify_Approve_DriverSSN () { return findElement("//*[@id='btnok_8']", LocatorType.XPath); }

    public WebElement Verify_Approve_DriverBirthday () { return getElementDriverVerification("Birthday",true) ;}

    public WebElement Verify_Approve_DriverPickupImages () { return getElementDriverVerification("Pickup images",true);}

    public WebElement Verify_Approve_DriverPickupMake () { return getElementDriverVerification("Pickup make",true);}

    public WebElement Verify_Approve_DriverPickupModel () { return getElementDriverVerification("Pickup model",true);}

    public WebElement Verify_Approve_DriverPickupYear () { return getElementDriverVerification("Pickup year",true); }

    public WebElement Verify_Approve_DriverPickupLicense () { return getElementDriverVerification("Pickup license number",true); }

    public WebElement Verify_Approve_DriverLicenseImage () { return getElementDriverVerification("License image",true); }

    public WebElement Verify_Approve_DriverLicenseNumber () { return getElementDriverVerification("License number",true); }

    public WebElement Verify_Approve_DriverLicenseExpiration () { return getElementDriverVerification("License expiration",true); }

    public WebElement Verify_Approve_DriverInsuranceImage () { return getElementDriverVerification("Insurance image",true); }

    public WebElement Verify_Approve_DriverInsurationExpiration () { return getElementDriverVerification("Insurance Expiration",true); }

    public WebElement Verify_Approve_DriverRoutingNumber () { return getElementDriverVerification("Routing Number",true, true); }

    public WebElement Verify_Approve_DriverAccountNumber () { return getElementDriverVerification("Account Number",true, true); }

    public WebElement Button_DriverApproveApplication () { return findElement("//*[@id='btnapprove']", LocatorType.XPath, true); }

    public WebElement Button_DriverConfirmApproval () { return findElement("//*[@id='btnapproveagree']", LocatorType.XPath); }

    public WebElement Verify_Reject_DriverPicture () { return getElementDriverVerification("Driver Picture",false); }

    public WebElement Verify_Reject_FirstName () { return getElementDriverVerification("First Name",false); }

    public WebElement Verify_Reject_LastName () { return getElementDriverVerification("Last Name",false); }

    public WebElement Verify_Reject_StreetAddress () { return getElementDriverVerification("Street address",false); }

    public WebElement Verify_Reject_City () { return getElementDriverVerification("City",false); }

    public WebElement Verify_Reject_State () { return getElementDriverVerification("State",false); }

    public WebElement Verify_Reject_ZipCode () { return getElementDriverVerification("Zip code",false); }

    //public WebElement Verify_Reject_SSN () { return findElement("//*[@id='btnremove_8']", LocatorType.XPath); }

    public WebElement Verify_Reject_Birthday () { return getElementDriverVerification("Birthday",false); }

    public WebElement Verify_Reject_DriverPickupImages () { return getElementDriverVerification("Pickup images",false); }

    public WebElement Verify_Reject_PickupMake () { return getElementDriverVerification("Pickup make",false); }

    public WebElement Verify_Reject_PickupModel () { return getElementDriverVerification("Pickup model",false); }

    public WebElement Verify_Reject_PickupYear () { return getElementDriverVerification("Pickup year",false); }

    public WebElement Verify_Reject_PickupLicenseNumber () { return getElementDriverVerification("Pickup license number",false); }

    public WebElement Verify_Reject_LicenseImage () { return getElementDriverVerification("License image",false); }

    public WebElement Verify_Reject_LicenseNumber () { return getElementDriverVerification("License number",false); }

    public WebElement Verify_Reject_LicenseExpiration () { return getElementDriverVerification("License expiration",false); }

    public WebElement Verify_Reject_InsuranceImage () { return getElementDriverVerification("Insurance image",false); }

    public WebElement Verify_Reject_InsuranceExpiration () { return getElementDriverVerification("Insurance Expiration",false); }

    public WebElement Verify_Reject_RoutingNumber () { return getElementDriverVerification("Routing Number",false,true); }

    public WebElement Verify_Reject_AccountNumber () { return getElementDriverVerification("Account Number",false, true); }

    public WebElement Status_Accepted (boolean...ignoreException) { return findElement("//td[text()='Driver Picture']/following-sibling::td[2]/div/input[@name ='AcceptedRejected']", LocatorType.XPath, ignoreException); }

    public WebElement Textinput_ReasonforRejection_Birthday () { return findElement("//td[text()='Birthday']/following-sibling::td[2]/div/input[@name ='AcceptedRejected']", LocatorType.XPath); }

    public WebElement Textinput_ReasonforRejection_DriverPicture () { return findElement("//td[text()='Driver Picture']/following-sibling::td[2]/div/input[@name ='AcceptedRejected']", LocatorType.XPath); }

    public WebElement Textinput_ReasonforRejection_PickupImages () { return findElement("//td[text()='Pickup images']/following-sibling::td[2]/div/input[@name ='AcceptedRejected']", LocatorType.XPath); }

    public WebElement Button_DriverResentButton () { return findElement("//button[text()='Resend Application']", LocatorType.XPath, true); }

    public WebElement Button_DriverConfirmResend () { return findElement("//button[(text()='Yes')]", LocatorType.XPath); }

    public WebElement Link_RejectApplication () { return findElement("//a[text()='Reject Application']", LocatorType.XPath); }

    public WebElement Popup_RejectApplication () { return findElement("//*[@id='rejectApp']//p[2]", LocatorType.XPath); }

    public WebElement Button_DriverConfirmReject_Yes () { return findElement("//button[text()='Yes']", LocatorType.XPath); }

    public WebElement Button_DriverConfirmReject_No () { return findElement("//button[text()='No']", LocatorType.XPath); }

    public WebElement Textinput_ReasonforRejectDriverApplication () { return findElement("//input[@class='reject-reason form-control']", LocatorType.XPath); }

    public WebElement Button_Submit () { return findElement("//button[text()='Submit']", LocatorType.XPath); }

    public WebElement Validation_Message_PleaseAddRejectionReason () { return findElement("//*[@id='rejectreason-errormsg']", LocatorType.XPath); }

    public WebElement Button_Save () { return findElement("//button[text()='Save']", LocatorType.XPath); }

    public WebElement Button_SaveForDriver () { return findElement("//*[@id='btnSave']", LocatorType.XPath); }

    public WebElement Button_Cancel () { return findElement("//a[text()='cancel']", LocatorType.XPath); }

    public WebElement Popup_ConfirmCancelDriverVerificationApplication () { return findElement("//*[@id='cancelApp']//p[2]", LocatorType.XPath); }

    public WebElement Button_VerifyDOB () { return findElement("//td[text()='Birthday']/following-sibling::td/div/button[starts-with(@id,'btnok')]", LocatorType.XPath); }

   // public WebElement Button_VerifySSN () { return findElement("//td[text()='Social Security Number']/following-sibling::td/div/button[starts-with(@id,'btnok')]", LocatorType.XPath); }

    //public WebElement Textbox_SSNComment () { return findElement("//input[@id='DriverDetails_DriverVerification_DriverVerificationDetails_8__Comment']", LocatorType.XPath); }
}
