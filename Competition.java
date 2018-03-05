package edu.kit.informatik;

import java.util.ArrayList;

public class Competition {
    
    private static ArrayList<Competition> competitions = new ArrayList<Competition>();
    private Athlete athlete;
    private short year;
    private Sport sport;
    private boolean gold;
    private boolean silver;
    private boolean bronze;
    
    /**
     * Constructor for Competition
     * 
     * @param athleteID represents the id of the athlete
     * @param year represents the year in which the competition to be added took place
     * @param countryName represents the country of the athlete
     * @param sportType represents the type of sport the competition was for
     * @param discipline represents the sport discipline the competition was for
     * @param gold represents whether or not the athlete won a gold medal in the competition
     * @param silver represents whether or not the athlete won a silver medal in the competition
     * @param bronze represents whether or not the athlete won a bronze medal in the competition
     */
    public Competition(String athleteID, short year, String countryName, String sportType, 
            String discipline, byte gold, byte silver, byte bronze) {
        try {
            for (int i = 0; i < Sport.getSports().size(); i++) {
                if (Sport.getSports().get(i).getType().equals(sportType)
                        && Sport.getSports().get(i).getDiscipline().equals(discipline)) {
                    this.sport = Sport.getSports().get(i);
                }
            }
            boolean athleteExists = false;
            if (!athleteID.matches("[0-9]{4}")) {
                throw new IllegalArgumentException();
            }
            for (int i = 0; i < Athlete.getAthletes().size(); i++) {
                Athlete currentAthlete = Athlete.getAthletes().get(i);
                if ((currentAthlete.getID() == Short.parseShort(athleteID))
                        && currentAthlete.getCountry().getCountryName().equals(countryName)) {
                    if (this.sport != null && currentAthlete.getSports().contains(this.sport)) {
                        athleteExists = true;
                        this.athlete = currentAthlete;
                    } else {
                        throw new NullPointerException();
                    }
                }
            }
            if (!athleteExists) {
                throw new IllegalArgumentException();
            }
            boolean validYear = false;
            for (int i = 0; i <= 23; i++) {
                if (year == (1926 + (4 * i))) {
                    validYear = true;
                    this.year = year;
                    break;
                }
            }
            if (year < this.athlete.getCountry().getYear()) {
                validYear = false;
            }
            if (!validYear) {
                throw new IllegalArgumentException();
            }
            for (int i = 0; i < competitions.size(); i++) {
                Competition currentComp = competitions.get(i);
                if (currentComp.athlete.equals(this.athlete) && this.year == currentComp.year 
                        && currentComp.sport.getType().equals(sportType)
                        && currentComp.sport.getDiscipline().equals(discipline)) {
                    for (int j = 0; j < competitions.size(); j++) {
                        Terminal.printLine(competitions.get(j).athlete.getFirstName());
                    }
                    throw new IllegalArgumentException();
                }
            }
            
            if ((gold != 1 && gold != 0) && (silver != 1 || silver != 0) && (bronze != 0 && bronze != 1)) {
                throw new IllegalArgumentException();
            } else if ((gold == 1 && silver == 1) || (gold == 1 && bronze == 1) || (bronze == 1 && silver == 1)) {
                throw new IllegalArgumentException();
            } else {
                this.gold = (gold == 1) ? true : false;
                this.silver = (silver == 1) ? true : false;
                this.bronze = (bronze == 1) ? true : false;
                
                if (this.gold) {
                    this.athlete.addGold();
                }
                if (this.silver) {
                    this.athlete.addSilver();
                }
                if (this.bronze) {
                    this.athlete.addBronze();
                }
            }
            competitions.add(this);
            Terminal.printLine("OK");
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter valid competition details");
        } catch (NullPointerException n) {
            Terminal.printError("Please enter a valid sport");
        }
    }
    
    /**
     * Get the list competitions done/added so far
     * 
     * @return the list of competitions
     */
    public static ArrayList<Competition> getCompetitions() {
        return competitions;
    }

}
