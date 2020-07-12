package com.chaostools.ansh.Myjims.RVfiles.placements;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chaostools.ansh.Myjims.R;

/**
* Created by ansh on 15/11/17.
*/
public class Rvholder_plc extends RecyclerView.ViewHolder{
    public TextView srNO,nme,cmpny,sal_pkg;
    public Rvholder_plc(View itemView) {
        super(itemView);
        srNO=itemView.findViewById(R.id.serialno_plc_l);
        nme=itemView.findViewById(R.id.name_plc_l);
        cmpny=itemView.findViewById(R.id.company_plc_l);
        sal_pkg=itemView.findViewById(R.id.package_plc_l);


    }
}
