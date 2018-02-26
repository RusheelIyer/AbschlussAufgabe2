package edu.kit.informatik;

import java.util.ArrayList;

public class IOC {
    
    private static ArrayList<IOC> countries = new ArrayList<IOC>();
    private short id;
    private String code;
    private String countryName;
    private int establishmentYear;
    private ArrayList<Athlete> athletes = new ArrayList<Athlete>();
    //TODO: Add athlete to respective array when created
    
    /**
     * Constructor for an IOC-Country
     * 
     * @param id represents the id of the country to be added
     * @param code represents the new code of the country to be added
     * @param countryName represents the name of the country to be added
     * @param establishmentYear represents the year in which the code was established
     */
    public IOC(String id, String code, String countryName, String establishmentYear) {
        try {
            if (id.matches("[0-9]{3}")) {
                for (int i = 0; i < countries.size(); i++) {
                    if (countries.get(i).id == Short.parseShort(id)) {
                        throw new IllegalArgumentException();
                    }
                }
                this.id = Short.parseShort(id);
            } else {
                throw new IllegalArgumentException();
            }
            
            for (int i = 0; i < countries.size(); i++) {
                if (countries.get(i).code.equals(code)) {
                    throw new IllegalArgumentException();
                }
            }
            this.code = code;
            
            for (int i = 0; i < countries.size(); i++) {
                if (countries.get(i).countryName.equals(countryName)) {
                    throw new IllegalArgumentException();
                }
            }
            this.countryName = countryName;
            
            if (establishmentYear.matches("[0-9]{4}")) {
                this.establishmentYear = Integer.parseInt(establishmentYear);
            } else {
                throw new IllegalArgumentException();
            }
            
            countries.add(this);
            
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter valid IOC information");
        }
    }
    
    /**
     * get the list of all the IOC countries created
     * 
     * @return the list of the countries
     */
    public static ArrayList<IOC> getCountries() {
        return countries;
    }
    
    /**
     * Getter for the id of the country
     * 
     * @return the id of the country
     */
    public short getID() {
        return this.id;
    }
    
    /**
     * Getter for the IOC code of the country
     * 
     * @return the IOC code of the country
     */
    public String getCode() {
        return this.code;
    }
    
    /**
     * Getter for the name of the country
     * 
     * @return the name of the country
     */
    public String getCountryName() {
        return this.countryName;
    }
    
    /**
     * Getter for the year, in which the country's IOC Code was established
     * 
     * @return the year, in which the country's IOC Code was established
     */
    public int getYear() {
        return this.establishmentYear;
    }

    /**
     * get the list of athletes belonging to a particular country
     * 
     * @return the list of athletes belonging to the country
     */
    public ArrayList<Athlete> getAthletes() {
        return this.athletes;
    }
    
    /**
     * Calculate the total number of medals won by athletes belonging to the country
     * 
     * @return total number of medals won
     */
    public int calculateMedals() {
        
        int medalCount = 0;
        
        for (int i = 0; i < this.athletes.size(); i++) {
            int golds = this.athletes.get(i).getGolds();
            int silvers = this.athletes.get(i).getSilvers();
            int bronzes = this.athletes.get(i).getBronzes();
            medalCount = medalCount + golds + silvers + bronzes;
        }
        
        return medalCount;
    }

}
