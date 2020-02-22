package com.techno.googlyyy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class coord_main_menu extends AppCompatActivity implements View.OnClickListener{

    private Button live_btn;
    private Button upcoming_btn;
    private Button finish_btn;
    private Button team_btn;
    private Button info_btn;
    private Button logout;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coord_main_menu);

        live_btn = findViewById(R.id.coord_live_btn);
        live_btn.setOnClickListener(this);

        upcoming_btn = findViewById(R.id.coord_upcoming_btn);
        upcoming_btn.setOnClickListener(this);

        finish_btn = findViewById(R.id.coord_finished_btn);
        finish_btn.setOnClickListener(this);

        team_btn = findViewById(R.id.coord_team_btn);
        team_btn.setOnClickListener(this);

        info_btn = findViewById(R.id.coord_info_btn);
        info_btn.setOnClickListener(this);

        logout = findViewById(R.id.coord_logOut_btn);
        logout.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Logout to go back", Toast.LENGTH_SHORT).show();
    }

    private void goLive(){
        startActivity(new Intent(this, coord_live.class));
    }

    private void goUpcoming(){
        startActivity(new Intent(this, coord_upcoming.class));
    }

    private void goFinish(){
        startActivity(new Intent(this, coord_finished.class));
    }

    private void goTeam(){
        startActivity(new Intent(this, coord_team_register.class));
    }
    private void goInfo(){
        startActivity(new Intent(this, coord_information.class));
    }
    private void logOut(){
        mAuth.signOut();
        Toast.makeText(this, "Coordinator Logged Out", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, coordinator_login.class));
        finish();
    }





    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch(id){
            case R.id.coord_live_btn:
                    goLive();
                break;

            case R.id.coord_upcoming_btn:
                goUpcoming();
                break;

            case R.id.coord_finished_btn:
                goFinish();
                break;

            case R.id.coord_info_btn:
                goInfo();
                break;

            case R.id.coord_team_btn:
                goTeam();
                break;

            case R.id.coord_logOut_btn:
                logOut();
                break;
        }
    }
}
