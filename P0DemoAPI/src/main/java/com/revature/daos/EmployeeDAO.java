package com.revature.daos;

import com.revature.models.Employee;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {

    public ArrayList<Employee> getAllEmployees(){

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM employees";

            //notice there are no variables in the SQL statement.
            //So we'll use Statement instead of PreparedStatement. Nothing for us to prepare!
            Statement s = conn.createStatement();

            //Execute our query into a ResultSet
            ResultSet rs = s.executeQuery(sql);

            //Instantiate an empty ArrayList to store our incoming employee data
            ArrayList<Employee> employees = new ArrayList<>();

            //Instantiate a RoleDAO so we can use the getRoleById method
            RoleDAO rDAO = new RoleDAO();

            //"while there are records we haven't accessed, keep looping"
            //this while loop will fill our ArrayList one by one (record by record)
            while(rs.next()){

                //We need to make an Employee object
                Employee e = new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        //we need to retrieve a Role by its id (since employee data only has the FK)
                        //if only we had a method that could get role by id!! oh wait....
                        rDAO.getRoleById(rs.getInt("role_id_fk"))
                );

                //add the new employee to the ArrayList!
                employees.add(e);

            } //end of while loop

            //when the while loop breaks, we should have a fully populated ArrayList we can return!
            return employees;

        } catch(SQLException e){
            System.out.println("GET ALL EMPLOYEES FAILED");
            e.printStackTrace();
        }
        return null;
    }

    public Employee insertEmployee(Employee employee){

        //Remember, every DAO method must start with a try/catch and a connection being made
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "INSERT INTO employees (first_name, last_name, role_id_fk) VALUES (?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, employee.getFirst_name());
            ps.setString(2, employee.getLast_name());
            ps.setInt(3, employee.getRole_id_fk());

            //now that our statement variables are filled with values, we execute the UPDATE (not query)
            //executeQuery is for selects, executeUpdate is for insert/update/delete
            ps.executeUpdate();

            return employee;

        } catch(SQLException e){
            e.printStackTrace();
        }

        return null; //if something goes wrong, return null

    }

}
