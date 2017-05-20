package com.example.nocturnal.PreferedShare;

import android.content.SharedPreferences;

import com.example.nocturnal.Model.Register;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by bhuiy on 5/16/2017.
 */

public class LogInPref {

    public static final String User_Id="user_id";
    public static final String Is_Log_in="is_Log_in";
    public static final String User_Name="userName";
    public static final String UserEmail="UserEmail";
    private SharedPreferences myPrefrence;
    private SharedPreferences.Editor editor;
    DatabaseReference database;
    boolean canBeLogin;
    public static final String User_Profile="user_image";
    public static final String Log_In_SHARE_PREFERENCE_NAME="LogIn";
    public ArrayList<Register> allregisters=new ArrayList<>();;
    public static final String TAG="Login";
    public LogInPref(SharedPreferences preferences)
    {
        this.myPrefrence=preferences;
        this.editor=preferences.edit();

    }
    public boolean LogIn(final String email)
    {

        database= FirebaseDatabase.getInstance().getReference().child("Tourism");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()) {
                    Register register=data.getValue(Register.class);
                    register.setImageUtl(data.getKey());
                    allregisters.add(register);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (allregisters.size()>0)
        {
            for (Register register:allregisters) {
                if (register.getEmail().toUpperCase().equals(email.toUpperCase()))
                {

                    canBeLogin=true;
                    editor.putString(User_Id, register.getImageUtl());
                    editor.putString(Is_Log_in,"true");
                    editor.putString(User_Name,register.getFullName());
                    editor.putString(UserEmail,register.getEmail());
                    editor.putString(User_Profile,register.getImagePath());
                    break;
                }
                else{
                    canBeLogin=false;
                    editor.putString(Is_Log_in,"false");
                }
            }
            editor.commit();
        }
        return canBeLogin;
    }
    public boolean IsAlreadyLoggedIn()
    {
       Boolean isLoged= Boolean.valueOf(myPrefrence.getString(Is_Log_in,"false"));
        if (isLoged==true)
        {
            return true;
        }
        else{
            return false;
        }
    }
    public String GetUserKey()
    {
        if (IsAlreadyLoggedIn())
        {
            return myPrefrence.getString(User_Id,null);
        }
        else{
            return null;
        }
    }
    public Register GetUserProfile()
    {
        Register register=new Register();
        register.setImagePath(myPrefrence.getString(User_Profile,null));
        register.setEmail(myPrefrence.getString(UserEmail,null));
        register.setFullName(myPrefrence.getString(User_Name,null));
        return register;
    }
    public void MakeNull()
    {
        editor.putString(Is_Log_in,"false");
        editor.commit();
    }
    public void LogOut()
    {
        editor.putString(User_Id,null);
        editor.putString(User_Profile,null);
        editor.putString(Is_Log_in,"false");
        editor.commit();
    }
}
