package com.example.inputpointfti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inputpointfti.ui.home.HomeViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText editTxtUsername, editTxtPassword;
    private Button btnLogin;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTxtUsername = findViewById(R.id.editTxt_username);
        editTxtPassword = findViewById(R.id.editTxt_Password);
        btnLogin = findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currUser = mAuth.getCurrentUser();
        if (currUser != null) {
            userIsAuthorized(currUser.getEmail());
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTxtUsername.getText().toString();
                String password = editTxtPassword.getText().toString();

                mAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    userIsAuthorized(username);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Kombinasi username/password salah", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void userIsAuthorized(String username) {
        Intent intentToHome = new Intent(MainActivity.this, HomeActivity.class);
        intentToHome.putExtra("user", username);
        startActivity(intentToHome);
        finish();
    }
}