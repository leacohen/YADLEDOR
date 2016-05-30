package com.lea.samsung.orledorapp.Common;

import com.lea.samsung.orledorapp.Models.User;

/**
 * Created by USER on 5/6/2016.
 */
public class UserContext {
    public static User LoggedUser;

    public static User getLoggedUser() {
        return LoggedUser;
    }

    public static void setLoggedUser(User loggedUser) {
        LoggedUser = loggedUser;
    }
}
