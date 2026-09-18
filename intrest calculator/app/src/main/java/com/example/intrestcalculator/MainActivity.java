package com.example.intrestcalculator;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Spinner drp1;
    private ImageButton cal_btn;


    private TextInputEditText amt_prin;
    private TextInputEditText intr;
    private TextInputEditText yr;

    private TextView res_lab;
    private TextView total_lab;
    private RadioButton year_radio;
    private  RadioButton month_radio;




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


        cal_btn = findViewById(R.id.id_cal_btn);

        amt_prin = findViewById(R.id.textinp1);
        intr = findViewById(R.id.textinp2);
        yr = findViewById(R.id.textinp3);
        res_lab = findViewById(R.id.textView2);
        total_lab = findViewById(R.id.textView3);
        year_radio = findViewById(R.id.radioButton4);
        month_radio = findViewById(R.id.radioButton);



        cal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String g1 = amt_prin.getText().toString();
                String g2 = intr.getText().toString();
                String g3 = yr.getText().toString();

                if (g1.isEmpty() || g2.isEmpty() || g3.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please enter all values",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                int n1 = Integer.parseInt(g1);
                double n2 = Double.parseDouble(g2);
                int n3 = Integer.parseInt(g3);



                if (year_radio.isChecked()){
                    double form = (n1*n2*n3)/100.0;
                    double total = n1+form;
                    res_lab.setText("Interest: " + form);
                    total_lab.setText("Total Amount: " + total);
                } else if (month_radio.isChecked()) {
                    double form_m = (n1*n2*n3)/(100.0*12);
                    double total_m = n1+form_m;
                    res_lab.setText(String.format("Interest: %.2f", form_m));
                    total_lab.setText(String.format("Total Amount: %.2f", total_m));

                }




                amt_prin.setText("");
                intr.setText("");
                yr.setText("");
            }
        });
    }
}
