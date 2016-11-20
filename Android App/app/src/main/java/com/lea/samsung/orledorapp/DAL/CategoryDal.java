package com.lea.samsung.orledorapp.DAL;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.lea.samsung.orledorapp.Inerfaces.ICategoryNotifier;
import com.lea.samsung.orledorapp.Models.Category;
import com.lea.samsung.orledorapp.Models.Multimedia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 11/20/2016.
 */
public class CategoryDal extends BaseDal {
    private Firebase _categoriesCollection = super.DB.child("categories");

    public void getCategories(final ICategoryNotifier notifier)
    {
        _categoriesCollection.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Category> categories = new ArrayList();
                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()) {
                    categories.add(categorySnapshot.getValue(Category.class));
                }

                notifier.onCategoriesLoaded(categories);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
