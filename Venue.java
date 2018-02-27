package edu.kit.informatik;

import java.util.ArrayList;

public class Venue {
    
    private static ArrayList<Venue> venues = new ArrayList<Venue>();
    private String location;
    @SuppressWarnings("unused")
    private short openingYear;
    @SuppressWarnings("unused")
    private IOC country;
    @SuppressWarnings("unused")
    private String venueName;
    private int capacity;
    private short id;
    
    /**
     * Constructor for venue
     * 
     * @param id represents the ID of the venue
     * @param countryName represents the name of the country in which the venue is located
     * @param location represents the location of the venue
     * @param venueName represents the name of the venue
     * @param openingYear represents the opening year of the venue
     * @param capacity represents the seating capacity of the venue
     */
    public Venue(String id, String countryName, String location, String venueName, String openingYear, int capacity) {
        
        try {
            
            if (id.matches("[0-9]{3}") && Short.parseShort(id) >= 1) {
                for (int i = 0; i < venues.size(); i++) {
                    if (venues.get(i).id == Short.parseShort(id)) {
                        throw new IllegalArgumentException();
                    }
                }
                this.id = Short.parseShort(id);
            } else {
                throw new IllegalArgumentException();
            }
            
            boolean countryExists = false;
            for (int i = 0; i < IOC.getCountries().size(); i++) {
                if (IOC.getCountries().get(i).getCountryName().equals(countryName)) {
                    countryExists = true;
                    this.country = IOC.getCountries().get(i);
                    break;
                }
            }
            
            if (!countryExists) {
                throw new IllegalArgumentException();
            }
            
            this.location = location;
            this.venueName = venueName;
            
            if (openingYear.matches("[0-9]{4}")) {
                this.openingYear = Short.parseShort(openingYear);
            } else {
                throw new IllegalArgumentException();
            }
            
            if (capacity < 0) {
                throw new IllegalArgumentException();
            }
            this.capacity = capacity;
            venues.add(this);
            
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter valid venue details");
        }
    }
    
    /**
     * get a list of the venues added so far
     * 
     */
    public static void listVenues() {
        venues.sort(comparator);
        for (int i = 0; i < venues.size(); i++) {
            Terminal.printLine("(" + i + 1 + " " + String.format("%03d", venues.get(i).id)
            + " " + venues.get(i).location + " " + venues.get(i).capacity);
        }
    }
}
