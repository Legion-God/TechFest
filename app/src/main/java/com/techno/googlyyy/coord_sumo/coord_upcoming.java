package com.techno.googlyyy.coord_sumo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.techno.googlyyy.R;
import com.techno.googlyyy.data_model_classes.cmn_upcoming_structure;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class coord_upcoming extends AppCompatActivity {

    Spinner team1;
    Spinner team2;
    Button set_match;
    Button view_match;
    EditText round;
    EditText group;
    EditText time;
    EditText date;

    DatabaseReference mDatabase;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;

    List<String> team1Data;
    List<String> team2Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coord_upcoming);

        team1 = findViewById(R.id.upcoming_team1_spinner);
        team2 = findViewById(R.id.upcoming_team2_spinner);

        round = findViewById(R.id.Round_number);
        group = findViewById(R.id.Group_number);
        time = findViewById(R.id.match_time);
        date = findViewById(R.id.match_date);

        mDatabase = FirebaseDatabase.getInstance().getReference("sumo/teams");
        team1Data = new ArrayList<>();
        team2Data = new ArrayList<>();



        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, team1Data);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, team2Data);


        team1.setAdapter(adapter1);
        team2.setAdapter(adapter2);

        set_match = findViewById(R.id.set_upcoming_team);
        set_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMatch();
            }
        });


        view_match = findViewById(R.id.view_upcoming_matches);
        view_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goToMatches();
            }
        });

        setSpinnerData();

    }

    private void setMatch(){

        DatabaseReference upcomingDatabase = FirebaseDatabase.getInstance().getReference("sumo/upcoming");

        String team1name = team1.getSelectedItem().toString();
        String team2name = team2.getSelectedItem().toString();
        String tempDate = date.getText().toString();
        String tempTime = time.getText().toString();
        Integer rndNum = Integer.parseInt(round.getText().toString());
        Integer grpNum = Integer.parseInt(group.getText().toString());

        if(!team1name.isEmpty() || !team2name.isEmpty() || !tempDate.isEmpty() || !tempTime.isEmpty() || !rndNum.toString().isEmpty() || !grpNum.toString().isEmpty()){

            if(!team1name.equals(team2name)){


                cmn_upcoming_structure dataObj = new cmn_upcoming_structure(rndNum,grpNum,team1name,team2name,tempDate,tempTime);

                //make proper directory struct

                upcomingDatabase.child("groups").child("group"+dataObj.getGroup()+"round"+dataObj.getRound()).setValue(dataObj);
                Toast.makeText(this, "Match set", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "Select different teams", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Enter the details", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(this,coord_main_menu.class));
        finish();
    }

    private void goToMatches(){
        startActivity(new Intent(this, coord_upcoming_view_matches.class));
        finish();
    }

    //fetches data from firebase into spinner views
    public void setSpinnerData(){

        Query q = mDatabase.orderByChild("team_num");
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {
                    DataSnapshot next = iterator.next();

                    adapter1.add(""+next.child("team_name").getValue());
                    adapter2.add(""+next.child("team_name").getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
