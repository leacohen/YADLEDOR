package com.lea.samsung.orledorapp.DAL;

import com.firebase.client.Firebase;

/**
 * Created by USER on 5/6/2016.
 */
public class BaseDal {
    protected Firebase DB;

    public BaseDal() {
        // TODO: get the url from config file
        DB = new Firebase("rhttps://fiery-fire-8931.firebaseio.com/");
    }
}