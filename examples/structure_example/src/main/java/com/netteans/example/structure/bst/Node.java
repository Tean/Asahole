package com.netteans.examples.structure.bst;

import com.netteans.examples.structure.INode;
import com.netteans.examples.structure.IValue;

public class Node<V extends IValue<?>> implements INode<V> {
    V value;

    Node<V> parent, left, right;

    public Node(Node<V> parent, V value) {
        this.parent = parent;
        this.value = value;
    }

    public V getValue() {
        return value;
    }
}
