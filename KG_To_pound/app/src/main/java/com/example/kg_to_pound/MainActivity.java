package com.example.kg_to_pound;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textview;
    private EditText edittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textview = findViewById(R.id.textView3);
        edittext = findViewById(R.id.editTextNumber);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s = edittext.getText().toString();

                double kg = Double.parseDouble(s);
                double pound = 2.20462 * kg;

                textview.setText("The Corresponding Value in Pound is : " + pound);

                Toast.makeText(
                        MainActivity.this,
                        "Successfully Converted To Pound",
                        Toast.LENGTH_SHORT
                ).show();



            }
        });
    }
}