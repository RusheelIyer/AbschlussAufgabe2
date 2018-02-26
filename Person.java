package edu.kit.informatik;

public abstract class Person {
    
    private String firstName;
    private String lastName;
    
    /**
     * Constructor for a Person object
     * 
     * @param firstName represents the first name of the person
     * @param lastName represents the last name of the person
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    /**
     * Getter to get the first name of the person
     * 
     * @return the first name
     */
    public String getFirstName() {
        return this.firstName;
    }
    
    /**
     * Getter to get the last name of the person
     * 
     * @return the last name
     */
    public String getLastName() {
        return this.lastName;
    }

}
