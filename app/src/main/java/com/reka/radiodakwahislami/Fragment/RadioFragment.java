package com.reka.radiodakwahislami.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reka.radiodakwahislami.Adapter.RadioAdapter;
import com.reka.radiodakwahislami.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RadioFragment extends Fragment {

    RecyclerView recyclerView;
    private ArrayList<String> dataNama;
    private String[] namaRadio = {"Radio Aisyah",
            "Radio Mutiara Qur'an",
            "Radio Majelis Al-Barokah",
            "Radio Sahabat Sunnah"};
    private ArrayList<String> dataUrl;
    private String[] urlRadio = { "http://radioaisyah.onlivestreaming.net:10220/stream/",
            "http://45.64.98.181:8104",
            "http://radio.majelis-albarokah.com:8040",
            "http://live.radiosunnah.net"};

    public RadioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_radio, container, false);
        dataNama = new ArrayList<>();
        dataUrl = new ArrayList<>();
        recyclerView = view.findViewById(R.id.id_rc);
        daftarItem();
        recyclerView.setAdapter(new RadioAdapter(getActivity(),dataNama,dataUrl));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        return view;
    }
    private void daftarItem() {
        for (int w = 0; w < namaRadio.length; w++) {
            dataNama.add(namaRadio[w]);
            dataUrl.add(urlRadio[w]);
        }
    }


}
