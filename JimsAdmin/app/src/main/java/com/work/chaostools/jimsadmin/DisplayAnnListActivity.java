package com.work.chaostools.jimsadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.work.chaostools.jimsadmin.RvFiles.RvAnnListAdapter;

import java.util.ArrayList;

public class DisplayAnnListActivity extends AppCompatActivity {
    //ansh add smooth scroll to top, notify data inserted  in recycler view/adpter
    //ansh :attach to cloud database


    RecyclerView rvAnnList;
    RvAnnListAdapter adpAnnList;


    //firebase components
    FirebaseDatabase firebaseDBClassInstance;
    DatabaseReference dbRootnode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ann_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        enableRealtimeDatabase();
        enableDbLiveUpdates();


        setRecyclerViews();

    }
    private void enableRealtimeDatabase() {
        firebaseDBClassInstance = FirebaseDatabase.getInstance();
        dbRootnode = firebaseDBClassInstance.getReference();
        //both steps are required for the realtime data updates too
    }


    private void enableDbLiveUpdates() {
        dbRootnode.addValueEventListener(new ValueEventListener() {
                                     @Override
                                     public void onDataChange(DataSnapshot dataSnapshot) {
                                         Iterable<DataSnapshot> alldata=dataSnapshot.getChildren();

                                         ArrayList<AnnouncementStructure> dataArr=adpAnnList.getAnnouncementsArr();
                                         dataArr.clear();
                                         for (DataSnapshot i:alldata) {
                                             AnnouncementStructure entry=i.getValue(AnnouncementStructure.class);
                                             dataArr.add(entry);

                                         }
                                         adpAnnList.setAnnouncementsArr(dataArr);
                                         rvAnnList.scrollToPosition(adpAnnList.getItemCount()-1);
                                     }
                                     @Override
                                     public void onCancelled(DatabaseError databaseError) {

                                     }
                                 }
        );

    }


    private void setRecyclerViews() {
        rvAnnList = findViewById(R.id.ann_rv_l);
        adpAnnList = new RvAnnListAdapter(new ArrayList<AnnouncementStructure>());
        rvAnnList.setAdapter(adpAnnList);
        rvAnnList.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        );

    }



}
