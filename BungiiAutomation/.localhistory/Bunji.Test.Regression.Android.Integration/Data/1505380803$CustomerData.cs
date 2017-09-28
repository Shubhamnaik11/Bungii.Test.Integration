using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bunji.Test.Regression.Android.Integration.Data
{
    class CustomerData
    {

        public string CustomerPhonenumber = "9850441257";
        public string CustomerPassword = "Cci12345";
        public string currentlocation = "Unnamed Road, Verna Industrial Estate, Verna, Goa, 403722";
        //public string phonenumber = "9999990125";

        public string password = "Cci12345";

        //Registration 

        public string CustomerFirstName = "TestCustomer_AppleTest";
        public string CustomerLastName = "Auto";
        public string Email = "neha.naik@creativecapsule.com";
        public string ReferralCode = "";


        //Generating tel no. public string Email = "neha.naik@creativecapsule.com";
        static Random rand = new Random();

        public string GetRandomTelNo()
        {
            string telNo = null;
            int number;
            for (int i = 0; i < 3; i++)
            {
                number = rand.Next(0, 8); // digit between 0 (incl) and 8 (excl)
                telNo = telNo + number.ToString();
            }
            //  telNo = telNo + "-";
            number = rand.Next(0, 743); // number between 0 (incl) and 743 (excl)
            telNo = telNo + String.Format("{0:D3}", number);
            // telNo = telNo + "-";
            number = rand.Next(0, 10000); // number between 0 (incl) and 10000 (excl)
            telNo = telNo + String.Format("{0:D4}", number);
            return telNo.ToString();
        }

        

    }
}
