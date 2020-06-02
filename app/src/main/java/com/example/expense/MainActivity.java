package com.example.expense;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addExpense = findViewById(R.id.main_expense_add);

        EditText amount = findViewById(R.id.main_expense_amount);
        EditText notes = findViewById(R.id.main_expense_notes);

        ToggleButton toggleFood = (ToggleButton)findViewById(R.id.main_expense_food);
        ToggleButton toggleTravel = (ToggleButton)findViewById(R.id.main_expense_travel);
        ToggleButton toggleUtilities = (ToggleButton)findViewById(R.id.main_expense_utilities);
        ToggleButton toggleEntertainment = (ToggleButton)findViewById(R.id.main_expense_entertainment);
        ToggleButton toggleClothing = (ToggleButton)findViewById(R.id.main_expense_clothing);
        ToggleButton toggleBills = (ToggleButton)findViewById(R.id.main_expense_bills);
        ToggleButton toggleHousehold = (ToggleButton)findViewById(R.id.main_expense_household);
        ToggleButton togglePersonal = (ToggleButton)findViewById(R.id.main_expense_personal);
        ToggleButton toggleLend = (ToggleButton)findViewById(R.id.main_expense_lend);
        ToggleButton toggleMisc = (ToggleButton)findViewById(R.id.main_expense_misc);

        float amountValue = Float.parseFloat(amount.getText().toString());
        String noteString =  notes.getText().toString();

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

        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList <String> tags = new ArrayList<>();
                for( int  i = 0 ; i<tb.size() ; ++i)
                {
                    if( tb.get(i).isChecked() )
                    {
                        tags.add(tb.get(i).getText().toString());
                    }
                }

                if(tb.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Plaese Select a tag" , Toast.LENGTH_LONG).show();
                }

                else{
                    Toast.makeText(MainActivity.this,"Added" , Toast.LENGTH_LONG).show();
                }


            }
        });




    }

}
