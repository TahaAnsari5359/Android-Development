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

public class question7 extends AppCompatActivity {

    private Button q7op1;
    private Button q7op2;
    private Button q7op3;
    private Button q7op4;

    private Button q7_next;
    private Button q7_prev_btn;

    private TextView res_lab;

    private int score;
    private boolean optionSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question7);

        score = getIntent().getIntExtra("score", 0);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars = insets.getInsets(
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

        q7op1 = findViewById(R.id.q7op1);
        q7op2 = findViewById(R.id.q7op2);
        q7op3 = findViewById(R.id.q7op3);
        q7op4 = findViewById(R.id.q7op4);

        res_lab = findViewById(R.id.textView2);

        q7_prev_btn = findViewById(R.id.q7_prev);
        q7_next = findViewById(R.id.q7_nxt);

        q7op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);

                disableButtons();
            }
        });

        q7op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);

                disableButtons();
            }
        });

        q7op3.setOnClickListener(new View.OnClickListener() {
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

        q7op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);

                disableButtons();
            }
        });

        q7_prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                Intent i = new Intent(
                        question7.this,
                        question6.class
                );

                i.putExtra("score", score);

                startActivity(i);
                finish();
            }
        });

        q7_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                if (!optionSelected) {

                    Toast.makeText(
                            question7.this,
                            "Please Select Any 1 Of Options",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }

                Intent i = new Intent(
                        question7.this,
                        question8.class
                );

                i.putExtra("score", score);

                startActivity(i);
                finish();
            }
        });
    }

    private void disableButtons() {

        q7op1.setEnabled(false);
        q7op2.setEnabled(false);
        q7op3.setEnabled(false);
        q7op4.setEnabled(false);
        q7_prev_btn.setEnabled(false);
    }
}