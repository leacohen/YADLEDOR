package com.lea.samsung.orledorapp.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 5/6/2016.
 */
public class User {
    private String _userName;
    private String _password;
    private String _firstName;
    private String _lastName;
    private Date _birthDate;
    private List<String> _language;
    private List<BaseMultimedia> _userMultimedia;

    public User() {
        _userMultimedia = new ArrayList();
    }

    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _passwordl) {
        this._password = _passwordl;
    }

    public String get_firstName() {
        return _firstName;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String get_lastName() {
        return _lastName;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }

    public Date get_birthDate() {
        return _birthDate;
    }

    public void set_birthDate(Date _birthDate) {
        this._birthDate = _birthDate;
    }

    public List<String> get_language() {
        return _language;
    }

    public void set_language(List<String> _language) {
        this._language = _language;
    }

    public List<BaseMultimedia> get_userMultimedia() {
        return _userMultimedia;
    }

    public void set_userMultimedia(List<BaseMultimedia> _userMultimedia) {
        this._userMultimedia = _userMultimedia;
    }
}
