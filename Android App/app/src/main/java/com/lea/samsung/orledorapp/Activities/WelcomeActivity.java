package com.lea.samsung.orledorapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.client.Firebase;
import com.lea.samsung.orledorapp.Activities.Login.LoginActivity;
import com.lea.samsung.orledorapp.Activities.Login.SignUpActivity;
import com.lea.samsung.orledorapp.Common.UserContext;
import com.lea.samsung.orledorapp.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Firebase.setAndroidContext(this);

        if(UserContext.getLoggedUser() != null) {
            Intent intent = new Intent(getApplicationContext(), MainFavoriteActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getApplicationContext().startActivity(intent);
            finish();
        }

        SetLoginOnClickAction();
        SetSignupOnClickAction();
    }

    private void SetLoginOnClickAction() {
        findViewById(R.id.btnWelcomeLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
    }

    private void SetSignupOnClickAction()
    {
        findViewById(R.id.btnWelcomeRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
    }

}
