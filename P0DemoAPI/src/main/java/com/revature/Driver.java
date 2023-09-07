package com.revature;

import com.revature.daos.RoleDAO;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

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

        //instantiate a RoleDAO object so we can use its methods
        RoleDAO rDAO = new RoleDAO();

        //remember, we need to print it out, since the DAO method returns a Role object
        System.out.println(rDAO.getRoleById(4));



    }

}
