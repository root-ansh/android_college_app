package com.chaostools.ansh.Myjims.MainActivityFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chaostools.ansh.Myjims.R;
import com.chaostools.ansh.Myjims.RVfiles.placements.RVdata_plc;
import com.chaostools.ansh.Myjims.RVfiles.placements.RvAdapter_plc;

import java.util.ArrayList;

public class PlacementsFrag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater
                .inflate(R.layout.fragment_placements, container, false);



        RecyclerView rv=v.findViewById(R.id.placementfrag_RV_l);
        rv.setAdapter(new RvAdapter_plc(getRVdata()));
        rv.setLayoutManager(new LinearLayoutManager(getContext()));



        return v;
    }

    public ArrayList<RVdata_plc> getRVdata() {
        ArrayList<RVdata_plc> rVdata=new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            RVdata_plc data0=new RVdata_plc
                    ("Sample Student Name","Sample Company Name","INR Sample Package");
            rVdata.add(data0);

        }
        return  rVdata;
    }

    public ArrayList<Integer> getGridAdpdata() {
         ArrayList<Integer> imgdataArr=new ArrayList<>();
        for (int i = 0; i <25 ; i++) {
            imgdataArr.add((i%2==0)?R.drawable.p0 : R.drawable.p1);

        }
        return imgdataArr;
    }
}