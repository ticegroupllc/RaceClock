package com.example.myapplication.Utilities;

public class NumericCheck {
    public static void main(String[] args) {
        String str = "12345";

        if (isNumeric(str)) {
            int number = Integer.parseInt(str);
            //System.out.println("The string is numeric. Converted integer: " + number);
        } else {
            //System.out.println("The string is not numeric.");
        }
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
