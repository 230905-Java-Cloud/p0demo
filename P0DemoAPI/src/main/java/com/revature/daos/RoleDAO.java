package com.revature.daos;

import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//This Class is responsible for all things ROLE DATA. (RoleDAO == Role Data Access Object)
//This Class will have methods that query or update the roles table in the DB.
public class RoleDAO {

    //This method will take in an int for int, and return the Role with that ID
    public Role getRoleById(int id){

        //the first thing we need in a DAO method is to ESTABLISH A DB CONNECTION
        try(Connection conn = ConnectionUtil.getConnection()){

            //We need a String that holds the SQL statement we want to run
            //This String has a wildcard/placeholder that we must inject the user-inputted id into
            String sql = "SELECT * FROM roles WHERE role_id = ?";

            //we need a PreparedStatement object to fill in the placeholder (fill in the ?)
            //PreparedStatements "prepare" a sql command to be sent to the DB
            PreparedStatement ps = conn.prepareStatement(sql);

            //here, we will insert a value for the ? in the sql query
            ps.setInt(1, id);
            //"Take the 1st wildcard, and inject the id value given in the method params"

            //Finally, we can execute the SQL statement and save the value in a ResultSet object
            ResultSet rs = ps.executeQuery();

            //we must call the next() method of ResultSet to access the incoming data
            //next() returns true if there is a record of data that hasn't been accessed yet
            if(rs.next()) {

                //Extract the Role data from the ResultSet.
                //We need to use the all-args constructor of Role
                Role r = new Role(
                        rs.getInt("role_id"),
                        rs.getString("role_title"),
                        rs.getInt("role_salary")
                );
                //return the Role!
                return r;
            }

        } catch(SQLException e){
            System.out.println("ERROR GETTING ROLE");
            e.printStackTrace(); //the actual error/exception message
        }

        return null; //if something goes wrong, null will get returned instead of a Role object

    }

    public int updateRoleSalary(String title, int newSalary){

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "UPDATE roles SET role_salary = ? WHERE role_title = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, newSalary);
            ps.setString(2, title);

            ps.executeUpdate();

            return newSalary; //return the user-inputted salary if update works appropriately

        } catch(SQLException e){
            e.printStackTrace();
        }

        return 0; //return null only works if there is an OBJECT return type

    }

}
