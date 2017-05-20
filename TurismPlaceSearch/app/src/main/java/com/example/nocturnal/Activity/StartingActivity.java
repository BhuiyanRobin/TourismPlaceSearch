package com.example.nocturnal.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nocturnal.Fragment.AddTravelEvent;
import com.example.nocturnal.Fragment.AddTravelExpense;
import com.example.nocturnal.Fragment.AddTravelMoment;
import com.example.nocturnal.Fragment.FragmentLogin;
import com.example.nocturnal.Fragment.RegisterFragment;
import com.example.nocturnal.Fragment.TravelEventList;
import com.example.nocturnal.Fragment.TravelExpenseList;
import com.example.nocturnal.Model.Register;
import com.example.nocturnal.PreferedShare.LogInPref;
import com.example.nocturnal.placesearch.MapsActivity;
import com.example.nocturnal.placesearch.R;
import com.google.firebase.database.DatabaseReference;

import java.io.IOException;

public class StartingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences sharedPreferences;
    DatabaseReference database;
    LogInPref logInPref;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        sharedPreferences=getSharedPreferences(LogInPref.Log_In_SHARE_PREFERENCE_NAME,MODE_PRIVATE);
        logInPref=new LogInPref(sharedPreferences);
        Register register=new Register();
        register=logInPref.GetUserProfile();
        Bitmap imageBitmap = null;
        View navView=navigationView.getHeaderView(0);
        if (register.getImagePath()==null||register.getImagePath()=="")
        {
            TextView fullname= (TextView) navView.findViewById(R.id.show_fullName);
            TextView email= (TextView) navView.findViewById(R.id.show_email);
            fullname.setText("");
            email.setText("");
        }
        else{
            try {
                imageBitmap =decodeFromFirebaseBase64(register.getImagePath());
                imageView= (ImageView)navView.findViewById(R.id.profilePic);
                imageView.setImageBitmap(imageBitmap);
                TextView fullname= (TextView) navView.findViewById(R.id.show_fullName);
                TextView email= (TextView) navView.findViewById(R.id.show_email);
                fullname.setText(register.getFullName());
                email.setText(register.getEmail());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }
    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.starting, menu);
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
        int id = item.getItemId();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (id==R.id.nav_Register)
        {
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            RegisterFragment fragmentRegisters = new RegisterFragment();
            ft.replace(R.id.SetFragment, fragmentRegisters, "fragemnent");
            ft.commit();
        }
        if (id == R.id.nearby_place) {
            Intent intent  = new Intent(StartingActivity.this,MapsActivity.class);
            startActivity(intent);
        } else if (id == R.id.weather_update) {

        } else if (id == R.id.nav_LogIn) {
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            FragmentLogin fragmentLogin = new FragmentLogin();
            ft.replace(R.id.SetFragment, fragmentLogin);
            ft.commit();

        } else if (id == R.id.nav_LogOut) {
            logInPref.LogOut();
            finish();
            startActivity(getIntent());

        }
        else if (id==R.id.nav_travel_event)
        {
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            AddTravelEvent travelEvent = new AddTravelEvent();
            ft.replace(R.id.SetFragment, travelEvent,"AddTravelEvent");
            ft.commit();
        }
        else if(id==R.id.nav_my_travel_event){
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            TravelEventList event = new TravelEventList();
            ft.replace(R.id.SetFragment, event,"MyTravelEvents");
            ft.commit();
        }
        else if(id==R.id.nav_my_add_travel_Expense)
        {
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            AddTravelExpense expense = new AddTravelExpense();
            ft.replace(R.id.SetFragment, expense,"AddTravelEvent");
            ft.commit();
        }
        else if (id==R.id.nav_add_travel_moment)
        {
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            AddTravelMoment moment = new AddTravelMoment();
            ft.replace(R.id.SetFragment, moment,"AddTravelMoment");
            ft.commit();
        }
        else if(id==R.id.nav_my_view_travel_Expense){
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            TravelExpenseList expenseList = new TravelExpenseList();
            ft.replace(R.id.SetFragment, expenseList,"AddTravelMoment");
            ft.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
