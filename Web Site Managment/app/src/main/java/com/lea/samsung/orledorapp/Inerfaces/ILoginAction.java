package com.lea.samsung.orledorapp.Inerfaces;

import com.lea.samsung.orledorapp.Models.User;

/**
 * Created by USER on 5/6/2016.
 */
public interface ILoginAction {
    void UserLoggedIn(User user);
    void UserNotFound();
}
