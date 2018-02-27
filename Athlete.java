package edu.kit.informatik;

import java.util.ArrayList;

public class Athlete extends Person {
    
    private static ArrayList<Athlete> athletes = new ArrayList<Athlete>();
    private short id;
    private IOC country;
    private Sport sport;
    private int golds;
    private int silvers;
    private int bronzes;
    
    /**
     * Constructor for an athlete
     * 
     * @param id represents the athlete's ID
     * @param firstName represents the athlete's first name
     * @param lastName represents the athlete's last name
     * @param country represents the athlete's nationality
     * @param sportType represents the sport type the athlete performs
     * @param discipline represents the sport discipline that the athlete performs
     */
    public Athlete(String id, String firstName, String lastName, String country, String sportType, String discipline) {
        super(firstName, lastName);
        try {
            
            if (id.matches("[0-9]{4}") && Short.parseShort(id) >= 1) {
                for (int i = 0; i < athletes.size(); i++) {
                    if (athletes.get(i).id == Short.parseShort(id)) {
                        throw new IllegalArgumentException();
                    }
                }
                this.id = Short.parseShort(id);
            } else {
                throw new IllegalArgumentException();
            }
            
            
            if (IOC.getCountries().isEmpty()) {
                throw new IllegalArgumentException();
            }
            boolean countryExists = false;
            for (int i = 0; i < IOC.getCountries().size(); i++) {
                if (IOC.getCountries().get(i).getCountryName().equals(country)) {
                    countryExists = true;
                    this.country = IOC.getCountries().get(i);
                    IOC.getCountries().get(i).getAthletes().add(this);
                    break;
                }
            }
            
            if (!countryExists) {
                throw new IllegalArgumentException();
            }
            
            if (Sport.getSports().isEmpty()) {
                throw new IllegalArgumentException();
            }
            boolean sportExists = false;
            for (int i = 0; i < Sport.getSports().size(); i++) {
                if (Sport.getSports().get(i).getType().equals(sportType)
                        && Sport.getSports().get(i).getDiscipline().equals(discipline)) {
                    sportExists = true;
                    this.sport = Sport.getSports().get(i);
                }
            }
            
            if (!sportExists) {
                throw new IllegalArgumentException();
            }
            
            athletes.add(this);
            
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter vlaid Athlete Details");
        }
        
    }
    
    /**
     * gets the list of athletes added so far
     * 
     * @return list of athletes
     */
    public static ArrayList<Athlete> getAthletes() {
        return athletes;
    }
    
    /**
     * Getter for the athlete's ID
     * 
     * @return the athlete's ID
     */
    public short getID() {
        return this.id;
    }
    
    /**
     * Getter for the athlete's country information
     * 
     * @return the athlete's country
     */
    public IOC getCountry() {
        return this.country;
    }
    
    /**
     * Getter for the athlete's sport
     * 
     * @return the athlete's sport information
     */
    public Sport getSport() {
        return this.sport;
    }
    
    /**
     * Getter for the number of gold medals the athlete has won
     * 
     * @return the number of gold medals won by the athlete
     */
    public int getGolds() {
        return this.golds;
    }
    
    /**
     * Getter for the number of silver medals the athlete has won
     * 
     * @return the number of silver medals won by the athlete
     */
    public int getSilvers() {
        return this.silvers;
    }
    
    /**
     * Getter for the number of bronze medals the athlete has won
     * 
     * @return the number of bronze medals won by the athlete
     */
    public int getBronzes() {
        return this.bronzes;
    }
    
    /**
     * increase the number of gold medals won by the athlete by 1, 
     * if s/he has taken part in and won a new competition
     * 
     */
    public void addGold() {
        this.golds = this.golds + 1;
    }
    
    /**
     * increase the number of silver medals won by the athlete by 1, 
     * if s/he has taken part in and won a new competition
     * 
     */
    public void addSilver() {
        this.silvers = this.golds + 1;
    }

    /**
     * increase the number of bronze medals won by the athlete by 1, 
     * if s/he has taken part in and won a new competition
     * 
     */
    public void addBronze() {
        this.bronzes = this.bronzes + 1;
    }

}
