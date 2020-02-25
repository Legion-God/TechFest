package com.techno.googlyyy.user_soccer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.techno.googlyyy.R;

public class soccer_game_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soccer_game_menu);

        findViewById(R.id.soccer_finished_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(soccer_game_menu.this,soccer_finished.class);
                startActivity(i);
            }
        });

        findViewById(R.id.soccer_live_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(soccer_game_menu.this,soccer_live.class);
                startActivity(i);
            }
        });

        findViewById(R.id.soccer_upcoming_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(soccer_game_menu.this,soccer_upcoming.class);
                startActivity(i);
            }
        });
    }
}
