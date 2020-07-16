package com.example.worker04l;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.worker04l.bean.PbMsg;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.txt_progress)
    TextView txtProgress;
    @BindView(R.id.btn_retrofit)
    Button btnRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        checkPermiss();
        EventBus.getDefault().register(this);
    }


    private void checkPermiss() {
        int i = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (i != PackageManager.PERMISSION_GRANTED) {//没有授权，申请权限
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 100);
        }
    }


    @OnClick(R.id.btn_retrofit)
    public void onViewClicked() {
        MyService myService = new MyService();
        myService.onCreate();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)//设置eventbus接收方法为主线程，才能更新UI组件
    public void getMsg(PbMsg msg){
        if (msg.getFlag() == 0) {//设置进度条的最大进度值
            pb.setMax(msg.getMax());
        } else if (msg.getFlag() == 1) {//设置进度条的当前进度
            pb.setProgress(msg.getProgress());
            //计算百分比
            int progress = pb.getProgress();//得到进度
            int max = pb.getMax();//得到最大进度
            int b = (int) (((float) progress / max) * 100);//0.33444 * 100 = 33.444  得到百分比
            txtProgress.setText(b + "%");
        }
    }
}
