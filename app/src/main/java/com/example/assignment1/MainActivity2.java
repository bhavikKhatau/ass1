package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    public static final String name = "Name";
    public static final String email = "Email";
    public static final String g_month = "month";
    public static final String g_day = "day";
    public static final String g_year = "year";
    String user_name, user_email;
    int month, day, year;
    TextView tvname, tvemail, tvdob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvname = (TextView) findViewById(R.id.tvname);
        tvemail = (TextView) findViewById(R.id.tvemail);
        tvdob = (TextView) findViewById(R.id.tvDOB);

        Intent i = getIntent();
        user_name = i.getStringExtra(name);
        user_email = i.getStringExtra(email);
        month = i.getIntExtra(g_month, 0);
        day = i.getIntExtra(g_day, 0);
        year = i.getIntExtra(g_year, 0);

        tvname.setText(user_name);
        tvemail.setText(user_email);
        tvdob.setText(day + "-" + month + "-" + year);

    }
}