package com.techno.googlyyy.coord_sumo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.techno.googlyyy.R;
import com.techno.googlyyy.data_model_classes.*;
import com.techno.googlyyy.adapters.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class coord_registered_team extends AppCompatActivity {

     ListView listViewTeams;
     List<cmn_team_structure> teams;
     DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coord_registered_team);

        mDatabase = FirebaseDatabase.getInstance().getReference("sumo/teams");
        listViewTeams = findViewById(R.id.listView_teams);

        teams = new ArrayList<>();//stores teams
    }

    private void refresh(){
        finish();
        overridePendingTransition(0,0);
        startActivity(getIntent());
        overridePendingTransition(0,0);
    }

    @Override
    protected void onStart() {
        super.onStart();



        ValueEventListener queryValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                teams.clear();

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {
                    DataSnapshot next = iterator.next();

                    cmn_team_structure sumoTeam = next.getValue(cmn_team_structure.class);
                    teams.add(sumoTeam);

                }

                team_adapter_list tAdapter = new team_adapter_list(coord_registered_team.this, teams);
                listViewTeams.setAdapter(tAdapter);

               // refresh();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        Query query = mDatabase.orderByChild("team_num");
        query.addListenerForSingleValueEvent(queryValueListener);
    }

}
