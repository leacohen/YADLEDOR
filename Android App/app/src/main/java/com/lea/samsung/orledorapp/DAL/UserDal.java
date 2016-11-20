package com.lea.samsung.orledorapp.DAL;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.lea.samsung.orledorapp.Inerfaces.ILoginAction;
import com.lea.samsung.orledorapp.Logger.Logger;
import com.lea.samsung.orledorapp.Models.User;

/**
 * Created by USER on 5/6/2016.
 */
public class UserDal extends BaseDal {
    private Firebase _usersCollection = super.DB.child("users");

    public void GetUser(final String userName, final String password, final ILoginAction loginAction)
    {
        _usersCollection.orderByChild(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User tempUser = userSnapshot.getValue(User.class);

                    if (tempUser.get_userName().equals(userName) &&
                            tempUser.get_password().equals(password)) {
                        loginAction.UserLoggedIn(tempUser);
                        return;
                    }
                }
                loginAction.UserNotFound();
            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {
                loginAction.UserNotFound();
            }
        });
    }

    public boolean SaveUser(User user) {
        String userName = user.get_userName();
        if(userName == null || userName.isEmpty())
        {
            return false;
        }

        try {
            _usersCollection.child(userName).setValue(user);
            return true;
        }
        catch (Exception ex) {
            Logger.LogErrorWithException("Failed to save user", ex);
            return false;
        }
    }
}
