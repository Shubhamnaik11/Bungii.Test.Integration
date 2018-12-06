package com.bungii.ios.utilityfunctions;

public class GeneralUtility {
    static final double MIN_COST = 39;

    /**
     * Calculate estimate cost of trip check if less than minimum cost then
     * return minimum cost
     *
     * @param tripDistance
     * @param loadTime
     * @param estTime
     * @param Promo
     * @return
     */
    public double bungiiEstimate(String tripDistance, String loadTime, String estTime, String Promo) {

        double distance = Double.parseDouble(tripDistance.replace(" miles", ""));
        double loadUnloadTime = Double.parseDouble(loadTime.replace(" mins", ""));
        double tripTime = Double.parseDouble(estTime);

        double estimate = distance + loadUnloadTime + tripTime;
        estimate = estimate > MIN_COST ? estimate : MIN_COST;

        return estimate;
    }
}
