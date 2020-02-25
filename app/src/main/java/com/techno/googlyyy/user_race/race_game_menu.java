package com.techno.googlyyy.user_race;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.techno.googlyyy.R;
import com.techno.googlyyy.user_lan.lan_finished;

public class race_game_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_game_menu);

        findViewById(R.id.robo_finished_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent finished_intent = new Intent(race_game_menu.this, race_finished.class);
                startActivity(finished_intent);
            }
        });

        findViewById(R.id.robo_live_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent finished_intent = new Intent(race_game_menu.this, race_live.class);
                startActivity(finished_intent);
            }
        });

        findViewById(R.id.robo_upcoming_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent finished_intent = new Intent(race_game_menu.this, race_upcoming.class);
                startActivity(finished_intent);
            }
        });

    }
}
