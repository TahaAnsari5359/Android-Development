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

public class question4 extends AppCompatActivity {

    private Button q4op1;
    private Button q4op2;
    private Button q4op3;
    private Button q4op4;

    private Button q4_next;
    private TextView res_lab;
    private Button q4_prev_btn;

    private int score;
    private boolean optionSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question4);

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

        q4op1 = findViewById(R.id.q4op1);
        q4op2 = findViewById(R.id.q4op2);
        q4op3 = findViewById(R.id.q4op3);
        q4op4 = findViewById(R.id.q4op4);

        res_lab = findViewById(R.id.textView2);

        q4_prev_btn = findViewById(R.id.q4_prev);
        q4_next = findViewById(R.id.q4_nxt);

        q4_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                if (!optionSelected) {

                    Toast.makeText(
                            question4.this,
                            "Please Select Any 1 Of Options",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }

                Intent k = new Intent(
                        question4.this,
                        question5.class
                );

                k.putExtra("score", score);

                startActivity(k);
                finish();
            }
        });

        q4_prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                Intent i = new Intent(
                        question4.this,
                        question3.class
                );

                i.putExtra("score", score);

                startActivity(i);
                finish();
            }
        });

        q4op4.setOnClickListener(new View.OnClickListener() {
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

        q4op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);

                disableButtons();
            }
        });

        q4op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);

                disableButtons();
            }
        });

        q4op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);

                disableButtons();
            }
        });
    }

    private void disableButtons() {

        q4op1.setEnabled(false);
        q4op2.setEnabled(false);
        q4op3.setEnabled(false);
        q4op4.setEnabled(false);
        q4_prev_btn.setEnabled(false);
    }
}