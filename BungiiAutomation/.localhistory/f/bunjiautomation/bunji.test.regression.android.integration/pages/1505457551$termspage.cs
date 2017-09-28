
using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bungii.Test.Regression.Android.Integration.Pages
{
    class TermsPage
    {

        public TermsPage(IWebDriver driver)
       {
           PageFactory.InitElements(driver, this);
       }
        //Terms Page Elements

        //Checkboxes

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/checkbox_accept_terms")]
        public IWebElement Checkbox_Agree { get; set; }

        
        //----------------Buttons--------------//

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/btn_terms_continue")]
        public IWebElement Button_Continue { get; set; }

        //Permissions popup

        [FindsBy(How = How.Id, Using = "com.android.packageinstaller:id/permission_allow_button")]
        public IWebElement Button_PermissionsAllow { get; set; }

        [FindsBy(How = How.Id, Using = "com.android.packageinstaller:id/permission_deny_button")]
        public IWebElement Button_PermissionsDeny { get; set; }        
    }
}


