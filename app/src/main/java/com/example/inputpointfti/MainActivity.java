package com.example.inputpointfti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inputpointfti.ui.home.HomeViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText editTxtUsername, editTxtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTxtUsername = findViewById(R.id.editTxt_username);
        editTxtPassword = findViewById(R.id.editTxt_Password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String existingUsername = "admin";
                String existingPassword = "admin";

                String username = editTxtUsername.getText().toString();
                String password = editTxtPassword.getText().toString();

                if (username.equals(existingUsername) && password.equals(existingPassword))
                {
                    Intent intentToHome = new Intent(MainActivity.this, HomeActivity.class);
                    intentToHome.putExtra("user", username);
                    startActivity(intentToHome);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Kombinasi username/password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}