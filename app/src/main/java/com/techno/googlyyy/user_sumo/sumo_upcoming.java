package com.techno.googlyyy.user_sumo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.techno.googlyyy.R;
import com.techno.googlyyy.adapters.upcoming_adapter_list;
import com.techno.googlyyy.coord_sumo.coord_upcoming_view_matches;
import com.techno.googlyyy.data_model_classes.cmn_upcoming_structure;

import java.util.ArrayList;
import java.util.List;

public class sumo_upcoming extends AppCompatActivity {


    ListView listViewMatches;
    List<cmn_upcoming_structure> matches;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumo_upcoming);


        mDatabase = FirebaseDatabase.getInstance().getReference("sumo/upcoming/groups");
        listViewMatches = findViewById(R.id.listView_matches);

        matches = new ArrayList<>();

    }


    @Override
    protected void onStart() {
        super.onStart();

        Query q = mDatabase.orderByChild("group"); //query by round would show same round results together
        q.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                cmn_upcoming_structure single_match_obj = dataSnapshot.getValue(cmn_upcoming_structure.class);

                Log.i("CRASH_TEST00111", "" + dataSnapshot.getValue(cmn_upcoming_structure.class));


                upcoming_adapter_list tAdapter = new upcoming_adapter_list(sumo_upcoming.this, matches);
                tAdapter.add(single_match_obj);
                tAdapter.notifyDataSetChanged();
                listViewMatches.setAdapter(tAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {


            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
