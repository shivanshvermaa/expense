package com.example.expense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Registeration extends AppCompatActivity {

    EditText fn;
    EditText ln;
    EditText phone;
    EditText email;
    EditText password;
    EditText passwordconfirm;
    Button register;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        fn = findViewById(R.id.reg_firstname_edittext);
        ln = findViewById(R.id.reg_lastname_edittext);
        phone = findViewById(R.id.reg_phonenumber_edittext);
        email = findViewById(R.id.reg_email_edittext);
        password = findViewById(R.id.reg_pass_edittext);
        passwordconfirm = findViewById(R.id.reg_passconf_edittext);
        register = findViewById(R.id.reg_register_button);

        fAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first = fn.getText().toString().trim();
                String last = ln.getText().toString().trim();
                String mail = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String pass_c = passwordconfirm.getText().toString().trim();
            }
        });





    }
}
