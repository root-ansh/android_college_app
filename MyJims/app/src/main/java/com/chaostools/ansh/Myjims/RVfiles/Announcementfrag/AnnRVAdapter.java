package com.chaostools.ansh.Myjims.RVfiles.Announcementfrag;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.chaostools.ansh.Myjims.R;

import java.util.ArrayList;

/**
 * Created by ansh on 23/11/17.
 */

public class AnnRVAdapter extends RecyclerView.Adapter<AnnRVholder> {
    ArrayList<AnnRVdata> dataArr;
    int isopen=0;

    public AnnRVAdapter(ArrayList<AnnRVdata> dataArr) {
        this.dataArr = dataArr;
    }

    @Override
    public AnnRVholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AnnRVholder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_eachrow_layout_announcement,parent,false));
    }

    @Override
    public void onBindViewHolder(final AnnRVholder holder, final int i) {
        holder.teachernameHolder.setText(dataArr.get(i).getTeachername());
        holder.dateHolder.setText(dataArr.get(i).getTime());
        holder.announcementHolder.setText(dataArr.get(i).getAnnouncementdata());
        holder.ll.setVisibility(View.GONE);

        final String imageurl=null;
        if(dataArr.get(i).getAttachfiles()!=null){
            //ansh:create a string of all comma seperted links
            holder.filesHolder.setText("click here");
            holder.filesHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    run a loop and to get links from firebase' child send to browser
                }
            });
        }
        else{
            holder.filesHolder.setText("no files attached.");
            holder.filesHolder.setTextColor(R.color.color_white);
            Log.e("", "onBindViewHolder: "+"no files attached ");


        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.makeonclickchanges(imageurl, isopen);
                isopen =(isopen==0)?1:0;
            }
        });



    }

    @Override
    public int getItemCount() {
        return dataArr.size();
    }
}
