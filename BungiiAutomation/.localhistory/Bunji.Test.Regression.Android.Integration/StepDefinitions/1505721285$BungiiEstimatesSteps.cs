using System;
using TechTalk.SpecFlow;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class BungiiEstimatesSteps
    {
        [Given(@"I am logged in as a customer")]
        public void GivenIAmLoggedInAsACustomer()
        {
            ScenarioContext.Current.Pending();
        }
        
        [When(@"I set correct ""(.*)"" location")]
        public void WhenISetCorrectLocation(string p0)
        {
            ScenarioContext.Current.Pending();
        }
        
        [When(@"I click on the ""(.*)"" link")]
        public void WhenIClickOnTheLink(string p0)
        {
            ScenarioContext.Current.Pending();
        }
        
        [Then(@"the ""(.*)"" page should be displayed")]
        public void ThenThePageShouldBeDisplayed(string p0)
        {
            ScenarioContext.Current.Pending();
        }
    }
}
