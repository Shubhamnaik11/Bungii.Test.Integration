using System;
using System.Configuration;
using System.Data.SqlClient;

namespace Bungii.Test.Integration.Framework
{
    public class GeneralUtilityFunctions
    {
        //------Generating random Tel No starting with 999999-------------------------------------------------
        static Random random = new Random();

        public string RandomPhoneNum()
        {
            string PhoneNum = "999999" + random.Next(1000, 9999).ToString();
            return PhoneNum;
        }

        //------Generating random Tel No----------------------------------------------------------------------
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

        private static string connection = ConfigurationManager.AppSettings["QA.Database.ConnectionUri"];
        public bool IsPhoneUnique(string PhoneNumber)
        {
            string Id = string.Empty;
            try
            {
                using (SqlConnection conn = new SqlConnection())
                {
                    conn.ConnectionString = connection;
                    conn.Open();
                    // Creating the command
                    SqlCommand command = new SqlCommand("SELECT Id FROM Customer WHERE Phone = @Phone", conn);
                    // Adding the parameters.
                    command.Parameters.Add(new SqlParameter("Phone", PhoneNumber));
                    // Creating new SqlDataReader object and read data from the command.
                    using (SqlDataReader reader = command.ExecuteReader())
                    {
                        // while there is another record present
                        while (reader.Read())
                        {
                            Id = reader["Id"].ToString();
                        }
                    }
                    conn.Close();

                    if (Id == "")
                        return true;
                    else
                        return false;
                }
            }
            catch (Exception)
            {
                return true;
            }
        }
    }
}
