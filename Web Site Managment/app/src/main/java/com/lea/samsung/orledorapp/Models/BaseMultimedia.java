package com.lea.samsung.orledorapp.Models;

import com.lea.samsung.orledorapp.Inerfaces.IRecommended;

/**
 * Created by Samsung on 15/05/2016.
 */
public class BaseMultimedia  implements IRecommended {
    private String _name;
    private String _link;

    public MultimediaType get_type() {
        return _type;
    }

    public void set_type(MultimediaType _type) {
        this._type = _type;
    }

    private MultimediaType _type;

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_link() {
        return _link;
    }

    public void set_link(String _link) {
        this._link = _link;
    }


    @Override
    public String toString() {
        return get_name();
    }
}
