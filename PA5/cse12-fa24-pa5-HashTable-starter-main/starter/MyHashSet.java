/*
 * Name: Steven Chen
 * Email: stc008@ucsd.edu
 * PID: A17375690
 * Sources Used: Lectures and slides
 *
 * The purpose of this file is to create a class that
 * uses MyHashMap as a data structure to store elements.
 * Each element is unique by mapping elements to a constant
 * default object.
 */

public class MyHashSet<E> {
    public static final Object DEFAULT_OBJECT = new Object();
    
    // The underlying hash map to store the elements
    MyHashMap<E, Object> hashMap;

    /**
     * Default constructor initializing the hash set with default capacity.
     */
    public MyHashSet() {
        this.hashMap = new MyHashMap<>();
    }

    /**
     * Constructor initializing the hash set with a specified capacity
     * 
     * @param initialCapacity the initial capacity of the hash set
     * @throws IllegalArgumentException if initialCapacity is 0 or negative
     */
    public MyHashSet(int initialCapacity) {
        this.hashMap = new MyHashMap<>(initialCapacity);
    }

    /**
     * Adds the element to this set if it is not already present
     * 
     * @param element the element to add
     * @return true if the set did not already contain the specified element,
     * false otherwise
     * @throws NullPointerException if element is null
     */
    public boolean add(E element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        return hashMap.put(element, DEFAULT_OBJECT) == null;
    }

    /**
     * Removes the specified element from this set if it is present
     * 
     * @param element the element to remove
     * @return true if the set contained the specified element,
     * false otherwise
     * @throws NullPointerException if element is null
     */
    public boolean remove(E element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        return hashMap.remove(element) != null;
    }

    /**
     * Returns the number of elements in this set
     * 
     * @return the number of elements in this set
     */
    public int size() {
        return hashMap.size();
    }

    /**
     * Removes all of the elements from this set
     */
    public void clear() {
        hashMap.clear();
    }

    /**
     * Returns true if this set contains no elements
     * 
     * @return true if this set contains no elements,
     * false otherwise
     */
    public boolean isEmpty() {
        return hashMap.isEmpty();
    }
}