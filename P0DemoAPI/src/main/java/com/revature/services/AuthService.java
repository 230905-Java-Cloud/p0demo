package com.revature.services;

import com.revature.models.Employee;
import com.revature.models.LoginDTO;

public class AuthService {

    //in your Project, login will hopefully not be hardcoded :)
    //the DAO method will likely have a SELECT, where it looks for a user based on username/password
    //..where username = ? and password = ?
    //if nothing is returned you know it's not a valid username/password combo

    public Employee hardcodedLogin(LoginDTO lDTO){

        if(lDTO.getFirst_name().equals("Caleb") && lDTO.getLast_name().equals("Mcolin")){

            //in your project, the DAO should return the employee selected from the DB
            //in this case, I'm just instantiating it here so that we have something to return
            Employee caleb = new Employee("Caleb", "Mcolin", 1);

            return caleb;

        }

        return null;

    }


}
