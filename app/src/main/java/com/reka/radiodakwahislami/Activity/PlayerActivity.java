package com.reka.radiodakwahislami.Activity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener {
    //retrofit
    int currentlisteners;
    String serverTitle;
    private TextView pendengar,namaRadio;
    private ProgressDialog progressDialog;


    //radio
    private ProgressBar playSeekBar;
    private Button buttonPlay;
    private Button buttonStopPlay;
    private MediaPlayer player;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initializeUIElements();
        initializeMediaPlayer();
        pendengar = findViewById(R.id.tvpendengar);
        namaRadio = findViewById(R.id.tv_namaradio);

        ambilData();
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
                pendengar.setText("Pendengar :" + currentlisteners);
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
    private void initializeUIElements() {

        playSeekBar = (ProgressBar) findViewById(R.id.progressBar1);
        playSeekBar.setMax(100);
        playSeekBar.setVisibility(View.INVISIBLE);

        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);

        buttonStopPlay = (Button) findViewById(R.id.buttonStopPlay);
        buttonStopPlay.setEnabled(false);
        buttonStopPlay.setOnClickListener(this);

    }

    public void onClick(View v) {
        if (v == buttonPlay) {
            startPlaying();
        } else if (v == buttonStopPlay) {
            stopPlaying();
        }
    }

    private void startPlaying() {
        buttonStopPlay.setEnabled(true);
        buttonPlay.setEnabled(false);

        playSeekBar.setVisibility(View.VISIBLE);

        player.prepareAsync();

        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                player.start();
            }
        });

    }

    private void stopPlaying() {
        if (player.isPlaying()) {
            player.stop();
            player.release();
            initializeMediaPlayer();
        }

        buttonPlay.setEnabled(true);
        buttonStopPlay.setEnabled(false);
        playSeekBar.setVisibility(View.INVISIBLE);
    }

    private void initializeMediaPlayer() {
        player = new MediaPlayer();

        try {
            player.setDataSource(getIntent().getStringExtra("URL"));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {

            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                playSeekBar.setSecondaryProgress(percent);
                Log.i("Buffering", "" + percent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player.isPlaying()) {
//            player.stop();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}


