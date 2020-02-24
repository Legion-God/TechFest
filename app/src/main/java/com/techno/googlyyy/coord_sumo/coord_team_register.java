package com.techno.googlyyy.coord_sumo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.techno.googlyyy.R;
import com.techno.googlyyy.data_model_classes.cmn_team_structure;

public class coord_team_register extends AppCompatActivity implements View.OnClickListener {
    //only for sumo class
    //TODO: change the background of team register layout with triangles of same color also change the buttons to primary dark.
    private Button register;
    private Button view_team;
    private DatabaseReference mDatabase;
    private EditText team_text;
    private EditText team_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coord_team_register);

        register = findViewById(R.id.register_btn);
        register.setOnClickListener(this);

        team_text = findViewById(R.id.team_name);
        team_number = findViewById(R.id.team_num);

        view_team = findViewById(R.id.view_teams_btn);
        view_team.setOnClickListener(this);

       mDatabase = FirebaseDatabase.getInstance().getReference("sumo");
    }

    private void addTeam(){
        String team_name = team_text.getText().toString();
        String temp_team_num = team_number.getText().toString();
        Integer team_num = 0;
        if(!team_name.isEmpty()){
            if(!temp_team_num.isEmpty()){

                try{
                        team_num = Integer.parseInt(temp_team_num);

                }catch (NumberFormatException e){
                   Log.i("Numbers","Incorrect number format");
                }
                
                cmn_team_structure sumo = new cmn_team_structure(team_name, team_num);

                mDatabase.child("teams").child("team"+team_num).setValue(sumo); //team_num is unique number for each team in game category
                Toast.makeText(this, "Team Added", Toast.LENGTH_SHORT).show();

            }

        }else{
            Toast.makeText(this, "Enter the Team name", Toast.LENGTH_SHORT).show();
        }
    }

    private void goViewTeam(){
       startActivity(new Intent(this, coord_registered_team.class));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.register_btn){
            addTeam();
        }else if(id == R.id.view_teams_btn){
            goViewTeam();
        }
    }
}
