package com.reka.radiodakwahislami.Adapter;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.reka.radiodakwahislami.Activity.PlayerActivity;
import com.reka.radiodakwahislami.Fragment.RadioFragment;
import com.reka.radiodakwahislami.R;


import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by reka on 7/17/18.
 */

public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.RadioVH>{

    Context context;
    private ArrayList<String> arrayList;
    private ArrayList<String> arrayListRadio;

    public RadioAdapter(Context context, ArrayList<String> arrayList, ArrayList<String> arrayListRadio) {
        this.context = context;
        this.arrayList = arrayList;
        this.arrayListRadio = arrayListRadio;
    }

    @Override
    public RadioAdapter.RadioVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_view,parent,false);
        RadioVH radioVH = new RadioVH(view);
        return radioVH;
    }

    @Override
    public void onBindViewHolder(final RadioAdapter.RadioVH holder, final int position) {
        final String nama = arrayList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(context, PlayerActivity.class);
                pindah.putExtra("URL", arrayListRadio.get(position));

                context.startActivity(pindah);


            }
        });
        holder.namaRadio.setText(nama);
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RadioVH extends RecyclerView.ViewHolder {
        private TextView namaRadio;
        public RadioVH(View itemView) {
            super(itemView);
            namaRadio = itemView.findViewById(R.id.id_radio);

        }
    }
}
