package com.lea.samsung.orledorapp.DAL;

import com.firebase.client.Firebase;
import com.lea.samsung.orledorapp.Models.Log;

/**
 * Created by USER on 5/7/2016.
 */
public class LogDal extends BaseDal{
    private Firebase _logsCollection = super.DB.child("logs");

    public void WriteLog(Log log)
    {
        try {
            _logsCollection.push().setValue(log);
        }
        catch (Exception ex) {
            // Nothing to do if the write to log failed.. :(
        }
    }
}
