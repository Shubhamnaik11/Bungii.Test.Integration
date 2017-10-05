namespace Bungii.Test.Regression.Android.Integration.Data
{
    class Data_Validations_Customer
    {
        //------Customer Login page-------------------------------------------------------------
        public string Cust_Login_Snackbar_IncorrectPassword = "Oops! It looks like that password you entered does not match our records.",
                      Cust_Login_Err_IncorrectPhone = "Please enter the phone number.",
                      Cust_Login_Err_BlankPassword = "Please enter the password.";

        //------Customer Signup page-------------------------------------------------------------
        public string Cust_Signup_Err_FirstName = "",
                      Cust_Signup_Err_LastName = "",
                      Cust_Signup_Err_Email = "Invalid email id.",
                      Cust_Signup_Err_PhoneNum = "Invalid phone number.",
                      Cust_Signup_Err_Password = "Password should contain minimum 6 characters.",
                      Cust_Signup_ReferralSource = "Other",
                      Cust_Signup_Snackbar_ExistingPhone = "Phone Number already exists.";

        //------PromoCodes-----------------------------------------------------------------------
        public string ValidPercentPromoCode = "10% - BAT";

        //------Forgot Password------------------------------------------------------------------
        public string ForgotPasswordPageTitle = "",
                      Cust_ForgotPassword_Err_InvalidPassword = "Password should contain minimum 6 characters.";

        //------Support Page------------------------------------------------------------------
        public string SupportFeeback_Error = "Please enter comments before submitting.",
                      SupportFeedback_Snackbar = "Your support question has been submitted. We'll be contacting you shortly.";

        //------Save Money Page------------------------------------------------------------------
        public string SaveMoney_Snackbar_Invalid = "Oops! It looks like that promo code is invalid.",
                      SaveMoney_Snackbar_Expired = "Oops! It looks like that promo code has expired.",
                      SaveMoney_Snackbar_CodeAlreadyAdded = "Oops! It looks like you have already added this promo code.",
                      SaveMoney_Snackbar_ReferralFromSaveMoney = "Oops! Referral codes are only eligible for new accounts.",
                      SaveMoney_Snackbar_FirsttimeOldUser = "Oops! Each account is only eligible for one new user coupon.";

        //------Invite Page----------------------------------------------------------------------
        public string Invite_Title = "GIVE $10, GET $10",
                      Invite_Text = "Refer your friends and we’ll give you $10 when they use Bungii.",

                      FBApp_Title = "Facebook",
                      FBApp_PreviewText = "A preview will be added after you post this to…";

        //------Payment Page---------------------------------------------------------------------
        public string Payment_NoPaymentText = "Please click below, to add your first payment method",
                      Error_InvalidCard = "Card number is invalid";
    }
}
