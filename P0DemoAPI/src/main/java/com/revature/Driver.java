package com.revature;

import com.revature.daos.EmployeeDAO;
import com.revature.daos.RoleDAO;
import com.revature.models.Employee;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Driver {

    //shortcut to making main method: type "main" -> hit enter
    public static void main(String[] args) {


        try(Connection conn = ConnectionUtil.getConnection()){
            System.out.println("connection successful :)");
            //shortcut for making print statements: type "sout" -> hit enter
        } catch(SQLException e) {
            e.printStackTrace(); //method from Exception Class that tell us what's wrong in the console
        }


        //testing our DAO methods in main until Friday (when we talk Javalin)

        //instantiate DAOs so we can use their methods
        RoleDAO rDAO = new RoleDAO();
        EmployeeDAO eDAO = new EmployeeDAO();

        //instantiating and inserting a new Employee
        Employee newEmployee = new Employee("Akash", "Kumar", 1);

        eDAO.insertEmployee(newEmployee);

        //remember, we need to print it out, since the DAO method returns a Role object
        System.out.println(rDAO.getRoleById(4));

        //now, lets print out get all employees!
        //lets use an enhanced for loop
        ArrayList<Employee> employees = eDAO.getAllEmployees();

        //"for every Employee object, which we'll call 'e', in the employees ArrayList..."
        for(Employee e : employees){
            System.out.println(e); //print out the employee!
        }

    }

}
