package com.reka.radiodakwahislami.Activity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.reka.radiodakwahislami.Model.RadioModel;
import com.reka.radiodakwahislami.R;
import com.reka.radiodakwahislami.Rest.ApiServices;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayerActivity extends AppCompatActivity {


    //retrofit
    int currentlisteners;
    String serverTitle;
    private TextView pendengar,namaRadio;
    private ProgressDialog progressDialog;


    //radio
    //deklarasi widget
    private Button startButton;
    private Button stopButton;

    //
    private SimpleExoPlayer player;
    private BandwidthMeter bandwidthMeter;
    private ExtractorsFactory extractorsFactory;
    private TrackSelection.Factory trackSelectionFactory;
    private TrackSelector trackSelector;
    private DefaultBandwidthMeter defaultBandwidthMeter;
    private DataSource.Factory dataSourceFactory;
    private MediaSource mediaSource;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        pendengar = (TextView) findViewById(R.id.tv_pendengar);
        namaRadio = (TextView) findViewById(R.id.tv_namaradio);
        //getdata
        ambilData();

        startButton = (Button) findViewById(R.id.buttonPlay);
        stopButton = (Button) findViewById(R.id.buttonStop);

        callRadio();

    }
    //get data
    private void ambilData() {
        progressDialog = new ProgressDialog(PlayerActivity.this);
        progressDialog.setMessage("Loading");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();


        String url = getIntent().getStringExtra("URL");

        Toast.makeText(this, "ini data" + url, Toast.LENGTH_SHORT).show();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit
                retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        ApiServices api = retrofit.create(ApiServices.class);
        Call<RadioModel> call = api.getData();
        call.enqueue(new Callback<RadioModel>() {
            @Override
            public void onResponse(Call<RadioModel> call, Response<RadioModel> response) {
                progressDialog.dismiss();
                    currentlisteners = response.body().getCurrentlisteners();
                    pendengar.setText("Pendengar :" +currentlisteners);
                    serverTitle = response.body().getServertitle();
                    namaRadio.setText("Nama Radio :" + serverTitle);
            }

            @Override
            public void onFailure(Call<RadioModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PlayerActivity.this, "Cek Koneksi Anda", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //radio
    private void callRadio(){
        bandwidthMeter = new DefaultBandwidthMeter();
        extractorsFactory = new DefaultExtractorsFactory();

        trackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);

        trackSelector = new DefaultTrackSelector(trackSelectionFactory);

/*        dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "mediaPlayerSample"),
                (TransferListener<? super DataSource>) bandwidthMeter);*/

        defaultBandwidthMeter = new DefaultBandwidthMeter();
        dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "mediaPlayerSample"), defaultBandwidthMeter);

        String urlRadio = getIntent().getStringExtra("URL");

        mediaSource = new ExtractorMediaSource(Uri.parse(urlRadio), dataSourceFactory, extractorsFactory, null, null);

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);


        player.prepare(mediaSource);

        play();
        stop();



    }



    private void play() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                player.setPlayWhenReady(true);
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
            }
        });
    }
    private void stop() {
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setPlayWhenReady(false);
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
            }
        });
    }
    //
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        player.setPlayWhenReady(false);
//    }

}


