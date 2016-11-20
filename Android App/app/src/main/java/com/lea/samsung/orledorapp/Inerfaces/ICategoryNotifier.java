package com.lea.samsung.orledorapp.Inerfaces;

import com.lea.samsung.orledorapp.Models.Category;

import java.util.List;

/**
 * Created by USER on 11/20/2016.
 */
public interface ICategoryNotifier {
    void onCategoriesLoaded(List<Category> categories);
}
