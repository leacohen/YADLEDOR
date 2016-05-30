package com.lea.samsung.orledorapp.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lea.samsung.orledorapp.Logic.SignupLogic;
import com.lea.samsung.orledorapp.MainMenuActivity;
import com.lea.samsung.orledorapp.R;

import org.w3c.dom.Text;

import java.util.Date;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button submit = (Button)findViewById(R.id.email_sign_up_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: change message
                Toast.makeText(getApplicationContext(),"Saving...", Toast.LENGTH_LONG);

                // TODO: temp all users born in israel. talk with Lea
                // TODO: Very Bad year practice, not implemented. talk with Lea
                Boolean isSignUpSucceed = new SignupLogic().SignUp(
                        ((EditText)findViewById(R.id.email_sign_up_textview)).getText().toString(),
                        ((EditText)findViewById(R.id.sign_up_password)).getText().toString(),
                        ((EditText)findViewById(R.id.sign_up_fullName)).getText().toString(),
                        "", // TODO: rethink if should split to first name and last name. remove if not
                        new Date(), // TODO: change to date picker, get the date from the date picker and replace the new Date()
                        "Israel"); // TODO: rethink if should save the lang or the country

                if(isSignUpSucceed) {
                    Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                }
                else {
                     // TODO: change the message
                    Toast.makeText(getApplicationContext(),"Register failed, try again", Toast.LENGTH_LONG);
                }
            }
        });
    }
}