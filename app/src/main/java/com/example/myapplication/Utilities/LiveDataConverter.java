package com.example.myapplication.Utilities;

import com.example.myapplication.TypeClasses.Meeting;
import com.example.myapplication.TypeClasses.Race;
import com.example.myapplication.TypeClasses.RaceEvent;
import com.example.myapplication.TypeClasses.Session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LiveDataConverter {
    public  static Race convertMeetingToRace(Meeting meeting) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.US);
        SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss", Locale.US);
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
        Date raceDate = null;
        Date gmtOffset = null;
        //System.out.println("Starting convertMeetingToRace");
        //System.out.println(meeting.toString());
        try {
           // System.out.println(new String("Startdate = " + meeting.getDateStart()));
            raceDate = formatter.parse(meeting.getDateStart());
            //raceDate = LocalDateTime.parse(meeting.getDateStart(),formatter);
            gmtOffset = formatter2.parse(meeting.getGmtOffset());
        } catch (ParseException e) {
            System.out.println("conversion Errors:");
            e.printStackTrace();
        }

        return new Race(
                meeting.getMeetingName(),
                meeting.getCircuitShortName(),
                meeting.getMeetingKey(),
                raceDate,
                gmtOffset,
                meeting.getLocation(),
                meeting.getCircuitShortName(),
                meeting.getCircuitKey(),
                meeting.getCountryName(),
                meeting.getCountryKey(),
                0.0, // Placeholder for raceLat
                0.0, // Placeholder for raceLon
                null, // Placeholder for trackLayoutImage
                null  // Placeholder for raceEvents
        );
    }

    public  static Meeting convertRaceToMeeting(Race race) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String dateStart = dateFormat.format(race.getRaceDate());
        String gmtOffset = dateFormat.format(race.getGmt_offset());

        return new Meeting(
                race.getCircuitKey(),
                race.getCircuitName(),
                race.getCountry(),
                race.getCountryKey(),
                race.getCountry(),
                dateStart,
                gmtOffset,
                race.getRaceTrack(),
                race.getMeeting_key(),
                race.getRaceName(),
                race.getRaceName(),
                race.getRaceDate().getYear() + 1900 // Adjusting for the year offset
        );
    }

    public static Session convertRaceEventToSession(RaceEvent raceEvent) {
        Session session = new Session(raceEvent.getCircuitKey(), raceEvent.getCircuitShortName(), raceEvent.getCountryCode(), raceEvent.getCountryKey(), raceEvent.getCountryName(), raceEvent.getDateEnd(), raceEvent.getDateStart(), raceEvent.getGmtOffset(), raceEvent.getLocation(), raceEvent.getMeetingKey(), raceEvent.getSessionKey(), raceEvent.getSessionName(), raceEvent.getSessionType(), raceEvent.getYear());
        return session;
    }

    public  static RaceEvent convertSessionToRaceEvent(Session session) {
        return new RaceEvent(
                session.getSessionName(),
                RaceEvent.getTypeFromString(session.getSessionType()),
                    session.getDateStart(),
                    session.getCircuitKey(),
                    session.getCircuitShortName(),
                    session.getCountryCode(),
                    session.getCountryKey(),
                    session.getCountryName(),
                    session.getDateEnd(),
                    session.getDateStart(),
                    session.getGmtOffset(),
                    session.getLocation(),
                    session.getMeetingKey(),
                    session.getSessionKey(),
                    session.getSessionName(),
                    session.getSessionType(),
                    session.getYear()
            );
        }


}
