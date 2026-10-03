package com.example.quizgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class result extends AppCompatActivity {

    private TextView temp;
    private TextView corr;
    private TextView wrong;
    private Button playagain;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

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

        temp = findViewById(R.id.textView3);
        corr = findViewById(R.id.textView5);
        wrong = findViewById(R.id.textView6);
        playagain = findViewById(R.id.button);
        exit = findViewById(R.id.button5);

        // Receive final score






        int score = getIntent().getIntExtra("score", 0);

        // Display score
        int wronggg = 13-score;
        temp.setText(score + " / 13");
        corr.setText(String.valueOf(score));
        wrong.setText(String.valueOf(wronggg));

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                finishAffinity();
            }
        });



        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Intent l = new Intent(result.this, MainActivity.class);
                startActivity(l);
            }
        });

        SharedPreferences preferences =
                getSharedPreferences("QuizData", MODE_PRIVATE);

        int highScore = preferences.getInt("highScore", 0);

        if (score > highScore) {
            preferences.edit()
                    .putInt("highScore", score)
                    .apply();
        }

    }
}