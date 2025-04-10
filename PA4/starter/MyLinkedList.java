/*
 *  Name: Steven Chen
 *  Email: stc008@ucsd.edu
 *  PID:A1737590
 *  Sources Used: Lecture Notes
 *  This file will store my interpretation of LinkedList. I used a
 *  number of methods, constructors, and variables to optimize my implementation.
 */
import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * This class is a doubly linked list that extends AbstractList. It can perform
 * methods such as adding, removing, and retriveing elements from a list.
 */
public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

    //  Implementation of the MyLinkedList Class

    /**
     * Constructor for the class, it sets up an empty list with sentinel head and tail nodes.
     */
    public MyLinkedList() {
        this.size = 0;
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * Returns number of elements in list.
     * 
     * @return The size of the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Gets element at certain index
     * 
     * @param index The index of the element
     * @return The element at the index
     * @throws IndexOutOfBoundsException If the index is out of bounds
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return getNth(index).getElement();
    }

    /**
     * Adds element at a certain index
     * 
     * @param index The index that data is being inserted into
     * @param data The element being inserted
     * @throws NullPointerException If data is null
     * @throws IndexOutOfBoundsExeception If index is out of range
     */
    @Override
    public void add(int index, E data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Node newNode = new Node(data);
        Node prevNode = getNth(index - 1);
        Node nextNode = prevNode.next;

        newNode.setPrev(prevNode);
        newNode.setNext(nextNode);
        prevNode.setNext(newNode);
        nextNode.setPrev(newNode);

        size++;
    }

    /**
     * Adds a new element to the end of the list
     * 
     * @param data The element being inserted
     * @return True if element was added
     * @throws NullPointerExeception If data is null
     */
    @Override
    public boolean add(E data) {
        if (data == null) {
            throw new NullPointerException();
        }
        add(size, data);
        return true;
    }

    /**
     * Sets data at a certain index
     * 
     * @param index The index being modified
     * @param data The data being set
     * @return The previous element at that index
     * @throws IndexOutOfBoundsException If index is out of bounds
     * @throws NullPointerException If data is null
     */
    @Override
    public E set(int index, E data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node targetNode = getNth(index);
        E oldData = targetNode.getElement();
        targetNode.setElement(data);

        return oldData;
    }

    /**
     * Removes element at certain index and returns it
     * 
     * @param index The index of the element being removed
     * @return The element that was removed
     * @throws IndexOutOfBoundsException If index is out of bounds
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    
        Node toRemove = getNth(index);
        E data = toRemove.data;
    
        Node prevNode = toRemove.prev;
        Node nextNode = toRemove.next;
    
        if (prevNode != null) {
            prevNode.next = nextNode;
        }
    
        if (nextNode != null) {
            nextNode.prev = prevNode;
        }
    
        if (index == 0) {
            head.next = nextNode;
        }
    
        if (index == size - 1) {
            tail.prev = prevNode;
        }
    
        size--;
        return data;
    }

    /**
     * Clears list by removing all non-sentinal nodes
     */
    @Override
    public void clear() {
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    /**
     * Checks to see if list is empty
     * 
     * @return size of 0
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Method to help retrieve node at certain index
     * 
     * @param index The index of the node being retreived 
     * @return The node at the index
     */
    protected Node getNth(int index) {
        if (index < -1 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node current = head;
        for (int i = -1; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    /**
     * Checks if the list has certain data within a certain range
     * 
     * @param data Data being searched
     * @param start Start of range
     * @param end End of range
     * @return True if data is in round, false if not
     * @throws IndexOutOfBoundsException If the start or end is invalid
     */
    public boolean contains(E data, int start, int end) {
        if (data == null) {
            return false;
        }

        if (start < 0 || end > size || end <= start) {
            return false;
        }

        for (int i = start; i < end; i++) {
            E element = get(i);
            if (element.equals(data)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Returns the index of data when it first appears
     * 
     * @param data Data being searched
     * @return Index of element 
     * @throws NullPointerException If data is null
     */
    public int indexOfElement(E data) {
        if (data == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < size; i++) {
            if (get(i).equals(data)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * A protected class that adds to MyLinkedList that allows the user to
     * iterate the list. This should assist in modifying the list if needed.
     */
    protected class MyListIterator implements ListIterator<E> {
        
        /**
         * Instance variables
         */
        Node left, right; 
        int idx;
        boolean forward;
        boolean canRemoveOrSet;

        /**
         * Constructor to initialize the iterator starting at the beginning of the list
         */
        public MyListIterator() {
            this.left = head;
            this.right = head.next;
            this.idx = 0;
            this.forward = true;
            this.canRemoveOrSet = false;
        }

        /**
         * Checks if there is an element next in the forward direction
         * 
         * @return true if there is a next element, false otherwise
         */
        @Override
        public boolean hasNext() {
            return right != tail;
        }

        /**
         * Goes to the next element in the list
         * 
         * @return the next element in the list
         * @throws NoSuchElementException if there is no next element
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            left = right;
            right = right.next;
            idx++;
            forward = true;
            canRemoveOrSet = true;
            return left.data;
        }

        /**
         * Checks if there is an element previous in the backward direction
         * 
         * @return true if there is a previous element, false otherwise
         */
        @Override
        public boolean hasPrevious() {
            return left != head;
        }

        /**
         * Moves backward to the previous element in the list
         * 
         * @return the previous element in the list
         * @throws NoSuchElementException if there is no previous element
         */
        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            right = left;
            left = left.prev;
            idx--;
            forward = false;
            canRemoveOrSet = true;
            return right.data;
        }

        /**
         * Returns the index of the next element
         * 
         * @return the index of the next element or list size if at the end
         */
        @Override
        public int nextIndex() {
            return idx;
        }

        /**
         * Returns the index of the previous element
         * 
         * @return the index of the previous element or -1 if at the start
         */
        @Override
        public int previousIndex() {
            return idx - 1;
        }

        
        /**
         * Adds a new element at the current position of the iterator
         * 
         * @param element the element to add
         * @throws NullPointerException if the element is null
         */
        @Override
        public void add(E element) {
            if (element == null) {
                throw new NullPointerException();
            }
            Node newNode = new Node(element);
            newNode.prev = left;
            newNode.next = right;
            left.next = newNode;
            right.prev = newNode;
            left = newNode;
            idx++;
            canRemoveOrSet = false;
            size++;
        }

        /**
         * Replaces the last element returned by next() or previous() with the specified element
         * 
         * @param element the element to set
         * @throws NullPointerException if the element is null
         * @throws IllegalStateException if next, previous has been called, 
         * or add or remove has been called after the last call to next or previous
         */
        @Override
        public void set(E element) {
            if (element == null) {
                throw new NullPointerException();
            }
            if (!canRemoveOrSet) {
                throw new IllegalStateException();
            }
            if (forward) {
                left.data = element;
            }
            else {
                right.data = element;
            }
        }

        /**
         * Removes the last element returned by next or previous
         * 
         * @throws IllegalStateException if neither next nor previous has been called, 
         * or if add or remove has been called after the last call to next or previous
         */
        @Override
        public void remove() {
            if (!canRemoveOrSet) {
                throw new IllegalStateException();
            }
            if (forward) {
                Node temp = left.prev;
                temp.next = right;
                right.prev = temp;
                left = temp;
                idx--;
            }
            else {
                Node temp = right.next;
                temp.prev = left;
                left.next = temp;
                right = temp;
            }
            canRemoveOrSet = false;
            size--;
        }
    }
    
    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }
    
    public Iterator<E> iterator() {
        return new MyListIterator();
    }
}


