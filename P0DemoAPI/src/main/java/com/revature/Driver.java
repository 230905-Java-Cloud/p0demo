package com.revature;

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

    }

}
