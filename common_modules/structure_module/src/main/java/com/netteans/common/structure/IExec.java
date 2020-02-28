package com.netteans.common.structure;

public interface IExec<E> {
    void exec(IAsync<E> async);
    E exec();
}
