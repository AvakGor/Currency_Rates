package com.example.gor.currency_rates.Activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gor.currency_rates.API.Api;
import com.example.gor.currency_rates.Adapter.Adapter;
import com.example.gor.currency_rates.Model.ValCurs;
import com.example.gor.currency_rates.Model.Valute;
import com.example.gor.currency_rates.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    TextView mTextView;
    ArrayList<Valute> mValutes;
    String date;


    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mValutes= new ArrayList<>();
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTextView = findViewById(R.id.update_info);
        date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        final Handler mHandler = new Handler();
        new Thread(new Runnable(){
            @Override
            public void run () {
                mHandler.post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run () {
                        getCurrency();
                        mTextView.setText("Дата обновления: " + new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss a zzz").format(new Date()));
                        mHandler.postDelayed(this, 60000);
                    }
                });
            }
        }).start();
    }

    public void getCurrency(){
        Api.getApi().getData(date).enqueue(new Callback<ValCurs>() {
            @Override
            public void onResponse(@NonNull Call<ValCurs> call, @NonNull Response<ValCurs> response) {
                assert response.body() != null;
                mValutes = response.body().getValute();
                mAdapter = new Adapter(mValutes);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<ValCurs> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Ошибка соединения", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
