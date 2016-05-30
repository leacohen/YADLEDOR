package com.lea.samsung.orledorapp.Models;

import java.util.Date;

/**
 * Created by USER on 5/7/2016.
 */
public class Log {
    private Date _logTime;
    private String _logContent;
    private Exception _exception;

    public Date get_logTime() {
        return _logTime;
    }

    public void set_logTime(Date _logTime) {
        this._logTime = _logTime;
    }

    public String get_logContent() {
        return _logContent;
    }

    public void set_logContent(String _logContent) {
        this._logContent = _logContent;
    }

    public Exception get_exception() {
        return _exception;
    }

    public void set_exception(Exception _exception) {
        this._exception = _exception;
    }
}
