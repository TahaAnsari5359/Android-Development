package com.example.calendarapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private CalendarView caldender;
    private EditText ed1;
    private EditText ed2;
    private Button fetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        caldender = findViewById(R.id.calendarView);
        ed1 = findViewById(R.id.editTextNumber);
        ed2 = findViewById(R.id.editTextNumber2);
        fetch = findViewById(R.id.button);


        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                String getting = ed1.getText().toString();
                String getting2 = ed2.getText().toString();


                if (getting2.isEmpty() || getting.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Year and Month", Toast.LENGTH_SHORT).show();
                    return;
                }





                int year = Integer.parseInt(getting);
                int month = Integer.parseInt(getting2);
                int datee = 1;

                if (month > 12){
                    Toast.makeText(MainActivity.this, "Invalid month", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (year < 1900) {
                    Toast.makeText(MainActivity.this, "Error Cannot Fetch Calendar below 1900", Toast.LENGTH_SHORT).show();

                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        Toast.makeText(MainActivity.this, "Fetched Calendar of year 1900", Toast.LENGTH_SHORT).show();
                    }, 2000);
                }

                if (year > 2100) {
                    Toast.makeText(MainActivity.this, "Error Cannot Fetch Calendar above 2100", Toast.LENGTH_SHORT).show();

                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        Toast.makeText(MainActivity.this, "Fetched Calendar of year 2100", Toast.LENGTH_SHORT).show();
                    }, 2000);
                }


                Calendar cal = Calendar.getInstance();

                cal.set(year,month -1,datee);
                caldender.setDate(cal.getTimeInMillis(),true,true);

                ed1.setText("");
                ed2.setText("");

            }
        });

}}