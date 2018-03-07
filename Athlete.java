package edu.kit.informatik;

import java.util.ArrayList;
import java.util.TreeMap;

public class Athlete extends Person implements Comparable<Athlete> {
    
    private static ArrayList<Athlete> athletes = new ArrayList<Athlete>();
    private short id;
    private IOC country;
    private TreeMap<Sport, Integer> sports = new TreeMap<Sport, Integer>();
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
            
            Sport inputSport = null;
            for (int i = 0; i < Sport.getSports().size(); i++) {
                Sport current = Sport.getSports().get(i);
                if (current.getType().equals(sportType) && current.getDiscipline().equals(discipline)) {
                    inputSport = current;
                }
            }
            if (inputSport == null) {
                throw new NullPointerException();
            }
            
            if (athleteExists) {   
                if (existingAthlete.sports.keySet().contains(inputSport)) {
                    throw new IllegalArgumentException();
                } else {
                    existingAthlete.sports.put(inputSport, 0);   
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
                this.sports.put(inputSport, 0);
                this.country.getAthletes().add(this);
                athletes.add(this);
            }
            Terminal.printLine("OK");
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter valid Athlete Details");
        } catch (NullPointerException n) {
            Terminal.printError("Please enter a valid sport");
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
            
            Sport listSport = null;
            for (int i = 0; i < Sport.getSports().size(); i++) {
                Sport current = Sport.getSports().get(i);
                if (current.getType().equals(sportType) && current.getDiscipline().equals(discipline)) {
                    listSport = current;
                }
            }
            
            if (listSport == null) {
                throw new NullPointerException();
            }
            
            TreeMap<Athlete, Integer> sportAthletes = new TreeMap<Athlete, Integer>();
            for (int i = 0; i < athletes.size(); i++) {
                if (athletes.get(i).sports.keySet().contains(listSport)) {
                    sportAthletes.put(athletes.get(i), athletes.get(i).sports.get(listSport));
                }
            }
            
            Athlete[] sortedAthletes = new Athlete[sportAthletes.size()];
            int index = 0;
            for (Athlete athlete : sportAthletes.keySet()) {
                sortedAthletes[index] = athlete;
                index++;
            }
            int count = 1;
            while (count < sortedAthletes.length) {
                for (int i = 0; i < sortedAthletes.length - 1; i++) {
                    if (sortedAthletes[i].sports.get(listSport) < sortedAthletes[i + 1].sports.get(listSport)) {
                        Athlete temp = sortedAthletes[i + 1];
                        sortedAthletes[i + 1] = sortedAthletes[i];
                        sortedAthletes[i] = temp;
                    } else if (sortedAthletes[i].sports.get(listSport) == sortedAthletes[i + 1].sports.get(listSport)) {
                        if (sortedAthletes[i].id > sortedAthletes[i + 1].id) {
                            Athlete temp = sortedAthletes[i + 1];
                            sortedAthletes[i + 1] = sortedAthletes[i];
                            sortedAthletes[i] = temp;
                        }
                    }
                }
                count++;
            }
            for (Athlete athlete : sortedAthletes) {
                Terminal.printLine(String.format("%04d", athlete.id) + " " + athlete.getFirstName()
                + " " + athlete.getLastName() + " " + athlete.sports.get(listSport));
            }
            
        } catch (NullPointerException e) {
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
    public TreeMap<Sport, Integer> getSports() {
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

    @Override
    public int compareTo(Athlete athlete) {
        if (this == athlete) {
            return 0;
        } else {
            return 1;
        }
    }
}