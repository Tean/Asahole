package com.netteans.exampless.gof.absfactory;

public interface IFactory<P extends ISuit> {

    <ZP extends P> ZP getSuit(Class<? extends ZP> suitclass);
}
