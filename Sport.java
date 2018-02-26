package edu.kit.informatik;

import java.util.ArrayList;

public class Sport {
    
    private static ArrayList<Sport> sports = new ArrayList<Sport>();
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
            } else {
                this.sportType = sportType;
                this.discipline = discipline;
            }
            
            sports.add(this);
            
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
