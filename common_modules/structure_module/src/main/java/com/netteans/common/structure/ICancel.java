package com.netteans.common.structure;

public interface ICancel<E> {
    void cancel(IAsync<E> async);

    E cancel();
}
