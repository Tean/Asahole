package com.netteans.example.structure.bst;

import com.netteans.example.structure.INode;
import com.netteans.example.structure.IValue;

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
