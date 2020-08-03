package com.netteans.example.structure.bst;

import com.netteans.example.structure.ITree;
import com.netteans.example.structure.IValue;

public class BinaryTree<V extends IValue<?>> implements ITree<Node<V>> {
    private Node<V> root;

    @Override
    public ITree<Node<V>> insert(Node<V> node) {
        if (root == null) {
            root = node;
        } else {
            Node<V> iroot = root;
            while (iroot != null) {
                if (node.compareTo(iroot) < 0) {
                    if (iroot.left == null) {
                        iroot.left = node;
                        node.parent = iroot;
                        break;
                    } else {
                        iroot = iroot.left;
                    }
                } else {
                    if (iroot.right == null) {
                        iroot.right = node;
                        node.parent = iroot;
                        break;
                    } else {
                        iroot = iroot.right;
                    }
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

