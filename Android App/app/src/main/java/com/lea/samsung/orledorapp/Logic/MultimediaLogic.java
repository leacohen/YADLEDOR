package com.lea.samsung.orledorapp.Logic;

import com.lea.samsung.orledorapp.Common.UserContext;
import com.lea.samsung.orledorapp.DAL.MultimediaDal;
import com.lea.samsung.orledorapp.Inerfaces.IRecommendedActivity;
import com.lea.samsung.orledorapp.Inerfaces.IRecommended;
import com.lea.samsung.orledorapp.Inerfaces.IMultimediaAction;
import com.lea.samsung.orledorapp.Models.BaseMultimedia;
import com.lea.samsung.orledorapp.Models.Multimedia;
import com.lea.samsung.orledorapp.Models.MultimediaType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 5/6/2016.
 */
public class MultimediaLogic implements IMultimediaAction {

    private IRecommendedActivity _irecommendedActivity;

    public MultimediaLogic(IRecommendedActivity activity) {
        _irecommendedActivity = activity;
    }

    public void LoadAllMultimedia()  {
        new MultimediaDal().GetAllMultimedia(this);
    }

    public void LoadSpecificMultimedia(MultimediaType type) {
        new MultimediaDal().GetAllMultimedia(this, type);
    }

    @Override
    public void onMultimediaLoaded(List<Multimedia> multimedias, MultimediaType type) {
        if (UserContext.getLoggedUser() == null) {
            _irecommendedActivity.NothingToShow();
            return;
        }

        List<IRecommended> finalMultimedia = new ArrayList();
        List<String> userLanguages = UserContext.getLoggedUser().get_language();
        Date userBirthday = UserContext.getLoggedUser().get_birthDate();
        Date startDateFilter = new Date(userBirthday.getYear() + 10, 1, 1);
        Date endDateFilter = new Date(userBirthday.getYear() + 30, 1, 1);

        for (BaseMultimedia userMultimedia : UserContext.getLoggedUser().get_userMultimedia()) {
            if (type == null || userMultimedia.get_type() == type) {
                finalMultimedia.add(userMultimedia);
            }
        }

        for (Multimedia currentMultimedia : multimedias) {
            if(currentMultimedia.get_type() == MultimediaType.Movie || currentMultimedia.get_type() == MultimediaType.Song) {
                if(userLanguages.contains(currentMultimedia.get_language())
                        && currentMultimedia.get_publishDate().after(startDateFilter)
                        && currentMultimedia.get_publishDate().before(endDateFilter))
                {
                    finalMultimedia.add(currentMultimedia);
                }
            }
            else if (type != null){
                finalMultimedia.add(currentMultimedia);
            }
        }

        // TODO: Order the final multimedia correctly

        _irecommendedActivity.RecommencedListLoaded(finalMultimedia);
    }

    @Override
    public void noMatchFound() {
        _irecommendedActivity.NothingToShow();
    }
}
