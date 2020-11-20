package com.example.wttr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class PopupActivity extends AppCompatActivity {

    DisplayMetrics dm;
    int pubWidth, pubHeight;
    Button buttonDone, buttonCancel;
    EditText editTextLocation;
    String location;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        setTitle("Enter the location");
        init();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.9), (int) (height*.3));

        pubWidth = (int) (width*.9);
        pubHeight = (int) (height*.3) - 40;

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;

        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);
    }

    private void init() {
        dm = new DisplayMetrics();
        buttonDone = findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location = editTextLocation.getText().toString();

                sharedPreferencesEditor.putString("location", location);

                sharedPreferencesEditor.apply();
                finish();
            }
        });
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finishAffinity();
                Intent intent = new Intent(PopupActivity.this, Proba.class);
                startActivity(intent);
            }
        });
        editTextLocation = findViewById(R.id.editTextTextLocation);
        location = "";
        sharedPreferences = getSharedPreferences("com.example.wttr", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();
    }
}