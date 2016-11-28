package com.lea.samsung.orledorapp.Common;

import com.lea.samsung.orledorapp.Models.Multimedia;
import com.lea.samsung.orledorapp.Models.MultimediaType;
import com.lea.samsung.orledorapp.Models.User;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 11/28/2016.
 */
public class MediaSortService {
    private static final int SELECT_LEFT = -1;
    private static final int SELECT_RIGHT = 1;
    private static final int SELECT_EVEN = 0;

    private static final int BASIC_AGE = 18;
    private static final int YEAR_RANGE = 5;

    public static void SortSystemSuggestion(List<Multimedia> medias) {
        Collections.sort(medias, new Comparator<Multimedia>() {
            @Override
            public int compare(Multimedia left, Multimedia right) {
                User loggedUser = UserContext.getLoggedUser();

                HashMap<String, Integer> userLikes = loggedUser.getLikes();
                Date userBirthDate = loggedUser.get_birthDate();

                boolean isUserLikedLeft = userLikes.containsKey(left.get_name());
                boolean isUserLikedRight = userLikes.containsKey(right.get_name());

                if (isUserLikedLeft && isUserLikedRight) {
                    int leftLikeValue = userLikes.get(left.get_name());
                    int rightLikeValue = userLikes.get(right.get_name());

                    if(leftLikeValue > rightLikeValue) {
                        return SELECT_LEFT;
                    }
                    if (leftLikeValue < rightLikeValue) {
                        return SELECT_RIGHT;
                    }
                }
                else if(isUserLikedLeft) {
                    return userLikes.get(left.get_name()) > 0
                            ? SELECT_LEFT
                            : SELECT_RIGHT;
                }

                else if(isUserLikedRight) {
                    return userLikes.get(right.get_name()) > 0
                            ? SELECT_RIGHT
                            : SELECT_LEFT;
                }

                int leftYearRange = GetMediaYearRange(left,userBirthDate);
                int rightYearRange = GetMediaYearRange(right,userBirthDate);

                if(leftYearRange < rightYearRange) {
                    return SELECT_LEFT;
                }

                if(rightYearRange < leftYearRange) {
                    return SELECT_RIGHT;
                }

                int leftMediaLikes = left.getLikes();
                int rightMediaLikes = right.getLikes();

                if(leftMediaLikes > rightMediaLikes) {
                    return SELECT_LEFT;
                }

                if(rightMediaLikes > leftMediaLikes) {
                    return SELECT_RIGHT;
                }

                    return SELECT_EVEN;
            }
        });
    }

    private static int GetMediaYearRange(Multimedia media, Date userBirthDate) {
        if(media.get_type() == MultimediaType.Other) {
            return 0;
        }

        return Math.abs((GetDateYear(media.get_publishDate()) - (GetDateYear(userBirthDate) + BASIC_AGE)) / YEAR_RANGE);
    }

    private static int GetDateYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
}
