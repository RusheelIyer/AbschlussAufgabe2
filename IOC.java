package edu.kit.informatik;

import java.util.ArrayList;
import java.util.Comparator;

public class IOC {
    
    private static ArrayList<IOC> countries = new ArrayList<IOC>();
    /**
     * Create comparator to sort the countries in the array list ascending according to 
     * the year that they were established or their ID, if the establishment years
     * are the same
     */
    private static Comparator<IOC> comparator = new Comparator<IOC>() {
        
        @Override
        public int compare(IOC countryOne, IOC countryTwo) {
            
            if (countryOne.establishmentYear < countryTwo.establishmentYear) {
                return -1;
            } else if (countryOne.establishmentYear == countryTwo.establishmentYear) {
                if (countryOne.id < countryTwo.id) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }  
    };
    
    /**
     * Create second comparator to sort the countries in the array list according to 
     * first, the number of gold medals won, then silver, then bronze and if all three equal
     * lastly ID
     */
    private static Comparator<IOC> medalComparator = new Comparator<IOC>() {
        
        @Override
        public int compare(IOC countryOne, IOC countryTwo) {
            
            if (countryOne.goldMedals() < countryTwo.goldMedals()) {
                return 1;
            } else if (countryOne.goldMedals() == countryTwo.goldMedals()) {
                if (countryOne.silverMedals() < countryTwo.silverMedals()) {
                    return 1;
                } else if (countryOne.silverMedals() == countryTwo.silverMedals()) {
                    if (countryOne.bronzeMedals() < countryTwo.bronzeMedals()) {
                        return 1;
                    } else if (countryOne.bronzeMedals() == countryTwo.bronzeMedals()) {
                        if (countryOne.id < countryTwo.id) {
                            return -1;
                        } else {
                            return 1;
                        }
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            } else { 
                return -1;
            }
        }  
    };
    
    private short id;
    private String code;
    private String countryName;
    private int establishmentYear;
    private ArrayList<Athlete> athletes = new ArrayList<Athlete>();
    
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
            if (id.matches("[0-9]{3}") && Short.parseShort(id) >= 1) {
                for (int i = 0; i < countries.size(); i++) {
                    if (countries.get(i).id == Short.parseShort(id)) {
                        throw new IllegalArgumentException();
                    }
                }
            } else {
                throw new IllegalArgumentException();
            }
            
            for (int i = 0; i < countries.size(); i++) {
                if (countries.get(i).id == Short.parseShort(id)) {
                    throw new IllegalArgumentException();
                }
            }
            this.id = Short.parseShort(id);
            
            if (code.length() != 3) {
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
            Terminal.printLine("OK");
            
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter valid IOC details");
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
     * list all the IOC codes created so far
     */
    public static void listIOC() {
        countries.sort(comparator);
        
        for (int i = 0; i < countries.size(); i++) {
            Terminal.printLine(String.format("%04d", countries.get(i).establishmentYear) + " "
                    + String.format("%03d", countries.get(i).id) + " " 
                    + countries.get(i).code + " " + countries.get(i).countryName);
        }
    }
    
    /**
     * Method to print out the olympic medal standings with the countries
     */
    public static void medalTable() {
        
        countries.sort(medalComparator);
        for (int i = 0; i < countries.size(); i++) {
            Terminal.printLine("(" + i + 1 + " " + String.format("%03d", countries.get(i).id)
                    + " " + countries.get(i).code + " " + countries.get(i).countryName + " " 
                    + countries.get(i).goldMedals() + " " + countries.get(i).silverMedals() + " "
                    + countries.get(i).bronzeMedals() + " " + countries.get(i).calculateMedals() + ")");
        }
        
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
     * Calculate the total number of gold medals won by athletes belonging to the country
     * 
     * @return total number of medals won
     */
    public int goldMedals() {
        
        int medalCount = 0;
        for (int i = 0; i < this.athletes.size(); i++) {
            medalCount = medalCount + this.athletes.get(i).getGolds();
        }

        return medalCount;
    }
    
    /**
     * Calculate the total number of silver medals won by athletes belonging to the country
     * 
     * @return total number of medals won
     */
    public int silverMedals() {
        
        int medalCount = 0;
        for (int i = 0; i < this.athletes.size(); i++) {
            medalCount = medalCount + this.athletes.get(i).getSilvers();
        }
        
        return medalCount;
    }
    
    /**
     * Calculate the total number of bronze medals won by athletes belonging to the country
     * 
     * @return total number of medals won
     */
    public int bronzeMedals() {
        
        int medalCount = 0;
        for (int i = 0; i < this.athletes.size(); i++) {
            medalCount = medalCount + this.athletes.get(i).getBronzes();
        }
        
        return medalCount;
    }
    
    /**
     * Calculate the total number of medals won by athletes belonging to the country
     * 
     * @return total number of medals won
     */
    public int calculateMedals() {
        
        return this.goldMedals() + this.silverMedals() + this.bronzeMedals();
                
    }

}
