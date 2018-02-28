package edu.kit.informatik;

import java.util.ArrayList;

public class Admin extends Person {
    
    private static ArrayList<Admin> admins = new ArrayList<Admin>();
    private String username;
    private String password;
    
    /**
     * Constructor for an admin
     * 
     * @param firstName represents the first name of the admin to be added
     * @param lastName represents the last name of the admin to be added
     * @param username represents the username of the admin to be added
     * @param password represents the password of the admin to be added
     */
    public Admin(String firstName, String lastName, String username, String password) {
        
        super(firstName, lastName);
        
        try {
            
            if (username.length() >= 4 && username.length() <= 8) {
                for (int i = 0; i < admins.size(); i++) {
                    if (admins.get(i).username.equals(username)) {
                        throw new IllegalArgumentException();
                    }
                    this.username = username;
                }
            } else {
                throw new IllegalArgumentException();
            }
            if (password.length() >= 8 && username.length() <= 12) {
                this.password = password;   
            } else {
                throw new IllegalArgumentException();
            }
            
            admins.add(this);
            Terminal.printLine("OK");
            
        } catch (IllegalArgumentException e) {
            Terminal.printError("Please enter a valid username and password");
        }
        
    }

    /**
     * get the list of creaed admins
     * 
     * @return the list of admins
     */
    public static ArrayList<Admin> getAdmins() {
        return admins;
    }
    
    /**
     * Method to login the admin
     * 
     * @param username represents the username of the admin attempting to login
     * @param password represents the password of the admin attempting to login
     * @return the admin with the corresponding username and password
     */
    public Admin loginAdmin(String username, String password) {
        Admin admin = null;
        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).username.equals(username) && admins.get(i).equals(password)) {
                admin = admins.get(i);
                break;
            }
        }
        return admin;
    }
    
    /**
     * Getter method for the admin's username
     * 
     * @return the admin's username
     */
    public String getUsername() {
        return this.username;
    }
    
    /**
     * Getter method for the admin's password
     * 
     * @return the admin's password
     */
    public String getPassword() {
        return this.password;
    }

}
