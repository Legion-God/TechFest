package com.techno.googlyyy.commons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.techno.googlyyy.R;
import com.techno.googlyyy.user_lan.lan_game_menu;
import com.techno.googlyyy.user_line.line_game_menu;
import com.techno.googlyyy.user_race.race_game_menu;
import com.techno.googlyyy.user_soccer.soccer_finished;
import com.techno.googlyyy.user_soccer.soccer_game_menu;
import com.techno.googlyyy.user_sumo.sumo_menu;

public class event_menu extends AppCompatActivity implements View.OnClickListener{


    private Button lan_game;
    private Button sumo;
    private Button settings;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

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

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mAuth = FirebaseAuth.getInstance();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        findViewById(R.id.settings_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                log_out();

            }

        });

        findViewById(R.id.line_follow_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(event_menu.this, line_game_menu.class);
                startActivity(i);
            }
        });

        findViewById(R.id.race_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(event_menu.this, race_game_menu.class);
                startActivity(i);
            }
        });

        findViewById(R.id.soccer_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(event_menu.this, soccer_game_menu.class);
                startActivity(i);
            }
        });

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


    private void log_out(){
        mAuth.signOut();

        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(event_menu.this, "Logged Out", Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(event_menu.this,MainActivity.class);
                    startActivity(i);

                }else {
                    Toast.makeText(event_menu.this, "Fucked Up!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
