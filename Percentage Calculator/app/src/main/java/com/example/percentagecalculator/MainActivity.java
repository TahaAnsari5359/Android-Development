package com.example.percentagecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.graphics.Color;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;

    private Button clear_btn;
    private TextView textview_per_obt;

    private TextView textView7_per;

    private TextView textView8_total_mrks;
    private EditText edittextn1;
    private EditText edittextn2;
    private EditText edittextn3;
    private EditText edittextn4;
    private EditText edittextn5;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            button = findViewById(R.id.button);
            clear_btn = findViewById(R.id.button2);

            textview_per_obt = findViewById(R.id.textView2);
            textView8_total_mrks = findViewById(R.id.textView8);
            textView7_per = findViewById(R.id.textView7);




            edittextn1 = findViewById(R.id.editTextNumber);
            edittextn2 = findViewById(R.id.editTextNumber2);
            edittextn3 = findViewById(R.id.editTextNumber3);
            edittextn4 = findViewById(R.id.editTextNumber7);
            edittextn5 = findViewById(R.id.editTextNumber8);


            clear_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edittextn1.setText("");
                    edittextn2.setText("");
                    edittextn3.setText("");
                    edittextn4.setText("");
                    edittextn5.setText("");

                    textview_per_obt.setText("Marks Obtained: ");
                    textview_per_obt.setTextColor(Color.parseColor("#000000"));
                    textView7_per.setText("Percentage: ");
                    textView7_per.setTextColor(Color.parseColor("#000000"));
                    textView8_total_mrks.setText("Total Marks: ");

                    textView8_total_mrks.setTextColor(Color.parseColor("#000000"));
                }
            });


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String n1 = edittextn1.getText().toString();

                    String n2 = edittextn2.getText().toString();

                    String n3 = edittextn3.getText().toString();

                    String n4 = edittextn4.getText().toString();

                    String n5 = edittextn5.getText().toString();


                    if (n1.isEmpty() || n2.isEmpty() || n3.isEmpty() || n4.isEmpty() || n5.isEmpty()){
                        Toast.makeText(MainActivity.this, "Please Enter All Required Marks", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int p1 = Integer.parseInt(n1);
                    int p2 = Integer.parseInt(n2);
                    int p3 = Integer.parseInt(n3);
                    int p4 = Integer.parseInt(n4);
                    int p5 = Integer.parseInt(n5);

                    double total_nums = p1+p2+p3+p4+p5;
                    double percentage = (total_nums * 100.0) / 500;

                    textView7_per.setText("Percentage: " + percentage + "%");
                    textView7_per.setTextColor(Color.parseColor("#008F6B"));

                    textview_per_obt.setText("Marks Obtained: " + total_nums);
                    textview_per_obt.setTextColor(Color.parseColor("#1D4ED8"));

                    textView8_total_mrks.setText("Total Marks: 500");
                    textView8_total_mrks.setTextColor(Color.parseColor("#6D28D9"));

                    Toast.makeText(MainActivity.this, "Succesffuly Calculated", Toast.LENGTH_SHORT).show();
                }
            });







            return insets;

        });
    }
}