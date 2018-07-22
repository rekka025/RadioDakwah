package com.reka.radiodakwahislami.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.reka.radiodakwahislami.Adapter.CostumeWindowAdapter;
import com.reka.radiodakwahislami.MainActivity;
import com.reka.radiodakwahislami.R;

public class InformasiActivity extends AppCompatActivity implements OnMapReadyCallback {
    MapView mapView;
    TextView tv_waputra, tv_waputri;
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //gambar
        int gambar = getIntent().getIntExtra("GAMBAR",0);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.id_colap);
        collapsingToolbarLayout.setBackgroundResource(gambar);
        collapsingToolbarLayout.setTitle(getIntent().getStringExtra("Tittle"));

        //website
        TextView link = (TextView) findViewById(R.id.web);
        tv_waputra = (TextView) findViewById(R.id.putra);
        tv_waputri = (TextView) findViewById(R.id.putri);
        String waPutri1 = getIntent().getStringExtra("NAMAPUTRI");
        String waPutri2 = getIntent().getStringExtra("WAPUTRI");
        tv_waputra.setText(getIntent().getStringExtra("NAMAPUTRA")+"  "+getIntent().getStringExtra("WAPUTRA"));
        tv_waputri.setText(waPutri1+"  "+waPutri2);

        if (waPutri1 == null && waPutri2 == null){
            tv_waputri.setVisibility(View.INVISIBLE);
        }



        //get background
        link.setText(Html.fromHtml(getIntent().getStringExtra("link")));
        link.setMovementMethod(LinkMovementMethod.getInstance());

        mapView = (MapView) findViewById(R.id.map);
        if (mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(InformasiActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(this);
        String[] latlong =  getIntent().getStringExtra("Lang").split(",");
        double latitude = Double.parseDouble(latlong[0]);
        double longitude = Double.parseDouble(latlong[1]);
        LatLng posisi = new LatLng(latitude,longitude);



        String title = getIntent().getStringExtra("Tittle");
        String subTitle = getIntent().getStringExtra("subTittle");



        //Marker
        MarkerOptions markerOpt = new MarkerOptions();
        markerOpt.position(posisi)
                .title(title)
                .snippet(subTitle);

        //Set Custom InfoWindow Adapter
        CostumeWindowAdapter adapter = new CostumeWindowAdapter(InformasiActivity.this);
        googleMap.setInfoWindowAdapter(adapter);

        googleMap.addMarker(markerOpt).showInfoWindow();

        CameraPosition Masjid = CameraPosition.builder().target(posisi)
                .zoom(16)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Masjid));

    }

    public void putra(View view) {
        String putrawa = getIntent().getStringExtra("WAPUTRA");
        try {
            Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + "" + putrawa + "?body=" + ""));
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(InformasiActivity.this, "Silahkan Install Aplikasi WhatApp Terlebih Dahulu", Toast.LENGTH_LONG).show();

        }
    }
    public void putri(View view) {
        String putriwa = getIntent().getStringExtra("WAPUTRI");
        try {
            Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + "" + putriwa + "?body=" + ""));
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(InformasiActivity.this, "Silahkan Install Aplikasi WhatApp Terlebih Dahulu", Toast.LENGTH_LONG).show();
        }
    }
}
