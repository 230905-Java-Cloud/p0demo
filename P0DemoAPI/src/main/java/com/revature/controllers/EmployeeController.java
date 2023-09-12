package com.revature.controllers;


import com.google.gson.Gson;
import com.revature.models.Employee;
import com.revature.services.EmployeeService;
import io.javalin.http.Handler;

import java.util.ArrayList;

//The Controller layer is where HTTP Requests get routed to (after Javalin directs them)
//In this layer, JSON comes in and gets convert to Java, or vice versa
//We will either be getting Java from the Service to turn into JSON, or JSON from HTTP Requests to turn into Java
public class EmployeeController {

    //Instantiate an EmployeeService to access its methods
    EmployeeService es = new EmployeeService();

    public Handler getEmployeesHandler = (ctx) -> {

    //what's ctx? the context object! It contains methods that help us process HTTP Requests/Responses

    //If the HttpSession is not null (which means the user has logged in), let them get all employees
    //Otherwise, tell them to log in!
    if(AuthController.ses != null) {

        //I want an ArrayList of Employee objects courtesy of the EmployeeService
        ArrayList<Employee> employees = es.getAllEmployees();

        //GSON is a Class that helps us make JSON <-> Java conversions... less verbose than jackson!
        Gson gson = new Gson();

        /* GSON METHODS:

        toJson() - turns Java into JSON
        fromJson() - turns JSON into Java */

        //Make sure the JSON is stored in a String type
        String JSONEmployees = gson.toJson(employees);

        //we can use ctx to set the status code, and return the HTTP Response
        ctx.status(200); //200 - OK
        ctx.result(JSONEmployees); //the body of the response

    } else {
        ctx.status(401); //401 UNAUTHORIZED
        ctx.result("You must log in to view all employees");
    }

    }; //lambdas with logic in curly braces need a semicolon

    public Handler insertEmployeeHandler = (ctx) -> {

        //with POST requests, we have JSON coming in the request body, we can access it with ctx.body()
        String body = ctx.body();

        //now, we use GSON again! We'll use it to convert the JSON String into an Employee object

        Gson gson = new Gson();

        //"Take the incoming JSON and turn it into an object of the Employee class"
        Employee newEmployee = gson.fromJson(body, Employee.class);

        //if the service returns not null, it was successful, return a success status code
        //if the service returns null, send a failure status code

        System.out.println(newEmployee);

       try {
           //Full employee from the DB (Tom is a Java God)
           Employee returnedEmp = es.insertEmployee(newEmployee);

           System.out.println(returnedEmp);

           String JSONEmployee = gson.toJson(returnedEmp);

           ctx.status(201); //201 - CREATED
           ctx.result(JSONEmployee); //send the newly stringified Employee back to the user
       } catch(IllegalArgumentException e){
            ctx.status(406); //406 - NOT ACCEPTABLE
            ctx.result(e.getMessage());
        }


    };

}
