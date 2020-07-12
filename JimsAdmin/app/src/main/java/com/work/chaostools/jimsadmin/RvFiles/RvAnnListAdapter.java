package com.work.chaostools.jimsadmin.RvFiles;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.work.chaostools.jimsadmin.AnnouncementStructure;
import com.work.chaostools.jimsadmin.R;
import com.work.chaostools.jimsadmin.SingleAnnView;

import java.util.ArrayList;

/**
 * Created by ansh on 6/1/18.
 */

public class RvAnnListAdapter extends RecyclerView.Adapter<RvAnnListHolder> {
    ArrayList<AnnouncementStructure> announcementsArr;

    public RvAnnListAdapter(ArrayList<AnnouncementStructure> announcementsArr) {
        this.announcementsArr = announcementsArr;
    }

    @Override
    public RvAnnListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                              .inflate(R.layout.rv_eachrow_display_ann,parent,false);
        return new RvAnnListHolder(v);
    }

    @Override
    public void onBindViewHolder(RvAnnListHolder holder, final int position) {
        holder.teachernameHolder.setText(announcementsArr.get(position).getTeacherName());
        holder.miniMessageHolder.setText(announcementsArr.get(position).getMessage());
        holder.timeHolder.setText(announcementsArr.get(position).getTime());
        holder.dateHolder.setText(announcementsArr.get(position).getDate());

        ArrayList<String> filelinks=announcementsArr.get(position).getFilelinksArr();
        Boolean showBubble=filelinks==null||filelinks.size()<1;
        holder.attachmentsAvailableHolder.setVisibility(showBubble? View.GONE:View.VISIBLE);

        holder.itemView.setClickable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(), SingleAnnView.class);

                i.putExtra("ENTRY",announcementsArr.get(position));

                v.getContext().startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return announcementsArr.size();
    }

    public ArrayList<AnnouncementStructure> getAnnouncementsArr() {
        return announcementsArr;
    }

    public void setAnnouncementsArr(ArrayList<AnnouncementStructure> announcementsArr) {
        this.announcementsArr = announcementsArr;
        notifyItemRangeChanged(0,3);
    }
}
