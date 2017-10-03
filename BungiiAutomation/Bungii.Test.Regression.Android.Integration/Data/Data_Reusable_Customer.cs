using System;

namespace Bungii.Test.Regression.Android.Integration.Data
{
    class Data_Reusable_Customer
    {
        //------Customer Login Details-------------------------------------------------------------------------
        public string CustomerPhonenumber = "9999997923",
                      CustPhn_NewlyRegistered = "9999991722",
                      CustPhn_WithBungiis = "9999996101",
                      CustPhn_HavingReferral = "9999992345",
                      CustomerPassword = "cci12345";

        //------Customer Sign up-------------------------------------------------------------------------------
        public string CustomerFirstName = "TestCustomer_AppleTest",
                      CustomerLastName = "Auto'Test",
                      Email = "seonad.fernandes@creativecapsule.com",
                      ReferralCode = "XX4ID",
                      FirstTimePromo = "FTP",
                      UsedOneOffCode = "U1OFF",
                      InvalidData = "a1"; 

        //------Invalid Customer Details-----------------------------------------------------------------------
        public string InvalidCustomerPhone = "2121212121",
                      CustomerPhoneLessThan10 = "12345",
                      CustPasswordLessThan6 = "abc",
                      InvalidEmail = "ss@dd",
                      InvalidCode = "invalid",
                      ValidPercentCode = "BAT",
                      IncorrectVerificationCode = "000000";

        //------Location--------------------------------------------------------------------------------------
        public string currentlocation_verna = "Unnamed Road, Verna Industrial Estate, Verna, Goa, 403722",
                      currentlocation_US = "4 International Square, Kansas City, Missouri, 64153",
                      dropofflocation_US = "4 International Circle, KCMO, MO, United States";

        //------Support Page--------------------------------------------------------------------------------------
        public string Support_Text = "I have nothing but great things to say. I can't tell you how many times I've been out front of IKEA fanagling, unpacking, and trying to tie things to my roof. Now, I can simply tap a button and get it picked up and delivered.",
                      BlankSpaces = "         ";

        //------Save Money Page--------------------------------------------------------------------------------------
        public string ValidPromo = "newp",
                      ExpiredPromo = "EXPC";

        //------Generating random Tel No starting with 999999------------------------------------------------------------------------------
        static Random random = new Random();

        public string RandomPhoneNum()
        {
            string CustomerPhoneNum = "999999" + random.Next(1000, 9999).ToString();
            return CustomerPhoneNum;
        }

        //------Generating random Tel No------------------------------------------------------------------------------
        public string GetRandomTelNo()
        {
            string telNo = null;
            int number;
            for (int i = 0; i < 3; i++)
            {
                number = random.Next(0, 8); // digit between 0 (incl) and 8 (excl)
                telNo = telNo + number.ToString();
            }
            //  telNo = telNo + "-";
            number = random.Next(0, 743); // number between 0 (incl) and 743 (excl)
            telNo = telNo + String.Format("{0:D3}", number);
            // telNo = telNo + "-";
            number = random.Next(0, 10000); // number between 0 (incl) and 10000 (excl)
            telNo = telNo + String.Format("{0:D4}", number);
            return telNo.ToString();
        }
    }
}
