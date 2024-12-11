package com.example.myapplication.Utilities;

public class StringToInteger {
    public static int main(String args) {
        String numberStr = "12345";

        try {
            int number = Integer.parseInt(args);
            //System.out.println("Converted integer: " + number);
            return number;
        } catch (NumberFormatException e) {
            //System.out.println("Invalid number format");
        }
        return -1;
    }
}