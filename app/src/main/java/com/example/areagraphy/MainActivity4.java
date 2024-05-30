package com.example.areagraphy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity4 extends AppCompatActivity {


    FirebaseAuth auth;
    Button button;
    TextView textView;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        auth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.user_details);
        button = findViewById(R.id.sign_out_button); // Initialize the button
        user = auth.getCurrentUser();

        if (user == null) {
            Intent intent = new Intent(getApplicationContext() , Login.class);
            startActivity(intent);
            finish();
        } else {
            textView.setText(user.getEmail());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext() , Login.class);
                startActivity(intent);
                finish();
            }
        });
    }




    public void stages_history(View view) {
        startActivity(new Intent(this, stages_history.class));
    }

    public void stages_surface(View view) {
        startActivity(new Intent(this, stages_surface.class));
    }

    public void stages_crust(View view) {
        startActivity(new Intent(this, stages_crust.class));
    }

    public void Login(View view) {
        startActivity(new Intent(this, Login.class));
    }
}