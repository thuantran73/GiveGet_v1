package com.larten.giveget_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class loginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button backButton;
    private Button loginButton;

    private TextInputLayout loginEmail;
    private TextInputLayout loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        backButton = (Button) findViewById(R.id.btn_back);
        loginButton = (Button) findViewById(R.id.btn_login);

        loginEmail = (TextInputLayout) findViewById(R.id.txtEmail);
        loginPassword = (TextInputLayout) findViewById(R.id.txtPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = loginEmail.getEditText().getText().toString();
                String password = loginPassword.getEditText().getText().toString();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){

                    loginUser(email, password);

                }

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent startIntent = new Intent(loginActivity.this, startActivity.class);
                startActivity(startIntent);

            }
        });

    }

    private void loginUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    Intent mainIntent = new Intent(loginActivity.this, MainActivity.class);
                    startActivity(mainIntent);

                }else {

                    Toast.makeText(loginActivity.this, "Cannot login", Toast.LENGTH_LONG);

                }

            }
        });

    }
}
