package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private final E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            // TODO
            this.element = e;
            this.next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            // TODO
            if(next != null) {
                return next;
            }
            else {
                return null;
            }
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            // TODO
            next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        // TODO
        return size;
    }

    //@Override
    public boolean isEmpty() {
        // TODO
        if(head == null) {
            return true;
        }
        return false;
    }

    @Override
    public E get(int position) {
        // TODO
        int count = 0;
        Node<E> temp = head;
        while(temp != null) {
            if(count == position) {
                return temp.getElement();
            }
            else{
                count++;
                temp = temp.getNext();
            }
        }
        return null;
    }

    @Override
    public void add(int position, E e) {
        // TODO
        int count = 0;
        Node<E> newNode = new Node<>(e, null);
        Node<E> temp = head;
        if(position == 0) {
            newNode.setNext(head);
            head = newNode;
            size++;
            return;
        }
        while(temp != null) {
            if(count == position - 1) {
                newNode.setNext(temp.getNext());
                temp.setNext(newNode);
                size++;
            }
            count++;
            temp = temp.getNext();
        }
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

    @Override
    public E remove(int position) {
        // TODO
        int count = 0;
        Node<E> temp = head;
        if(position < 0 || position >= size) {
            return null;
        }
        if(position == 0) {
            E removed = head.getElement();
            head = head.getNext();
            size--;
            return removed;
        }
        while(temp.getNext() != null) {
            if(count == position-1) {
                Node<E> toRemove = temp.getNext();
                E removed = toRemove.getElement();
                temp.setNext(toRemove.getNext());
                size--;
                return removed;
            }
            count++;
            temp = temp.getNext();
        }
        return null;
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

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public SinglyLinkedList<E> sortedMerge(SinglyLinkedList<E> other) {
        SinglyLinkedList<E> result = new SinglyLinkedList<>();

        Node<E> a = this.head;
        Node<E> b = other.head;

        Node<E> dummy = new Node<>(null, null);
        Node<E> tail = dummy;

        int count = 0;

        while (a != null && b != null) {
            E ea = a.getElement();
            E eb = b.getElement();

            int cmp = ((Comparable<? super E>) ea).compareTo(eb);

            if (cmp <= 0) {
                tail.setNext(new Node<>(ea, null));
                a = a.getNext();
            } else {
                tail.setNext(new Node<>(eb, null));
                b = b.getNext();
            }
            tail = tail.getNext();
            count++;
        }

        while (a != null) {
            tail.setNext(new Node<>(a.getElement(), null));
            tail = tail.getNext();
            a = a.getNext();
            count++;
        }

        while (b != null) {
            tail.setNext(new Node<>(b.getElement(), null));
            tail = tail.getNext();
            b = b.getNext();
            count++;
        }

        result.head = dummy.getNext();
        result.size = count;

        return result;
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> twin = new SinglyLinkedList<E>();
        Node<E> tmp = head;
        while (tmp != null) {
            twin.addLast(tmp.getElement());
            tmp = tmp.next;
        }
        return twin;
    }

    public void reverse() {
        Node<E> prev = null;
        Node<E> curr = head;
        Node<E> next;
        while(curr != null) {
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public static void main(String[] args) {
        /*
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        System.out.println(ll.size());
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);
        */
        SinglyLinkedList<Integer> l1 = new SinglyLinkedList<>();
        l1.addLast(2);
        l1.addLast(6);
        l1.addLast(20);
        l1.addLast(24);

        SinglyLinkedList<Integer> l2 = new SinglyLinkedList<>();
        l2.addLast(1);
        l2.addLast(3);
        l2.addLast(5);
        l2.addLast(8);
        l2.addLast(12);
        l2.addLast(19);
        l2.addLast(25);

        SinglyLinkedList<Integer> result = l1.sortedMerge(l2);
        System.out.println(l1);
        System.out.println(l2);
        System.out.println("Merged L1 and L2: " + result);
        System.out.println("Cloned L1: " + l1.copy());
        l1.reverse();
        System.out.println("Reversed L1: " + l1);
    }
}
