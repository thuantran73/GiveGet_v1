package com.larten.giveget_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ImageButton userButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        userButton = (ImageButton) findViewById(R.id.btn_user);

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent userIntent = new Intent(MainActivity.this, userActivity.class);
                startActivity(userIntent);

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){

            Intent startIntent = new Intent(MainActivity.this, startActivity.class);
            startActivity(startIntent);
            finish();

        }

    }

}
