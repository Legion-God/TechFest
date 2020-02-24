package com.techno.googlyyy.user_lan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.techno.googlyyy.R;

public class lan_game_menu extends AppCompatActivity implements View.OnClickListener{


    private Button live_btn;
    private Button upcoming_btn;
    private Button finished_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lan_game_menu);

        live_btn = findViewById(R.id.lan_live_btn);
        live_btn.setOnClickListener(this);

        upcoming_btn = findViewById(R.id.lan_upcoming_btn);
        upcoming_btn.setOnClickListener(this);

        finished_btn = findViewById(R.id.lan_finished_btn);
        finished_btn.setOnClickListener(this);

    }

    public void goLive(){
        Intent live_intent = new Intent(this, lan_live.class);
        startActivity(live_intent);
    }

    public void goUpcoming(){
        Intent upcoming_intent = new Intent(this, lan_upcoming.class);
        startActivity(upcoming_intent);
    }

    public void goFinished(){
        Intent finished_intent = new Intent(this, lan_finished.class);
        startActivity(finished_intent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch(id){
            case R.id.lan_live_btn:
                goLive();
                break;
            case R.id.lan_upcoming_btn:
                goUpcoming();
                break;
            case R.id.lan_finished_btn:
                goFinished();
                break;
                default:
                    break;
        }

    }
}
