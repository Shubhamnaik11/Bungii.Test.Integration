package com.bungii.common.utilities;

public class ThreadLocalStepDefinitionMatch {

    private static final ThreadLocal<String> threadStepDefMatch = new InheritableThreadLocal<String>();
    private static int numberOfSteps;

    private ThreadLocalStepDefinitionMatch() {
    }

    public static String get() {
        return threadStepDefMatch.get();
    }

    public static void set(String stepText) {
        if (get() != null) {
            //check if same step defination is calling it
            if (!get().equals(stepText)) {
                numberOfSteps++;
            }
        }else{
            //First time in test case
            numberOfSteps++;
        }
        threadStepDefMatch.set(stepText);
    }

    public static int getNumberOfSteps() {
        return numberOfSteps;
    }

    public static void resetNumberOfSteps() {
        numberOfSteps = 0;
    }

    public static void remove() {
        threadStepDefMatch.remove();
    }
}