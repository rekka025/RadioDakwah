package com.reka.radiodakwahislami.Rest;

import com.reka.radiodakwahislami.Model.RadioModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by reka on 7/17/18.
 */

public interface ApiServices {
    @GET("stats?json=1")
        //mengGet data dengan yang di jadikan patokan adalah username
    Call<RadioModel> getData();
}
