package com.example.nocturnal.Fragment;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nocturnal.Model.TravelMoment;
import com.example.nocturnal.Model.TravelMomentRepository;
import com.example.nocturnal.placesearch.R;
import com.example.nocturnal.placesearch.databinding.FragmentAddTravelMomentBinding;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;
import static com.example.nocturnal.Fragment.RegisterFragment.REQUEST_IMAGE_CAPTURE;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTravelMoment extends Fragment {


    FragmentAddTravelMomentBinding binding;
    Bitmap myImage;
    Uri uri;
    TravelMomentRepository repository;
    public AddTravelMoment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.setContentView(getActivity(),R.layout.fragment_add_travel_moment);
        repository=new TravelMomentRepository();
        binding.captureMoment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
        binding.SaveTravelMoment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelMoment moment=new TravelMoment();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                myImage.compress(Bitmap.CompressFormat.PNG, 100, baos);
                String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
                moment.setIamge(imageEncoded);
                String travelId="";
                moment.setTravelId(travelId);
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                moment.setDateTime(timeStamp);
                moment.setMomentDetails(binding.momentDetails.getText().toString());
                repository.Save(moment);
            }
        });
        return inflater.inflate(R.layout.fragment_add_travel_moment, container, false);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            binding.captureMoment.setImageBitmap(imageBitmap);
            uri=data.getData();
            myImage=imageBitmap;

        }
        else{

        }
    }

}
