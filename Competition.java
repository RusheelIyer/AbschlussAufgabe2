package edu.kit.informatik;

public class Competition {
    
    private Athlete athlete;
    @SuppressWarnings("unused")
    private short year;
    @SuppressWarnings("unused")
    private boolean gold;
    @SuppressWarnings("unused")
    private boolean silver;
    @SuppressWarnings("unused")
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
            
            boolean athleteExists = false;
            for (int i = 0; i < Athlete.getAthletes().size(); i++) {
                if (Athlete.getAthletes().get(i).getID() == Short.parseShort(athleteID)) {
                    if (Athlete.getAthletes().get(i).getCountry().getCountryName().equals(countryName)) {
                        if (Athlete.getAthletes().get(i).getSport().getType().equals(sportType)
                                && Athlete.getAthletes().get(i).getSport().getDiscipline().equals(discipline)) {
                            athleteExists = true;
                            this.athlete = Athlete.getAthletes().get(i);
                        }
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
            
            if (!validYear) {
                throw new IllegalArgumentException();
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

            Terminal.printLine("OK");
            
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter valid competition details");
        }
    }

}
