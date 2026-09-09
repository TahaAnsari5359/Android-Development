package com.example.mathstablegenerator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button gen_button;

    private Button clr_btn;
    private EditText ent_num;
    private TextView lb_text;

    private ImageView win_img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top,
                    systemBars.right, systemBars.bottom);
            return insets;
        });

        gen_button = findViewById(R.id.button);
        clr_btn = findViewById(R.id.button3);
        ent_num = findViewById(R.id.id_num);
        lb_text = findViewById(R.id.id_result_lb);
        win_img = findViewById(R.id.imageView2);


        clr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lb_text.getText().toString().trim().isEmpty()){
                    Toast.makeText(MainActivity.this, "Nothing To Reset", Toast.LENGTH_SHORT).show();
                    return;
                }
                lb_text.setText("");
                ent_num.setText("");
                lb_text.setBackgroundResource(0);
                win_img.setVisibility(View.VISIBLE);
            }
        });


        gen_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String edt = ent_num.getText().toString();

                if (edt.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please Enter Number",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                int edt1 = Integer.parseInt(edt);

                StringBuilder result = new StringBuilder();

                for (int i = 1; i <= 10; i++) {
                    result.append(edt1)
                            .append(" X ")
                            .append(i)
                            .append(" = ")
                            .append(edt1 * i)
                            .append("\n");
                }
                win_img.setVisibility(View.GONE);
                lb_text.setText(result.toString());
                lb_text.setBackgroundResource(R.drawable.border);
            }
        });
    }
}