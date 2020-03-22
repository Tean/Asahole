package com.netteans.examples.structure;

public interface ITree<N extends INode> {
    ITree<N> insert(N node);

    ITree<N> delete(N node);

    N search(N node);
}
