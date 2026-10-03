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

public class question3 extends AppCompatActivity {

    private Button q3op1;
    private Button q3op2;
    private Button q3op3;
    private Button q3op4;

    private Button q3_next;
    private TextView res_lab;
    private Button q3_prev_btn;

    private int score;

    // Option selected or not
    private boolean optionSelected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question3);

        // Receive score from Question 2
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

        q3op1 = findViewById(R.id.q1op1);
        q3op2 = findViewById(R.id.q1op2);
        q3op3 = findViewById(R.id.q1op3);
        q3op4 = findViewById(R.id.q1op4);

        res_lab = findViewById(R.id.textView2);

        q3_prev_btn = findViewById(R.id.q1_prev);
        q3_next = findViewById(R.id.q1_nxt);


        // NEXT → QUESTION 4
        q3_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                if (!optionSelected) {
                    Toast.makeText(
                            question3.this,
                            "Please Select Any 1 Of Options",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }

                Intent k = new Intent(
                        question3.this,
                        question4.class
                );

                k.putExtra("score", score);

                startActivity(k);
                finish();
            }
        });


        // PREVIOUS → QUESTION 2
        q3_prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                Intent i = new Intent(
                        question3.this,
                        question2.class
                );

                i.putExtra("score", score);

                startActivity(i);
                finish();
            }
        });


        // OPTION 4 - WRONG
        q3op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);

                disableButtons();
            }
        });


        // OPTION 3 - WRONG
        q3op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);

                disableButtons();
            }
        });


        // OPTION 2 - WRONG
        q3op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);

                disableButtons();
            }
        });


        // OPTION 1 - CORRECT
        q3op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("CORRECT ✅");
                res_lab.setTextColor(Color.GREEN);

                score++;

                disableButtons();
            }
        });
    }


    // Disable all options
    private void disableButtons() {

        q3op1.setEnabled(false);
        q3op2.setEnabled(false);
        q3op3.setEnabled(false);
        q3op4.setEnabled(false);
        q3_prev_btn.setEnabled(false);
    }
}