package com.lea.samsung.orledorapp.Logic;

import com.lea.samsung.orledorapp.DAL.CategoryDal;
import com.lea.samsung.orledorapp.Inerfaces.ICategoryNotifier;
import com.lea.samsung.orledorapp.Inerfaces.IRecommendedActivity;
import com.lea.samsung.orledorapp.Models.Category;

import java.util.List;

/**
 * Created by USER on 11/20/2016.
 */
public class SubcategoriesLogic {
    public void getCategories(ICategoryNotifier notifier)
    {
        new CategoryDal().getCategories(notifier);
    }
}
