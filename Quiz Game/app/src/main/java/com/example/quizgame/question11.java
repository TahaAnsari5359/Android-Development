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

public class question11 extends AppCompatActivity {

    private Button q11op1;
    private Button q11op2;
    private Button q11op3;
    private Button q11op4;

    private Button q11_next;
    private Button q11_prev_btn;

    private TextView res_lab;

    private int score;
    private boolean optionSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question11);

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

        q11op1 = findViewById(R.id.q11op1);
        q11op2 = findViewById(R.id.q11op2);
        q11op3 = findViewById(R.id.q11op3);
        q11op4 = findViewById(R.id.q11op4);

        res_lab = findViewById(R.id.textView2);

        q11_prev_btn = findViewById(R.id.q11_prev);
        q11_next = findViewById(R.id.q11_nxt);

        q11op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);

                disableButtons();
            }
        });

        q11op2.setOnClickListener(new View.OnClickListener() {
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

        q11op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);

                disableButtons();
            }
        });

        q11op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);

                disableButtons();
            }
        });

        q11_prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                Intent i = new Intent(
                        question11.this,
                        question10.class
                );

                i.putExtra("score", score);

                startActivity(i);
                finish();
            }
        });

        q11_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                if (!optionSelected) {

                    Toast.makeText(
                            question11.this,
                            "Please Select Any 1 Of Options",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }

                Intent k = new Intent(
                        question11.this,
                        question12.class
                );

                k.putExtra("score", score);

                startActivity(k);
                finish();
            }
        });
    }

    private void disableButtons() {

        q11op1.setEnabled(false);
        q11op2.setEnabled(false);
        q11op3.setEnabled(false);
        q11op4.setEnabled(false);
        q11_prev_btn.setEnabled(false);
    }
}