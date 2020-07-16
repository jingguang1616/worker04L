package com.example.worker04l;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;

import com.example.worker04l.bean.PbMsg;
import com.example.worker04l.net.ApiService;

import org.greenrobot.eventbus.EventBus;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import androidx.core.app.ActivityCompat;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitDownLoad();
    }
    private void retrofitDownLoad() {
        //设置文件的保存路径
        String saveFilePath = "/storage/emulated/0/aaa.apk";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<ResponseBody> observable = apiService.getAPK();
        observable.subscribeOn(Schedulers.io())//网络请求在子线程
                .observeOn(Schedulers.io())//得到文件的输入流后需要读取写到本地手机，IO流耗时操作在子线程
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        InputStream is = responseBody.byteStream();//得到文件的输入流（数据流）
                        long max = responseBody.contentLength();//得到文件的大小
                        saveFile(saveFilePath,is,max);//保存文件的方法：读取is里的数据写入到本手机
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void saveFile(String saveFilePath, InputStream is, long max) {
        //设置进度条的最大进度
        EventBus.getDefault().post(new PbMsg(0,(int)max,0));
        FileOutputStream os = null;//创建本地接收文件的输出流，保存文件
        try {
            os = new FileOutputStream(saveFilePath);
            byte[] buff = new byte[1024];
            int len = 0;
            int count = 0;//下载的大小
            while ((len = is.read(buff)) != -1){
                os.write(buff,0,len);//写入
                count = count+len;//累加上传进度
                EventBus.getDefault().post(new PbMsg(1,(int)max,count));//发送最新进度
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭流
                if(is != null)
                    is.close();
                if(os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
