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

public class question12 extends AppCompatActivity {

    private Button q12op1;
    private Button q12op2;
    private Button q12op3;
    private Button q12op4;

    private Button q12_next;
    private Button q12_prev_btn;

    private TextView res_lab;

    private int score;
    private boolean optionSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question12);

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

        q12op1 = findViewById(R.id.q12op1);
        q12op2 = findViewById(R.id.q12op2);
        q12op3 = findViewById(R.id.q12op3);
        q12op4 = findViewById(R.id.q12op4);

        res_lab = findViewById(R.id.textView2);

        q12_prev_btn = findViewById(R.id.q12_prev);
        q12_next = findViewById(R.id.q12_nxt);

        q12op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);

                disableButtons();
            }
        });

        q12op2.setOnClickListener(new View.OnClickListener() {
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

        q12op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);

                disableButtons();
            }
        });

        q12op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);

                disableButtons();
            }
        });

        q12_prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                Intent i = new Intent(
                        question12.this,
                        question11.class
                );

                i.putExtra("score", score);

                startActivity(i);
                finish();
            }
        });

        q12_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                if (!optionSelected) {

                    Toast.makeText(
                            question12.this,
                            "Please Select Any 1 Of Options",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }

                Intent i = new Intent(
                        question12.this,
                        question13.class
                );

                i.putExtra("score", score);

                startActivity(i);
                finish();
            }
        });
    }

    private void disableButtons() {

        q12op1.setEnabled(false);
        q12op2.setEnabled(false);
        q12op3.setEnabled(false);
        q12op4.setEnabled(false);
        q12_prev_btn.setEnabled(false);
    }
}