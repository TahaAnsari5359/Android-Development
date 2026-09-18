package com.example.adanibillgenerator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button bill_button;

    private Button clr_btn;

    private TextInputEditText text1;

    private TextView res_text;

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

        bill_button = findViewById(R.id.button);
        text1 = findViewById(R.id.id_textinp);
        res_text = findViewById(R.id.textView);
        clr_btn = findViewById(R.id.button2);

        clr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res_text.setText("");
                text1.setText("");
            }
        });



        bill_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unit_str = text1.getText().toString();

                float unit = Float.parseFloat(unit_str);
                int ranges = 0;

                if (unit > 0 && unit <=100) {
                    ranges = 90;
                }
                else if (unit >100 && unit <=500) {
                    ranges = 135;
                }
                else{
                    ranges = 160;
                }
                System.out.println(ranges);

                float slab1 = Math.min(unit, 100);

                float slab2 = Math.min(Math.max(unit - 100, 0), 200);

                float slab3 = Math.min(Math.max(unit - 300, 0), 200);

                float slab4 = Math.max(unit - 500, 0);

                double form_slab1 = 5.46 * slab1;
                double form_slab2 = 9.30 *slab2;
                double form_slab3 = 10.76 *slab3;
                double form_slab4 = 12.15 *slab4;



                System.out.println(form_slab1);
                System.out.println(form_slab2);
                System.out.println(form_slab3);
                System.out.println(form_slab4);

                double form_sum = ranges+form_slab1+form_slab2+form_slab3+form_slab4;
                System.out.println(form_sum);

                double govm_elec_duty = form_sum*0.16;
                double maha_tax = unit * 0.3594;
                double final_sum = form_sum + govm_elec_duty + maha_tax;
                System.out.println(final_sum);
                String formated_str = String.format("%.2f",final_sum);
                res_text.setText("Final Bill Amount:\n" + formated_str+"₹");
            }
        });
    }
}