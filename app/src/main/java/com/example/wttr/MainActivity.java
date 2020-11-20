package com.example.wttr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    static final String baseUri = "http://wttr.in/";
    WebView webView;
    String location;
    SharedPreferences sharedPreferences;
    FloatingActionButton floatingLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, PopupActivity.class);
        startActivity(intent);
    }

    private void init() {

        webView = findViewById(R.id.webView);
        webView.getSettings().setBuiltInZoomControls(true);
        sharedPreferences = getSharedPreferences("com.example.wttr", MODE_PRIVATE);
        location = sharedPreferences.getString("location", "Budapest");
        floatingLocation = findViewById(R.id.floatingLocation);
        floatingLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PopupActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
        if(location != null) {
            webView.loadUrl(baseUri+location);
        }
    }


    @Override
    public void onBackPressed() {
        finishAndRemoveTask();
    }
}