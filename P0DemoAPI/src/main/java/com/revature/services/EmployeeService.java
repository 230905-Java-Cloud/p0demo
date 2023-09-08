package com.revature.services;

import com.revature.daos.EmployeeDAO;
import com.revature.models.Employee;

import java.util.ArrayList;

//The Service Layer is typically where any extra processing happens
//Data may need to be validated, formatted, or otherwise changed before hitting the DAO or Controller
public class EmployeeService {

    //We need an EmployeeDAO to access its methods
    EmployeeDAO eDAO = new EmployeeDAO();

    //In general, "get all" methods are very simple in the service. Not much to process
    //This is the bare minimum that a service does - basically a bridge between controller/DAO
    public ArrayList<Employee> getAllEmployees(){
        return eDAO.getAllEmployees();
    }




    /* Hypothetical Method checkUsernameisvalid() {

        //check username is long enough

        //check username is unique

        //check username contains no vulgarity

        //throw an exception if any of these checks fail

    } */

}
