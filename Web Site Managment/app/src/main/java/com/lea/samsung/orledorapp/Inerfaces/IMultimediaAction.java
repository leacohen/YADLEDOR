package com.lea.samsung.orledorapp.Inerfaces;

import com.lea.samsung.orledorapp.Models.Multimedia;
import com.lea.samsung.orledorapp.Models.MultimediaType;

import java.util.List;

/**
 * Created by USER on 5/6/2016.
 */
public interface IMultimediaAction {
    void onMultimediaLoaded(List<Multimedia> multimedias, MultimediaType type);
    void noMatchFound();
}
