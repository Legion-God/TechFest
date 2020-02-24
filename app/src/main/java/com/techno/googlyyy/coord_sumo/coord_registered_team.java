package com.techno.googlyyy.coord_sumo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

        listViewTeams.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                cmn_team_structure single_sumo_team = teams.get(position);
                //deleteDialog(all_sumo_teams.getTeam_num()+"", all_sumo_teams.getTeam_name());

                deleteDialog(single_sumo_team.getTeam_name(),single_sumo_team.getTeam_num()+"");

                return true;
            }
        });

        teams = new ArrayList<>();//stores teams
    }

    //refreshes activity 
    private void refresh(){
        finish();
        //overridePendingTransition(0,0);
        startActivity(getIntent());
        //overridePendingTransition(0,0);
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

    private void deleteTeam(String tnum){
        DatabaseReference del = FirebaseDatabase.getInstance().getReference("sumo/teams").child("team"+tnum);

        del.removeValue();
        Toast.makeText(this, "Team Deleted", Toast.LENGTH_SHORT).show();

        refresh();
    }

    private void deleteDialog(String tname,final String tnum){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete?");
        builder.setMessage("Team Number: "+tnum+"\n"+"Team Name: "+tname)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteTeam(tnum);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }



}
