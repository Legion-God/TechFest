package com.techno.googlyyy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sumo_menu extends AppCompatActivity implements View.OnClickListener {

    private Button sumo_live;
    private Button sumo_upcoming;
    private Button sumo_finished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumo_menu);

        sumo_live = findViewById(R.id.sumo_live_btn);
        sumo_live.setOnClickListener(this);

        sumo_upcoming = findViewById(R.id.sumo_upcoming_btn);
        sumo_upcoming.setOnClickListener(this);

        sumo_finished = findViewById(R.id.sumo_finished_btn);
        sumo_finished.setOnClickListener(this);
    }

    private void goLive(){
        Intent live_intent = new Intent(this, sumo_live.class);
        startActivity(live_intent);
    }

    private void goUpcoming(){
        Intent upcoming_intent = new Intent(this, sumo_upcoming.class);
        startActivity(upcoming_intent);
    }

    private void gofinished(){
        Intent finish_intent = new Intent(this, sumo_finished.class);
        startActivity(finish_intent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.sumo_live_btn:
                goLive();
                break;

            case R.id.sumo_upcoming_btn:
                goUpcoming();
                break;

            case R.id.sumo_finished_btn:
                gofinished();
                break;

                default:
                    break;
        }
    }
}
