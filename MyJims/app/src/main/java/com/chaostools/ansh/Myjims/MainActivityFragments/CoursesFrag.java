package com.chaostools.ansh.Myjims.MainActivityFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaostools.ansh.Myjims.R;
import com.chaostools.ansh.Myjims.RVfiles.Courses.RVadapter_crs;
import com.chaostools.ansh.Myjims.RVfiles.Courses.RVdata_crs;

import java.util.ArrayList;


public class CoursesFrag extends Fragment {
    RecyclerView rv;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_courses, container, false);



        rv = v.findViewById(R.id.rv_l);
        ArrayList<RVdata_crs> dataArr = getdata();
        rv.setAdapter(new RVadapter_crs(dataArr));
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;

    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);



    }

    public ArrayList<RVdata_crs> getdata() {
        ArrayList<RVdata_crs> dataarr = new ArrayList<>();

        RVdata_crs d = new RVdata_crs();
        d.setCoursename("BTech");
        d.setDegree("Bachelors in Technology In CSc/ECE/CIVIL/ELECTRICAL or MECH");
        d.setDuration("4 Years");
        d.setExam("GGSIPU enterence Exam");
        d.setEligibility("clickhere");
        d.setCoursefee("INR 400000/-");
        dataarr.add(d);


        RVdata_crs e = new RVdata_crs();
        e.setCoursename("BBA");
        e.setDegree("Bachelor of Business Administration (BBA)");
        e.setDuration("3 Years");
        e.setExam("GGSIPU enterence Exam");
        e.setEligibility("clickhere");
        e.setCoursefee("INR 350000/-");
        dataarr.add(e);


        RVdata_crs f = new RVdata_crs();
        f.setCoursename("BCA");
        f.setDegree("Bachelor of Computer Applications (BCA)");
        f.setDuration("4 Years");
        f.setExam("");
        f.setEligibility("clickhere");
        f.setCoursefee("INR 300000/-");
        dataarr.add(f);


        RVdata_crs g = new RVdata_crs();
        g.setCoursename("B.Ed");
        g.setDegree("Bachelor of Education (B.Ed)");
        g.setDuration("4 Years");
        g.setExam("");
        g.setEligibility("clickhere");
        g.setCoursefee("INR 250000/-");
        dataarr.add(g);


        RVdata_crs h = new RVdata_crs();
        h.setCoursename("BTech");
        h.setDegree("Bachelors in Technology In CSc/ECE/CIVIL/ELECTRICAL or MECH");
        h.setDuration("4 Years");
        h.setExam("GGSIPU enterence Exam");
        h.setEligibility("clickhere");
        h.setCoursefee("INR 400000/-");
        dataarr.add(h);



        return dataarr;


    }
}
