package com.techno.googlyyy.adapters;
import android.app.Activity;
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

public class team_adapter_list extends ArrayAdapter<cmn_team_structure> {

    private Activity context;
    List<cmn_team_structure> teamList;

    public team_adapter_list(Activity context, List<cmn_team_structure> teamList){
        super(context, R.layout.team_view_list_layout, teamList);
        this.context = context;
        this.teamList = teamList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.team_view_list_layout, null, true);

        TextView teamName =  listViewItem.findViewById(R.id.team_view_tname);
        TextView teamNumber =  listViewItem.findViewById(R.id.team_view_tnum);

        cmn_team_structure sumo_teams = teamList.get(position);
        teamName.setText(sumo_teams.getTeam_name());
        String tempNumber = "Team"+sumo_teams.getTeam_num()+" :";

        teamNumber.setText(tempNumber);

        return listViewItem;
    }
}
