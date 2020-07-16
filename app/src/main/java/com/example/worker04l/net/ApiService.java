package com.example.worker04l.net;

import com.example.worker04l.bean.Shitilei;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface ApiService {
//
    String URL=("https://www.wanandroid.com/");
    @GET("project/list/1/json?cid=294")
    Observable<Shitilei> getobservable();

    String baseUrl = "https://dl.hdslb.com/mobile/latest/";
    @GET("iBiliPlayer-bili.apk?t=1589783162000&spm_id_from=333.47.b_646f776e6c6f61642d6c696e6b.1")
    @Streaming
    Observable<ResponseBody> getAPK();

}
