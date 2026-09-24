package com.example.notesapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText title_inp;
    private EditText notes_inp;
    private Button save_btn;
    private  Button clr_btn;
    private ListView notes_display;

    private ArrayList<String> notes;
    private ArrayAdapter<String> adapter;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

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

        title_inp = findViewById(R.id.editText);
        notes_inp = findViewById(R.id.noteBox);
        save_btn = findViewById(R.id.button);
        notes_display = findViewById(R.id.notesList);
        clr_btn = findViewById(R.id.button3);




        preferences = getSharedPreferences("notes", MODE_PRIVATE);

        notes = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                R.layout.list_item,
                notes
        );

        notes_display.setAdapter(adapter);

        // Saved notes load
        String savedNotes = preferences.getString("all_notes", "");

        if (!savedNotes.isEmpty()) {

            String[] noteList = savedNotes.split("###");

            for (String note : noteList) {
                notes.add(note);
            }

            adapter.notifyDataSetChanged();
        }

        clr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Clear Notes")
                        .setMessage("Are you sure you want to delete all notes?")
                        .setPositiveButton("Yes", (dialog, which) -> {

                            title_inp.setText("");
                            notes_inp.setText("");

                            preferences.edit().clear().apply();

                            notes.clear();
                            adapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Sucessfully Deleted All Data", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });




        // SAVE
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes_display.setVisibility(View.VISIBLE);
                String noteTitle = title_inp.getText().toString();
                String noteDescription = notes_inp.getText().toString();

                if (noteTitle.isEmpty() && noteDescription.isEmpty()) {
                    return;
                }

                String newNote =
                        "Title: " + noteTitle +
                                "\nNotes: " + noteDescription + "\n------------------------------------------------------------";

                // ListView mein add
                notes.add(newNote);

                // Purane notes lo
                String oldNotes =
                        preferences.getString("all_notes", "");

                // New note add karo
                String updatedNotes;

                if (oldNotes.isEmpty()) {
                    updatedNotes = newNote;
                } else {
                    updatedNotes = oldNotes + "###" + newNote;
                }

                // Save
                preferences.edit()
                        .putString("all_notes", updatedNotes)
                        .apply();

                // ListView refresh
                adapter.notifyDataSetChanged();

                // Input clear
                title_inp.setText("");
                notes_inp.setText("");
            }

        });
    }
}