package com.techno.googlyyy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class event_menu extends AppCompatActivity implements View.OnClickListener{


    private Button lan_game;
    private Button sumo;
    private Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_menu);

        lan_game = findViewById(R.id.lan_game_btn);
        lan_game.setOnClickListener(this);

        sumo = findViewById(R.id.sumo_btn);
        sumo.setOnClickListener(this);

        settings = findViewById(R.id.settings_btn);
        settings.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Go to settings to Logout", Toast.LENGTH_SHORT).show();
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
            case R.id.settings_btn:
                //TODO: make activity to settings and display basic user information.
                break;
            default:
                break;
        }
    }
}
