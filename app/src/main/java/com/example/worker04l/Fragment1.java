package com.example.worker04l;

import android.content.Intent;
import android.widget.Toast;

import com.example.worker04l.Base.Basefragment;
import com.example.worker04l.adapter.Rcyadapter;
import com.example.worker04l.bean.Shitilei;
import com.example.worker04l.presenter.F1Presenter;
import com.example.worker04l.view.F1view;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Basefragment<F1Presenter> implements F1view {

    @BindView(R.id.rcy)
    RecyclerView rcy;

    private ArrayList<Shitilei.DataBean.DatasBean> list;
    private Rcyadapter rcyadapter;
    private Unbinder bind;

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    protected void initListen() {

    }

    @Override
    protected void initData() {
        mpresenter.getString();
    }

    public void initView() {
        rcy.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcy.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        list = new ArrayList<>();
        rcyadapter = new Rcyadapter(list, getActivity());
        rcy.setAdapter(rcyadapter);
        rcyadapter.setOnItemchickLis(new Rcyadapter.OnItemchickLis() {
            @Override
            public void Chick(int position) {
                startActivity(new Intent(getActivity(),Main2Activity.class));
            }
        });
    }

    @Override
    protected void initPresenter() {
        mpresenter = new F1Presenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_1;
    }

    @Override
    public void setData(Shitilei shitilei) {
        list.addAll(shitilei.getData().getDatas());
        rcyadapter.notifyDataSetChanged();
    }

    @Override
    public void showTosat(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
