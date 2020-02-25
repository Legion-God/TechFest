package com.techno.googlyyy.user_line;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.techno.googlyyy.R;
import com.techno.googlyyy.commons.event_menu;

public class line_game_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_game_menu);

        findViewById(R.id.line_live_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(line_game_menu.this, line_live.class);
                startActivity(i);
            }
        });

        findViewById(R.id.line_upcoming_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(line_game_menu.this, line_upcoming.class);
                startActivity(i);
            }
        });

        findViewById(R.id.line_finished_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(line_game_menu.this, line_finished.class);
                startActivity(i);
            }
        });
    }
}
