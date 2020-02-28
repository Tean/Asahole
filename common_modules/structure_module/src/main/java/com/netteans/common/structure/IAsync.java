package com.netteans.common.structure;

import java.util.concurrent.TimeUnit;

public interface IAsync<E> {
    E get();

    E get(TimeUnit timeUnit, long time);
}
