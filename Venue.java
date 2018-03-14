package edu.kit.informatik;

import java.util.ArrayList;
import java.util.Comparator;

public class Venue {
    
    private static ArrayList<Venue> venues = new ArrayList<Venue>();
    /**
     * Create comparator to sort the venues in the array list according to capacity if unequal,
     * otherwise by id
     */
    private static Comparator<Venue> comparator = new Comparator<Venue>() {
        
        @Override
        public int compare(Venue venueOne, Venue venueTwo) {
            
            if (venueOne.capacity < venueTwo.capacity) {
                return -1;
            } else if (venueOne.capacity == venueTwo.capacity) {
                if (venueOne.id < venueTwo.id) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
            
        }
        
    };
    
    private String location;
    @SuppressWarnings("unused")
    private short openingYear;
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
            
            if (openingYear.matches("[0-9]{4}") && Short.parseShort(openingYear) >= 1) {
                this.openingYear = Short.parseShort(openingYear);
            } else {
                throw new IllegalArgumentException();
            }
            
            if (capacity < 0) {
                throw new IllegalArgumentException();
            }
            this.capacity = capacity;
            venues.add(this);
            Terminal.printLine("OK");
            
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter valid venue details");
        }
    }
    
    /**
     * get the list of venues created so far
     * 
     * @return list of venues
     */
    public static ArrayList<Venue> getVenues() {
        return venues;
    }
    
    /**
     * get a list of the venues in a specified country 
     * 
     * @param countryName represents the country, of whose venues are to be listed
     */
    public static void listVenues(String countryName) {
        
        try {
            
            boolean countryExists = false;
            for (int i = 0; i < IOC.getCountries().size(); i++) {
                if (IOC.getCountries().get(i).getCountryName().equals(countryName)) {
                    countryExists = true;
                }
            }
            
            if (!countryExists) {
                throw new IllegalArgumentException();
            }
            
            ArrayList<Venue> countryVenues = new ArrayList<Venue>();
            
            for (int i = 0; i < venues.size(); i++) {
                if (venues.get(i).country.getCountryName().equals(countryName)) {
                    countryVenues.add(venues.get(i));
                }
            }
            
            countryVenues.sort(comparator);
            for (int i = 0; i < countryVenues.size(); i++) {
                Terminal.printLine("(" + (i + 1) + " " + String.format("%03d", countryVenues.get(i).id)
                + " " + countryVenues.get(i).location + " " + countryVenues.get(i).capacity + ")");
            }
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter a valid country name");
        }
    }
}
