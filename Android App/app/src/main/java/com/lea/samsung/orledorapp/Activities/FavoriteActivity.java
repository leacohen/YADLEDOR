package com.lea.samsung.orledorapp.Activities;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.lea.samsung.orledorapp.Logic.FavoriteLogic;
import com.lea.samsung.orledorapp.Models.BaseMultimedia;
import com.lea.samsung.orledorapp.Models.MultimediaType;
import com.lea.samsung.orledorapp.R;

public class FavoriteActivity extends BaseMultimediaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FavoriteLogic().LoadFavoriteMedia(this);

        setContentView(R.layout.activity_favorite);

        SetAddFavoriteButton();
    }

    private void SetAddFavoriteButton() {
        findViewById(R.id.addFavorite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAddNewFavoriteDialog();
            }
        });
    }

    private void ShowAddNewFavoriteDialog() {
        final Dialog dialog = new Dialog(this);


        dialog.setContentView(R.layout.dialog_add_new_link);

        final EditText inputName = (EditText)dialog.findViewById(R.id.input_name);
        final EditText inputURL = (EditText)dialog.findViewById(R.id.input_link);
        final Spinner inputType = (Spinner)dialog.findViewById(R.id.multimedia_spinner);

        Button saveBtn = (Button)dialog.findViewById(R.id.dialog_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m_TextName = inputName.getText().toString();
                String m_TextURL = inputURL.getText().toString();
                MultimediaType type = MultimediaType.values()[inputType.getSelectedItemPosition()];

                BaseMultimedia b = new BaseMultimedia();
                b.set_name(m_TextName);
                b.set_link(m_TextURL);
                b.set_type(type);

                new FavoriteLogic().AddLink(b);
                dialog.cancel();
                // TODO: think about that..
                //RefreshMultimediaList();
            }
        });
        Button cancelBtn = (Button)dialog.findViewById(R.id.dialog_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
