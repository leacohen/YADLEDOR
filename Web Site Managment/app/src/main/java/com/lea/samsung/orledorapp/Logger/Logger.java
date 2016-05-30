package com.lea.samsung.orledorapp.Logger;

import com.lea.samsung.orledorapp.DAL.LogDal;
import com.lea.samsung.orledorapp.Models.Log;

import java.util.Date;

/**
 * Created by USER on 5/7/2016.
 */
public class Logger {
    public static void LogError(String error) {
        LogErrorWithException(error, null);
    }

    public static void LogException(Exception ex) {
        LogErrorWithException(ex.getMessage(),ex);
    }

    public static void LogErrorWithException(String error, Exception ex) {
        Log log = new Log();
        log.set_exception(ex);
        log.set_logContent(error);
        log.set_logTime(new Date());
        new LogDal().WriteLog(log);
    }
}
