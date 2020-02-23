package com.techno.googlyyy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class coord_team_register extends AppCompatActivity implements View.OnClickListener {
    //only for sumo class

    private Button register;
    private DatabaseReference mDatabase;
    private EditText team_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coord_team_register);

        register = findViewById(R.id.register_btn);
        register.setOnClickListener(this);

        team_text = findViewById(R.id.team_name);

       mDatabase = FirebaseDatabase.getInstance().getReference("sumo");
    }

    private void addTeam(){
        String team = team_text.getText().toString().trim();
        if(!team.isEmpty()){

          cmn_structure sumo = new cmn_structure(team);
          mDatabase.child("teams").setValue(sumo);
            Toast.makeText(this, "Team Added", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Enter the Team name", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.register_btn){
            addTeam();
        }
    }
}
