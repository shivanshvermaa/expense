package com.example.expense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Registeration extends AppCompatActivity {

    EditText fn;
    EditText ln;
    EditText phone;
    EditText email;
    EditText password;
    EditText passwordconfirm;

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


    }
}
