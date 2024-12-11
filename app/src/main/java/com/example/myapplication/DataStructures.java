package com.example.myapplication;


public class DataStructures {


    public static RaceEventType getByName(String name) {
        return RaceEventType.valueOf(name);
    }

    public static void main(String[] args) {
        //System.out.println(getByName("Practice"));
    }


    // RaceEventType enum
    public enum RaceEventType {
        PRACTICE("Practice"),
        QUALIFYING("Qualifying"),
        SPRINT("Sprint"),
        RACE("Race");

        private final String type;

        RaceEventType(String type) {
            this.type = type;
        }

        public  String getType() {
            return type;
        }

        public static RaceEventType fromType(String type) {
            for (RaceEventType eventType : RaceEventType.values()) {
                if (eventType.getType().equalsIgnoreCase(type)) {
                    return eventType;
                }
            }
            throw new IllegalArgumentException("Unknown type: " + type);
        }
    }

    // RacingTypeAbbreviation enum
    public enum RacingTypeAbbreviation {
        F1("F1"),
        F2("F2"),
        F3("F3"),
        FE("FE"),
        MOTOGP("MOTOGP"),
        MOTO2("MOTO2"),
        MOTO3("MOTO3"),
        INDYNXT("INDYNXT"),
        INDY("INDY"),
        NASCAR("NASCAR"),
        WRC("WRC"),
        GT("GT"),
        RX("RX"),
        END("END"),
        KART("KART"),
        RR("RR"),
        EUROX("EUROX");

        private final String abbreviation;

        RacingTypeAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public String getAbbreviation() {
            return abbreviation;
        }
    }
}


