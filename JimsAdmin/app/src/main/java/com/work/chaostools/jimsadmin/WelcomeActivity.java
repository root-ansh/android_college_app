package com.work.chaostools.jimsadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    Button btCreateAnn,btDispAnn;

    private static final int iS_USER_SIGNED_IN =128 ;


    private static final String TAG =">>>WELCOME_ACTIVITY>>>>" ;
    FirebaseAuth firebaseAuthClassInstance;
    FirebaseAuth.AuthStateListener mySignInOutListener;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().setElevation(0);


        enableAuthentication();


    }


    private void enableAuthentication() {
        firebaseAuthClassInstance=FirebaseAuth.getInstance();
        mySignInOutListener= createMySignInOutListener();//listener starts listening on pause and on resume
        firebaseAuthClassInstance.addAuthStateListener(mySignInOutListener);

    }
    private FirebaseAuth.AuthStateListener createMySignInOutListener() {

        FirebaseAuth.AuthStateListener signInOutListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuthClassInstance2) {
                FirebaseUser user= firebaseAuthClassInstance2.getCurrentUser();
                if(user==null){
                    Log.e(TAG, "onAuthStateChanged: user==null recieved, launching  login/signup activity" );

                    launchFirebaseAuthUIintent();//user is signed out, launch signInUI intent
                }
                else{
                    Log.e(TAG, "onAuthStateChanged: user!=null recieved, launching main activity" );

                    initialiseLayouts();//user is logged on,run main activity tasks
                }
            }
        };

        return signInOutListener;
    }
    private void launchFirebaseAuthUIintent() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()
        );
        Intent openGoogleSignInScreen = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setAllowNewEmailAccounts(true)
                .setLogo(R.drawable.pdfpic_aldialogue_ann_l)
                .setIsSmartLockEnabled(false)
                .build();

        startActivityForResult(openGoogleSignInScreen,iS_USER_SIGNED_IN);
    }


    private void initialiseLayouts() {

        btCreateAnn=findViewById(R.id.bt_create_ann);
        btCreateAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,MActivity.class));

            }
        });

        btDispAnn=findViewById(R.id.bt_display_ann);
        btDispAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,DisplayAnnListActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: REQUEST CODE RECIEVED"+requestCode );


        if (requestCode == iS_USER_SIGNED_IN) {
            Log.e(TAG, "onActivityResult: RESULT CODE RECIEVED"+resultCode );

            if (resultCode == RESULT_OK) {

                startActivity(new Intent(this,WelcomeActivity.class));
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();

                Log.e(TAG, "onActivityResult: RESULTOK RECIEVED");
            } else if (resultCode == RESULT_CANCELED) {

                Toast.makeText(this, "Sign in denied", Toast.LENGTH_SHORT).show();

                Log.e(TAG, "onActivityResult: RESULT_DENIED RECIEVED,EXITING THE APP");
                finish();

            }
        }
    }




    @Override
    protected void onPause() {
        super.onPause();
        firebaseAuthClassInstance.addAuthStateListener(mySignInOutListener);
    }
    @Override
    protected void onResume() {
        super.onResume();
        firebaseAuthClassInstance.removeAuthStateListener(mySignInOutListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_logout){
            AuthUI.getInstance().signOut(WelcomeActivity.this);




        }
        return super.onOptionsItemSelected(item);



    }
}
