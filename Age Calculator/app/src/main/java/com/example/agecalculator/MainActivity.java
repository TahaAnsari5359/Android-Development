package com.example.agecalculator;

import android.app.DatePickerDialog;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import android.view.HapticFeedbackConstants;

public class MainActivity extends AppCompatActivity {

    private EditText year_inp;
    private Button cal_btn;
    private Button rst;
    private TextView result;
    private SoundPool soundPool;
    private int clickSoundId;
    private boolean soundLoaded = false;




    private int year_data = 0;
    private int month_data = 0;
    private int day_data = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {





        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(audioAttributes)
                .build();

        clickSoundId = soundPool.load(this, R.raw.notify_sound, 1);


        soundPool.setOnLoadCompleteListener((sp, sampleId, status) -> {
            soundLoaded = true;
        });










        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        year_inp = findViewById(R.id.editText);
        cal_btn = findViewById(R.id.button);
        result = findViewById(R.id.textView2);
        rst = findViewById(R.id.button3);
        result.setVisibility(View.GONE);





        rst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                year_inp.setText("");
                result.setText("");
            }
        });




        cal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);



                result.setVisibility(View.VISIBLE);
                String dobText = year_inp.getText().toString();

                if (dobText.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Valid Date Of Birth", Toast.LENGTH_SHORT).show();
                    return;
                }


                DateTimeFormatter formatter =
                        DateTimeFormatter.ofPattern("d/M/yyyy");

                LocalDate dob = LocalDate.parse(dobText, formatter);

                LocalDate today = LocalDate.now();

                Period age = Period.between(dob, today);

                int years = age.getYears();
                int months = age.getMonths();
                int days = age.getDays();




                if (dob.isAfter(today)) {
                    Toast.makeText(MainActivity.this,
                            "Cannot Calculate Future DOB",
                            Toast.LENGTH_SHORT).show();
                    return;

                }
                result.setText(years + " Years " +
                        "\n" + months + " Months " +
                        "\n" + days + " Days");

                soundPool.play(clickSoundId, 1, 1, 0, 0, 1);
            }
        });


        year_inp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Calendar calender = Calendar.getInstance();
                int year = calender.get(Calendar.YEAR);
                int month = calender.get(Calendar.MONTH);
                int day = calender.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        (view, selectedYear, selectedMonth, selectedDay) -> {

                            selectedMonth = selectedMonth + 1;

                            String date = selectedDay + "/" +
                                    selectedMonth + "/" +
                                    selectedYear;

                            year_inp.setText(date);
                            year_data = selectedYear;
                            month_data = selectedMonth;
                            day_data = selectedDay;

                        },
                        year, month, day

                );

                datePickerDialog.show();
            }
        });

    }
}