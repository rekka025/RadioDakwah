package com.reka.radiodakwahislami.Activity;


import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;

import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.Calendar;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class PlayerActivity extends AppCompatActivity {


    //retrofit
    int currentlisteners;
    String serverTitle;
    int streamstatus;
    private ImageView status;
    private TextView pendengar,namaRadio,statusServer;
    private ProgressDialog progressDialog;
    SwipeRefreshLayout swipeRefreshLayout;

    //radio
    //deklarasi widget
    private ImageView startButton;
    private ImageView stopButton;
    //exoplayer
    private SimpleExoPlayer player;
    private BandwidthMeter bandwidthMeter;
    private ExtractorsFactory extractorsFactory;
    private TrackSelection.Factory trackSelectionFactory;
    private TrackSelector trackSelector;
    private DefaultBandwidthMeter defaultBandwidthMeter;
    private DataSource.Factory dataSourceFactory;
    private MediaSource mediaSource;



    //record
    ImageView buttonStart, buttonStop ;
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder ;
    Random random ;
    public static final int RequestPermissionCode = 1;
    Chronometer simpleChronometer;


    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        status = (ImageView) findViewById(R.id.status);
        int imageBg;
        imageBg = getIntent().getIntExtra("GAMBAR",0);

        ImageView imgBlur = (ImageView) findViewById(R.id.blurBG);
        imgBlur.setImageResource(imageBg);
        ImageView img = (ImageView) findViewById(R.id.imgBG);
        img.setImageResource(imageBg);

        //getData
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                ambilData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        pendengar = (TextView) findViewById(R.id.tv_pendengar);
        namaRadio = (TextView) findViewById(R.id.tv_namaradio);

        //radio
        startButton = (ImageView) findViewById(R.id.buttonPlay);
        stopButton = (ImageView) findViewById(R.id.buttonStop);
        callRadio();
        stopButton.setVisibility(View.GONE);


        //record
        buttonStart = (ImageView) findViewById(R.id.buttonRecord);
        buttonStop = (ImageView) findViewById(R.id.buttonStoprecord);
        simpleChronometer = (Chronometer) findViewById(R.id.id_timer);
        simpleChronometer.setVisibility(View.GONE);
        random = new Random();
        startRecord();
        stopRecord();
        buttonStart.setVisibility(View.GONE);
        buttonStop.setVisibility(View.GONE);

    }

    //get data
    private void ambilData() {
        progressDialog = new ProgressDialog(PlayerActivity.this);
        progressDialog.setMessage("Loading");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();


        String url = getIntent().getStringExtra("URL");
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
                    serverTitle = response.body().getServertitle();
                    streamstatus = response.body().getStreamstatus();
                    if (streamstatus== 1){
                        status.setImageResource(R.drawable.green);
                    }else {
                        status.setImageResource(R.drawable.red);
                    }
                    pendengar.setText("Pendengar : " +currentlisteners);
                    namaRadio.setText("Nama Radio : " + serverTitle);

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
                ambilData();

                if (streamstatus == 0){
                    startButton.setVisibility(View.VISIBLE);
                    Toast.makeText(PlayerActivity.this, "Maaf Server Sedang Offline / Swipe Ke Bawah Untuk Refresh", Toast.LENGTH_SHORT).show();
                }else {
                    player.setPlayWhenReady(true);
                    startButton.setVisibility(View.GONE);
                    stopButton.setVisibility(View.VISIBLE);
                    buttonStart.setVisibility(View.VISIBLE);

                }

            }
        });
    }
    private void stop() {
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setPlayWhenReady(false);
                startButton.setVisibility(View.VISIBLE);
                stopButton.setVisibility(View.GONE);
                buttonStart.setVisibility(View.GONE);
                buttonStop.setVisibility(View.GONE);

            }
        });
    }

    //record

    public void MediaRecorderReady(){
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        mediaRecorder.setAudioEncodingBitRate(96000);
        mediaRecorder.setAudioSamplingRate(44100);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }



    private void requestPermission() {
        ActivityCompat.requestPermissions(PlayerActivity.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;


                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(PlayerActivity.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(PlayerActivity.this,"Permission",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void startRecord(){

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpleChronometer.setBase(SystemClock.elapsedRealtime());

                Calendar currentTime = Calendar.getInstance();
                int day = currentTime.get(Calendar.DAY_OF_MONTH);
                int month = currentTime.get(Calendar.MONTH);
                int year = currentTime.get(Calendar.YEAR);

                simpleChronometer.setVisibility(View.VISIBLE);
                simpleChronometer.start();
                if(checkPermission()) {

                    AudioSavePathInDevice =
                            Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +"("
                                    +day+"-"+month+"-"+year+")"+"  "+"RadioRecording.mp3" ;

                    MediaRecorderReady();

                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    buttonStart.setVisibility(View.GONE);
                    buttonStop.setVisibility(View.VISIBLE);

                    Toast.makeText(PlayerActivity.this, "Recording started",
                            Toast.LENGTH_LONG).show();
                } else {
                    requestPermission();
                }

            }
        });
    }
    private void stopRecord(){
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaRecorder.stop();
                simpleChronometer.setBase(SystemClock.elapsedRealtime());
                simpleChronometer.setVisibility(View.GONE);
                buttonStop.setVisibility(View.GONE);
                buttonStart.setVisibility(View.VISIBLE);
                Toast.makeText(PlayerActivity.this, "Recording Completed",
                        Toast.LENGTH_LONG).show();
            }
        });
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.setPlayWhenReady(false);
        stopRecord();
        finish();
    }

}



