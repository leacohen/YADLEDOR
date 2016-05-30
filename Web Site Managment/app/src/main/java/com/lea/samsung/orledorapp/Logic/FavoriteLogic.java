package com.lea.samsung.orledorapp.Logic;

import com.lea.samsung.orledorapp.Common.UserContext;
import com.lea.samsung.orledorapp.DAL.UserDal;
import com.lea.samsung.orledorapp.Models.BaseMultimedia;
import com.lea.samsung.orledorapp.Models.User;

/**
 * Created by Samsung on 15/05/2016.
 */
public class FavoriteLogic {
    public boolean AddLink(BaseMultimedia multimedia) {
        UserContext.getLoggedUser().get_userMultimedia().add(multimedia);
        return new UserDal().SaveUser(UserContext.getLoggedUser());
    }
}
