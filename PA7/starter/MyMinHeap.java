/*
 * Name: Steven Chen
 * Email: stc008@ucsd.edu
 * PID: A17375690
 * Sources Used: Lectures and slides
 *
 * The purpose of this file is to implement a MinHeap data structure.
 * It has methods for inserting, removing, and accessing elements
 * while maintaining the heap properties.
 */

import java.util.ArrayList;
import java.util.Collection;
 
/**
 * This class is my custom implementation of a MinHeap data structure.
 * It maintains the heap property where the root is always the minimum element.
 * This class supports generic types that extend Comparable.
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface<E> {
    //Instance Variable
    protected ArrayList<E> data;
 
    /**
     * Default constructor initializing the heap as an empty ArrayList
     */
    public MyMinHeap() {
        this.data = new ArrayList<>();
    }
 
    /**
     * Constructor initializing the heap with elements from a given collection
     * 
     * @param collection the collection of elements to initialize the heap with
     * @throws NullPointerException if the collection or any element in the collection is null
     */
    public MyMinHeap(Collection<? extends E> collection) {
        if (collection == null || collection.contains(null)) {
            throw new NullPointerException();
        }
        this.data = new ArrayList<>(collection);
        for (int i = data.size()/2 - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }
 
    /**
     * swap elements at two indices
     * 
     * @param from the index of the first element
     * @param to the index of the second element
     */
    protected void swap(int from, int to) {
        E temp = data.get(from);
        data.set(from, data.get(to));
        data.set(to, temp);
    }
 
    /**
     * get the parent index of a given index
     * 
     * @param index the index of the child node
     * @return the index of the parent node
     */
    protected static int getParentIdx(int index) {
        return (index - 1) / 2;
    }
 
    /**
     * get the left child index of a given index
     * 
     * @param index the index of the parent node
     * @return the index of the left child node
     */
    protected static int getLeftChildIdx(int index) {
        return 2 * index + 1;
    }
 
    /**
     * get the right child index of a given index
     * 
     * @param index the index of the parent node
     * @return the index of the right child node
     */
    protected static int getRightChildIdx(int index) {
        return 2 * index + 2;
    }
 
    /**
     * Get the index of the smaller child
     * 
     * @param index the index of the parent node
     * @return the index of the smaller child, or -1 if the node has no children
     */
    protected int getMinChildIdx(int index) {
        int left = getLeftChildIdx(index);
        int right = getRightChildIdx(index);
        if (left >= data.size()) {
            return -1;
        }
        if (right >= data.size()) {
            return left;
        }
        return data.get(left).compareTo(data.get(right)) <= 0 ? left : right;
    }
 
    /**
     * Percolate the element at the given index up to maintain heap property
     * 
     * @param index the index of the element to percolate up
     */
    protected void percolateUp(int index) {
        while (index > 0) {
            int parentIdx = getParentIdx(index);
            if (data.get(index).compareTo(data.get(parentIdx)) >= 0) {
                break;
            }
            swap(index, parentIdx);
            index = parentIdx;
        }
    }
 
    /**
     * Percolate the element at the given index down to maintain heap property
     * 
     * @param index the index of the element to percolate down
     */
    protected void percolateDown(int index) {
        int minChildIdx;
        while ((minChildIdx = getMinChildIdx(index)) != -1 && data.get(index).compareTo(data.get(minChildIdx)) > 0) {
            swap(index, minChildIdx);
            index = minChildIdx;
        }
    }

    /**
     * Remove the element at the given index from the heap and return it.
     * 
     * @param index the index of the element to be removed
     * @return the removed element
     */
    protected E deleteIndex(int index) {
        if (index < 0 || index >= data.size()) {
            throw new IndexOutOfBoundsException();
        }
        E removedElement = data.get(index);
        if (index == data.size() - 1) {
            data.remove(index);
        }
        else {
            data.set(index, data.remove(data.size() - 1));
            if (index > 0 && data.get(index).compareTo(data.get(getParentIdx(index))) < 0) {
                percolateUp(index);
            }
            else {
                percolateDown(index);
            }
        }
        return removedElement;
    }
 
    /**
     * insert an element into the heap
     * 
     * @param element the element to be inserted
     * @throws NullPointerException if the element is null
     */
    @Override
    public void insert(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        data.add(element);
        percolateUp(data.size() - 1);
    }
 
    /**
     * Get the minimum element from the heap
     * 
     * @return the minimum element or null if the heap is empty
     */
    @Override
    public E getMin() {
        return data.isEmpty() ? null : data.get(0);
    }
 
    /**
     * Remove and return the minimum element from the heap
     * 
     * @return the removed minimum element or null if the heap is empty
     */
    @Override
    public E remove() {
        if (size() == 0) {
            return null;
        }

        E minElement = data.get(0);
    
        if (size() == 1) {
            data.clear();
        }
        else {
            data.set(0, data.get(data.size() - 1));
            data.remove(data.size() - 1);
            percolateDown(0);
        }
    
        return minElement;
    }
 
    /**
     * Get the size of the heap
     * 
     * @return the number of elements in the heap
     */
    @Override
    public int size() {
        return data.size();
    }
 
    /**
     * Clear the heap
     */
    @Override
    public void clear() {
        data.clear();
    }
}
 