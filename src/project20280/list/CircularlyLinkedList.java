package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        // TODO
        Node<E> temp = tail.next;
        for(int k = 0; k < i; k++) {
            temp = temp.next;
        }
        return temp.data;
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        // TODO
        if(size == 0) {
            tail = new Node<>(e, null);
            tail.next = tail;
            size = 1;
            return;
        }

        if(i == 0) {
            tail.next = new Node<>(e, tail.next);
            size++;
            return;
        }

        Node<E> prev = tail.next;
        for (int k = 0; k < i - 1; k++) {
            prev = prev.next;
        }
        Node<E> newNode = new Node<>(e, prev.next);
        prev.next = newNode;

        if (i == size) {
            tail = newNode;
        }

        size++;
    }

    @Override
    public E remove(int i) {
        // TODO
        if (isEmpty()){
            return null;
        }

        if(size == 1){
            E val = tail.data;
            tail = null;
            size = 0;
            return val;
        }

        if (i == 0){
            Node<E> head = tail.next;
            E val = head.data;
            tail.next = head.next;
            size--;
            return val;
        }

        Node<E> prev = tail.next;
        for(int k = 0; k < i - 1; k++) {
            prev = prev.next;
        }
        Node<E> target = prev.next;
        E val = target.data;
        prev.next = target.next;

        if (target == tail) {
            tail = prev;
        }

        size--;
        return val;
    }

    public void rotate() {
        // TODO
        if  (tail != null) {
            tail = tail.next;
        }
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        // TODO
        return remove(0);
    }

    @Override
    public E removeLast() {
        // TODO
        return remove(size - 1);
    }

    @Override
    public void addFirst(E e) {
        // TODO
        add(0, e);
    }

    @Override
    public void addLast(E e) {
        // TODO
        add(size, e);
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
