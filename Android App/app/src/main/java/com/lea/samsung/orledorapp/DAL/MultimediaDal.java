package com.lea.samsung.orledorapp.DAL;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.lea.samsung.orledorapp.Inerfaces.IMultimediaAction;
import com.lea.samsung.orledorapp.Logger.Logger;
import com.lea.samsung.orledorapp.Models.Multimedia;
import com.lea.samsung.orledorapp.Models.MultimediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 5/6/2016.
 */
public class MultimediaDal extends BaseDal {
    private Firebase _multimediaCollection = super.DB.child("multimedia");

    public void GetAllMultimedia(final IMultimediaAction multimediaAction) {
        GetAllMultimedia(multimediaAction, null);
    }

    public void GetAllMultimedia(final IMultimediaAction multimediaAction, final MultimediaType type) {
        _multimediaCollection.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Multimedia> multimedia = new ArrayList();
                for (DataSnapshot songSnapshot : dataSnapshot.getChildren()) {
                    Multimedia tempMultimedia = songSnapshot.getValue(Multimedia.class);
                    if (type == null || tempMultimedia.get_type() == type) {
                        multimedia.add(tempMultimedia);
                    }
                }
                if (multimedia.isEmpty()) {
                    multimediaAction.noMatchFound();
                } else {
                    multimediaAction.onMultimediaLoaded(multimedia,type);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                multimediaAction.noMatchFound();
            }
        });
    }

    public Boolean AddMultimedia(Multimedia s) {
        try {
            _multimediaCollection.child(s.get_name()).setValue(s);
            return true;
        }
        catch (Exception ex) {
            Logger.LogErrorWithException("Failed to save multimedia", ex);
            return false;
        }
    }
}
