package com.work.chaostools.jimsadmin.RvFiles;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.work.chaostools.jimsadmin.R;

/**
 * Created by ansh on 6/1/18.
 */

public class RvAnnListHolder extends RecyclerView.ViewHolder {
    TextView teachernameHolder;
    TextView miniMessageHolder;
    TextView dateHolder,timeHolder;
    ImageView attachmentsAvailableHolder;


    public RvAnnListHolder(View itemView) {
        super(itemView);
        teachernameHolder=itemView.findViewById(R.id.rvrachrow_ann_teachername);
        miniMessageHolder=itemView.findViewById(R.id.rvrachrow_ann_message);
        dateHolder=itemView.findViewById(R.id.rvrachrow_ann_date_l);
        timeHolder=itemView.findViewById(R.id.rvrachrow_ann_time_l);

        attachmentsAvailableHolder=itemView.findViewById(R.id.rvrachrow_ann_hasattachments);

    }
}
