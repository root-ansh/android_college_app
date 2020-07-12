package com.chaostools.ansh.Myjims.RVfiles.Announcementfrag;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chaostools.ansh.Myjims.R;


/**
 * Created by ansh on 23/11/17.
 */

public class AnnRVholder extends RecyclerView.ViewHolder {
    TextView teachernameHolder;
    TextView dateHolder;
    TextView announcementHolder;
    TextView filesHolder;

    LinearLayout ll;

    public AnnRVholder(View itemView) {
        super(itemView);
        teachernameHolder=itemView.findViewById(R.id.teachername_l);
        dateHolder=itemView.findViewById(R.id.date_l);
        announcementHolder=itemView.findViewById(R.id.announcementminidisp_l);
        filesHolder=itemView.findViewById(R.id.files_l);

        ll=itemView.findViewById(R.id.main_announcementShowHide_ll);
        ll.setVisibility(View.GONE);



    }
    public void makeonclickchanges(String fileseurl,int open){
        if(open==0){
            //open it

            ll.setVisibility(View.VISIBLE);
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ansh:getdata from (all) the url and call an intent.open

                }
            });

        }
        else{
            ll.setVisibility(View.GONE);

        }


    }
}
