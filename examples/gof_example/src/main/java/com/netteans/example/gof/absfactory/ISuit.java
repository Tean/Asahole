package com.netteans.example.gof.absfactory;

public interface ISuit<S extends ISuit> {
    String getDetail();

    S from(String factname);
}
