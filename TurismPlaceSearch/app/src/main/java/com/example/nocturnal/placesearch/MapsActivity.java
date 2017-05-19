package com.example.nocturnal.placesearch;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nocturnal.Detailes.PlaceDetailes;
import com.example.nocturnal.NearbyPlace.NearbyPlacess;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.os.SystemClock.sleep;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private String nearByPlacessFullUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyC35ucq8CaVii5htoOZV2wL1wM_CX7WyYM";
    private String nearByPlacessBaseUrl = "https://maps.googleapis.com/";

    private GoogleMap mMap;
    private GPSTracker gpsTracker;
    private Location mlocation;
    double latitude, longitude;

    private NearbyPlacess nearbyPlacess;
    private Marker marker;

    private LatLng searchedLocation;
    private String placeName;
    private String placeAddress;
    private String placeTypes;
    private String nearByPlacessChangableUrl;
    private String detailePlacesChangableUrl;
    private String narebyPlaceId;

    private Geocoder geocoder;
    private NearbyPlaceApi nearbyPlaceApi;

    private AlertDialog.Builder myCustomAlert;
    private ArrayList<Object> selectedSearchCat = new ArrayList<Object>();


    private CheckBox bankCheckbox,bus_stationCheckbox,cafeCheckbox,churchCheckbox,
            doctorCheckbox,embassyCheckbox,hospitalCheckbox,parkCheckbox,
            policeCheckbox,restaurantCheckbox,shopping_mallCheckbox,subway_stationCheckbox,
            taxi_standCheckbox;

    private String placeDetaileFormatedName,placeDetaileFormatedAddress,placeDetailType,placeDetaileFormatedPhone,
            placeDetaileWeekday,placeDetaileWebsite;

    private ArrayList<String> nearbyPlacessName = new ArrayList<String>();
    private ArrayList<String> nearbyPlacesId = new ArrayList<String>();

    private String chosenPlace = "";


    TextView formatted_place_nameTv ,formatted_addressTv,typeTv ,formatted_phnTv ,weekday_textTv ,webTv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        myCustomAlert = new AlertDialog.Builder(this);
        geocoder = new Geocoder(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(nearByPlacessBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        nearbyPlaceApi = retrofit.create(NearbyPlaceApi.class);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {

            @Override
            public void onPlaceSelected(Place place) {
                mMap.clear();
                placeName = (String) place.getName();
                searchedLocation = place.getLatLng();
                setMarker(searchedLocation);
                placeAddress = place.getAddress().toString();
                placeTypes = place.getPlaceTypes().toString();
            }
            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                //Log.i(TAG, "An error occurred: " + status);
            }
        });

        gpsTracker = new GPSTracker(getApplicationContext());
        mlocation = gpsTracker.getLocation();
        latitude = mlocation.getLatitude();
        longitude = mlocation.getLongitude();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setMarker(LatLng searchedLocation) {
        if (marker != null){
            marker.remove();
        }
        MarkerOptions markerOptions = new MarkerOptions()
                .title(placeName)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                .position(searchedLocation);
        marker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(searchedLocation, 14));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mMap != null){
            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter(){

                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View view = getLayoutInflater().inflate(R.layout.place_info_window,null);
                    TextView placeNmaeTv = (TextView) view.findViewById(R.id.place_name);
                    placeNmaeTv.setText(marker.getTitle());
                    chosenPlace = placeNmaeTv.getText().toString();
                    return view;
                }
            });
            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(final Marker marker) {
                    List<Address> addresses = null;
                    try {addresses = geocoder.getFromLocationName(placeName,1);
                    }catch (IOException e) {e.printStackTrace();}

                    TextView title = new TextView(MapsActivity.this);
                    title.setText(addresses.get(0).getFeatureName());
                    title.setGravity(Gravity.CENTER);
                    title.setTextSize(20);
                    myCustomAlert.setCustomTitle(title);

                    LayoutInflater inflater = MapsActivity.this.getLayoutInflater();
                    final View dialogView = inflater.inflate(R.layout.more_layout_option, null);
                    myCustomAlert.setView(dialogView);

                    //region viewInitial
                    ImageView placeImageTv = (ImageView) dialogView.findViewById(R.id.placeImage);
                    TextView placeNmaeTv = (TextView) dialogView.findViewById(R.id.place_name);
                    Button moreinfoBTN = (Button) dialogView.findViewById(R.id.more_info);
                    Button nearbyPlaceBTN = (Button) dialogView.findViewById(R.id.nearby_palce);
                    Button findPlaceBTN = (Button) dialogView.findViewById(R.id.find_place);

                    bankCheckbox = (CheckBox) dialogView.findViewById(R.id.bank);
                    cafeCheckbox = (CheckBox) dialogView.findViewById(R.id.cafe);
                    doctorCheckbox = (CheckBox) dialogView.findViewById(R.id.doctor);
                    embassyCheckbox = (CheckBox) dialogView.findViewById(R.id.embassy);
                    hospitalCheckbox = (CheckBox) dialogView.findViewById(R.id.hospital);
                    parkCheckbox = (CheckBox) dialogView.findViewById(R.id.park);
                    policeCheckbox = (CheckBox) dialogView.findViewById(R.id.police);
                    restaurantCheckbox = (CheckBox) dialogView.findViewById(R.id.restaurant);
                    //endregion

                    placeNmaeTv.setText(placeAddress);

                    nearbyPlaceBTN.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout searchKindLayout = (LinearLayout) dialogView.findViewById(R.id.search_kind);
                            Button findPlaceBTN = (Button) dialogView.findViewById(R.id.find_place);
                            Button nearbyPlaceBTN = (Button) dialogView.findViewById(R.id.nearby_palce);
                            nearbyPlaceBTN.setVisibility(View.INVISIBLE);
                            findPlaceBTN.setVisibility(View.VISIBLE);
                            searchKindLayout.setVisibility(View.VISIBLE);
                        }
                    });

                    findPlaceBTN.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            List<Address> addresses = null;
                            String searchInput = "";

                            for (Object search : selectedSearchCat){searchInput =  search +"|"+searchInput;}
                            try {addresses = geocoder.getFromLocationName(placeName,1);
                            } catch (IOException e) {e.printStackTrace();}
                            nearByPlacessChangableUrl = "maps/api/place/nearbysearch/json?location="+addresses.get(0).getLatitude()+","+addresses.get(0).getLongitude()+"&radius=500&types="+searchInput+"&key=AIzaSyC35ucq8CaVii5htoOZV2wL1wM_CX7WyYM";
                            getNearbyPlaceData();

                        }

                    });

                    AlertDialog b = myCustomAlert.create();
                    b.show();
                    
                    moreinfoBTN.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView title = new TextView(MapsActivity.this);
                            title.setText("");
                            title.setGravity(Gravity.CENTER);
                            title.setTextSize(20);
                            myCustomAlert.setCustomTitle(title);
                            LayoutInflater inflater = MapsActivity.this.getLayoutInflater();
                            final View dialogView = inflater.inflate(R.layout.palce_detaile, null);

                            int index = nearbyPlacessName.indexOf(chosenPlace);
                            narebyPlaceId = nearbyPlacesId.get(index);
                            detailePlacesChangableUrl = "maps/api/place/details/json?placeid="+narebyPlaceId+"&key=AIzaSyC35ucq8CaVii5htoOZV2wL1wM_CX7WyYM";
                            getPlaceDetaileData();
                            //region init
                             formatted_place_nameTv = (TextView) dialogView.findViewById(R.id.formatted_place_name);
                             formatted_addressTv = (TextView) dialogView.findViewById(R.id.formatted_address);
                             typeTv = (TextView) dialogView.findViewById(R.id.type);
                             formatted_phnTv = (TextView) dialogView.findViewById(R.id.formatted_phn);
                             weekday_textTv = (TextView) dialogView.findViewById(R.id.weekday_text);
                             webTv = (TextView) dialogView.findViewById(R.id.web);
                            //endregion
                            myCustomAlert.setView(dialogView);
                            AlertDialog b = myCustomAlert.create();
                            b.show();
                        }
                    });
                }
            });
        }

        LatLng myLocation = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions()
                .title("Nocturnal")
                .position(myLocation);
        marker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 14));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
    }

    private void getPlaceDetaileData()  {
        Call<PlaceDetailes> DetaileeDataCall = nearbyPlaceApi.getPlaceDetaileData(detailePlacesChangableUrl);

        DetaileeDataCall.enqueue(new Callback<PlaceDetailes>() {
            @Override
            public void onResponse(Call<PlaceDetailes> call, Response<PlaceDetailes> response) {
                PlaceDetailes placeDetailes = response.body();

                try {
                placeDetaileFormatedName = placeDetailes.getResult().getName();
                placeDetaileFormatedAddress = placeDetailes.getResult().getFormattedAddress();
                placeDetailType = placeDetailes.getResult().getTypes().get(0).toString();
                placeDetaileFormatedPhone = placeDetailes.getResult().getFormattedPhoneNumber();
                placeDetaileWeekday = placeDetailes.getResult().getOpeningHours().getWeekdayText().toString();
                placeDetaileWeekday = placeDetaileWeekday.replaceAll("\\["," ");
                placeDetaileWeekday = placeDetaileWeekday.replaceAll("\\]"," ");
                placeDetaileWeekday = placeDetaileWeekday.replaceAll(", S",",\nS");
                placeDetaileWeekday = placeDetaileWeekday.replaceAll(","," ");
                placeDetaileWeekday = placeDetaileWeekday.replaceAll("day:","day:\n");
                placeDetaileWebsite = placeDetailes.getResult().getWebsite();
                } catch (Exception e) {e.printStackTrace();}

                formatted_place_nameTv.setText(placeDetaileFormatedName);
                formatted_addressTv.setText(placeDetaileFormatedAddress);
                typeTv.setText(placeDetailType);
                formatted_phnTv.setText(placeDetaileFormatedPhone);
                weekday_textTv.setText(placeDetaileWeekday);
                webTv.setText(placeDetaileWebsite);
            }

            @Override
            public void onFailure(Call<PlaceDetailes> call, Throwable t) {

            }
        });

    }

    private void getNearbyPlaceData() {
        Call<NearbyPlacess> NearbyPlaceDataCall = nearbyPlaceApi.getNearbyPlaceData(nearByPlacessChangableUrl);

        NearbyPlaceDataCall.enqueue(new Callback<NearbyPlacess>() {
            @Override
            public void onResponse(Call<NearbyPlacess> call, Response<NearbyPlacess> response) {

                nearbyPlacess = response.body();
                nearbyPlacessName.clear();
                nearbyPlacesId.clear();

                for (int i =0;i<nearbyPlacess.getResults().size();i++){
                    Double nearbyPlaceLat =  nearbyPlacess.getResults().get(i).getGeometry().getLocation().getLat();
                    Double nearbyPlacelong = nearbyPlacess.getResults().get(i).getGeometry().getLocation().getLng();

                    LatLng nearbyLocation = new LatLng(nearbyPlaceLat, nearbyPlacelong);
                    String narebyPlaceName = nearbyPlacess.getResults().get(i).getName();
                    String narebyPlaceId = nearbyPlacess.getResults().get(i).getPlaceId();

                    nearbyPlacessName.add(narebyPlaceName);
                    nearbyPlacesId.add(narebyPlaceId);
                    
                    /*Toast.makeText(MapsActivity.this,nearbyPlacessName.get(i)+"" , Toast.LENGTH_SHORT).show();
                    Toast.makeText(MapsActivity.this,nearbyPlacesId.get(i)+"" , Toast.LENGTH_SHORT).show();*/

                    setMarkerNearby(nearbyLocation, narebyPlaceName);
                }
            }

            @Override
            public void onFailure(Call<NearbyPlacess> call, Throwable t) {

            }
        });

    }

    private void setMarkerNearby(LatLng searchedLocation, String name) {

        MarkerOptions markerOptions = new MarkerOptions()
                .title(name)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                .position(searchedLocation);
        marker = mMap.addMarker(markerOptions);
    }

    public void changeType(View view) {
        if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL){
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }else {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }

    public void selectFood(View view) {
        boolean status = ((CheckBox)view).isChecked();


        switch (view.getId()){
            case R.id.bank:
                if(status){
                    selectedSearchCat.add(bankCheckbox.getText().toString());
                }else {
                    selectedSearchCat.remove(bankCheckbox.getText().toString());
                }
                break;
            case R.id.cafe:
                if(status){
                    selectedSearchCat.add(cafeCheckbox.getText().toString());
                }else {
                    selectedSearchCat.remove(cafeCheckbox.getText().toString());
                }
                break;
            case R.id.doctor:
                if(status){
                    selectedSearchCat.add(doctorCheckbox.getText().toString());
                }else {
                    selectedSearchCat.remove(doctorCheckbox.getText().toString());
                }
                break;
            case R.id.embassy:
                if(status){
                    selectedSearchCat.add(embassyCheckbox.getText().toString());
                }else {
                    selectedSearchCat.remove(embassyCheckbox.getText().toString());
                }
                break;
            case R.id.hospital:
                if(status){
                    selectedSearchCat.add(hospitalCheckbox.getText().toString());
                }else {
                    selectedSearchCat.remove(hospitalCheckbox.getText().toString());
                }
                break;
            case R.id.park:
                if(status){
                    selectedSearchCat.add(parkCheckbox.getText().toString());
                }else {
                    selectedSearchCat.remove(parkCheckbox.getText().toString());
                }
                break;
            case R.id.police:
                if(status){
                    selectedSearchCat.add(policeCheckbox.getText().toString());
                }else {
                    selectedSearchCat.remove(policeCheckbox.getText().toString());
                }
                break;
            case R.id.restaurant:
                if(status){
                    selectedSearchCat.add(restaurantCheckbox.getText().toString());
                }else {
                    selectedSearchCat.remove(restaurantCheckbox.getText().toString());
                }
                break;

        }
    }

    
}
