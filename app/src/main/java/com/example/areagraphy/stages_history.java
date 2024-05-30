package com.example.areagraphy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class stages_history extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stages_history);
    }

    public void lesson1_history(View view) {
        startActivity(new Intent(this, lesson1_history.class));
    }

    public void lesson2_history(View view) {
        startActivity(new Intent(this, lesson2_history.class));
    }


    public void lesson3_activity(View view) {
        startActivity(new Intent(this, lesson3_activity.class));
    }

    public void lesson4_history(View view) {
        startActivity(new Intent(this, lesson4_history.class));
    }



}