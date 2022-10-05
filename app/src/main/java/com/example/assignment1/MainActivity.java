package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView tdate, tvdate;
    private EditText name, email;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Button submit;
    int month, day, year;
    String user_name, user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.editTextName);
        tdate = (TextView) findViewById(R.id.textViewDOB);
        tvdate = (TextView) findViewById(R.id.textViewDate);
        email = (EditText) findViewById(R.id.editTextEmail);
        submit = (Button) findViewById(R.id.button);

        tdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int g_year, int g_month, int dayOfMonth) {
                month = g_month + 1;
                year = g_year;
                day = dayOfMonth;
                tvdate.setText(day + " - " + month + " - " + year);
            }
        };
    }

    public void see(View view) {
        user_name = name.getText().toString();
        user_email = email.getText().toString();
        String dob = day + "-" + month + "-" + year;
        DbManager db = new DbManager(this);
        if (db.addRecord(user_name, dob, user_email)) {
            System.out.println("Data added Successfully");
            Toast.makeText(this, "Data added Successfully", Toast.LENGTH_LONG).show();
            Intent i = new Intent(MainActivity.this, MainActivity2.class);
            i.putExtra(MainActivity2.name, user_name);
            i.putExtra(MainActivity2.email, user_email);
            i.putExtra(MainActivity2.g_month, month);
            i.putExtra(MainActivity2.g_day, day);
            i.putExtra(MainActivity2.g_year, year);
            startActivity(i);
        } else {
            System.out.println("Failed to add data");
            Toast.makeText(this, "Failed to add data", Toast.LENGTH_LONG).show();
        }
    }
}