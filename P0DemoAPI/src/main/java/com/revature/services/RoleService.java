package com.revature.services;

import com.revature.daos.RoleDAO;

public class RoleService {

    //instantiate a RoleDAO to use its methods
    RoleDAO rDAO = new RoleDAO();
    public int updateRoleSalary(String title, int newSalary){

        //check that the role title isn't null/empty
        if(title.equals("") || title == null){
            return 0;
        }

        //check that the new salary is a positive number
        if(newSalary < 0){
            return 0;
        }

        //this should return an int representing the new salary
        return rDAO.updateRoleSalary(title, newSalary);

    }

}
