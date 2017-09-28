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
    }
}
