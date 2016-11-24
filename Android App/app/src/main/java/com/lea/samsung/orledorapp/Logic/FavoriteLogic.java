package com.lea.samsung.orledorapp.Logic;

import com.lea.samsung.orledorapp.Common.UserContext;
import com.lea.samsung.orledorapp.DAL.MultimediaDal;
import com.lea.samsung.orledorapp.DAL.UserDal;
import com.lea.samsung.orledorapp.Inerfaces.IMultimediaAction;
import com.lea.samsung.orledorapp.Inerfaces.IRecommendedActivity;
import com.lea.samsung.orledorapp.Models.BaseMultimedia;
import com.lea.samsung.orledorapp.Models.Multimedia;
import com.lea.samsung.orledorapp.Models.MultimediaType;
import com.lea.samsung.orledorapp.Models.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Samsung on 15/05/2016.
 */
public class FavoriteLogic implements IMultimediaAction {
    private IRecommendedActivity activity;

    public boolean AddLink(BaseMultimedia multimedia) {
        UserContext.getLoggedUser().get_userMultimedia().add(multimedia);
        return new UserDal().SaveUser(UserContext.getLoggedUser());
    }

    public void LoadFavoriteMedia(IRecommendedActivity multimediaAction) {
        activity = multimediaAction;

        new MultimediaDal().GetAllMultimedia(this);
    }

    @Override
    public void onMultimediaLoaded(List<Multimedia> multimedias, MultimediaType type) {
        LinkedList<Multimedia> newMedias = new LinkedList<>();

        HashMap userLikes = UserContext.getLoggedUser().getLikes();

        for (Multimedia media : multimedias) {
            if (userLikes.containsKey(media.get_name())&&
                    userLikes.get(media.get_name()) == 1) {
                newMedias.add(media);
            }
        }

        activity.RecommencedListLoaded(newMedias);
    }

    @Override
    public void noMatchFound() {
        activity.NothingToShow();
    }
}
