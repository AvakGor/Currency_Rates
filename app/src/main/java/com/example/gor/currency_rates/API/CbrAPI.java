package com.example.gor.currency_rates.API;

import com.example.gor.currency_rates.Model.ValCurs;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CbrAPI {

    @GET("scripts/XML_daily_eng.asp")
    Call<ValCurs> getData(@Query("date_req") String dateReg);

}
