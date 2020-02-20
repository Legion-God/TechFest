package com.techno.googlyyy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class event_menu extends AppCompatActivity implements View.OnClickListener{


    private Button lan_game;
    private Button sumo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_menu);

        lan_game = findViewById(R.id.lan_game_btn);
        lan_game.setOnClickListener(this);

        sumo = findViewById(R.id.sumo_btn);
        sumo.setOnClickListener(this);
    }

    public void goToLan(){
        Intent lan_intent = new Intent(this, lan_game_menu.class);
        startActivity(lan_intent);
    }

    public void goToSumo(){
        Intent sumo_intent = new Intent(this, sumo_menu.class);
        startActivity(sumo_intent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.lan_game_btn:
                goToLan();
                break;

            case R.id.sumo_btn:
                goToSumo();
                break;

            default:
                break;
        }
    }
}
