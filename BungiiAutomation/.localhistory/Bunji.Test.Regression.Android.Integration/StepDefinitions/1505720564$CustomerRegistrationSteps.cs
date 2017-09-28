using System;
using TechTalk.SpecFlow;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class CustomerRegistrationSteps
    {
        [When(@"I enter the ""(.*)"" mandatory fields")]
        public void WhenIEnterTheMandatoryFields(string p0)
        {
            ScenarioContext.Current.Pending();
        }
        
        [When(@"I tap on the ""(.*)"" button")]
        public void WhenITapOnTheButton(string p0)
        {
            ScenarioContext.Current.Pending();
        }
        
        [When(@"I enter ""(.*)"" code")]
        public void WhenIEnterCode(string p0)
        {
            ScenarioContext.Current.Pending();
        }
        
        [Then(@"I should be logged in to the app")]
        public void ThenIShouldBeLoggedInToTheApp()
        {
            ScenarioContext.Current.Pending();
        }
    }
}
