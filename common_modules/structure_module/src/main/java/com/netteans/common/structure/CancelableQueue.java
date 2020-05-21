package com.netteans.common.structure;

import java.util.*;

/**
 * @author netteans
 */
public class CancelableQueue<E> implements Queue<E> {

    private Node<E> node = null;
    private int size = 0;

    private static class Node<E> {
        Node<E> prev;
        Node<E> next;

        E elem;

        Node(E elem) {
            this.elem = elem;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public E getElem() {
            return elem;
        }

        public void setElem(E elem) {
            this.elem = elem;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return node == null;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> n = node;
        for (; ; ) {
            if (n.getNext() != null) {
                n = node.getNext();
                if (n.getElem().equals(o)) {
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> iterator = new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return node.getNext() != null;
            }

            @Override
            public E next() {
                return node.getNext().getElem();
            }
        };
        return iterator;
    }

    @Override
    public Object[] toArray() {
        List<E> es = new ArrayList<>();
        Node<E> n = node;
        for (; ; ) {
            if (n != null) {
                es.add(n.getElem());
                n = n.getNext();
            } else {
                break;
            }
        }
        return es.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        List<E> es = new ArrayList<>();
        Node<E> n = node;
        for (; ; ) {
            if (n != null) {
                es.add(n.getElem());
                n = n.getNext();
            } else {
                break;
            }
        }
        return es.toArray(a);
    }

    @Override
    public boolean add(E e) {
        if (node == null) {
            node = new Node<>(e);
        } else {
            node.next = new Node<>(e);
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }
}
