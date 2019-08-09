using System;

namespace Bungii.Android.Regression.Test.Integration.Data
{
    public class Data_Validation_Driver
    {
        //----------Driver Login Page--------------------------------------------------------------------
        public string DriverLoginHeader = "Driver Login",
                      DriverForgotPasswordHeader = "Forgot Password",
                      DriverVerifyPhoneHeader = "Verify your phone";

        //----------Driver Registration Page-------------------------------------------------------------
        public string DriverRegistrationHeader = "Driver Registration",
                      DReg_FirstName_Invalid = "Oops! The first name is invalid",
                      DReg_LastName_Invalid = "Oops! The last name is invalid",
                      DReg_Email_Invalid = "Oops! The email address is invalid.",
                      DReg_Phone_Invalid = "Oops! The phone number is invalid.",
                      DReg_Phone_Exists = "Oops! It looks like the phone number you entered is already associated with an existing account. Please check the number and try again.",
                      DReg_Password_Short = "Password should be at least 8 characters long.",
                      DReg_Password_Invalid = "Password should consist of an uppercase, a lowercase and a digit.",
                      DReg_ConfirmPassword_Incorrect = "Oops! The two passwords you entered do not match.";

        //----------Phone Verification Page--------------------------------------------------------------
        public string RegSuccess = "Your Bungii account has been created.", //Registration success      
                      SMSVerifSuccess = "Verification Successful", //Verification Successful Message after SMS code is entered
                      VerifCode_Err_Blank = "Oops! Please enter verification code to proceed.",
                      VerifCode_Err_Invalid = "Oops! It looks like the verification code you entered is incorrect. Please try again.";

        //----------Driver Registration Steps------------------------------------------------------------
        public string DriverDetailsHeader = "Driver Details", ////Driver Details Header
                      RemoveFileLink = "Remove file", //Remove file link
                      PickupInfoHeader = "Pickup Information", //"Pickup Information" Header
                      DocHeader = "Documentation", //Documentation Header        
                      BankDetHeader = "Bank Details", //Bank Details Header        
                      TermsHeader = "Terms & Conditions", //Terms & Conditions Header    
                      TermsSubHeader = "Bungii™ Driver Agreement",
                      TermsText = "$ 0.30 per transaction plus 2.9% of transaction amount", //Terms & Conditions text        
                      VideoHeader = "Welcome Video", //Video Training Header   
                      FinishHeader = "Done", //Finish Header       
                      DriverDashboardHeader = "Dashboard"; // Driver Dashboard Header

        //----------Errors-------------------------------------------------------------------------------
        public string Err_Pages_BlankFields = "Oops! It looks like you missed something. Please fill out all fields before proceeding.",
                      Err_DriverLogin_IncorrectCredentials = "Incorrect phone number or password",
                      Err_DriverLogin_Phone = "Oops! The phone number is invalid.",
                      Err_DriverDetails_ZipCode = "Please enter a valid Zip code.",
                      Err_DriverDetails_Other = "Please enter availability to continue.",
                      Err_DriverDetails_Birthday = "Please enter a valid date of birth.",
                      Err_DriverDetails_SSN = "Please enter a valid Social Security Number.",
                      Err_Add1MoreTruckImage = "Add one more pickup image.",
                      Err_Add2MoreTruckImage = "Add two more pickup images.",
                      Err_LicenseNumber = "Oops! It looks like the driver license you entered is already associated with an existing Bungii driver account. Please try again.",
                      Err_InvalidDate = "Please enter a valid date.",
                      Err_InvalidLicenseExpiryDate = "Please enter a valid license expiry.",
                      Err_InvalidInsuranceExpiryDate = "Please enter a valid insurance expiry.",
                      Err_InvalidRoutingNumber = "Please enter a valid routing number.",
                      Err_InvalidBankAccount = "Please enter a valid account number.",
                      Err_ShortBankAccount = "Please enter at least 7 characters.";

        //----------Verify your phone----------------------------------------------------------------------
        public string Msg_PasswordResetSuccess = "Your password has been reset successfully.",
                      VerifyPhoneText1 = "Verification code has been sent to your phone number ending ",
                      VerifyPhoneText2 = ". Enter the code in the below field to complete your verification.";

        //----------Admin Dashboard----------------------------------------------------------------------
        public string AdminName = "Bungii Admin",//Admin Dashboard - Name        
                      AdminDriverVerHeader = "Driver verification", //Admin Driver Verification        
                      AdminDashTabTitle = "Dashboard | Bungii", //Admin Dashboard - Tab title        
                      DriverPicTabTitle = "GetProfilePictureThumbNailImage (PNG Image, 259 x 259 pixels)", //Driver Pic - New Tab title
                      RejectionReason = "Change this field"; //Rejection Reason
    }
}
