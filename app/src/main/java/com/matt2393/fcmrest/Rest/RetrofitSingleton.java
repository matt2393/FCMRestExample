package com.matt2393.fcmrest.Rest;

import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
    private static RetrofitSingleton retrofitSingleton;
    private static Retrofit retrofit;
    private static RetrofitRequest retrofitRequest;
    private static CompositeDisposable compositeDisposable;

    private Context context;

    private RetrofitSingleton(Context context) {
        this.context = context;


        compositeDisposable = new CompositeDisposable();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com/fcm/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build();

        retrofitRequest = retrofit.create(RetrofitRequest.class);
    }

    public static synchronized RetrofitSingleton getInstance(Context context) {
        if (retrofitSingleton == null)
            retrofitSingleton = new RetrofitSingleton(context);
        return retrofitSingleton;
    }

    public RetrofitRequest getRequests() {
        return retrofitRequest;
    }

    public void addRequest(Disposable d) {
        compositeDisposable.add(d);
    }
}
