package com.revature;

import com.revature.controllers.EmployeeController;
import com.revature.utils.ConnectionUtil;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.SQLException;

public class Driver {

    //shortcut to making main method: type "main" -> hit enter
    public static void main(String[] args) {

        //DB CONNECTION TEST---
        try(Connection conn = ConnectionUtil.getConnection()){
            System.out.println("connection successful :)");
            //shortcut for making print statements: type "sout" -> hit enter
        } catch(SQLException e) {
            e.printStackTrace(); //method from Exception Class that tell us what's wrong in the console
        }

        //JAVALIN CONFIG---

        //instantiate a Java object, and set it to listen on port 8080
        Javalin app = Javalin.create().start(8080);


        //Javalin Endpoint Handlers - this is where we specify the different endpoints of our API

        //instantiate controllers so we can access their handlers
        EmployeeController ec = new EmployeeController();


        app.get("/employees", ec.getEmployeesHandler);

        app.post("/employees", ec.insertEmployeeHandler);











        /*
        //testing our DAO methods in main until Friday (when we talk Javalin)

        //instantiate DAOs so we can use their methods
        RoleDAO rDAO = new RoleDAO();
        EmployeeDAO eDAO = new EmployeeDAO();

        //instantiating and inserting a new Employee--------------------------------------
        Employee newEmployee = new Employee("Akash", "Kumar", 1);

        //eDAO.insertEmployee(newEmployee);

        //updating role salary--------------------------------------------------

        rDAO.updateRoleSalary("Fry Cook", 100000);

        //deleting the extra Akash (employee id 6)---------------------------------

        eDAO.deleteEmployeeById(6);

        //remember, we need to print it out, since the DAO method returns a Role object----------------------
        System.out.println(rDAO.getRoleById(3));

        //now, lets print out get all employees!--------------------------------------------------
        //lets use an enhanced for loop
        ArrayList<Employee> employees = eDAO.getAllEmployees();

        //"for every Employee object, which we'll call 'e', in the employees ArrayList..."
        for(Employee e : employees){
            System.out.println(e); //print out the employee!
        }

         */

    }

}
