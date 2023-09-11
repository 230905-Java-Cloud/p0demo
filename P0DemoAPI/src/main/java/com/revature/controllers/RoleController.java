package com.revature.controllers;

import com.revature.services.RoleService;
import io.javalin.http.Handler;

public class RoleController {

    //Instantiate a RoleService to use its method
    RoleService rs = new RoleService();

    public Handler updateRoleSalaryHandler = (ctx) -> {

        //take the incoming request body (which should just be a number), and turn it into an int
        //...because ctx.body() returns a String, since it's expecting JSON
        int newSalary = Integer.parseInt(ctx.body());

        //extract the title from the HTTP Request's path param
        String role_title = ctx.pathParam("title");

        if(rs.updateRoleSalary(role_title, newSalary) > 0){
            ctx.status(202); //202 - ACCEPTED
            ctx.result("NEW SALARY: " + newSalary);
        } else{
            ctx.status(406); //406 - NOT ACCEPTABLE
            ctx.result("role salary was not updated!");
        }

    };

}
