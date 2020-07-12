package com.work.chaostools.jimsadmin.RvFiles;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.work.chaostools.jimsadmin.R;

import java.util.ArrayList;

/**
 * Created by ansh on 6/1/18.
 */

public class RvSingViewLinksListAdapter extends RecyclerView.Adapter<RvSingViewLinksHolder> {
    private ArrayList<String> fileLinksArr;

    public RvSingViewLinksListAdapter(ArrayList<String> fileLinksArr) {
        this.fileLinksArr = fileLinksArr;
    }

    @Override
    public RvSingViewLinksHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                              .inflate(R.layout.rv_eachrow_single_ann_view,parent,false);
        return new RvSingViewLinksHolder(v);

    }

    @Override
    public void onBindViewHolder(RvSingViewLinksHolder holder, final int position) {
        String fileLink= fileLinksArr.get(position);
        holder.fileLinkTextHolder.setText(fileLink);


    }

    @Override
    public int getItemCount() {
        return fileLinksArr.size();

    }

    public ArrayList<String> getFileLinksArr() {
        return fileLinksArr;
    }

    public void setFileLinksArr(ArrayList<String> fileLinksArr) {
        this.fileLinksArr = fileLinksArr;
        notifyDataSetChanged();
    }
}
