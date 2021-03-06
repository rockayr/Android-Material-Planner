package com.example.shivam.materialnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.parse.Parse;
import com.parse.ParseUser;

import java.util.ArrayList;

import br.liveo.interfaces.NavigationLiveoListener;
import br.liveo.navigationliveo.NavigationLiveo;


public class MainActivity extends NavigationLiveo implements NavigationLiveoListener {

    public ArrayList<String> mListNameItem;
    ParseUser currentUser;
    @Override
    public void onInt(Bundle bundle) {
        currentUser = ParseUser.getCurrentUser();

        if(currentUser==null)
        {
            Intent i = new Intent(MainActivity.this, SignInActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        this.setNavigationListener(this);
        this.setDefaultStartPositionNavigation(0);
        this.removeSelectorNavigation();
        this.setColorSelectedItemNavigation(R.color.nliveo_blue_colorPrimary);
        mListNameItem.add(0, "Notes");
        mListNameItem.add(1, "Reminders");
        mListNameItem.add(2, "Upcoming Events");


        ArrayList<Integer> mListIconItem = new ArrayList<>();
        mListIconItem.add(0, R.drawable.ic_create_black_24dp);
        mListIconItem.add(1, R.drawable.ic_alarm_black_24dp); //Item no icon set 0
        mListIconItem.add(2, R.drawable.ic_notifications_black_24dp); //Item no icon set 0

        ArrayList<Integer> mListHeaderItem = new ArrayList<>();
        mListHeaderItem.add(4);


        this.setFooterNavigationVisible(true);
        this.setFooterInformationDrawer("Feedback", R.drawable.ic_settings_black_24dp);
        SparseIntArray mSparseCounterItem = new SparseIntArray(); //indicate all items that have a counter
        //this.setFooterNavigationVisible(false);
        this.setNavigationAdapter(mListNameItem, mListIconItem, mListHeaderItem, mSparseCounterItem);

        //this.setNavigationAdapter(mListNameItem,mListIconItem);
    }

    @Override
    public void onUserInformation() {

            currentUser = ParseUser.getCurrentUser();
        if(currentUser!=null) {
            this.mUserName.setText(currentUser.getString("Name"));
            this.mUserEmail.setText(currentUser.getEmail());
            this.mUserBackground.setImageResource(R.drawable.background3);
        }
        else
        {
            Intent i = new Intent(MainActivity.this, SignInActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        else if(id == R.id.action_logout)
        {
            ParseUser.logOut();
            Intent i = new Intent(MainActivity.this, SignInActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        else if(id == R.id.action_search)
        {

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClickNavigation(int position, int containerLayout) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        Fragment mFragment;
        switch(position) {
            case 0:
                this.getToolbar().setTitle("Notes");
                mFragment = new NotesFragment().newInstance();
                if (mFragment != null){
                    mFragmentManager.beginTransaction().replace(containerLayout, mFragment).commit();
                }
                break;
            case 1:
                this.getToolbar().setTitle("Reminders");
                mFragment = new RemindersFragment().newInstance();
                if (mFragment != null){
                    mFragmentManager.beginTransaction().replace(containerLayout, mFragment).commit();
                }
                break;
            case 2:
                this.getToolbar().setTitle("Upcoming Events");
                mFragment = new RemindersFragment().newInstance();
                if (mFragment != null){
                    mFragmentManager.beginTransaction().replace(containerLayout, mFragment).commit();
                }
                break;

        }


    }

    @Override
    public void onPrepareOptionsMenuNavigation(Menu menu, int i, boolean b) {

    }

    @Override
    public void onClickFooterItemNavigation(View view) {

    }

    @Override
    public void onClickUserPhotoNavigation(View view) {

    }

    /*FloatingActionButton mNotes, mReminders;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        mNotes = (FloatingActionButton) findViewById(R.id.make_notes);
        mReminders = (FloatingActionButton) findViewById(R.id.make_reminder);
        mNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Note",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, MakeNoteActivity.class);
                startActivity(i);
            }
        });
    }


    protected void setActionBarIcon(int iconRes) {

        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    }*/
}
