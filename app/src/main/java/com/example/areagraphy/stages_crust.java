package com.example.areagraphy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class stages_crust extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stages_crust);
    }

    public void lesson1_crust(View view) {
        startActivity(new Intent(this, lesson1_crust.class));
    }

    public void lesson2_crust(View view) {
        startActivity(new Intent(this, lesson2_crust.class));
    }


    public void lesson3_crust(View view) {
        startActivity(new Intent(this, lesson3_crust.class));
    }

    public void lesson4_crust(View view) {
        startActivity(new Intent(this, lesson4_crust.class));
    }



}