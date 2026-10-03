package com.example.namaztimealert;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText ed1, ed2, ed3, ed4, ed5;
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );

            return insets;
        });

        ed1 = findViewById(R.id.editText);
        ed2 = findViewById(R.id.editText2);
        ed3 = findViewById(R.id.editText3);
        ed4 = findViewById(R.id.editText4);
        ed5 = findViewById(R.id.editText5);

        b1 = findViewById(R.id.button);


        // Time Picker for all EditTexts

        ed1.setOnClickListener(v -> showTimePicker(ed1));

        ed2.setOnClickListener(v -> showTimePicker(ed2));

        ed3.setOnClickListener(v -> showTimePicker(ed3));

        ed4.setOnClickListener(v -> showTimePicker(ed4));

        ed5.setOnClickListener(v -> showTimePicker(ed5));


        // Button - Set Alarms

        b1.setOnClickListener(v -> {

            setAlarm(ed1, 100);
            setAlarm(ed2, 101);
            setAlarm(ed3, 102);
            setAlarm(ed4, 103);
            setAlarm(ed5, 104);

        });
    }


    // TimePicker Dialog

    private void showTimePicker(EditText editText) {

        TimePickerDialog dialog = new TimePickerDialog(
                MainActivity.this,

                (view, hourOfDay, minute) -> {

                    String time = String.format(
                            "%02d:%02d",
                            hourOfDay,
                            minute
                    );

                    editText.setText(time);
                },

                12,
                0,
                true
        );

        dialog.show();
    }


    // Alarm Set Method

    private void setAlarm(EditText editText, int requestCode) {

        if (editText.getText().toString().isEmpty()) {
            return;
        }

        String time = editText.getText().toString();

        String[] parts = time.split(":");

        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);


        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);


        // Agar time aaj nikal chuka hai
        // toh next day alarm hoga

        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {

            calendar.add(Calendar.DAY_OF_MONTH, 1);

        }


        Intent intent = new Intent(this, AlarmReciever.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT |
                        PendingIntent.FLAG_IMMUTABLE
        );


        AlarmManager alarmManager =
                (AlarmManager) getSystemService(ALARM_SERVICE);


        if (alarmManager != null) {

            alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    pendingIntent
            );
        }
    }
}