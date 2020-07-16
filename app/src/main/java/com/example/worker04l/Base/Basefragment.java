package com.example.worker04l.Base;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.worker04l.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public abstract class Basefragment<P extends BasePresenter> extends Fragment implements BaseView {
    public P mpresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this,view);
        initPresenter();
        if (mpresenter!=null){
            mpresenter.bindview(this);
        }
        initView();
        initData();
        initListen();
        return view;
    }




    protected abstract void initListen();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initPresenter();

    protected abstract int getLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mpresenter!=null){
            mpresenter.Dispory();
            mpresenter=null;
        }
    }
}
