/*
 * Name: Steven Chen
 * Email: stc008@ucsd.edu
 * PID: A17375690
 * Sources Used: Lectures and slides
 *
 * This file contains my implementation of a generic deque.
 * It has operations such as adding and removing elements from 
 * the front or end of the queue. 
 */

/**
 * This class is my implementation of a deque. This data
 * structure allows elements to be added or removed from the deque.
 * 
 * Instance variables:
 * - data: array holding the elements
 * - size: number of elements 
 * - front: index of first element
 * - rear: index of last element
 */
public class MyDeque<E> implements DequeInterface<E> {
    //Instance Variables
    Object[] data;
    int size;
    int front;
    int rear; 

    /**
     * Initializes data array with initial capacity
     * 
     * @param initialCapacity 
     * @throws IllegalArgumentException if initialCapacity is negative
     */
    public MyDeque(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
        size = 0;
        front = 0;
        rear = 0;
    }

    /**
     * Returns number of elements in deque
     * 
     * @returns The current size of the deque
     */
    public int size() {
        return size;
    }

    /**
     * Doubles the capacity of the deque and rearranges elements
     */
    @Override
    public void expandCapacity() {
        int newCapacity = (data.length == 0) ? 10 : data.length * 2;
        Object[] newData = new Object[newCapacity];

        if (size > 0) {
            for (int i = 0; i < size; i++) {
                newData[i] = data[(front + i) % data.length];
            }
        }

        data = newData;

        if (size == 0) {
            front = 0;
            rear = 0;
        }
        else {
            front = 0;
            rear = size - 1;
        }
    }

    /**
     * Adds an element to the front of deque
     * 
     * @param element The element to add
     * @Throws NullPointerExeption if element is null
     */
    @Override
    public void addFirst(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (size == data.length) {
            expandCapacity();
        }
        if (size == 0) {
            front = 0;
            rear = 0;
        }
        else {
            front = (front - 1 + data.length) % data.length;
        }
        data[front] = element;
        size++;
    }

    /**
     * Adds an element to the end of the deque
     * 
     * @param element The element to add
     * @Throws NullPointerException if element is null
     */
    @Override
public void addLast(E element) {
    if (element == null) {
        throw new NullPointerException();
    }
    if (size == data.length) {
        expandCapacity();
    }
    if (size == 0) {
        front = 0;
        rear = 0;
    }
    else {
        rear = (rear + 1) % data.length;
    }
    data[rear] = element;
    size++;
}

    /**
     * Removes and returns the element at the front of the deque
     * Returns null if deque is empty
     * 
     * @return The element that is removed or null if empty
     */
    @Override
    public E removeFirst() {
        if (size == 0) {
            return null;
        }

        E removedElement = (E) data[front];
        data[front] = null;
        if (size == 1) {
            front = -1;
            rear = -1;
        }
        else {
            front = (front + 1) % data.length;
        }
        size--;
        return removedElement;
}

    /**
     * Removed and returns the element at the end of the deque
     * Returns null if deque is empty
     * 
     * @return The element that is removed or null if empty
     */
    @Override
    public E removeLast() {
        if (size == 0) {
            return null;
        }
        E removedElement = (E) data[rear];
        data[rear] = null;
        if (size == 1) {
            front = -1;
            rear = -1;
        }
        else {
            rear = (rear - 1 + data.length) % data.length;
        }
        size--;
        return removedElement;
}

    /**
     * Returns the element at the front of the deque
     * Returns null if empty
     * 
     * @return The element at the front
     */
    public E peekFirst() {
        if (size == 0) {
            return null;
        }
        return (E) data[front];
    }

    /**
     * Returns the element at the end of the deque
     * Returns null if empty
     * 
     * @return The element at the end
     */
    public E peekLast() {
        if (size == 0) {
            return null;
        }
        return (E) data[rear];
    }
}
