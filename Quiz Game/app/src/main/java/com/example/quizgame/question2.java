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

public class question2 extends AppCompatActivity {

    private Button q2op1;
    private Button q2op2;
    private Button q2op3;
    private Button q2op4;

    private Button q2_next;
    private TextView res_lab;
    private Button q2_prev_btn;

    private int score;

    // Option selected or not
    private boolean optionSelected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question2);

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


        q2op1 = findViewById(R.id.q1op1);
        q2op2 = findViewById(R.id.q1op2);
        q2op3 = findViewById(R.id.q1op3);
        q2op4 = findViewById(R.id.q1op4);

        res_lab = findViewById(R.id.textView2);

        q2_prev_btn = findViewById(R.id.q1_prev);
        q2_next = findViewById(R.id.q1_nxt);


        // NEXT BUTTON
        q2_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                // Check option selected
                if (!optionSelected) {

                    Toast.makeText(
                            question2.this,
                            "Please Select Any 1 Of Options",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }


                Intent k = new Intent(
                        question2.this,
                        question3.class
                );

                k.putExtra("score", score);

                startActivity(k);

                finish();
            }
        });


        // PREVIOUS BUTTON
        q2_prev_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                Intent i = new Intent(
                        question2.this,
                        question1.class
                );

                i.putExtra("score", score);

                startActivity(i);

                finish();
            }
        });


        // OPTION 4 - WRONG
        q2op4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);

                disableButtons();
            }
        });


        // OPTION 3 - CORRECT
        q2op3.setOnClickListener(new View.OnClickListener() {

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


        // OPTION 2 - WRONG
        q2op2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                optionSelected = true;

                res_lab.setText("WRONG ❌");
                res_lab.setTextColor(Color.RED);

                disableButtons();
            }
        });


        // OPTION 1 - WRONG
        q2op1.setOnClickListener(new View.OnClickListener() {


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

        q2op1.setEnabled(false);
        q2op2.setEnabled(false);
        q2op3.setEnabled(false);
        q2op4.setEnabled(false);
        q2_prev_btn.setEnabled(false);
    }
}