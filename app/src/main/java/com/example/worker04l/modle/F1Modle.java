package com.example.worker04l.modle;

import com.example.worker04l.net.ApiService;
import com.example.worker04l.Base.BaseModle;
import com.example.worker04l.bean.Shitilei;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class F1Modle extends BaseModle {

    public void getString(F1CallBork f1CallBork) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getobservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Shitilei>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Shitilei shitilei) {
                            f1CallBork.Onsuccess(shitilei);
                    }

                    @Override
                    public void onError(Throwable e) {
                        f1CallBork.OnFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
