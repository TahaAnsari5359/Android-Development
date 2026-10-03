package com.example.quizgame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class question1 extends AppCompatActivity {

    private Button q1op1;
    private Button q1op2;
    private Button q1op3;
    private Button q1op4;

    private Button q1_next;
    private TextView res_lab;
    private Button prev_btn;

    private int score;
    private boolean optionSelected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question1);

        // Receive score from MainActivity
        score = getIntent().getIntExtra("score", 0);


        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );


        q1op1 = findViewById(R.id.q1op1);
        q1op2 = findViewById(R.id.q1op2);
        q1op3 = findViewById(R.id.q1op3);
        q1op4 = findViewById(R.id.q1op4);

        res_lab = findViewById(R.id.textView2);

        prev_btn = findViewById(R.id.q1_prev);
        q1_next = findViewById(R.id.q1_nxt);






        q1_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                if (!optionSelected) {
                    Toast.makeText(
                            question1.this,
                            "Please Select Any 1 Of Options",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                Intent k = new Intent(
                        question1.this,
                        question2.class
                );

                k.putExtra("score", score);

                startActivity(k);
                finish();
            }
        });


        // PREVIOUS → MAIN ACTIVITY
        prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                Intent i = new Intent(
                        question1.this,
                        MainActivity.class
                );

                startActivity(i);
                finish();
            }
        });


        // OPTION 4 - WRONG
        q1op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);
                optionSelected = true;
                disableButtons();
            }
        });


        // OPTION 3 - WRONG
        q1op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);
                optionSelected = true;
                disableButtons();
            }
        });


        // OPTION 2 - CORRECT
        q1op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                res_lab.setText("CORRECT ✅");
                res_lab.setTextColor(Color.GREEN);
                optionSelected = true;
                // Increase score
                score++;

                disableButtons();
            }
        });


        // OPTION 1 - WRONG
        q1op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);
                optionSelected = true;
                disableButtons();
            }
        });
    }


    // Disable all options
    private void disableButtons() {

        q1op1.setEnabled(false);

        q1op2.setEnabled(false);
        q1op3.setEnabled(false);
        q1op4.setEnabled(false);
        prev_btn.setEnabled(false);
    }

}