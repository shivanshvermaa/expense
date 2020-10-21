package com.example.expense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Registeration extends AppCompatActivity {

    EditText fn;
    EditText ln;
    EditText phone;
    EditText email;
    EditText password;
    EditText passwordconfirm;
    TextView reglog;
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
        reglog = findViewById(R.id.reg_login_text);

        fAuth = FirebaseAuth.getInstance();


        reglog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Registeration.this,Login.class));
            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String first = fn.getText().toString().trim();
                final String last = ln.getText().toString().trim();
                final String phonenum = phone.getText().toString().trim();
                final String mail = email.getText().toString().trim();
                final String pass = password.getText().toString().trim();
                final String pass_c = passwordconfirm.getText().toString().trim();

                if( first.isEmpty())
                {
                    fn.setError("Please enter your first name");
                    fn.requestFocus();
                    return;
                }

                if( last.isEmpty())
                {
                    ln.setError("Please enter your last name");
                    ln.requestFocus();
                    return;
                }

                if( mail.isEmpty())
                {
                    email.setError("Please enter your email");
                    email.requestFocus();
                    return;
                }

                if(pass.isEmpty())
                {
                    password.setError("Please enter a password");
                    password.requestFocus();
                    return;
                }

                if( pass_c.isEmpty())
                {
                    passwordconfirm.setError("Please confirm your password");
                    passwordconfirm.requestFocus();
                    return;
                }

                if( !pass_c.equals(pass) )
                {
                    Toast.makeText(Registeration.this,"Passwords do not match" ,Toast.LENGTH_SHORT).show();
                    return;
                }

                else
                {
                    fAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if( task.isSuccessful())
                            {
                                User user =  new User(first,last,mail,phonenum);

                                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if( task.isSuccessful())
                                        {
                                            Toast.makeText(Registeration.this,"Registration Done" , Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(Registeration.this,MainActivity.class);
                                            startActivity(i);
                                        }

                                        else {
                                            Toast.makeText(Registeration.this,"Error in Registration" , Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                            }

                            else{
                                Toast.makeText(Registeration.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if( fAuth.getCurrentUser() != null )
        {
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }

    }

}
