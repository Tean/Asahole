package com.netteans.examples.gof.absfactory;

public interface ISuit<S extends ISuit> {
    String getDetail();

    S from(String factname);
}
