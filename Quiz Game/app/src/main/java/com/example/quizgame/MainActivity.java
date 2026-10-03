package com.example.quizgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button strt_btn;
    private Button abt_btn;
    private Button high_scr_btn;
    private Button exit_btn;

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

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

        strt_btn = findViewById(R.id.button1);
        abt_btn = findViewById(R.id.button2);
        high_scr_btn = findViewById(R.id.button3);
        exit_btn = findViewById(R.id.button4);


        // START QUIZ
        strt_btn.setOnClickListener(v -> {
            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

            Intent intent = new Intent(
                    MainActivity.this,
                    question1.class
            );

            // Starting score
            intent.putExtra("score", 0);

            startActivity(intent);
        });



        high_scr_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                SharedPreferences preferences =
                        getSharedPreferences("QuizData", MODE_PRIVATE);

                int highScore = preferences.getInt("highScore", 0);

                Toast.makeText(
                        MainActivity.this,
                        "High Score: " + highScore + " / 13",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


        // EXIT
        exit_btn.setOnClickListener(v -> {
            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            finishAffinity();
        });


        // ABOUT
        abt_btn.setOnClickListener(v -> {
            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

            Toast.makeText(
                    MainActivity.this,
                    "QUIZ GAME CREATED BY TAHA ANSARI",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }
}