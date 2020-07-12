package com.chaostools.ansh.Myjims;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.chaostools.ansh.Myjims.MainActivityFragments.AboutFrag;
import com.chaostools.ansh.Myjims.MainActivityFragments.AnnouncementFrag1;
import com.chaostools.ansh.Myjims.MainActivityFragments.BlankFragment1;
import com.chaostools.ansh.Myjims.MainActivityFragments.ContactFrag;
import com.chaostools.ansh.Myjims.MainActivityFragments.CoursesFrag;
import com.chaostools.ansh.Myjims.MainActivityFragments.PlacementsFrag;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG ="MainActivity" ;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.color_green_Dark));
        }
        else{
            //do nothin'.let window draw its black bg.
        }


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        drawer.setScrimColor(Color.TRANSPARENT);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);


        FragmentManager manager=getSupportFragmentManager();

        FragmentTransaction transaction=manager.beginTransaction();

            transaction.replace(R.id.mainfrag,new AboutFrag(),"HOME");
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main__drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        navigationView.setBackgroundColor(getResources().getColor(R.color.color_white));
        item.setChecked(true);
        int id = item.getItemId();

        if (id == R.id.nav_options_item_home) {
            changeAnimations(item,R.drawable.nav_view_item_selected_green,R.color.color_green_Dark);
            Fragment f=new AboutFrag();
            changeFragmentOrActivity(f,"HOME");
        }
        else if (id == R.id.nav_options_item_Courses) {
            changeAnimations(item,R.drawable.nav_view_item_selected_blue,R.color.color_blue);
            Fragment f=new CoursesFrag();
            changeFragmentOrActivity(f,"COURSES");
        }
        
        else if (id == R.id.nav_options_item_contactdetails) {
            changeAnimations(item,R.drawable.nav_view_item_selected_orange,R.color.color_orange);
            Fragment f=new ContactFrag();
            changeFragmentOrActivity(f,"CONTACTDETAILS");
            
        }
        else if (id == R.id.nav_options_item_Placements) {
            changeAnimations(item,R.drawable.nav_view_item_selected_violet,R.color.color_voilet_dark);
            Fragment f=new PlacementsFrag();
            changeFragmentOrActivity(f,"PLACEMENTS");
        }
        else if (id == R.id.nav_options_items_Announcements) {
            changeAnimations(item,R.drawable.nav_view_item_selected_blue,R.color.color_blue);
           Fragment f=new AnnouncementFrag1();
            changeFragmentOrActivity(f,"ANNOUNCEMENTS");
            
        }
        else if (id == R.id.nav_options_item_Login) {
            //should open an activity, but ok for now.
           Fragment f=new BlankFragment1();
            changeFragmentOrActivity(f,"BlankS");
            
        }
        else if (id == R.id.nav_options_item_ContactStaff) {
            //should open an activity, but ok for now.
           Fragment f=new BlankFragment1();
            changeFragmentOrActivity(f,"BlankS");
            
        }
        
        else if (id == R.id.nav_options_items_Settings) {
            changeAnimations(item,R.drawable.nav_view_item_selected_pink,R.color.pinkAccent);
            //should open an activity, but ok for now.
           Fragment f=new BlankFragment1();
            changeFragmentOrActivity(f,"BlankS");
            
        }
        else if (id == R.id.nav_options_item_appinfo) {
            changeAnimations(item,R.drawable.nav_view_item_selected_brown,R.color.brown);
            //should open an activity, but ok for now.
           Fragment f=new BlankFragment1();
            changeFragmentOrActivity(f,"BlankS");
            
        }
        else if (id == R.id.nav_options_item_bugreport) {
            changeAnimations(item,R.drawable.nav_view_item_selected_pink,R.color.pinkAccent);
            //should open an activity, but ok for now.
           
           Fragment f=new BlankFragment1();
            changeFragmentOrActivity(f,"BlankS");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       // drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void changeAnimations(MenuItem item,int itemselected_backcolorID ,int toolbarColoID){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this,toolbarColoID));
        }
        else{
            //do nothin'.let window draw its black bg.
        }
        toolbar.setBackgroundResource(toolbarColoID);
        navigationView.setItemBackgroundResource(itemselected_backcolorID);











//        Added in API level 21
//        Android Lollipop brought with it the ability to change the color of status bar in your
//        app for a more immersive user experience and in tune with Google’s Material Design Guidelines.
//        Here is how you can change the color of the status bar using the new window.setStatusBarColor
//        method introduced in API level 21.
//        Changing the color of status bar also requires setting two additional flags on the Window;
//        you need to add the FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag and clear the FLAG_TRANSLUCENT_STATUS flag.



    }

    public void changeFragmentOrActivity(Fragment fragment,String tag){

        //this needs to be done in an asynctask with a progress dialogue
        // or how about loading the frame first and then runnimg an aynctask for data? well, have to think on this
        //nah fragments need to be inflated in asynctask with some animations, else it sucks and looks very bad
        FragmentManager manager=MainActivity.this.getSupportFragmentManager();

        FragmentTransaction transaction=manager.beginTransaction();
//            if(manager.findFragmentByTag(tag)==null) {

        transaction.replace(R.id.mainfrag, fragment, tag);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
//}

        manager.executePendingTransactions();

//        AsyncTaskLoadFragment loadFragment=new AsyncTaskLoadFragment(fragment,tag);
//        loadFragment.execute();


        // manager.executePendingTransactions();
        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
//                        android.R.anim.fade_out);
//                fragmentTransaction.replace(R.id.mainfrag, new CoursesFrag());
//                fragmentTransaction.commit();
//            }
//        });
//        Runnable mPendingRunnable = new Runnable() {
//            @Override
//            public void run() {
//                // update the main content by replacing fragments
//
//
//
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
//                        android.R.anim.fade_out);
//                fragmentTransaction.replace(R.id.mainfrag, new CoursesFrag());
//                fragmentTransaction.commitAllowingStateLoss();
//            }
//        };
//
//        // If mPendingRunnable is not null, then add to the message queue
//        if (mPendingRunnable != null) {
//            mHandler.post(mPendingRunnable);
//        }

    }

//    public class AsyncTaskLoadFragment extends AsyncTask<Void,Void,Void> {
//        ProgressDialog pd;
//        Fragment fragment;
//        String tag;
//
//        public AsyncTaskLoadFragment(Fragment fragment, String tag) {
//            this.fragment = fragment;
//            this.tag = tag;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            pd = new ProgressDialog(MainActivity.this);
//            pd.setTitle("Please Wait");
//            pd.show();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//
//
//           return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void avoid) {
//            super.onPostExecute(avoid);
//            if (pd.isShowing()) {
//                pd.cancel();
//            }
//
//        }
//    }



}
