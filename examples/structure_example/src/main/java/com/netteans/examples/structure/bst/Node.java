package com.netteans.examples.structure.bst;

import com.netteans.examples.structure.INode;
import com.netteans.examples.structure.IValue;

public class Node<V extends IValue<?>> implements INode<V>, Comparable<Node> {
    V value;

    Node<V> parent, left, right;

    public Node(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    @Override
    public int compareTo(Node o) {
        return value.getValue().compareTo(o.getValue().getValue());
    }
}
