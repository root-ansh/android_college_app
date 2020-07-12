package com.chaostools.ansh.Myjims.RVfiles.Courses;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chaostools.ansh.Myjims.R;

/**
 * Created by ansh on 14/11/17.
 */

public class RVholder_crs extends RecyclerView.ViewHolder {
    TextView coursename_h,degree_h,duration_h,eligibility_h,exams_h,coursefees_h;
    
    public RVholder_crs(View itemView) {
        super(itemView);
        coursename_h=itemView.findViewById(R.id.coursename_l);
        degree_h=itemView.findViewById(R.id.degree_l);
        duration_h=itemView.findViewById(R.id.duration_l);
        eligibility_h=itemView.findViewById(R.id.eligibility_l);
        eligibility_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* FIXME */
            }
        });
        exams_h=itemView.findViewById(R.id.exams_l);
        coursefees_h=itemView.findViewById(R.id.coursefees_l);

    }
}
