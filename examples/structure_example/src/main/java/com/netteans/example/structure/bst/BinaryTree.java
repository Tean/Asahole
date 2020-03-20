package com.netteans.examples.structure.bst;

import com.netteans.examples.structure.ITree;
import com.netteans.examples.structure.IValue;

import java.util.Stack;

public class BinaryTree<V extends IValue<?>> implements ITree<Node<V>> {
    private Node<V> root;

    @Override
    public ITree<Node<V>> insert(Node<V> node) {
        if (root == null) {
            root = node;
        } else {
            Node<V> iroot = root;
            Node<V> ileft = iroot.left;
            Node<V> iright = iroot.right;
            Stack<Node<V>> nodeStack = new Stack<>();

            while (true) {
                if (ileft == null) {
                    ileft = node;
                } else if (iright == null) {
                    iright = node;
                } else {
                    iroot = ileft;
                    ileft = iroot.left;
                    iright = iroot.right;
                }
            }
        }
        return this;
    }

    private Node<V> parentOf(Node<V> node) {
        return node == null ? null : node.parent;
    }

    @Override
    public ITree<Node<V>> delete(Node<V> node) {
        return this;
    }

    @Override
    public Node<V> search(Node<V> node) {
        return null;
    }
}

