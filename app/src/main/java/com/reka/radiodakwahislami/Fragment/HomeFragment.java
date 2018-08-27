package com.reka.radiodakwahislami.Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;

import com.reka.radiodakwahislami.Activity.InformasiActivity;
import com.reka.radiodakwahislami.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    GridLayout gridLayout;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        gridLayout = (GridLayout) view.findViewById(R.id.mainGrid);

        //set event
        setSingleEvent(gridLayout);
        return view;
    }

    private void setSingleEvent(final GridLayout gridLayout) {
        //loop all child item of main grid
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            //you can see, all child item is cardview, so we just cast object to cardview
            final CardView cardView = (CardView) gridLayout.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1)
                    {
                        // warna background ketika di klik
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6FF0"));
                        if (finalI == 0){
                            dataAhmadbinhambal();
                        }else if (finalI ==1){
                            dataAlbarokah();
                        }else if (finalI ==2){
                            dataMutiaraquran();

                        }else if (finalI ==3){
                            dataAisyah();

                        }
                        getActivity().finish();

                    }else {
                        // warna background ketika di klik
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));

                    }

                }
                //nama dan nomor kudu di perbarui
                private void dataAisyah() {
                    String mapAisyah = "-7.075164, 110.359270";
                    String linkText = "<a href='http://yayasankhodijah.com/radioaisyah//'>Masjid Ummul Mu'minin Aisyah</a>";
                    String title = "Masjid Ummul Mu'minin Aisyah";
                    String subTitle = "Jl. Raya Manyaran-Gunungpati, Cepoko, Gn. Pati\n Kota Semarang, Jawa Tengah 50223";
                    String nPutra = "WhatApp : ";
                    String waPutra = "+6281290890305";
                    int gambar = R.drawable.gmasjidasiyah;


                    Intent intent = new Intent(getActivity(), InformasiActivity.class);
                    intent.putExtra("GAMBAR",gambar);
                    intent.putExtra("NAMAPUTRA",nPutra);
                    intent.putExtra("link",linkText);
                    intent.putExtra("Lang",mapAisyah);
                    intent.putExtra("Tittle",title);
                    intent.putExtra("subTittle",subTitle);
                    intent.putExtra("WAPUTRA",waPutra);
                    startActivity(intent);
                }

                private void dataMutiaraquran() {
                    String mapMutiaraquran = "-6.959225, 110.491620";
                    String linkText = "<a href='https://radiomutiaraquran.com/'>Radio Mutiara Qur'an</a>";
                    String title = "Radio Mutiara Qur'an";
                    String subTitle = "Karangroto, Genuk\n Kota Semarang, Jawa Tengah 50117";
                    String nPutra = "WhatApp/SMS :";
                    String waPutra = "+6289625984276";
                    int gambar = R.drawable.gtempatradiomutiaraquran;


                    Intent intent = new Intent(getActivity(), InformasiActivity.class);
                    intent.putExtra("GAMBAR",gambar);
                    intent.putExtra("NAMAPUTRA",nPutra);
                    intent.putExtra("link",linkText);
                    intent.putExtra("Lang",mapMutiaraquran);
                    intent.putExtra("Tittle",title);
                    intent.putExtra("subTittle",subTitle);
                    intent.putExtra("WAPUTRA",waPutra);
                    startActivity(intent);
                }

                private void dataAhmadbinhambal() {
                    String mapAhmadbinhambal = "-7.053364, 110.469933";
                    String linkText = "<a href='http://binhambal.or.id/'>Madrasah Imam Ahmad Bin Hambal</a>";
                    String title = "Masjid Imam Ahmad Bin Hambal";
                    String subTitle = "Jl.Durenan Asri Meteseh, Mangunharjo, Tembalang\nKota Semarang, Jawa Tengah 50271";
                    String nPutra = "WhatApp : ";
                    String waPutra = "+6285727290077";
                    int gambar = R.drawable.gmasjidmiah;


                    Intent intent = new Intent(getActivity(), InformasiActivity.class);
                    intent.putExtra("GAMBAR",gambar);
                    intent.putExtra("NAMAPUTRA",nPutra);
                    intent.putExtra("link",linkText);
                    intent.putExtra("Lang",mapAhmadbinhambal);
                    intent.putExtra("Tittle",title);
                    intent.putExtra("subTittle",subTitle);
                    intent.putExtra("WAPUTRA",waPutra);
                    startActivity(intent);

                }
                private void dataAlbarokah() {

                    String mapAlbarokah = "-7.007054, 110.426995";
                    String title = "Masjid Al- Barokah";
                    String linkText = "<a href='http://majelis-albarokah.com/'>Masjid Al-Barokah</a>";
                    String subTitle = "Jl. Tegalsari Raya No.41, Candi, Candisari\nKota Semarang, Jawa Tengah 50614";
                    String nPutra = "WhatApp Putra :";
                    String nPutri = "WhatApp Putri  :";
                    String waPutra = "+6281325888015";
                    String waPutri = "+6281325914002";
                    int gambar = R.drawable.gmasjidalbarokah;


                    Intent intent = new Intent(getActivity(), InformasiActivity.class);
                    intent.putExtra("GAMBAR",gambar);
                    intent.putExtra("NAMAPUTRA",nPutra);
                    intent.putExtra("NAMAPUTRI",nPutri);
                    intent.putExtra("link",linkText);
                    intent.putExtra("Lang",mapAlbarokah);
                    intent.putExtra("Tittle",title);
                    intent.putExtra("subTittle",subTitle);
                    intent.putExtra("WAPUTRA",waPutra);
                    intent.putExtra("WAPUTRI",waPutri);
                    startActivity(intent);
                }

            });
        }
    }

}


