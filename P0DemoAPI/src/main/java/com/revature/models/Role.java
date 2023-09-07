package com.revature.models;

public class Role {

    private int role_id;
    private String role_title;
    private int role_salary;

    //BOILERPLATE CODE--------------------------------------------------

    //right click -> generate -> constructor -> select none
    /*NO ARGS CONSTRUCTOR
    In case we need to instantiate a Role object with no values, we can use this.
    This is assuming we would fill these values later */
    public Role() {
    }

    /*ALL ARGS CONSTRUCTOR
    If we know the values going into our new Role object, we can use this all args constructor*/
    public Role(int role_id, String role_title, int role_salary) {
        this.role_id = role_id;
        this.role_title = role_title;
        this.role_salary = role_salary;
    }

    //right click -> generate -> getter and setter -> choose all of them and create!
    /*GETTERS AND SETTERS
    These, along with private variables, accomplish ENCAPSULATION.
    We don't want our variables to be accessed or changed by accident or malice
    We can build some security into our apps by only allowing views/changes via GETTERS and SETTERS */
    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_title() {
        return role_title;
    }

    public void setRole_title(String role_title) {
        this.role_title = role_title;
    }

    public int getRole_salary() {
        return role_salary;
    }

    public void setRole_salary(int role_salary) {
        this.role_salary = role_salary;
    }

    //right click -> generate -> toString
    //the toString method lets us print out Role objects with their data instead of a memory address
    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", role_title='" + role_title + '\'' +
                ", role_salary=" + role_salary +
                '}';
    }
}
