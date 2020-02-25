package com.techno.googlyyy.adapters;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.techno.googlyyy.R;
import com.techno.googlyyy.data_model_classes.cmn_team_structure;
import com.techno.googlyyy.data_model_classes.cmn_upcoming_structure;

public class upcoming_adapter_list extends ArrayAdapter<cmn_upcoming_structure> {

    private Activity context;
    List<cmn_upcoming_structure> grpList;

    public upcoming_adapter_list(Activity context, List<cmn_upcoming_structure> grpList){
        super(context, R.layout.upcoming_match_list_layout, grpList);
        this.context = context;
        this.grpList = grpList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.upcoming_match_list_layout, null, true);

        TextView round_num = listViewItem.findViewById(R.id.upcoming_round_number_layout);
        TextView group_num = listViewItem.findViewById(R.id.upcoming_group_number_layout);
        TextView date = listViewItem.findViewById(R.id.upcoming_date_layout);
        TextView time = listViewItem.findViewById(R.id.upcoming_time_layout);
        TextView team1 = listViewItem.findViewById(R.id.upcoming_team1_layout);
        TextView team2 = listViewItem.findViewById(R.id.upcoming_team2_layout);

        cmn_upcoming_structure sumo_upcoming_matches = grpList.get(position);

        String tempRound = "Round "+sumo_upcoming_matches.getRound();
        String tempGroup = "Group "+sumo_upcoming_matches.getGroup();
        round_num.setText(tempRound);
        group_num.setText(tempGroup);

        date.setText(sumo_upcoming_matches.getDate());
        time.setText(sumo_upcoming_matches.getTime());


        team1.setText(sumo_upcoming_matches.getTeam1());
        team2.setText(sumo_upcoming_matches.getTeam2());

        return listViewItem;

    }
}
