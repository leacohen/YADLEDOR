package com.lea.samsung.orledorapp.Logic;

import com.lea.samsung.orledorapp.Common.UserContext;
import com.lea.samsung.orledorapp.DAL.UserDal;
import com.lea.samsung.orledorapp.Models.User;

import java.util.Date;

/**
 * Created by USER on 5/7/2016.
 */
public class SignupLogic {
    public boolean SignUp(String userName,String password, String firstName, String lastName, Date birthDate, String country)
    {
        User newUser = new User();
        newUser.set_userName(userName);
        newUser.set_password(password);
        newUser.set_firstName(firstName);
        newUser.set_lastName(lastName);
        newUser.set_birthDate(birthDate);
        newUser.set_country(country);

        Boolean isAddSucceed = new UserDal().SaveUser(newUser);

        if(isAddSucceed) {
            UserContext.setLoggedUser(newUser);
        }

        return isAddSucceed;
    }
}
