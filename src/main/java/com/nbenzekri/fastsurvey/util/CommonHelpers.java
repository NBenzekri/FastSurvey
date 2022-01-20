package com.nbenzekri.fastsurvey.util;

public class CommonHelpers {
    public static boolean checkInputStringIfNull(String input) {
        return (input == null) || (input.trim().length() == 0);
    }
}
