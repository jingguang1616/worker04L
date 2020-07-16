package com.example.worker04l.Base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public abstract class BaseModle {
    public CompositeDisposable mdisposable=null;
    public void adddisposable(Disposable disposable){
        if (mdisposable==null){
            synchronized (BaseModle.class){
                if (mdisposable==null){
                    mdisposable=new CompositeDisposable();
                }
            }
        }
        mdisposable.add(disposable);
    }
    public void dispoable(){
        mdisposable.dispose();
    }
    public void remove(Disposable disposable){
        if (mdisposable!=null){
            mdisposable.remove(disposable);
            mdisposable=null;
        }
    }
}
