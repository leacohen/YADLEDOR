package com.lea.samsung.orledorapp.Inerfaces;

import java.util.List;

/**
 * Created by USER on 5/8/2016.
 */
public interface IRecommendedActivity {
    void NothingToShow();

    void RecommencedListLoaded(List<? extends IRecommended> list);
}
