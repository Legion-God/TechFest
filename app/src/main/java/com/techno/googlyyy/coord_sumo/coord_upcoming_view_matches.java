package com.techno.googlyyy.coord_sumo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.techno.googlyyy.adapters.team_adapter_list;
import com.techno.googlyyy.data_model_classes.*;
import com.techno.googlyyy.adapters.*;
import java.util.ArrayList;
import java.util.List;

public class coord_upcoming_view_matches extends AppCompatActivity {

    ListView listViewMatches;
    List<cmn_upcoming_structure> matches;
    DatabaseReference mDatabase;
    //TODO: fix the UI of upcoming_match_list_layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coord_upcoming_view_matches);

        mDatabase = FirebaseDatabase.getInstance().getReference("sumo/upcoming/groups");
        listViewMatches = findViewById(R.id.listView_matches);

        listViewMatches.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                cmn_upcoming_structure actionDataObj = matches.get(position);
                actionDialog(actionDataObj.getGroup()+"",actionDataObj.getRound()+"");

                return true;
            }
        });

        matches = new ArrayList<>();
        //store each match instance
    }

    @Override
    protected void onStart() {
        super.onStart();

        Query q = mDatabase.orderByChild("group"); //query by round would show same round results together
        q.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                cmn_upcoming_structure single_match_obj = dataSnapshot.getValue(cmn_upcoming_structure.class);

                upcoming_adapter_list tAdapter = new upcoming_adapter_list(coord_upcoming_view_matches.this, matches);

                tAdapter.add(single_match_obj);
                tAdapter.notifyDataSetChanged();
                listViewMatches.setAdapter(tAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                cmn_upcoming_structure single_match_obj = dataSnapshot.getValue(cmn_upcoming_structure.class);

                upcoming_adapter_list tAdapter = new upcoming_adapter_list(coord_upcoming_view_matches.this, matches);
                tAdapter.add(single_match_obj);
                tAdapter.notifyDataSetChanged();
                refresh();

                listViewMatches.setAdapter(tAdapter);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                cmn_upcoming_structure single_match_obj = dataSnapshot.getValue(cmn_upcoming_structure.class);

                upcoming_adapter_list tAdapter = new upcoming_adapter_list(coord_upcoming_view_matches.this, matches);
                tAdapter.add(single_match_obj);
                refresh();

                listViewMatches.setAdapter(tAdapter);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void moveToLive(String grpNum){
         //DatabaseReference movFrom = FirebaseDatabase.getInstance().getReference("sumo/upcoming/groups").child("group"+grpNum);
        // DatabaseReference movTo = FirebaseDatabase.getInstance().getReference("sumo/live").child("group"+grpNum);

            //TODO: implement this

    }

    private void delMatch(String grpNum, String rndNum){   //grpNum is unique to each match

        DatabaseReference del = FirebaseDatabase.getInstance().getReference("sumo/upcoming/groups").child("group"+grpNum+"round"+rndNum);
        del.removeValue();
        Toast.makeText(this, "Match Deleted", Toast.LENGTH_SHORT).show();
    }

    private void actionDialog(final String grpNum,final String rndNum){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Action");
        builder.setMessage("Delete this match?\n\nSet this match to Live?")
                .setPositiveButton("Live", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //live function code here
                        //delete the selected match
                        //send the data to live activity and add to sumo/live.
                        moveToLive(grpNum);
                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //delete function code here
                        delMatch(grpNum,rndNum);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void refresh(){ //refreshes the activity
        finish();
        //overridePendingTransition(0,0);
        startActivity(getIntent());
        //overridePendingTransition(0,0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(this, coord_upcoming.class));
        finish();
    }
}
