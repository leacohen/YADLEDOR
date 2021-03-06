package com.lea.samsung.orledorapp.Activities.Login;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.lea.samsung.orledorapp.Activities.MainFavoriteActivity;
import com.lea.samsung.orledorapp.Activities.MultiSpinner;
import com.lea.samsung.orledorapp.Logic.SignupLogic;
import com.lea.samsung.orledorapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity implements MultiSpinner.MultiSpinnerListener {

    private final int DATE_PIRCK_DIALOG = 999;
    private static ArrayList<String> availableLanguages;
    private LinkedList<String> selectedLanguages = new LinkedList();
    private Date selectedDate = new Date();

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int month, int day) {
            Calendar tempDate = Calendar.getInstance();
            tempDate.set(Calendar.YEAR,year);
            tempDate.set(Calendar.MONTH, month);
            tempDate.set(Calendar.DAY_OF_MONTH, day);
            selectedDate = tempDate.getTime();
            ((EditText)findViewById(R.id.sign_up_born_year)).setText(new StringBuilder().append(day).append("/")
                    .append(month + 1).append("/").append(year));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        availableLanguages = new ArrayList();
        for(Locale locale : Locale.getAvailableLocales()) {
            //locale.
            if(!availableLanguages.contains(locale.getDisplayLanguage(Locale.UK))) {
                availableLanguages.add(locale.getDisplayLanguage(Locale.UK));
            }
        }

        ((EditText)findViewById(R.id.sign_up_born_year)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_PIRCK_DIALOG);
            }
        });

        ((MultiSpinner)findViewById(R.id.languages)).setItems(
                availableLanguages,
                "שפות מדוברות",
                this);

        Button submit = (Button)findViewById(R.id.email_sign_up_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: change message
                Toast.makeText(getApplicationContext(), "Saving...", Toast.LENGTH_LONG);

                // TODO: temp all users born in israel. talk with Lea
                // TODO: Very Bad year practice, not implemented. talk with Lea
                Boolean isSignUpSucceed = false;
                if(validateSignupFields()) {
                    isSignUpSucceed = new SignupLogic().SignUp(
                            ((EditText) findViewById(R.id.email_sign_up_textview)).getText().toString(),
                            ((EditText) findViewById(R.id.sign_up_password)).getText().toString(),
                            ((EditText) findViewById(R.id.sign_up_fullName)).getText().toString(),
                            "", // TODO: rethink if should split to first name and last name. remove if not
                            selectedDate,
                            selectedLanguages);
                }

                if (isSignUpSucceed) {
                    Intent intent = new Intent(getApplicationContext(), MainFavoriteActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getApplicationContext().startActivity(intent);
                    finish();
                } else {
                    // TODO: change the message
                    Toast.makeText(getApplicationContext(), "Register failed, try again", Toast.LENGTH_LONG);
                }
            }
        });
    }

    private boolean validateSignupFields() {
        if (((EditText) findViewById(R.id.email_sign_up_textview)).getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "חובה למלא שם משתמש", Toast.LENGTH_LONG).show();
            return false;
        }
        if (((EditText) findViewById(R.id.sign_up_password)).getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "חובה למלא סיסמא", Toast.LENGTH_LONG).show();
            return false;
        }
        if (((EditText) findViewById(R.id.sign_up_re_password)).getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "חובה למלא ווידוא סיסמא", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!((EditText) findViewById(R.id.sign_up_re_password)).getText().toString().equals(((EditText) findViewById(R.id.sign_up_password)).getText().toString())) {
            Toast.makeText(getApplicationContext(), "הסיסמא והווידוא סיסמא שונות זו מזו", Toast.LENGTH_LONG).show();
            return false;
        }

        if (((EditText) findViewById(R.id.sign_up_fullName)).getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "חובה למלא שם מלא", Toast.LENGTH_LONG).show();
            return false;
        }

        if (selectedDate == null) {
            Toast.makeText(getApplicationContext(), "חובה למלא תאריך לידה", Toast.LENGTH_LONG).show();
            return false;
        }

        if (selectedLanguages.isEmpty()) {
            Toast.makeText(getApplicationContext(), "חובה למלא שפות מדוברות", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DATE_PIRCK_DIALOG) {
            Calendar c = Calendar.getInstance();

            return new DatePickerDialog(
                    this,
                    myDateListener,
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    @Override
    public void onItemsSelected(boolean[] selected){
        // Clear the old list
        selectedLanguages.clear();

        for (int i = 0; i<availableLanguages.size(); i++) {
            if (selected[i]) {
                selectedLanguages.add(availableLanguages.get(i));
            }
        }
    }
}