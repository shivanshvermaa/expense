package com.example.expense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText emailLogin;
    EditText passwordLogin;
    TextView registerLogin;
    Button loginbtn;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLogin = findViewById(R.id.login_mail_edittext);
        passwordLogin = findViewById(R.id.login_password_edittext);
        registerLogin = findViewById(R.id.login_register_text);
        loginbtn = findViewById(R.id.login_login_button);

        firebaseAuth = FirebaseAuth.getInstance();

        registerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Login.this,Registeration.class));
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mailLogin = emailLogin.getText().toString().trim();
                final String passLogin = passwordLogin.getText().toString().trim();


                if( mailLogin.isEmpty() )
                {
                    emailLogin.setError("Please enter your email");
                    Toast.makeText(com.example.expense.Login.this,mailLogin ,Toast.LENGTH_LONG ).show();
                    emailLogin.requestFocus();
                    return;
                }

                if( passLogin.isEmpty() )
                {
                    passwordLogin.setError("Password required");
                    passwordLogin.requestFocus();
                    return;
                }


                firebaseAuth.signInWithEmailAndPassword(mailLogin,passLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(com.example.expense.Login.this,"Welcome :" ,Toast.LENGTH_LONG );
                            startActivity( new Intent(com.example.expense.Login.this,MainActivity.class));
                        }

                        else
                        {
                            Toast.makeText(com.example.expense.Login.this,"Unable to login" , Toast.LENGTH_LONG);
                        }
                    }
                });
            }
        });


    }
}
