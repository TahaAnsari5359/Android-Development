package com.example.bmicalculator;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button button;

    private Button clr_btn;

    private TextInputEditText textinp;
    private TextInputEditText textinp2;

    private TextView bmiid;
    private TextView obs_id;

    private ProgressBar bmi_meter;
    private ProgressBar cir_meter;


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
        button = findViewById(R.id.button);
        textinp = findViewById(R.id.textinp1);
        textinp2 = findViewById(R.id.textinp2);
        bmiid = findViewById(R.id.bmi_id);
        obs_id = findViewById(R.id.obesity_id);
        bmi_meter = findViewById(R.id.id_progressBar);
        bmi_meter.setVisibility(View.GONE);
        clr_btn = findViewById(R.id.button2);





        clr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textinp.setText("");
                textinp2.setText("");
                bmiid.setText("BMI :- ");
                bmiid.setTextColor(Color.BLACK);
                obs_id.setText("Category :- ");
                obs_id.setTextColor(Color.BLACK);
                bmi_meter.setVisibility(View.GONE);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hei = textinp.getText().toString();


                String wei = textinp2.getText().toString();

                if (hei.isEmpty() || (wei.isEmpty())){
                    Toast.makeText(MainActivity.this, "Height And Weight is mandetory", Toast.LENGTH_SHORT).show();
                return;
                }




                int hei_parsing = Integer.parseInt(hei);
                int wei_parsing = Integer.parseInt(wei);
                double conv_to_meter = hei_parsing / 100.0;

                double bmi_form = wei_parsing / (conv_to_meter * conv_to_meter);

                if (bmi_form <18.5){
                    obs_id.setText("Catergory :- UnderWeight");
                    obs_id.setTextColor(Color.parseColor("#2563EB"));
                    bmiid.setTextColor(Color.parseColor("#2563EB"));
                    bmi_meter.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#2563EB")));
                    bmi_meter.setVisibility(View.VISIBLE);

                } else if (bmi_form >= 18.5 && bmi_form < 25) {
                    obs_id.setText("Category :- NormalWeight");
                    obs_id.setTextColor(Color.parseColor("#16A34A"));
                    bmiid.setTextColor(Color.parseColor("#16A34A"));
                    bmi_meter.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#16A34A")));
                    bmi_meter.setVisibility(View.VISIBLE);
                }
                else if (bmi_form >= 25 && bmi_form < 30) {
                    obs_id.setText("Category :- OverWeight");
                    obs_id.setTextColor(Color.parseColor("#D97706"));
                    bmiid.setTextColor(Color.parseColor("#D97706"));
                    bmi_meter.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#D97706")));
                    bmi_meter.setVisibility(View.VISIBLE);
                } else if (bmi_form >=30 && bmi_form < 35) {
                    obs_id.setText("Category :- Obesity 1");
                    obs_id.setTextColor(Color.parseColor("#EA580C"));
                    bmiid.setTextColor(Color.parseColor("#EA580C"));
                    bmi_meter.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#EA580C")));
                    bmi_meter.setVisibility(View.VISIBLE);

                } else if (bmi_form>=35 && bmi_form <= 40) {
                    obs_id.setText("Category :- Obesity 2");
                    obs_id.setTextColor(Color.parseColor("#C2410C"));
                    bmiid.setTextColor(Color.parseColor("#C2410C"));
                    bmi_meter.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#C2410C")));
                    bmi_meter.setVisibility(View.VISIBLE);

                }else {
                    obs_id.setText("Category :- Obesity 3");
                    obs_id.setTextColor(Color.parseColor("#DC2626"));
                    bmiid.setTextColor(Color.parseColor("#DC2626"));
                    bmi_meter.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#DC2626")));
                    bmi_meter.setVisibility(View.VISIBLE);

                }

                bmiid.setText(String.format("BMI :- %.2f", bmi_form));

                bmi_meter.setProgress((int) bmi_form);


            }
        });
    }
}