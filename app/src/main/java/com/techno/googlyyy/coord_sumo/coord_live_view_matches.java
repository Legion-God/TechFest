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
import com.techno.googlyyy.R;
import com.techno.googlyyy.adapters.live_adapter_list;
import com.techno.googlyyy.adapters.upcoming_adapter_list;
import com.techno.googlyyy.data_model_classes.*;

import java.util.ArrayList;
import java.util.List;

public class coord_live_view_matches extends AppCompatActivity {

    ListView listViewMatches;
    List<cmn_live_matches_structure> matches;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coord_live_view_matches);

        mDatabase = FirebaseDatabase.getInstance().getReference("sumo/live/groups");
        listViewMatches = findViewById(R.id.live_listView_matches);

        listViewMatches.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                cmn_live_matches_structure actionDataObj = matches.get(position);
                actionDialog(actionDataObj.getGroup()+"",actionDataObj.getRound()+"");
                return true;
            }
        });

        matches = new ArrayList<>();



    }

    @Override
    protected void onStart() {
        super.onStart();


        Query q = mDatabase.orderByChild("group"); //query by round would show same round results together
        q.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                cmn_live_matches_structure single_match_obj = dataSnapshot.getValue(cmn_live_matches_structure.class);

                live_adapter_list tAdapter = new live_adapter_list(coord_live_view_matches.this, matches);


                tAdapter.add(single_match_obj);

                tAdapter.notifyDataSetChanged();
                listViewMatches.setAdapter(tAdapter);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                cmn_live_matches_structure single_match_obj = dataSnapshot.getValue(cmn_live_matches_structure.class);

                live_adapter_list tAdapter = new live_adapter_list(coord_live_view_matches.this, matches);
                tAdapter.add(single_match_obj);

                tAdapter.notifyDataSetChanged();
                listViewMatches.setAdapter(tAdapter);

                refresh();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                cmn_live_matches_structure single_match_obj = dataSnapshot.getValue(cmn_live_matches_structure.class);
                live_adapter_list tAdapter = new live_adapter_list(coord_live_view_matches.this, matches);
                tAdapter.add(single_match_obj);

                tAdapter.notifyDataSetChanged();
                tAdapter.remove(single_match_obj);

                listViewMatches.setAdapter(tAdapter);

                refresh();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void actionDialog(final String grpNum,final String rndNum){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Action");
        builder.setMessage("Delete this match?\nUpdate?\n")
                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


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


    private void delMatch(String grpNum, String rndNum){   //grpNum is unique to each match
        DatabaseReference deleteRef = FirebaseDatabase.getInstance().getReference("sumo/live/groups");

        deleteRef.child("group"+grpNum+"round"+rndNum).removeValue();
        Toast.makeText(this, "This match should be deleted", Toast.LENGTH_SHORT).show();
    }

    private void refresh(){ //refreshes the activity
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(this, coord_live.class));
        finish();
    }
}
