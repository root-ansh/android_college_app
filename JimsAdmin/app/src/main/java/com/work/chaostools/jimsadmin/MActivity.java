package com.work.chaostools.jimsadmin;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.work.chaostools.jimsadmin.RvFiles.RvFileListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MActivity extends AppCompatActivity {
    private static final String TAG = "MActivity";
    TextView dateView, timeView;
    EditText etTeachername, etMessage;
    FloatingActionButton fabAttachFiles;
    FloatingActionButton fabSendAnnouncement;


    RecyclerView rvFilenames;
    RvFileListAdapter adpfilenamelist;


    //constants
    private static final int RC_GALLERY_OPENED = 130;
    private static final int RC_DOCUMENT = 131;


    ProgressDialog pdUpload;

    FirebaseDatabase firebaseDBClassInstance;
    DatabaseReference dbRootnode;
    FirebaseStorage firebaseStorageClassObj;
    StorageReference firebaseStorageRoot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        enableRealtimeDatabase();
        enableStorage();//todo
        initialiseTextViewsAndEdittexts();
        initialiseRecyclerView();
        initialiseFabAttach();
        initialiseFabSend();

    }

    private void enableRealtimeDatabase() {
        firebaseDBClassInstance = FirebaseDatabase.getInstance();
        dbRootnode = firebaseDBClassInstance.getReference();
    }

    private void enableStorage() {
        firebaseStorageClassObj = FirebaseStorage.getInstance();
        firebaseStorageRoot = firebaseStorageClassObj.getReference();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_show_announcements) {
            startActivity(new Intent(MActivity.this, DisplayAnnListActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }


    private void initialiseTextViewsAndEdittexts() {
        dateView = findViewById(R.id.text_date_l);
        timeView = findViewById(R.id.text_time_l);
        etMessage = findViewById(R.id.et_message);
        etTeachername = findViewById(R.id.et_teachername_l);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df;

        df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());





        df = new SimpleDateFormat("h:mm a");
        String formattedtime = df.format(c.getTime());

        dateView.setText(formattedDate);
        timeView.setText(formattedtime);

    }

    private void initialiseRecyclerView() {
        rvFilenames = findViewById(R.id.rv_main_l);
        adpfilenamelist = new RvFileListAdapter(new ArrayList<String>(), new ArrayList<Uri>(), new ArrayList<String>());
        rvFilenames.setAdapter(adpfilenamelist);
        rvFilenames.setVisibility(View.GONE);
        rvFilenames.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        );

    }


    private void initialiseFabAttach() {
        fabAttachFiles = findViewById(R.id.fab_attachfile_l);
        fabAttachFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAlertDialogue();
            }
        });
    }

    private void createAlertDialogue() {

        View dialogueview = LayoutInflater.from(MActivity.this)
                .inflate(R.layout.al_filepicker, null, false);

        setAlIconIntents(dialogueview);

        AlertDialog al = new AlertDialog.Builder(MActivity.this)
                .setView(dialogueview)
                .setMessage("Please Make A Selection.")
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                })
                .setCancelable(false)
                .create();
        al.show();

    }

    public void setAlIconIntents(View AlView) {
        Button btGallery = AlView.findViewById(R.id.al_bt_gallery);
        Button btDoc = AlView.findViewById(R.id.al_bt_document);
        Button btOther = AlView.findViewById(R.id.al_bt_document);

        btGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(Intent.ACTION_GET_CONTENT);
                i2.setType("image/*");
                startActivityForResult(Intent.createChooser(i2, "Complete Action Using:"), RC_GALLERY_OPENED);
            }
        });
        btDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(Intent.ACTION_GET_CONTENT);
                i3.setType("*/*");
                startActivityForResult(Intent.createChooser(i3, "Complete Action Using:"), RC_DOCUMENT);
            }
        });
        btOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(Intent.ACTION_GET_CONTENT);
                i3.setType("*/*");
                startActivityForResult(i3, RC_DOCUMENT);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intentData) {
        if ((requestCode == RC_DOCUMENT || requestCode == RC_GALLERY_OPENED) && resultCode == RESULT_OK) {
            Uri fileUri = intentData.getData();

            if (fileUri != null) {
                String halfname = fileUri.getLastPathSegment();
                String mimetype = getContentResolver().getType(fileUri);
                String filename = halfname + "." + mimetype.substring(mimetype.lastIndexOf('/') + 1);
                String fileLink = "";

                ArrayList<String> fileNamesArr = adpfilenamelist.getFileNameArr();
                ArrayList<Uri> fileUrisArr = adpfilenamelist.getFileUriArr();
                ArrayList<String> fileLinksArr = adpfilenamelist.getFileLinksArr();

                fileLinksArr.add(fileLink);
                fileNamesArr.add(filename);
                fileUrisArr.add(fileUri);

                adpfilenamelist.setFileNameArr(fileNamesArr);
                adpfilenamelist.setFileUriArr(fileUrisArr);
                adpfilenamelist.setFileLinksArr(fileLinksArr);
                adpfilenamelist.notifyDataSetChanged();
                if (fileNamesArr.size() > 0) {
                    rvFilenames.setVisibility(View.VISIBLE);
                } else {
                    rvFilenames.setVisibility(View.GONE);
                }


                Log.e(TAG, "onActivityResult: not null uri recieved. entry is added.\n\t\t"
                        + "the current arraylists are " + fileNamesArr + "," + fileUrisArr + "," + fileLinksArr);
            } else {
                Log.e(TAG, "null uri recieved");


            }


        }
    }


    private void initialiseFabSend() {
        fabSendAnnouncement = findViewById(R.id.fab_send);

        pdUpload = new ProgressDialog(MActivity.this);
        pdUpload.setMessage("Uploading...");
        pdUpload.setTitle("");
        pdUpload.setCancelable(false);
        pdUpload.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (pdUpload.isShowing()) {

                    pdUpload.dismiss();
                    finish();
                }
            }
        });
        fabSendAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdUpload.show();

                String dbID=generateDataBaseReference();
                AnnouncementStructure ann=createHalfAnnoumcement(dbID);

                pushHalfAnnToDB(ann);
                createLinksArrayInSTORAGEandAttachToDbase(dbID,ann.getDate()+ann.getTime());


//                AnnouncementStructure newAnnouncement = pushToStorageAndCreateAnnouncement();
//                sendAnnouncementToDB(newAnnouncement);

            }
        });

    }
    public String generateDataBaseReference() {
        String id = dbRootnode.push().getKey();
        Log.e(TAG, "=========================================================getDbID:id generated. id= " + id);
        return id;
    }
    private AnnouncementStructure createHalfAnnoumcement(String dbID) {
        String teachername = etTeachername.getText().toString();
        String message = etMessage.getText().toString();
        String date = dateView.getText().toString();
        String time = timeView.getText().toString();


        AnnouncementStructure newAnnouncement =
                new AnnouncementStructure(dbID, teachername, date, time, message,
                        new ArrayList<String>(), new ArrayList<Uri>(), new ArrayList<String>());

        return newAnnouncement;
    }

    private void pushHalfAnnToDB(AnnouncementStructure announcement) {
//        Log.e(TAG, "XXXXXXXXXXXX!!!!!!!!XXXXXXXxsendAnnouncementToDB:startedXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxx" );
//        Log.e(TAG, "sendAnnouncementToDB: annpuncement"+newAnnouncement );
        if (!announcement.teacherName.equals("")) {
            Task<Void> addTodb = dbRootnode.child(announcement.getStructureID()).setValue(announcement);
            addTodb.addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(MActivity.this, "Succesfully added to db !", Toast.LENGTH_SHORT).show();

                }
            });
            addTodb.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MActivity.this, "Adding failed,check logs", Toast.LENGTH_SHORT).show();
//                    Log.e(TAG, "onAddToDBFailure:" + e.getMessage());
//
                }
            });
        } else {
            Toast.makeText(MActivity.this.getApplicationContext(), "Teacher's name Cannot Be Empty", Toast.LENGTH_SHORT).show();

        }
    }

    private void createLinksArrayInSTORAGEandAttachToDbase(final String dbID, String folderName) {
        ArrayList<String> filenamesArr = adpfilenamelist.getFileNameArr();
        ArrayList<Uri> fileUrisArr = adpfilenamelist.getFileUriArr();
//
//      Log.e(TAG, "##################createFILELinksARRAY:CURRENT DATA RECIEVED========== "+filenamesArr+fileUrisArr+"<no file links list>,foldername"+foldername);
        final StorageReference perticularMsgFolder = firebaseStorageRoot.child("Allfile/"+folderName);
//        Log.e(TAG, "###########createFILELinksARRAY: perticular message folder is successfully created" );
//
        final ArrayList<String>finalFilelinksArray=new ArrayList<>();
        for (int i = 0; i < filenamesArr.size(); i++) {
            final String filename = filenamesArr.get(i);
            Uri file = fileUrisArr.get(i);
            Log.e(TAG, "===============================================file uri:============== " + file);

            StorageTask<UploadTask.TaskSnapshot> task = perticularMsgFolder.child(filename).putFile(file);
            pdUpload.setMessage("Now Uploading " + filename);

            addSuccessListener(task, filename, finalFilelinksArray);
            addProgressListener(task, filename, pdUpload);
            addOnFailListener(task, filename, i, filenamesArr, fileUrisArr, finalFilelinksArray);

            if(i==filenamesArr.size()-1){
                task.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        dbRootnode.child(dbID).child("filelinksArr").setValue(finalFilelinksArray);
                        finish();

                    }
                });
            }

        }

    }


    private void addSuccessListener(StorageTask<UploadTask.TaskSnapshot> task, final String filename, final ArrayList<String> finalFilelinksArray) {
        task.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(MActivity.this,
                        "successfully added " + filename,
                        Toast.LENGTH_SHORT).show();
                Uri link = taskSnapshot.getDownloadUrl();
                Log.e(TAG, "fileuploaded,link:" + link);

                finalFilelinksArray.add("" + link);

            }
        });

    }
    private void addProgressListener(StorageTask<UploadTask.TaskSnapshot> task, final String filename, final ProgressDialog pdUpload) {
        task.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                pdUpload.setMessage(filename + ":" + taskSnapshot.getBytesTransferred() + "/" + taskSnapshot.getTotalByteCount() + " Bytes");
            }
        });
    }
    private void addOnFailListener(StorageTask<UploadTask.TaskSnapshot> task, final String filename, int i, final ArrayList<String> filenamesArr, final ArrayList<Uri> fileUrisArr, final ArrayList<String> finalFilelinksArray) {
        final int finalI = i;
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MActivity.this,
                        "error adding " + filename + ",skipping it.",
                        Toast.LENGTH_SHORT).show();
                filenamesArr.remove(finalI);
                fileUrisArr.remove(finalI);

                finalFilelinksArray.add("Failed To upload" + filename);
                Log.e(TAG, "storage adding failure: " + e.getMessage());
            }
        });

    }

//
//    private AnnouncementStructure pushToStorageAndCreateAnnouncement() {
//
//
//
//        ArrayList<String> fileLinksArr = createFILELinksARRAY(dbId,date+time, filenamesArr, fileUrisArr);
//
//        //clearing file Uris is very importent,else it cancause the greatest error series in history:stackOverflowError
//        fileUrisArr.clear();
//        fileUrisArr=new ArrayList<>();
//
//        AnnouncementStructure ann = new AnnouncementStructure(dbId, teachername, date, time, message,
//                filenamesArr, fileUrisArr, fileLinksArr);
//
//        Log.e(TAG, "pushToSTORAGEand CREATE ANNOUNCEMENT has run=======================================================...:new announcement=" + ann);
//        return ann;
//
//    }
//
//
//
//    private ArrayList<String> createFILELinksARRAY(final String dbID, String foldername, final ArrayList<String> filenamesArr, final ArrayList<Uri> fileUrisArr) {
//
//
//
//        fdbIDinal ArrayList<String> finalFilelinksArray = new ArrayList<>();
//
//
//        for (int i = 0; i < filenamesArr.size(); i++) {
//            final String filename = filenamesArr.get(i);
//            Uri file = fileUrisArr.get(i);
//            Log.e(TAG, "===============================================file uri:============== " + file);
//
//            StorageTask<UploadTask.TaskSnapshot> task = perticularMsgFolder.child(filename).putFile(file);
//            pdUpload.setMessage("Now Uploading " + filename);
//
//            addSuccessListener(task, filename, finalFilelinksArray);
//            addProgressListener(task, filename, pdUpload);
//            addOnFailListener(task, filename, i, filenamesArr, fileUrisArr, finalFilelinksArray);
//
//            if(i==filenamesArr.size()-1){
//                task.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                        dbRootnode.child(dbID).child("filelinksArr").setValue(finalFilelinksArray);
//
//                    }
//                });
//            }
//
//        }
//        return finalFilelinksArray;
//
//    }
//    private void sendAnnouncementToDB(AnnouncementStructure newAnnouncement) {
//        Log.e(TAG, "XXXXXXXXXXXX!!!!!!!!XXXXXXXxsendAnnouncementToDB:startedXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxx" );
//        Log.e(TAG, "sendAnnouncementToDB: annpuncement"+newAnnouncement );
//
//        if (!newAnnouncement.teacherName.equals("")) {
//
//            Task<Void> addTodb = dbRootnode.child(newAnnouncement.getStructureID()).setValue(newAnnouncement);
//
//            addTodb.addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void aVoid) {
//                    Toast.makeText(MActivity.this, "Succesfully added to db !", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(MActivity.this, DisplayAnnListActivity.class));
//                    if (pdUpload.isShowing()) {
//                        pdUpload.dismiss();
//                    }
//                }
//            });
//            addTodb.addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(MActivity.this, "Adding failed,check logs", Toast.LENGTH_SHORT).show();
//                    Log.e(TAG, "onAddToDBFailure:" + e.getMessage());
//                    startActivity(new Intent(MActivity.this, WelcomeActivity.class));
//                    if (pdUpload.isShowing()) {
//                        pdUpload.dismiss();
//                    }
//
//                }
//            });
//
//
//        } else {
//
//            Toast.makeText(MActivity.this.getApplicationContext(), "Teachernam Cannot Be NULL", Toast.LENGTH_SHORT).show();
//
//        }
//    }
//

}
