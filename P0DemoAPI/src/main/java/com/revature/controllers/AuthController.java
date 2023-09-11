package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.Employee;
import com.revature.models.LoginDTO;
import com.revature.services.AuthService;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;



public class AuthController {

    //Instantiate an AuthService to use its method
    AuthService as = new AuthService();

    //uninitialized HttpSession object that will be filled upon successful login
    public static HttpSession ses;

    //login will be a POST request, since we're sending the login credentials in the request body
    public Handler loginHandler = (ctx) -> {

        //gather the login credentials with ctx.body()
        String body = ctx.body();

        //for Java <-> JSON conversions
        Gson gson = new Gson();

        //take the incoming body data, and translate it from JSON into Java
        LoginDTO lDTO = gson.fromJson(body, LoginDTO.class);

        //If the login is successful, this Employee object will be populated, otherwise null
        Employee loggedInEmployee = as.hardcodedLogin(lDTO);

        if(loggedInEmployee != null){

            //If login is successful, establish a new session for the user
            ses = ctx.req().getSession();

            //we can use setAttribute() to set certain values to certain keys in our session attributes
            //THIS IS HOW WE CAN STORE USER-SPECIFIC DATA WITHIN A SESSION
            ses.setAttribute("employee_id", loggedInEmployee.getEmployee_id());
            ses.setAttribute("first_name", loggedInEmployee.getFirst_name());

            //ok... so, how can we retrieve these session attributes? getAttribute()!
            ses.getAttribute("employee_id");

            //if you wanted to have logout functionality, and kill the session, you could use invalidate()
            //ses.invalidate();

            ctx.status(200); //200 - OK
            ctx.result("Welcome, " + ses.getAttribute("first_name"));

        } else {
            ctx.status(401); //401 - UNAUTHORIZED
            ctx.result("INVALID CREDENTIALS!");
        }

    };

}
