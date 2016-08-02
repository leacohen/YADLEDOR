package com.lea.samsung.orledorapp.Logic;

import com.lea.samsung.orledorapp.Common.UserContext;
import com.lea.samsung.orledorapp.DAL.UserDal;
import com.lea.samsung.orledorapp.Inerfaces.ILoginAction;
import com.lea.samsung.orledorapp.Activities.Login.LoginActivity;
import com.lea.samsung.orledorapp.Models.User;

/**
 * Created by USER on 5/6/2016.
 */
public class LoginLogic implements ILoginAction {
    private LoginActivity _loginActivity;

    public LoginLogic(LoginActivity activity) {
        _loginActivity = activity;
    }

    public void Login(String userName, String password)
    {
        new UserDal().GetUser(userName, password, this);
    }

    @Override
    public void UserLoggedIn(User user) {
        UserContext.setLoggedUser(user);
        _loginActivity.onLoginSuccsed();
    }

    @Override
    public void UserNotFound() {
        UserContext.setLoggedUser(null);
        _loginActivity.onLoginFailed();
    }
}
