package edu.kit.informatik;

import java.util.ArrayList;
import java.util.Comparator;

public class Sport {
    
    private static ArrayList<Sport> sports = new ArrayList<Sport>();
    /**
     * Create comparator to sort the sports in the array list alphabetically according to 
     * the type of sport, otherwise discipline, if type is the same.
     */
    private static Comparator<Sport> comparator = new Comparator<Sport>() {
        
        @Override
        public int compare(Sport sportOne, Sport sportTwo) {
            
            if (sportOne.sportType.compareTo(sportTwo.sportType) < 0) {
                return -1;
            } else if (sportOne.sportType.compareTo(sportTwo.sportType) == 0) {
                if (sportOne.discipline.compareTo(sportTwo.discipline) < 0) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
            
        }
    };
    
    private String sportType;
    private String discipline;
    
    /**
     * Constructor for a sport
     * 
     * @param sportType represents the name of the type of sport
     * @param discipline represents the name of the sport discipline
     */
    public Sport(String sportType, String discipline) {
        
        try {
            
            boolean sportExists = false;
            for (int i = 0; i < sports.size(); i++) {
                if (sports.get(i).sportType.equals(sportType) && sports.get(i).discipline.equals(discipline)
                        || sports == null) {
                    sportExists = true;
                    break;
                }
            }
            
            if (sportExists) {
                throw new IllegalArgumentException();
            }
            
            this.sportType = sportType;
            this.discipline = discipline;
            
            sports.add(this);
            Terminal.printLine("OK");
            
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter a valid sport type and discipline");
        }
    }
    
    /**
     * Getter for the list of sports created/added so far
     * 
     * @return the list of sports created
     */
    public static ArrayList<Sport> getSports() {
        return sports;
    }
    
    /**
     * get a list of the sports added so far 
     */
    public static void listSports() {
        sports.sort(comparator);
        for (int i = 0; i < sports.size(); i++) {
            Terminal.printLine(sports.get(i).sportType + " " + sports.get(i).discipline);
        }
    }
    
    /**
     * Getter for the type of sport
     * 
     * @return the sport type
     */
    public String getType() {
        return this.sportType;
    }
    
    /**
     * Getter for the sport discipline
     * 
     * @return the sport discipline
     */
    public String getDiscipline() {
        return this.discipline;
    }
}
