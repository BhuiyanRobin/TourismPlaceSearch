package com.example.nocturnal.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nocturnal.Activity.StartingActivity;
import com.example.nocturnal.Model.LogIn;
import com.example.nocturnal.Model.Register;
import com.example.nocturnal.PreferedShare.LogInPref;
import com.example.nocturnal.placesearch.R;
import com.example.nocturnal.placesearch.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment {

    FragmentLoginBinding binding;
    Register register;
    Bitmap myImage;
    LogInPref logInPref;
    SharedPreferences sharedPreferences;
    FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public FragmentLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.setContentView(getActivity(),R.layout.fragment_login);
        sharedPreferences=getActivity().getSharedPreferences(LogInPref.Log_In_SHARE_PREFERENCE_NAME,MODE_PRIVATE);
        logInPref=new LogInPref(sharedPreferences);
        auth=FirebaseAuth.getInstance();
        logInPref.LogIn("hello@live.com");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(LogInPref.TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(LogInPref.TAG, "onAuthStateChanged:signed_out");
                }

            }
        };

        if (logInPref.IsAlreadyLoggedIn()==true)
        {
           // Intent intent=new Intent(this,);
           // startActivity(intent);
        }
        else{
            binding.btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final LogIn logIn=new LogIn(binding.inputEmail.getText().toString(),binding.inputPassword.getText().toString());
                    final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                            R.style.Theme_AppCompat_DayNight_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Authenticating...");
                    progressDialog.show();
auth.signInWithEmailAndPassword(binding.inputEmail.getText().toString(),binding.inputPassword.getText().toString()).addOnCompleteListener(getActivity(),new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        auth.getCurrentUser().getEmail().toString();
    }
});
                    boolean isLogIn=logInPref.LogIn(auth.getCurrentUser().getEmail());

                    //ArrayList<Register> a=LogInPref.allregisters;
                    if (isLogIn==true)
                    {
                        Intent intent=new Intent(getActivity(), StartingActivity.class);
                        startActivity(intent);
                        progressDialog.dismiss();
                    }
                    else{
                      //  Toast.makeText(getActivity(),"User Name or Password is incorrect",Toast.LENGTH_SHORT).show();
                        //progressDialog.dismiss();
                    }
                }
            });
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

}
