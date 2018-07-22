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
    private ArrayList<Integer> dataList;
    private int[] gambarList = {R.drawable.logo1,R.drawable.logo2,R.drawable.logo3,R.drawable.logo4};
    private ArrayList<String> dataNama;
    private String[] namaRadio = {
            "Radio Mutiara Qur'an",
            "Radio Majelis Al-Barokah",
            "Radio Aiysah Ummul Mu'minin",
            "Radio Ahmad Bin Hambal"};
    private ArrayList<String> dataUrl;
    private String[] urlRadio = {"http://45.64.98.181:8104",
            "http://radio.majelis-albarokah.com:8040",
            "http://103.28.148.99:9302",
            "http://103.28.148.99:9302"};
    private ArrayList<Integer> dataGambar;
    private int[] gambarStasiun = {R.drawable.gtempatradiomutiaraquran,R.drawable.gmasjidalbarokah,R.drawable.gmasjidasiyah,R.drawable.gmasjidmiah};

    public RadioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_radio, container, false);
        dataList = new ArrayList<>();
        dataNama = new ArrayList<>();
        dataUrl = new ArrayList<>();
        dataGambar = new ArrayList<>();
        recyclerView = view.findViewById(R.id.id_rc);

        recyclerView.setAdapter(new RadioAdapter(getActivity(),dataNama,dataUrl,dataGambar,dataList));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        daftarItem();
        return view;
    }
    private void daftarItem() {
        for (int w = 0; w < namaRadio.length; w++) {
            dataList.add(gambarList[w]);
            dataNama.add(namaRadio[w]);
            dataUrl.add(urlRadio[w]);
            dataGambar.add(gambarStasiun[w]);
        }
    }


}
