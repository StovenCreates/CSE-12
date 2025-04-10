/*
 * Name: Steven Chen
 * Email: stc008@ucsd.edu
 * PID: A17375690
 * Sources Used: Slides
 * This file will store my interpretation of ArrayList. I create a
 * number of variables, constructors, and methods to do so. 
 */

/**
 * This class takes an array of objects and resizes it if needed. It does functions
 * such as append, prepend, insert, remove, etc. 
 * 
 * Instance variables:
 * data - The underlying data structure of the ArrayList
 * size - the number of valid elements in array
 */
@SuppressWarnings("unchecked")
public class MyArrayList<E> implements MyList<E> {

    /** Instance Variables */
    Object[] data;
    int size;

    /**
     * No-arg constructor that initializes the array with a default capacity of 5
     * The size is initialized to 0
     */
    public MyArrayList() {
        this.data = new Object[5];
        this.size = 0;
    }

    /**
     * Constructor that initializes the array with the given capacity
     * If the capacity is invalid, an exception is thrown
     * 
     * @param initialCapacity  The initial capacity of the array
     * @throws IllegalArgumentException  If initialCapacity is less than 0
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.data = new Object[initialCapacity];
        this.size = 0;
    }

    /**
     * Constructor that initializes the array with a copy of the provided array
     * All elements in the input array are considered valid
     * 
     * @param arr  The input array to copy elements from
     */
    public MyArrayList(E[] arr) {
        if (arr == null) {
            this.data = new Object[5];
        } else {
            this.data = new Object[arr.length];
            System.arraycopy(arr, 0, this.data, 0, arr.length);
            this.size = arr.length;
        }
    }

    /**
     * Expands the capacity of the array if needed
     * The capacity is doubled or set to the requiredCapacity if larger
     * 
     * @param requiredCapacity  The capacity the array needs to expand to
     * @throws IllegalArgumentException  If requiredCapacity is less than current capacity
     */
    public void expandCapacity(int requiredCapacity) {
        if (requiredCapacity < this.data.length) {
            throw new IllegalArgumentException("requiredCapacity cannot be smaller than current capacity");
        }
    
        int newCapacity;
        if (this.data.length == 0) {
            newCapacity = 5;
        }
        else {
            newCapacity = Math.max(this.data.length * 2, requiredCapacity);
        }
    
        Object[] newData = new Object[newCapacity];
        System.arraycopy(this.data, 0, newData, 0, this.size);
        this.data = newData;
    }

    /**
     * Returns the current capacity of the array
     * 
     * @return The length of the underlying array
     */
    public int getCapacity() {
        return this.data.length;
    }

    /**
     * Inserts an element at the specified index, shifting subsequent elements
     * If the array is at capacity, expands the capacity
     * 
     * @param index    The index where the element should be inserted
     * @param element  The element to insert
     * @throws IndexOutOfBoundsException  If the index is out of bounds
     */
    public void insert(int index, E element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (this.size == this.data.length) {
            expandCapacity(this.size + 1);
        }
        System.arraycopy(this.data, index, this.data, index + 1, this.size - index);
        this.data[index] = element;
        this.size++;
    }

    /**
     * Appends an element to the end of the list
     * If the array is at capacity, expands the capacity
     * 
     * @param element  The element to append
     */
    public void append(E element) {
        if (this.size == this.data.length) {
            expandCapacity(this.size + 1);
        }
        this.data[this.size] = element;
        this.size++;
    }

    /**
     * Prepends an element to the beginning of the list, shifting all elements
     * If the array is at capacity, expands the capacity
     * 
     * @param element  The element to prepend
     */
    public void prepend(E element) {
        if (this.size == this.data.length) {
            expandCapacity(this.size + 1);
        }
        System.arraycopy(this.data, 0, this.data, 1, this.size);
        this.data[0] = element;
        this.size++;
    }

    /**
     * Returns the element at the specified index
     * 
     * @param index  The index of the element to retrieve
     * @return The element at the specified index
     * @throws IndexOutOfBoundsException  If the index is out of bounds
     */
    public E get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return (E) this.data[index];
    }

    /**
     * Sets the element at the specified index and returns the old element
     * 
     * @param index    The index where the element should be set
     * @param element  The element to set
     * @return The old element that was replaced
     * @throws IndexOutOfBoundsException  If the index is out of bounds
     */
    public E set(int index, E element) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        E oldElement = (E) this.data[index];
        this.data[index] = element;
        return oldElement;
    }

    /**
     * Removes the element at the specified index and returns it, shifting subsequent elements
     * 
     * @param index  The index of the element to remove
     * @return The removed element
     * @throws IndexOutOfBoundsException  If the index is out of bounds
     */
    public E remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        E removedElement = (E) this.data[index];
        System.arraycopy(this.data, index + 1, this.data, index, this.size - index - 1);
        this.data[this.size - 1] = null;
        this.size--;
        return removedElement;
    }

    /**
     * Returns the number of valid elements in the list
     * 
     * @return The number of elements in the list
     */
    public int size() {
        return this.size;
    }

    /**
     * Rotates elements to the right by the specified number of positions
     * 
     * @param numPositions  The number of positions to rotate
     * @throws IndexOutOfBoundsException  If numPositions is out of bounds
     */
    public void rotate(int numPositions) {
        if (numPositions < 0 || numPositions >= this.size) {
            throw new IndexOutOfBoundsException("Invalid number of positions");
        }
        Object[] temp = new Object[this.size];
        for (int i = 0; i < this.size; i++) {
            int newIndex = (i + numPositions) % this.size;
            temp[newIndex] = this.data[i];
        }
        System.arraycopy(temp, 0, this.data, 0, this.size);
    }

    /**
     * Finds the last occurrence of the specified element and returns its index
     * 
     * @param element  The element to find
     * @return The index of the last occurrence, or -1 if the element is not found
     */
    public int find(E element) {
        for (int i = this.size - 1; i >= 0; i--) {
            if ((element == null && this.data[i] == null) || (element != null && element.equals(this.data[i]))) {
                return i;
            }
        }
        return -1;
    }
}
