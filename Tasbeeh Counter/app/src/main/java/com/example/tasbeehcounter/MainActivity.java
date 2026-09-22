package com.example.tasbeehcounter;

import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.media.AudioAttributes;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button set_btn;
    private Button reset_btn;

    private TextInputEditText set_inp;
    private TextInputEditText limit_inp;

    private TextView res_lab;

    private TextView lim_lab;

    private Button inr_btn;
    private Button dcr_btn;


    private int counter = 0;
    private int limit = -1;

    private SoundPool soundPool;
    private int clickSoundId;

    private int clickSoundId2;
    private boolean soundLoaded = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        set_btn = findViewById(R.id.button);
        set_inp = findViewById(R.id.inp1);
        limit_inp = findViewById(R.id.inp2);
        res_lab = findViewById(R.id.textView);
        inr_btn = findViewById(R.id.button3);
        dcr_btn = findViewById(R.id.button4);
        reset_btn = findViewById(R.id.button2);
        lim_lab = findViewById(R.id.textView2);


        res_lab.setText(String.valueOf(counter));
        lim_lab.setText("Limit: 0");

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(audioAttributes)
                .build();

        clickSoundId = soundPool.load(this, R.raw.click_sound, 1);
        clickSoundId2 = soundPool.load(this, R.raw.btn_click_sound1, 1);

        soundPool.setOnLoadCompleteListener((sp, sampleId, status) -> {
            soundLoaded = true;
        });

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {








                set_inp.setText("");
                limit_inp.setText("");
                counter = 0;
                limit = -1;
                res_lab.setText(String.valueOf(counter));
                lim_lab.setText("Limit: 0");
                Toast.makeText(MainActivity.this, "Successfully Reset", Toast.LENGTH_SHORT).show();
                soundPool.play(clickSoundId2, 0.2f, 0.5f, 1, 0, 1f);
            }
        });


        inr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (counter == limit){
                    Toast.makeText(MainActivity.this, "You Reached Your Limit", Toast.LENGTH_SHORT).show();
                    soundPool.play(clickSoundId, 1, 0.5f, 1, 0, 1f);
                    return;
                }


                try {

                    counter += 1;
                    res_lab.setText(String.valueOf(counter));
                    soundPool.play(clickSoundId2, 0.2f, 0.5f, 1, 0, 1f);
                } catch (NumberFormatException e) {

                }
            }
        });

        dcr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (counter<1){
                        Toast.makeText(MainActivity.this, "Cannot Decrease More", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    counter -= 1;
                    res_lab.setText(String.valueOf(counter));
                    soundPool.play(clickSoundId2, 0.2f, 0.5f, 1, 0, 1f);
                } catch (NumberFormatException e) {

                }
            }
        });

        set_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getting_inp = set_inp.getText().toString();
                String get_limit_inp = limit_inp.getText().toString();
                try {

                    if (!get_limit_inp.isEmpty()){
                        int parsing_limit = Integer.parseInt(get_limit_inp);
                        limit = parsing_limit;
                        lim_lab.setText(String.valueOf("Limit: "+limit));
                        soundPool.play(clickSoundId2, 0.2f, 0.5f, 1, 0, 1f);
                    }


                    int parsing = Integer.parseInt(getting_inp);
                    counter = parsing;
                    res_lab.setText(String.valueOf(counter));
                    System.out.println(counter);
                    soundPool.play(clickSoundId2, 0.5f, 0.5f, 1, 0, 1f);
                    set_inp.setText("");
                    limit_inp.setText("");
                } catch (NumberFormatException e) {

                }
            }
        });

    }
}