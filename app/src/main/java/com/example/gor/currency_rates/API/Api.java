package com.example.gor.currency_rates.API;

import android.annotation.SuppressLint;
import android.app.Application;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

@SuppressLint("Registered")
public class Api extends Application {

    private static CbrAPI sCbrAPI;

    @Override
    public void onCreate() {
        super.onCreate();

        @SuppressWarnings("deprecation") Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.cbr.ru/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        sCbrAPI = retrofit.create(CbrAPI.class);
    }

    public static CbrAPI getApi() {
        return sCbrAPI;
    }
}