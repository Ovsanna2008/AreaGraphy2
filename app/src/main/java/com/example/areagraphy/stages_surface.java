package com.example.areagraphy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class  stages_surface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stages_surface);


    }

    public void lesson1_surface(View view) {
        startActivity(new Intent(this, lesson1_surface.class));
    }

    public void lesson2_surface(View view) {
        startActivity(new Intent(this, lesson2_surface.class));
    }

    public void lesson3_surface(View view) {
        startActivity(new Intent(this, lesson3_surface.class));
    }

    public void lesson4_surface(View view) {
        startActivity(new Intent(this, lesson4_surface.class));
    }



}