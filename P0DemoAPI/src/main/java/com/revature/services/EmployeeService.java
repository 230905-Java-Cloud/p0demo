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

    public Employee insertEmployee(Employee employee) throws IllegalArgumentException{

        //we can run some checks on the incoming employee to make sure it's valid

        //check that the first name and last name come in
        if(employee.getFirst_name() == null || employee.getLast_name() == null){
            throw new IllegalArgumentException("Employee names must not be null!");
            //if the first or last name aren't in, return null, which will trigger a 400 in the controller
        }

        //check that there are no vulgarities in the names
        if(employee.getFirst_name().equals("Javascript")){
            throw new IllegalArgumentException("Employee names must not be vulgar!");
        }

        //if the above checks pass, we can insert the new Employee!
        return eDAO.insertEmployee(employee);

    }



    /* Hypothetical Method checkUsernameisvalid() {

        //check username is long enough

        //check username is unique

        //check username contains no vulgarity

        //throw an exception if any of these checks fail

    } */

}
