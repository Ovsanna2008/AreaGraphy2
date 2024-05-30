package com.example.areagraphy;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class lesson3_crust extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3_crust);

        // Initialize Firebase database reference
        mDatabase = FirebaseDatabase.getInstance().getReference();

        textView = findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());

        // Retrieve text content from Firebase
        getTextFromFirebase();
    }

    private void getTextFromFirebase() {
        mDatabase.child("lesson3").child("crust2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String content = dataSnapshot.getValue(String.class);
                    textView.setText(content);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }

    public void QuizActivityCrust3(View view) {
        startActivity(new Intent(this, QuizActivityCrust3.class));
    }
}
