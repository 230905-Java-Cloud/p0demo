package com.revature.models;



//What is a DTO? DATA TRANSFER OBJECT. It's meant to model some data that doesn't pertain to a DB table
//In this case, we need to be able to transfer data for login credentials without making a whole new table
//You NEVER store a DTO in the database

/*Two main DTO use cases:

1) When you don't want to send or use an entire object (we just need username/password to login)
2) When you don't intend to store the incoming data in the DB (meant only for Java logic) */
public class LoginDTO {

    //variables to validate login (we'll use firstname/lastname, but YOU should use username/password)
    private String first_name;
    private String last_name;

    //realistically, we only need an all args constructor and getters/setters

    //right click -> generate
    public LoginDTO(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

}
