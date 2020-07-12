package com.chaostools.ansh.Myjims.RVfiles.placements;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaostools.ansh.Myjims.R;

import java.util.ArrayList;

/**
* Created by ansh on 15/11/17.
*/
public class RvAdapter_plc extends RecyclerView.Adapter<Rvholder_plc>{
    private ArrayList<RVdata_plc> dataArr;

    public RvAdapter_plc(ArrayList<RVdata_plc> dataArr) {
        this.dataArr = dataArr;
    }

    @Override
    public Rvholder_plc onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_eachrow_placements,parent,false);

        return new Rvholder_plc(v);
    }

    @Override
    public void onBindViewHolder(Rvholder_plc holder, int position) {
        holder.srNO.setText(""+(position+1));
        holder.nme.setText(dataArr.get(position).getName());
        holder.cmpny.setText(dataArr.get(position).getComplany());
        holder.sal_pkg.setText(dataArr.get(position).getSalarypackage());


    }

    @Override
    public int getItemCount() {
        return dataArr.size();
    }
}
