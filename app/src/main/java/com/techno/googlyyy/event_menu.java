package com.techno.googlyyy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class event_menu extends AppCompatActivity implements View.OnClickListener{


    private Button lan_game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_menu);

        lan_game = findViewById(R.id.lan_game_btn);
        lan_game.setOnClickListener(this);
    }

    public void goToLan(){
        Intent lan_intent = new Intent(this, lan_game_menu.class);
        startActivity(lan_intent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.lan_game_btn:
                goToLan();
                break;

            default:
                break;
        }
    }
}
