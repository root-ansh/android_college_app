package com.chaostools.ansh.Myjims.RVfiles.Courses;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaostools.ansh.Myjims.R;

import java.util.ArrayList;

/**
 * Created by ansh on 14/11/17.
 */

public class RVadapter_crs extends RecyclerView.Adapter<RVholder_crs> {
    ArrayList<RVdata_crs> dtaArr;

    public RVadapter_crs(ArrayList<RVdata_crs> dtaArr) {
        this.dtaArr = dtaArr;
    }

    @Override
    public RVholder_crs onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_eachrow_courses,parent,false);
        return  new RVholder_crs(v);


    }

    @Override
    public void onBindViewHolder(RVholder_crs holder, int position) {
        holder.coursename_h.setText(dtaArr.get(position).getCoursename());

        holder.eligibility_h.setText(dtaArr.get(position).getEligibility());
        holder.coursefees_h.setText(dtaArr.get(position).getCoursefee());
        holder.degree_h.setText(dtaArr.get(position).getDegree());
        holder.duration_h.setText(dtaArr.get(position).getDuration());
        holder.exams_h.setText(dtaArr.get(position).getExam());

    }

    @Override
    public int getItemCount() {
        return dtaArr.size();
    }
}
