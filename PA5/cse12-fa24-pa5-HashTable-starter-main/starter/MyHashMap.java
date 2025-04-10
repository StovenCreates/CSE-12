/*
 * Name: Steven Chen
 * Email: stc008@ucsd.edu
 * PID: A17375690
 * Sources Used: Lectures and slides
 *
 * The purpose of this file is to create a class that
 * is similar to Java's HashMap. It has methods such as
 * adding, removing, and getting elements. 
 */

import java.util.Objects;

/**
 * This class is a custom implementation of the hash map data structure. This
 * class gives a way to store and retrieve key value pairs with generic 
 * types. It has many methods and is a singly linked list. 
 */
public class MyHashMap<K,V> {

    //constants
    private static final int DEFAULT_CAPACITY = 5;
    private static final double LOAD_FACTOR = 0.8;
    private static final int EXPAND_CAPACITY_RATIO = 2;
    private static final int HASH_CODE_MASK = 0x7fffffff;

    //instance variables
    Node[] hashTable;
    int size;

    /**
     * Default constructor initializing the hash table with default capacity
     */
    public MyHashMap() {
        this.hashTable = new Node[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Constructor initializing the hash table with a specified capacity
     * 
     * @param initialCapacity the initial capacity of the hash table
     * @throws IllegalArgumentException if initialCapacity is 0 or negative
     */
    public MyHashMap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.hashTable = new Node[initialCapacity];
        this.size = 0;
    }

    /**
     * Returns the value associated with the key or null
     * if the key is not in the map
     * 
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key or null
     * if the key is not in the map
     * @throws NullPointerException if key is null
     */
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }
        int index = getHash(key, hashTable.length);
        Node<K, V> current = hashTable[index];
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Associates the specified value with the specified key in this map
     * Expands capacity if load factor is exceeded
     * 
     * @param key the key associated with the value
     * @param value the value associated with the key
     * @return the previous value associated with key or
     * null if there was no mapping for key
     * @throws NullPointerException if key or value is null
     */
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("Key and value cannot be null");
        }

        if ((double) size / hashTable.length >= LOAD_FACTOR) {
            expandCapacity();
        }

        int index = getHash(key, hashTable.length);
        Node<K, V> current = hashTable[index];
        Node<K, V> previous = null;

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                V oldValue = current.value;
                current.value = value;
                return oldValue;
            }
            previous = current;
            current = current.next;
        }

        Node<K, V> newNode = new Node<>(key, value);
        if (previous == null) {
            hashTable[index] = newNode;
        }
        else {
            previous.next = newNode;
        }
        size++;
        return null;
    }

    /**
     * Removes the mapping for the specified key from this map if present
     * 
     * @param key the key whose mapping is removed from the map
     * @return the previous value associated with key or null
     * if there was no mapping for key
     * @throws NullPointerException if key is null
     */
    public V remove(K key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        int index = getHash(key, hashTable.length);
        Node<K, V> current = hashTable[index];
        Node<K, V> previous = null;

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                if (previous == null) {
                    hashTable[index] = current.next;
                }
                else {
                    previous.next = current.next;
                }
                size--;
                return current.value;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    /**
     * Returns the number of (key, value) pairs in the map
     * 
     * @return the number of (key, value) pairs in the map
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns the capacity of the hash table
     * 
     * @return the capacity of the hash table
     */
    public int getCapacity() {
        return this.hashTable.length;
    }

    /**
     * Removes all mappings from this map
     */
    public void clear() {
        hashTable = new Node[hashTable.length];
        size = 0;
    }

    /**
     * Checks if the map is empty
     * 
     * @return true if the map is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Expands the capacity of the hash table and rehashes all entries
     */
    public void expandCapacity() {
        int newCapacity = hashTable.length * EXPAND_CAPACITY_RATIO;
        Node<K, V>[] newHashTable = new Node[newCapacity];

        for (Node<K, V> head : hashTable) {
            Node<K, V> current = head;
            while (current != null) {
                int newIndex = getHash(current.key, newCapacity);
                Node<K, V> nextNode = current.next;
                current.next = newHashTable[newIndex];
                newHashTable[newIndex] = current;
                current = nextNode;
            }
        }
        hashTable = newHashTable;
    }

    /**
     * Calculates the hash for a given key within the specified capacity
     * 
     * @param key the key being hashed
     * @param capacity the capacity of the hash table
     * @return the hash index for the key
     * @throws NullPointerException if key is null
     * @throws IllegalArgumentException if capacity is 0 or negative
     */
    public int getHash(K key, int capacity) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }

        // Hashing function is given here. DO NOT MODIFY THIS
        return (key.hashCode() & HASH_CODE_MASK) % capacity;
    }

    /**
     * A Node class that holds (key, value) pairs and references
     * to the next node in the linked list
     */
    protected class Node<K,V> {
        K key;
        V value;
        Node next;

        /**
         * Constructor to create a single node
         * @param key key to store in this node
         * @param value value to store in this node
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        /**
         * Accessor to get the next node in the list
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the next node in the list
         * @param n the new next node
         */
        public void setNext(Node n) {
            next = n;
        }

        /**
         * Accessor to get the node's key
         * @return this node's key
         */
        public K getKey() {
            return this.key;
        }

        /**
         * Set the node's key
         * @param key the new key
         */
        public void setKey(K key) {
            this.key = key;
        }

        /**
         * Accessor to get the node's value
         * @return this node's value
         */
        public V getValue() {
            return this.value;
        }

        /**
         * Set the node's value
         * @param value the new value
         */
        public void setValue(V value) {
            this.value = value;
        }

        /**
         * Check if this node is equal to another node
         * @param other the other node to check equality with
         * @return whether or not this node is equal to the other node
         */
        public boolean equals(Node<K, V> other) {
            return this.key.equals(other.key) && this.value.equals(other.value);
        }
    }
}
