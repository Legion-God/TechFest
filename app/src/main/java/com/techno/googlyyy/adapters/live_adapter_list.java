package com.techno.googlyyy.adapters;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.techno.googlyyy.R;
import com.techno.googlyyy.data_model_classes.*;

import java.util.List;

public class live_adapter_list extends ArrayAdapter<cmn_live_matches_structure> {

    private Activity context;
    List<cmn_live_matches_structure> grpList;

    public live_adapter_list(Activity context, List<cmn_live_matches_structure> grpList){
        super(context,R.layout.live_match_list_layout, grpList);
        this.context = context;
        this.grpList = grpList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.live_match_list_layout, null, true);

        TextView round_num = listViewItem.findViewById(R.id.live_round_number_layout);
        TextView group_num = listViewItem.findViewById(R.id.live_group_number_layout);
        TextView team1 = listViewItem.findViewById(R.id.live_team1Name_layout);
        TextView team2 = listViewItem.findViewById(R.id.live_team2Name_layout);
        TextView team1Score = listViewItem.findViewById(R.id.live_team1Score_layout);
        TextView team2Score = listViewItem.findViewById(R.id.live_team2Score_layout);
        TextView winner = listViewItem.findViewById(R.id.live_winner_layout);

        cmn_live_matches_structure matchObj= grpList.get(position);

        String tempRound = "Round "+matchObj.getRound();
        String tempGroup = "Group "+matchObj.getGroup();
        round_num.setText(tempRound);
        group_num.setText(tempGroup);

        team1.setText(matchObj.getTeam1());
        team2.setText(matchObj.getTeam2());

        String tempteam1score = matchObj.getTeam1score()+"";
        String tempteam2score = matchObj.getTeam2score()+"";
        team1Score.setText(tempteam1score);
        team2Score.setText(tempteam2score);

        String tempWinner = "Winner: "+matchObj.getWinner();
     winner.setText(tempWinner);

        return listViewItem;
    }
}
