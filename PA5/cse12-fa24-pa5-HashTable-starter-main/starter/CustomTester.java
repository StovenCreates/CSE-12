/*
 * Name: Steven Chen
 * Email: stc008@ucsd.edu
 * PID: A17375690
 * Sources Used: Lectures and slides
 * 
 * The purpose of this file is to test my files MyHashMap and MyHashSet. This
 * file should include most, if not all the test cases that need to pass. 
 *
 * IMPORTANT: Do not change the method headers. Your tests will be run against
 * good and bad implementations of Student/Course/Sanctuary. You will only
 * receive points if your test passes when run on a good implementation and
 * fails for the corresponding bad implementation.
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * The custom tester for PA5, which covers some basic test cases.
 */
public class CustomTester {
    // Optional: add test variables here
    private MyHashMap<String, Integer> hashMap;
    private MyHashSet<String> hashSet;

    /**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
    @Before
    public void setup() {
        // Optional: add setup here
        hashMap = new MyHashMap<>();
        hashSet = new MyHashSet<>();
    }

    // ----------------MyHashMap class----------------

    /**
     * Test MyHashMap constructor with an invalid initial capacity
     */
    @Test
    public void testMyHashMapConstructorInvalidCapacity() {
        try {
            new MyHashMap<>(-1);
            fail("Expected IllegalArgumentException for invalid capacity");
        }
        catch (IllegalArgumentException e) {
        }
    }

    /**
     * Test MyHashMap get with a null key
     */
    @Test(expected = NullPointerException.class)
    public void testMyHashMapGetNullKey() {
        hashMap.get(null);
    }

    /**
     * Test MyHashMap get with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashMapGetKeyDoesNotExist() {
        assertNull("Expected null for non-existing key", hashMap.get("nonexistent"));
    }

    /**
     * Test MyHashMap put with a null key
     */
    @Test(expected = NullPointerException.class)
    public void testMyHashMapPutNullKey() {
        hashMap.put(null, 1);
    }

    /**
     * Test MyHashMap put with a key that already exists in the hash table
     */
    @Test
    public void testMyHashMapPutKeyAlreadyExists() {
        hashMap.put("key", 1);
        Integer oldValue = hashMap.put("key", 2);
        assertEquals("Expected old value to be returned", Integer.valueOf(1), oldValue);
        assertEquals("Expected updated value in map", Integer.valueOf(2), hashMap.get("key"));
    }

    /**
     * Test MyHashMap remove with a null key
     */
    @Test(expected = NullPointerException.class)
    public void testMyHashMapRemoveNullKey() {
        hashMap.remove(null);
    }

    /**
     * Test MyHashMap remove with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashMapRemoveKeyDoesNotExist() {
        assertNull("Expected null when removing non-existing key", hashMap.remove("nonexistent"));
    }

    /**
     * Test MyHashMap getHash with a null key
     */
    @Test(expected = NullPointerException.class)
    public void testMyHashMapGetHashNullKey() {
        hashMap.getHash(null, 5);
    }

    // ----------------MyHashSet class----------------

    /**
     * Test MyHashMap put with a key that already exists in the hash table
     */
    @Test
    public void testMyHashSetAddAlreadyExists() {
        hashSet.add("element");
        assertFalse("Expected false when adding an existing element", hashSet.add("element"));
    }

    /**
     * Test MyHashSet remove with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashSetRemoveDoesNotExist() {
        assertFalse("Expected false when removing a non-existing element", hashSet.remove("nonexistent"));
    }
}