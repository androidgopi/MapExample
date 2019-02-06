package com.sreeyainfotech.mapexample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sreeyainfotech.mapexample.adapter.DetailAdapter;
import com.sreeyainfotech.mapexample.model.Loaction_model;
import com.sreeyainfotech.mapexample.model.Location_sub;
import com.sreeyainfotech.mapexample.networkcall.ApiClient;
import com.sreeyainfotech.mapexample.networkcall.Apiinterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private RecyclerView recyclerView;
    private DetailAdapter mAdapter;

    private Apiinterface apiInterface;
    List<Loaction_model> location_list = new ArrayList<>();
    List<Location_sub> location_subs = new ArrayList<>();
    private RecyclerView recycler_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        apiInterface = ApiClient.getClient().create(Apiinterface.class);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findViewes();
    }

    private void findViewes() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (isNetworkAvailable()) {
            getResult();
        } else {
            Toast.makeText(getApplicationContext(), " Please Check internet connection", Toast.LENGTH_SHORT).show();
        }

    }

    private void getResult() {
        Call<Loaction_model> call = apiInterface.doGetListResources(2);
        call.enqueue(new Callback<Loaction_model>() {
            @Override
            public void onResponse(Call<Loaction_model> call, Response<Loaction_model> response) {

                for (int i = 0; i < response.body().getLocations().size(); i++) {
                    Location_sub location_sub = new Location_sub();

                    location_sub.setId(response.body().getLocations().get(i).getId());
                    location_sub.setName(response.body().getLocations().get(i).getName());
                    location_sub.setSeries(response.body().getLocations().get(i).getSeries());

                    location_subs.add(location_sub);
                }

              //  Toast.makeText(getApplicationContext(), location_subs.get(0).getName(), Toast.LENGTH_SHORT).show();

//                for(int i=0; i<location_subs.size(); i++){
////                    location_subs.get(i).getSeries().getLang();
////                    location_subs.get(i).getId();
////                }

                locationMarkers();

                mAdapter = new DetailAdapter(MapsActivity.this,location_subs);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<Loaction_model> call, Throwable t) {

            }
        });

    }

    private void locationMarkers() {
        for (int i = 0; i < location_subs.size(); i++) {

            // Latitude & Longitude
            Double latitude = Double.valueOf(location_subs.get(i).getSeries().getLang());
            Double longitude = Double.valueOf((location_subs.get(i).getSeries().getLat()));

//            Double latitude = Double.valueOf(17.50001);
//            Double longitude = Double.valueOf((78.401527));

            MarkerOptions marker = new MarkerOptions();
            marker.position(new LatLng(latitude, longitude)).title(location_subs.get(i).getName());
            //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 5));
            mMap.addMarker(marker);
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

                    Toast.makeText(getApplicationContext(), "Marker click" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < location_subs.size(); i++) {
                        if (marker.getTitle().equalsIgnoreCase(location_subs.get(i).getName())) {
                            recyclerView.scrollToPosition(i);
                            break;
                        }
                    }
                    return false;
                }
            });
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(location_subs.get(0).getSeries().getLat()),Double.parseDouble(location_subs.get(0).getSeries().getLang())), 10));
            // mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );

        }

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
