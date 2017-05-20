package com.example.nocturnal.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nocturnal.Model.Register;
import com.example.nocturnal.PreferedShare.LogInPref;
import com.example.nocturnal.placesearch.R;
import com.example.nocturnal.placesearch.databinding.FragmentRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    FragmentRegisterBinding registerBinding;
    DatabaseReference database;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    StorageReference mRef;
    Uri uri;
    private ProgressDialog progressDialog;
    Register register;
    Bitmap myImage;
    LogInPref logInPref;
    SharedPreferences sharedPreferences;
    FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public RegisterFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        auth=FirebaseAuth.getInstance();
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

        registerBinding= DataBindingUtil.setContentView(getActivity(),R.layout.fragment_register);
        mRef= FirebaseStorage.getInstance().getReference();
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait we are registered you....");
        register=new Register();
        registerBinding.SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();
                register.setPassword(registerBinding.password.getText().toString());
                register.setFullName(registerBinding.fullName.getText().toString());
                register.setEmail(registerBinding.email.getText().toString().toLowerCase());
                register.setEmergencyConNo(registerBinding.emergencyNo.getText().toString());
                register.setAddress(registerBinding.address.getText().toString());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                myImage.compress(Bitmap.CompressFormat.PNG, 100, baos);
                String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
                register.setImagePath(imageEncoded);
                database= FirebaseDatabase.getInstance().getReference("Tourism");
                database.push().setValue(register);
                progressDialog.dismiss();

                auth.createUserWithEmailAndPassword(register.getEmail(),register.getPassword()).addOnCompleteListener(getActivity(),new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                        }
                        else{
                            FirebaseAuthException e = (FirebaseAuthException )task.getException();
                            Toast.makeText(getActivity(), "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                FragmentLogin fragmentLogin = new FragmentLogin();
                ft.replace(R.id.SetFragment, fragmentLogin);
                ft.commit();

            }
        });
        registerBinding.takepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        return inflater.inflate(R.layout.fragment_register, container, false);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            registerBinding.iamge.setImageBitmap(imageBitmap);
            uri=data.getData();
            myImage=imageBitmap;

        }
        else{

        }
    }

}
