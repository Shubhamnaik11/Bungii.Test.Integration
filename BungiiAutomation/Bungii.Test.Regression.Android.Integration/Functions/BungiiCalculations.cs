using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bungii.Test.Regression.Android.Integration.Functions
{
    public class BungiiCalculations
    {
        public double TotalEstimate(string Time, string Distance, string Promo)
        {
            double TripTime = Convert.ToDouble(Time.Replace("minutes", "").Replace(" ", ""));
            double TripDistance = Convert.ToDouble(Distance.Replace("miles", "").Replace(" ", ""));
            double PromoCode = Convert.ToDouble(Promo.Replace("-$",""));

            double TotalEstimate = TripTime + TripDistance - PromoCode;

            return TotalEstimate;
        }
    }
}
