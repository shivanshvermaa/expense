package com.example.expense;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {


    Button addExpense ;
    Button logout;

    EditText amount;
    EditText notes;

    ToggleButton toggleFood ;
    ToggleButton toggleTravel;
    ToggleButton toggleUtilities ;
    ToggleButton toggleEntertainment ;
    ToggleButton toggleClothing ;
    ToggleButton toggleBills ;
    ToggleButton toggleHousehold ;
    ToggleButton togglePersonal ;
    ToggleButton toggleLend ;
    ToggleButton toggleMisc;

    DatabaseReference transactionDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount= findViewById(R.id.main_expense_amount);
        notes= findViewById(R.id.main_expense_notes);
        addExpense = findViewById(R.id.main_expense_add);

        toggleFood= (ToggleButton)findViewById(R.id.main_expense_food);
        toggleTravel= (ToggleButton)findViewById(R.id.main_expense_travel);
        toggleUtilities = (ToggleButton)findViewById(R.id.main_expense_utilities);
        toggleEntertainment = (ToggleButton)findViewById(R.id.main_expense_entertainment);
        toggleClothing = (ToggleButton)findViewById(R.id.main_expense_clothing);
        toggleBills = (ToggleButton)findViewById(R.id.main_expense_bills);
        toggleHousehold = (ToggleButton)findViewById(R.id.main_expense_household);
        togglePersonal = (ToggleButton)findViewById(R.id.main_expense_personal);
        toggleLend = (ToggleButton)findViewById(R.id.main_expense_lend);
        toggleMisc = (ToggleButton)findViewById(R.id.main_expense_misc);
        logout = (Button)findViewById(R.id.main_expense_logout);

        transactionDatabase = FirebaseDatabase.getInstance().getReference("transactions");

        final ArrayList <ToggleButton> tb= new ArrayList<>();

        tb.add( toggleFood );
        tb.add( toggleTravel );
        tb.add( toggleUtilities );
        tb.add( toggleEntertainment );
        tb.add( toggleClothing );
        tb.add( toggleBills );
        tb.add( toggleHousehold );
        tb.add( togglePersonal );
        tb.add( toggleLend );
        tb.add( toggleMisc );

//        toggleFood.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if( toggleFood.isChecked())
//                {
//                    toggleFood.setTextColor(Color.parseColor("#00b4d8"));
//                }
//                else
//                {
//                    toggleFood.setTextColor(Color.parseColor("#FFFFFF"));
//                }
//            }
//        });

        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> tags = new ArrayList<>();
                String tag_values = new String("");
                tags.clear();


                if (amount.getText().toString().trim().length() == 0)
                {
                    Toast.makeText(MainActivity.this, "Enter a value", Toast.LENGTH_SHORT).show();
                    tags.clear();
                }


                else
                {
                    double amount_value = Double.parseDouble(amount.getText().toString());
                    String note_value= new String( notes.getText().toString() );


                    for (int i = 0; i < tb.size(); ++i) {
                        if (tb.get(i).isChecked()) {
                            tags.add(tb.get(i).getText().toString());
                        }
                    }

                    if (tags.isEmpty())
                    {
                        Toast.makeText(MainActivity.this, "Plaese Select a tag", Toast.LENGTH_SHORT).show();
                        tags.clear();
                    }
                    else
                    {


                        for ( int i=0 ; i< tags.size() ; ++i )
                        {
                            if( i == tags.size() -1 )
                            {
                                tag_values+=(tags.get(i));
                                break;
                            }
                            tag_values+=(tags.get(i)+",");
                        }

                        String id = transactionDatabase.push().getKey();
                        //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        transaction_details td = new transaction_details(amount_value,tag_values,note_value);
                        transactionDatabase.child(id).setValue(td);
                        Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,Login.class));
                finish();
            }
        });
    }
}
