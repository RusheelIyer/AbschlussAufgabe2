package edu.kit.informatik;

import java.util.ArrayList;
import java.util.Comparator;

public class Athlete extends Person {
    
    private static ArrayList<Athlete> athletes = new ArrayList<Athlete>();
    /**
     * Create comparator to sort the athletes in the array list descending according to
     * the number of medals s/he has won, or ascending according to the ID if the number of medals won are the same
     */
    private static Comparator<Athlete> comparator = new Comparator<Athlete>() {
        
        @Override
        public int compare(Athlete athleteOne, Athlete athleteTwo) {
            int athleteOneMedals = athleteOne.bronzes + athleteOne.silvers + athleteOne.golds;
            int athleteTwoMedals = athleteTwo.bronzes + athleteTwo.silvers + athleteTwo.golds;
            if (athleteOneMedals < athleteTwoMedals) {
                return 1;
            } else if (athleteOneMedals == athleteTwoMedals) {
                if (athleteOne.id < athleteTwo.id) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return -1;
            }
            
        }
        
    };
    
    private short id;
    private IOC country;
    private ArrayList<Sport> sports = new ArrayList<Sport>();
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
            boolean athleteExists = false;
            Athlete existingAthlete = null;
            if (id.matches("[0-9]{4}") && Short.parseShort(id) >= 1) {
                for (int i = 0; i < athletes.size(); i++) {
                    Athlete current = athletes.get(i);
                    if (current.id == Short.parseShort(id)) {
                        if (!current.getFirstName().equals(firstName) || !current.getLastName().equals(lastName)
                                || !current.country.getCountryName().equals(country)) {
                            throw new IllegalArgumentException();
                        }
                        athleteExists = true;
                        existingAthlete = current;
                    }
                }
            } else {
                throw new IllegalArgumentException();
            }
            if (athleteExists) {   
                boolean sportExists = false;
                for (int i = 0; i < Sport.getSports().size(); i++) {
                    Sport current = Sport.getSports().get(i);
                    if (current.getType().equals(sportType) && current.getDiscipline().equals(discipline)) {
                        sportExists = true;
                        if (existingAthlete.sports.contains(current)) {
                            throw new IllegalArgumentException();
                        } else {
                            existingAthlete.sports.add(current);   
                        }
                    }
                }
                if (!sportExists) {
                    throw new IllegalArgumentException();
                }
            } else {
                this.id = Short.parseShort(id);
                boolean countryExists = false;
                for (int i = 0; i < IOC.getCountries().size(); i++) {
                    if (IOC.getCountries().get(i).getCountryName().equals(country)) {
                        countryExists = true;
                        this.country = IOC.getCountries().get(i);
                        break;
                    }
                }
                if (!countryExists) {
                    throw new IllegalArgumentException();
                }
                boolean sportExists = false;
                for (int i = 0; i < Sport.getSports().size(); i++) {
                    Sport current = Sport.getSports().get(i);
                    if (current.getType().equals(sportType) && current.getDiscipline().equals(discipline)) {
                        sportExists = true;
                        this.sports.add(current);
                    }
                }
                if (!sportExists) {
                    throw new IllegalArgumentException();
                }
                this.country.getAthletes().add(this);
                athletes.add(this);
            }
            Terminal.printLine("OK");
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter valid Athlete Details");
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
     * Method to list athletes who perform the specified sport discipline
     * 
     * @param sportType represents the type of sport, whose performing athletes are wished to be listed
     * @param discipline represents the discipline, whose performing athletes are wished to be listed 
     */
    public static void listAthletes(String sportType, String discipline) {
        
        try {
            
            boolean sportExists = false;
            for (int i = 0; i < Sport.getSports().size(); i++) {
                Sport current = Sport.getSports().get(i);
                if (current.getType().equals(sportType) && current.getDiscipline().equals(discipline)) {
                    sportExists = true;
                }
            }
            
            if (!sportExists) {
                throw new IllegalArgumentException();
            }
            
            ArrayList<Athlete> sportAthletes = new ArrayList<Athlete>();
            for (int i = 0; i < athletes.size(); i++) {
                for (int j = 0; j < athletes.get(i).sports.size(); j++) {
                    ArrayList<Sport> currentAthleteSports = athletes.get(i).sports;
                    if (currentAthleteSports.get(j).getType().equals(sportType)
                            && currentAthleteSports.get(j).getDiscipline().equals(discipline)) {
                        sportAthletes.add(athletes.get(i));
                    }
                }
            }
            
            sportAthletes.sort(comparator);
            for (int i = 0; i < sportAthletes.size(); i++) {
                int medals = sportAthletes.get(i).bronzes + sportAthletes.get(i).silvers 
                        + sportAthletes.get(i).golds;
                Terminal.printLine(String.format("%04d", sportAthletes.get(i).id) + " "
                        + sportAthletes.get(i).getFirstName() + " "
                        + sportAthletes.get(i).getLastName() + " " + medals);
            }
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter a valid sport type and discipline");
        }
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
    public ArrayList<Sport> getSports() {
        return this.sports;
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
        this.silvers = this.silvers + 1;
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
