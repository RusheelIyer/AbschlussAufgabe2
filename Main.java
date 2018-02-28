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
                    loggedIn = loginAdmin(loggedIn, params);
                    break;
                case "logout-admin":
                    loggedIn = logout(loggedIn, params);
                    break;
                case "add-sports-venue":
                    newSportsVenue(loggedIn, params);
                    break;
                case "list-sports-venues":
                    listSportsVenues(loggedIn, params);
                    break;
                case "add-olympic-sport":
                    newSport(loggedIn, params);
                    break;
                case "list-olympic-sports":
                    printSports(loggedIn, params);
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
    
    private static boolean loginAdmin(boolean loggedIn, String[] params) {
        try {
            if (loggedIn == true || params == null | params.length != 2) {
                throw new IllegalArgumentException();
            }
            Admin.loginAdmin(params[0], params[1]);
            return true;
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter a valid command");
            return false;
        }
    }
    
    private static boolean logout(boolean loggedIn, String[] params) {
        try {
            if (params != null || !loggedIn) {
                throw new IllegalArgumentException();
            }
            Terminal.printLine("OK");
            return false;
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter a valid command and make sure no other admin is logged in");
            return true;
        }
    }
    
    private static void newSportsVenue(boolean loggedIn, String[] params) {
        try {
            if (!loggedIn || params == null || params.length != 6) {
                throw new IllegalArgumentException();
            }
            new Venue(params[0], params[1], params[2], params[3], params[4], Integer.parseInt(params[5]));
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter a valid command");
        }
    }
    
    private static void listSportsVenues(boolean loggedIn, String[] params) {
        try {
            if (!loggedIn || params == null || params.length != 1) {
                throw new IllegalArgumentException();
            }
            Venue.listVenues(params[0]);
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter a valid command");
        }
    }
    
    private static void newSport(boolean loggedIn, String[] params) {
        try {
            if (!loggedIn || params == null || params.length != 2) {
                throw new IllegalArgumentException();
            }
            new Sport(params[0], params[1]);
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter a valid command");
        }
    }
    
    private static void printSports(boolean loggedIn, String[] params) {
        try {
            if (!loggedIn || params != null) {
                throw new IllegalArgumentException();
            }
            Sport.listSports();
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
