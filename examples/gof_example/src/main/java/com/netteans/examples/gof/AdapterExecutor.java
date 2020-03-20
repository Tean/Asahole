package com.netteans.exampless.gof;

public class AdapterExecutor {
    private IAdapter adapter;

    public void injectAdapter(IAdapter adapter) {
        this.adapter = adapter;
    }

    public Object exec() {
        return this.adapter.exec();
    }
}


interface IAdapter<R> {
    R exec();
}

