package com.lea.samsung.orledorapp.Models;

import com.lea.samsung.orledorapp.Inerfaces.IRecommended;

import java.util.Date;
import java.util.List;

/**
 * Created by USER on 5/6/2016.
 */
public class Multimedia extends BaseMultimedia {
    private Date _publishDate;
    private List<String> _publishedCountry;

    public Date get_publishDate() {
        return _publishDate;
    }

    public void set_publishDate(Date _publishDate) {
        this._publishDate = _publishDate;
    }

    public List<String> get_publishedCountry() {
        return _publishedCountry;
    }

    public void set_publishedCountry(List<String> _publishedCountry) {
        this._publishedCountry = _publishedCountry;
    }
}

