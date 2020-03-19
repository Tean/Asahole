package com.netteans.examples.gof;

public class Builder implements IBuild<Product> {
    final Product product = new Product();

    @Override
    public void addPart(IPart p) {
        product.addPart(p);
    }

    @Override
    public Product build() {
        return product;
    }
}

interface IBuild<R> {
    void addPart(IPart p);

    R build();
}

interface IProdMaker {
    void addPart(IPart p);

    String getDetail();
}

