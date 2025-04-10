/*
 * Name: Steven Chen
 * Email: stc008@ucsd.edu
 * PID: A17375690
 * Sources Used: Slides
 * This file contains tests that I developed to hopefully match
 * the hidden tests on gradescope.
 */
import static org.junit.Assert.*;

import org.junit.*;

/**
 * This class contains the hidden tests for MyArrayList.
 * These tests focus on fnding any errors that weren't covered in
 * the public tests.
 * 
 * Instance Variable:
 * list - holds a list of strings for use in the hidden tests
 */
public class MyArrayListHiddenTester {
    /** Instance Variable */
    private MyArrayList<String> list;
    // Do not change the method signatures!
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test 
     */
    @Before
    public void setUp() throws Exception {
        list = new MyArrayList<>();
    }

    /**
     * Aims to test the constructor when the input argument
     * is not valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidArg(){
        new MyArrayList<>(-5);
    }

    /**
     * Aims to test the constructor when the input argument is null
     */
    @Test
    public void testConstructorNullArg(){
        MyArrayList<String> nullArrayList = new MyArrayList<>(null);
        assertEquals("Capacity should be default", 5, nullArrayList.getCapacity());
        assertEquals("Size should be 0", 0, nullArrayList.size());
    }

    /**
     * Aims to test the constructor when the input array has null elements
     */
    @Test
    public void testConstructorArrayWithNull(){
        String[] arrayWithNulls = {"a", null, "b", null};
        MyArrayList<String> arrayList = new MyArrayList<>(arrayWithNulls);
        assertEquals("Size should include null elements", 4, arrayList.size());
    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity(){
        for (int i = 0; i < list.getCapacity(); i++) {
            list.append("test" + i);
        }
        int oldCapacity = list.getCapacity();
        list.append("newItem");
        assertTrue("Capacity should have expanded", list.getCapacity() > oldCapacity);
        assertEquals("New item should be appended", "newItem", list.get(list.size() - 1));
    }

    /**
     * Aims to test the append method when null is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendNull(){
        list.append(null);
        assertEquals("Size should increase after appending null", 1, list.size());
        assertNull("Null should be appended to the list", list.get(0));
    }

    /**
     * Aims to test the prepend method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testPrependAtCapacity(){
        for (int i = 0; i < list.getCapacity(); i++) {
            list.append("test" + i);
        }
        int oldCapacity = list.getCapacity();
        list.prepend("newItem");
        assertTrue("Capacity should have expanded", list.getCapacity() > oldCapacity);
        assertEquals("New item should be prepended", "newItem", list.get(0));
    }
    
    /**
     * Aims to test the prepend method when a null element is added
     * Checks reflection on size and capacity
     * Checks whether null was added successfully
     */
    @Test
    public void testPrependNull(){
        list.prepend(null);
        assertEquals("Size should increase after prepending null", 1, list.size());
        assertNull("Null should be prepended to the list", list.get(0));
    }
    
    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertOutOfBounds(){
        list.insert(-1, "test");
    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple(){
        for (int i = 0; i < 1000; i++) {
            list.insert(0, "test" + i);
        }
        assertEquals("Size should be 1000", 1000, list.size());
    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBound(){
        list.get(-1);
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOutOfBound(){
        list.set(-1, "test");
    }

    /**
     * Aims to test the remove method when removing multiple items from a list
     */
    @Test
    public void testRemoveMultiple(){
        for (int i = 0; i < 10; i++) {
            list.append("test" + i);
        }
        for (int i = 0; i < 5; i++) {
            list.remove(0);
        }
        assertEquals("Size should be 5", 5, list.size());
    }

    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBound(){
        list.remove(-1);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is strictly less than the current capacity
     */
    @Test(expected = IllegalArgumentException.class)
    public void testExpandCapacitySmaller(){
        list.expandCapacity(1);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is greater than current capacity * 2 and default capacity
     */
    @Test
    public void testExpandCapacityLarge(){
        list.expandCapacity(50);
        assertEquals("Capacity should be expanded", 50, list.getCapacity());
    }

    /**
     * Aims to test the rotate method when 
     * input numPositions is out of bounds
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRotateOutOfBound() {
        list.rotate(-1);
    }

    /**
     * Aims to test the find method when 
     * there are multiple of the input element
     */
    @Test
    public void testFindMultiple(){
	    list.append("test");
        list.append("test");
        assertEquals("Should return the last occurrence", 1, list.find("test"));
    }
	
    /**
     * Aims to test the find method when 
     * input element does not exist in the list
     */
    @Test
    public void testFindDoesNotExist(){
        assertEquals("Should return -1 for non-existent element", -1, list.find("nonexistent"));
    }

}
