package com.lea.samsung.orledorapp.Models;

import com.lea.samsung.orledorapp.Inerfaces.IRecommended;

import java.util.Date;
import java.util.List;

/**
 * Created by USER on 5/6/2016.
 */
public class Multimedia extends BaseMultimedia {
    private Date _publishDate;
    private String _language;

    public Date get_publishDate() {
        return _publishDate;
    }

    public void set_publishDate(Date _publishDate) {
        this._publishDate = _publishDate;
    }

    public String get_language() {
        return _language;
    }

    public void set_language(String _language) {
        this._language = _language;
    }
}

