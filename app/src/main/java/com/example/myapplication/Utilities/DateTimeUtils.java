package com.example.myapplication.Utilities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtils {

    public static String convertUtcToLocalString(String utcDateTimeString) {
        // Define the formatter for the input UTC datetime string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");

        // Parse the UTC datetime string to a LocalDateTime object
        LocalDateTime utcDateTime = LocalDateTime.parse(utcDateTimeString, formatter);

        // Convert LocalDateTime to ZonedDateTime in UTC
        ZonedDateTime utcZonedDateTime = utcDateTime.atZone(ZoneId.of("UTC"));

        // Convert ZonedDateTime to the system's default time zone
        ZonedDateTime localZonedDateTime = utcZonedDateTime.withZoneSameInstant(ZoneId.systemDefault());

        // Format the local ZonedDateTime to a string
        String localDateTimeString = localZonedDateTime.format(formatter);

        return localDateTimeString;
    }
    public static LocalDateTime convertUtcToLocal(String utcDateTimeString) {
        // Define the formatter for the input UTC datetime string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");

        // Parse the UTC datetime string to a LocalDateTime object
        LocalDateTime utcDateTime = LocalDateTime.parse(utcDateTimeString, formatter);

        // Convert LocalDateTime to ZonedDateTime in UTC
        ZonedDateTime utcZonedDateTime = utcDateTime.atZone(ZoneId.of("UTC"));

        // Convert ZonedDateTime to the system's default time zone
        ZonedDateTime localZonedDateTime = utcZonedDateTime.withZoneSameInstant(ZoneId.systemDefault());

        // Format the local ZonedDateTime to a string
        String localDateTimeString = localZonedDateTime.format(formatter);

        return localZonedDateTime.toLocalDateTime();

        //return localDateTimeString;
    }

    public static long getNumberOfMillisecondsBetween(LocalDateTime start, LocalDateTime end) {
        // Calculate the number of milliseconds between the two LocalDateTime objects
        //System.out.println("start");
        //System.out.println(start.toString());
        //System.out.println("end");
        //System.out.println(end.toString());
        long millisecondsBetween = ChronoUnit.MILLIS.between(start, end);
        return millisecondsBetween;

    }


    public static void main(String[] args) {
        String utcDateTimeString = "2024-11-08T13:53:08";
        String localDateTimeString = convertUtcToLocalString(utcDateTimeString);
        //System.out.println("Local DateTime: " + localDateTimeString);
    }
}