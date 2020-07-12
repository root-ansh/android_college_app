package com.work.chaostools.jimsadmin.RvFiles;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.work.chaostools.jimsadmin.R;

import java.util.ArrayList;

/**
 * Created by ansh on 6/1/18.
 */

public class RvFileListAdapter extends RecyclerView.Adapter<RvFilelistHolder> {
    private ArrayList<String> fileNameArr ;
    private ArrayList<Uri> fileUriArr ;
    private ArrayList<String> fileLinksArr ;


    public RvFileListAdapter(ArrayList<String> fileNameArr, ArrayList<Uri> fileUriArr, ArrayList<String> fileLinksArr) {
        this.fileNameArr = fileNameArr;
        this.fileUriArr = fileUriArr;
        this.fileLinksArr = fileLinksArr;
    }

    @Override
    public RvFilelistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                              .inflate(R.layout.rv_eachrow_main,parent,false);
        return new RvFilelistHolder(v);

    }

    @Override
    public void onBindViewHolder(RvFilelistHolder holder, final int position) {
        final String fname=fileNameArr.get(position);
        holder.filenameHolder.setText(fname);
        holder.btCancelHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fileNameArr.remove(position);
                fileLinksArr.remove(position);
                fileUriArr.remove(position);


                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return fileNameArr.size();

    }

    public ArrayList<String> getFileNameArr() {
        return fileNameArr;
    }
    public ArrayList<Uri> getFileUriArr() {
        return fileUriArr;
    }
    public ArrayList<String> getFileLinksArr() {
        return fileLinksArr;
    }


    public void setFileNameArr(ArrayList<String> fileNameArr) {
        this.fileNameArr = fileNameArr;
        notifyDataSetChanged();
    }
    public void setFileUriArr(ArrayList<Uri> fileUriArr) {
        this.fileUriArr = fileUriArr;
        notifyDataSetChanged();
    }
    public void setFileLinksArr(ArrayList<String> fileLinksArr) {
        this.fileLinksArr = fileLinksArr;
        notifyDataSetChanged();
    }
}
