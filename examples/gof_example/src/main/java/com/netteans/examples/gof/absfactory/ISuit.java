package com.netteans.exampless.gof.absfactory;

public interface ISuit<S extends ISuit> {
    String getDetail();

    S from(String factname);
}
