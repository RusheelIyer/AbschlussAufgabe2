package edu.kit.informatik;

public class Main {

    /**
     * Main method
     * 
     * @param args arguments
     */
    public static void main(String[] args) {
        boolean quitProgram = false;
        boolean loggedIn = false;
        while (!quitProgram) {
            try {
                String line = Terminal.readLine();
                if (line.length() == 0 || line.endsWith(";")) {
                    throw new IllegalArgumentException();
                }
                String[] command = line.split(" ", 2);
                String[] params = (command.length > 1) ? command[1].split(";") : null;
                switch (command[0]) {
                case "add-admin":
                    addAdmin(loggedIn, params);
                    break;
                case "login-admin":
                    if (loggedIn == true || params == null | params.length != 2) {
                        throw new IllegalArgumentException();
                    }
                    Admin.loginAdmin(params[0], params[1]);
                    loggedIn = true;
                    break;
                case "logout-admin":
                    if (params != null || !loggedIn) {
                        throw new IllegalArgumentException();
                    }
                    loggedIn = false;
                    Terminal.printLine("OK");
                    break;
                case "add-sports-venue":
                    if (!loggedIn || params == null || params.length != 6) {
                        throw new IllegalArgumentException();
                    }
                    new Venue(params[0], params[1], params[2], params[3], params[4], Integer.parseInt(params[5]));
                    break;
                case "list-sports-venues":
                    if (!loggedIn || params == null || params.length != 1) {
                        throw new IllegalArgumentException();
                    }
                    Venue.listVenues(params[0]);
                    break;
                case "add-olympic-sport":
                    if (!loggedIn || params == null || params.length != 2) {
                        throw new IllegalArgumentException();
                    }
                    new Sport(params[0], params[1]);
                    break;
                case "list-olympic-sports":
                    if (!loggedIn || params != null) {
                        throw new IllegalArgumentException();
                    }
                    Sport.listSports();
                    break;
                case "add-ioc-code":
                    if (!loggedIn || params == null || params.length != 4) {
                        throw new IllegalArgumentException();
                    }
                    new IOC(params[0], params[1], params[2], params[3]);
                    break;
                case "list-ioc-codes":
                    if (!loggedIn || params != null) {
                        throw new IllegalArgumentException();
                    }
                    IOC.listIOC();
                    break;
                case "add-athlete":
                    if (!loggedIn || params == null || params.length != 6) {
                        throw new IllegalArgumentException();
                    }
                    new Athlete(params[0], params[1], params[2], params[3], params[4], params[5]);
                    break;
                case "summary-athletes":
                    if (!loggedIn || params == null || params.length != 1) {
                        throw new IllegalArgumentException();
                    }
                    Athlete.listAthletes(params[0]);
                    break;
                case "add-competition":
                    if (!loggedIn || params == null || params.length != 8) {
                        throw new IllegalArgumentException();
                    }
                    new Competition(params[0], Short.parseShort(params[1]), params[2], params[3], params[4], 
                            Byte.parseByte(params[5]), Byte.parseByte(params[6]), Byte.parseByte(params[7]));
                    break;
                case "olympic-medal-table":
                    if (!loggedIn || params != null) {
                        throw new IllegalArgumentException();
                    }
                    IOC.medalTable();
                    break;
                case "reset":
                    if (!loggedIn || params != null) {
                        throw new IllegalArgumentException();
                    }
                    reset();
                    break;
                case "quit":
                    if (params != null) {
                        throw new IllegalArgumentException();
                    }
                    quitProgram = true;
                    break;
                default:
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                Terminal.printError("Please enter a valid command");
            }
        }
    }
    
    private static void addAdmin(boolean loggedIn, String[] params) {
        
        try {
            
            if (loggedIn || params == null || params.length != 4) {
                throw new IllegalArgumentException();
            }
            new Admin(params[0], params[1], params[2], params[3]);
            
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter a valid command");
        }
    }
    
    private static void reset() {
        Athlete.getAthletes().clear();
        Venue.getVenues().clear();
        IOC.getCountries().clear();
        Sport.getSports().clear();
        Terminal.printLine("OK");
    }
}
