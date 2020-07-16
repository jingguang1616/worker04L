package com.example.worker04l.presenter;

import com.example.worker04l.Base.BasePresenter;
import com.example.worker04l.bean.Shitilei;
import com.example.worker04l.modle.F1CallBork;
import com.example.worker04l.modle.F1Modle;
import com.example.worker04l.view.F1view;

public class F1Presenter extends BasePresenter<F1view> implements F1CallBork {

    private F1Modle f1Modle;

    @Override
    protected void initModle() {
        f1Modle = new F1Modle();
        addModle(f1Modle);
    }

    @Override
    public void Onsuccess(Shitilei shitilei) {
        mview.setData(shitilei);
    }

    @Override
    public void OnFail(String str) {
        mview.showTosat(str);
    }

    public void getString() {
        f1Modle.getString(this);
    }
}
