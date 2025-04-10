/*
 * Name: Steven Chen
 * Email: stc008@uscd.edu
 * PID: A17375690
 * Sources Used: Lectures and slides
 *
 * The purpose of this file is to implement a Priority Queue using a MinHeap.
 * It has methods for adding, removing, and accessing elements while
 * maintaining priority ordering.
 */

import java.util.Collection;

/**
 * This class is my custom implementation of a Priority Queue using a MinHeap backend.
 * The elements in the queue are sorted by priority with the 
 * smallest element having the highest priority.
 */

public class MyPriorityQueue<E extends Comparable<E>> {
    //Instance Variable
    protected MyMinHeap<E> heap;

    /**
     * Default constructor initializing the priority queue as an empty MinHeap
     */
    public MyPriorityQueue() {
        this.heap = new MyMinHeap<>();
    }

    /**
     * Constructor initializing the priority queue with elements from a given collection.
     * 
     * @param collection the collection of elements to initialize the priority queue with
     * @throws NullPointerException if the collection or any element in the collection is null
     */
    public MyPriorityQueue(Collection<? extends E> collection) {
        if (collection == null || collection.contains(null)) {
            throw new NullPointerException();
        }
        this.heap = new MyMinHeap<>(collection);
    }

    /**
     * Adds an element to the priority queue
     * 
     * @param element the element to be added
     * @throws NullPointerException if the element is null
     */
    public void push(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        heap.insert(element);
    }

    /**
     * Returns the element with the highest priority 
     * 
     * @return the element with the highest priority or null if the queue is empty
     */
    public E peek() {
        return heap.getMin();
    }

    /**
     * Removes and returns the element with the highest priority
     * 
     * @return the removed element with the highest priority, or null if the queue is empty
     */
    public E pop() {
        return heap.remove();
    }

    /**
     * Returns the number of elements
     * 
     * @return the size of the priority queue
     */
    public int getLength() {
        return heap.size();
    }

    /**
     * Clears all elements
     */
    public void clear() {
        heap.clear();
    }
}
