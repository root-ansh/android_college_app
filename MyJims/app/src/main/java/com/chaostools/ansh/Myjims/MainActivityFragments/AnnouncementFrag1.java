package com.chaostools.ansh.Myjims.MainActivityFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chaostools.ansh.Myjims.R;
import com.chaostools.ansh.Myjims.RVfiles.Announcementfrag.AnnRVAdapter;
import com.chaostools.ansh.Myjims.RVfiles.Announcementfrag.AnnRVdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class AnnouncementFrag1 extends Fragment {
    RecyclerView rv;
/*


    FirebaseAuth db_Authorized_Instance;
    FirebaseAuth.AuthStateListener loginlistener;

*/

    private static final int intent_RESULT_CALLBACK = 1998;
    
    View v;



   

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_announcement, null, false);

/*

        db_Authorized_Instance = FirebaseAuth.getInstance();
        createAloginListener();//create a login checker "loginlistener".
                        // the listener will be registered in on resume and unregistered in onpause()


*/

        setInitiallayouts();


        //adddata:download data from the firebasedb using progress dialogue,set in recycler view for the first time
        // ,then clear listview_adapter and add a childstate listener
        return  v;

    }
/*

    private void createAloginListener() {
        loginlistener = new FirebaseAuth.AuthStateListener() {
            //will be attached to authorized instance in onresume and removed in onpause().

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = db_Authorized_Instance.getCurrentUser();
                if (currentUser != null) {
                    //user is logged on:do nothing


                } else {
                    //user is not loggged on, log him in


                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()
                    );


                    Intent callAuthUI = AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setAvailableProviders(providers)

                            .setAllowNewEmailAccounts(true)
                            .setLogo(R.mipmap.ic_launcher_round)
                            .setTheme(R.style.logintheme)

                            .build();
                    startActivityForResult(callAuthUI, intent_RESULT_CALLBACK);
                    //what to do if sign in is succesful or failed will be handled in on activity result
                    //keep in mind that this will only work if your app is registered on console.firebase.com's database
                    //and required authentication provider enabled from console.
                    //this listener will be attached_to/removed from   db_authorised_instance in onresume()/onpause()
                    //read onactivityresult() next
                }


            }
        };


    }
*/

    public void setInitiallayouts() {

        rv = v.findViewById(R.id.main_rv_l);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setAdapter(new AnnRVAdapter(getDataarr()));
    }

    public ArrayList<AnnRVdata> getDataarr() {
        ArrayList<AnnRVdata> rVdata = new ArrayList<>();
        int i = 0;
        String teachername="Professor Xyz";
        String Message1="Btech 3rd Sem \n All the students are informed that the sessionals will start from november 31st"
                +".\nplease find the datesheet attached";
        String date="29-11-17 5:30 pm";

        String teacher2="Mrs A";
        String Message2="Submit the FOCS Assignment by Monday";


        rVdata.add(new AnnRVdata("Teacher'sname" + i, "SAMPLE RVDATA" + i, "sampledate" + i, null));
        rVdata.add(new AnnRVdata(teachername,Message1,date,"True"));
        rVdata.add(new AnnRVdata(teacher2,Message2,date,null));
        rVdata.add(new AnnRVdata(teachername,Message1,date,"True"));
        rVdata.add(new AnnRVdata(teacher2,Message2,date,null));
        rVdata.add(new AnnRVdata(teachername,Message1,date,"True"));
        rVdata.add(new AnnRVdata(teacher2,Message2,date,null));
        rVdata.add(new AnnRVdata(teachername,Message1,date,"True"));
        rVdata.add(new AnnRVdata(teacher2,Message2,date,null));
        rVdata.add(new AnnRVdata(teachername,Message1,date,"True"));
        rVdata.add(new AnnRVdata(teachername,Message1,date,"True"));
        rVdata.add(new AnnRVdata(teachername,Message1,date,"True"));
        rVdata.add(new AnnRVdata(teachername,Message1,date,"True"));
        rVdata.add(new AnnRVdata(teachername,Message1,date,"True"));
        rVdata.add(new AnnRVdata(teachername,Message1,date,"True"));
        rVdata.add(new AnnRVdata(teacher2,Message2,date,null));
        rVdata.add(new AnnRVdata(teacher2,Message2,date,null));
        rVdata.add(new AnnRVdata(teacher2,Message2,date,null));
        rVdata.add(new AnnRVdata(teacher2,Message2,date,null));
        rVdata.add(new AnnRVdata(teacher2,Message2,date,null));



        return rVdata;
    }


    @Override
    public void onResume() {
        super.onResume();
        /*
        db_Authorized_Instance.addAuthStateListener(loginlistener);
        */
    }


    @Override
    public void onPause() {
        super.onPause();

     /*
      db_Authorized_Instance.removeAuthStateListener(loginlistener);
*/
    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
//        if (requestCode ==intent_RESULT_CALLBACK ) {
//            IdpResponse response = IdpResponse.fromResultIntent(data);
//
//            // Successfully signed in
//            if (resultCode == RESULT_OK) {
//                startActivity(new Intent(this, MainActivity.class));
//                finish();
//                return;
//            } else {
//                // Sign in failed
//                if (response == null) {
//                    // User pressed back button
//                    Toast.makeText(this,"backbutton pressed",Toast.LENGTH_SHORT).show();
//                    finish();
//                    return;
//                }
//
//                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
//                    Snackbar.make(getWindow().getDecorView().getRootView(),"No Network ",Snackbar.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
//                    Snackbar.make(getWindow().getDecorView().getRootView(),"Unknown Error",Snackbar.LENGTH_SHORT).show();
//                    return;
//                }
//            }
//
//        }
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //        if (requestCode == intent_RESULT_CALLBACK) {
//            if (resultCode == RESULT_OK) {
//                // Sign-in succeeded, set up the UI
//                startActivity(new Intent(this,MainActivity.class));
//                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
//            } else if (resultCode == RESULT_CANCELED) {
//                // Sign in was canceled by the user, finish the activity
//                Toast.makeText(this, "Sign in denied", Toast.LENGTH_SHORT).show();
//                //user needs to know why he got denied for sign in
//                finish();
//            }
//        }


    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//    }

}
