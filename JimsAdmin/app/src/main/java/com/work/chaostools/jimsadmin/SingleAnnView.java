package com.work.chaostools.jimsadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.work.chaostools.jimsadmin.RvFiles.RvSingViewLinksListAdapter;

import java.util.ArrayList;

public class SingleAnnView extends AppCompatActivity {
    TextView teachername, date, time, message;

    RecyclerView rvLinks;
    RvSingViewLinksListAdapter adp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_ann_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent recievedIntent = getIntent();
        AnnouncementStructure ann = recievedIntent.getParcelableExtra("ENTRY");
        initialiseLayouts();
        teachername.requestFocus();
        setData(ann);


    }

    private void initialiseLayouts() {

        teachername = findViewById(R.id.single_annview_teachername);
        message = findViewById(R.id.single_annview_message_l);
        date = findViewById(R.id.single_annview_date);
        time = findViewById(R.id.single_annview_time);

        rvLinks = findViewById(R.id.rv_singleann_view);
        adp = new RvSingViewLinksListAdapter(new ArrayList<String>());
        rvLinks.setAdapter(adp);
        rvLinks.setLayoutManager(new LinearLayoutManager(this));
        rvLinks.setNestedScrollingEnabled(false);

    }

    private void setData(AnnouncementStructure ann) {
        if (ann != null) {
            teachername.setText(ann.getTeacherName());
            message.setText(ann.getMessage());
            date.setText(ann.getDate());
            time.setText(ann.getTime());

            ArrayList<String> allLinks = ann.getFilelinksArr();

            if (allLinks == null || allLinks.size() < 1) {
                rvLinks.setVisibility(View.GONE);
            } else {
                adp.setFileLinksArr(allLinks);
            }

        }
    }

}
