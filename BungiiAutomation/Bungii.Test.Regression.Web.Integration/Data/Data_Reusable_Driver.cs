using System;

namespace Bungii.Android.Regression.Test.Integration.Data
{
    public class Data_Reusable_Driver
    {
        //-------------------Admin Login Details------------------------------------------------------------------
        public string AdminPhoneNumber = "9975960645",
                      AdminPassword = "cci12345";
        //--------------------------------------------------------------------------------------------------------

        //Driver Registration Details 1
        public string DriverFirstName = "James",
                      DriverLastName = "Edison",
                      DriverEmail = "seonad.fernandes@creativecapsule.com",
                      DriverPhoneNumber = "9999995555",
                      DriverPassword = "Cci12345",
                      //---Invalid Registration Details
                      Invalid_DriverName = "12!@#&-a",
                      Invalid_DriverEmail = "a1.2b",
                      Existing_DriverPhoneNumber = "9999998081",
                      Invalid_DriverPhoneNumber = "0000",
                      Invalid_DriverPassword = "december",
                      Short_DriverPassword = "Cci",

                      //------Driver Details
                      DriverStreet = "8500 Santa Fe Drive",
                      DriverCity = "Overland Park",
                      DriverState = "KS",
                      DriverZipCode = "66212",
                      DriverOther = "Not available after 12 AM",
                      DriverOccupation = "Freelancer",
                      DriverSSN = "111-24-2333",
                      DriverBirthday = "12/12/1990",
                      DriverPic = "DriverPic.png",
                      //---Invalid Driver Details
                      DriverZipCode_Invalid = "abc",
                      DriverSSN_Invalid = "000-00-000000",
                      Date_2015 = "12/12/2015",
                      Date_Invalid = "24/24/2424",

        //Pickup Information
        PickupMake = "Toyota",
                      PickupModel = "Tacoma",
                      PickupYear = "2017",
                      PickupLicenseNo = "KIP-1010", //unique
                      All3PickupImages = "\"tacoma1.png\" \"tacoma2.png\" \"tacoma3.png\"",

                      //Documentation
                      DriverLicenseImage = "License.png",
                      DriverLicenseNumber = "ABCDE878", //unique
                      ExpirationDate = "12/12/2019",
                      DriverInsuranceImage = "Insurance.png",
                      //---Invalid Documentation
                      DriverLicenseNumber_Existing = "7898789F", //unique

                      //Banking Details
                      DriverRoutingNumber = "122100024",
                      DriverBankAccNumber = "4598909012", //unique
                      //---Invalid Bank Details
                      InvalidValue = "a$",
                      DriverBankAccNumber_Invalid = "as$%%^&*";
        //--------------------------------------------------------------------------------------------------------

        //Driver Registration Details 2
        public string DriverFirstName_1 = "Danny",
                      DriverLastName_1 = "Tanner",
                      DriverEmail_1 = "seonad.fernandes@creativecapsule.com",
                      //Driver Details 
                      DriverStreet_1 = "PO Box 515381",
                      DriverCity_1 = "Los Angeles",
                      DriverState_1 = "CA",
                      DriverZipCode_1 = "90051-6681",

                      DriverSSN_1 = "111-24-2344",
                      DriverBirthday_1 = "10/11/1986",
                      DriverPic_1 = "DriverPic01.png",
                      //Pickup Information
                      PickupMake_1 = "Honda",
                      PickupModel_1 = "Ridgeline",
                      PickupYear_1 = "2016",
                      PickupLicenseNo_1 = "POV-54321",
                      PickupImages_1 = "\"Ridgeline1.jpg\" \"Ridgeline2.jpg\" \"Ridgeline3.jpg\"",
        //Documentation
                      DriverLicenseImage_1 = "License.png",
                      ExpirationDate_1 = "11/10/2020",
                      DriverInsuranceImage_1 = "Insurance.png",
        //Banking Details
                      DriverRoutingNumber_1 = "122100024";
    }
}
