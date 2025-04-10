/*
 *  Name: Steven Chen
 *  Email: stc008@ucsd.edu
 *  PID:A1737590
 *  Sources Used: Lecture Notes
 *  This file will store my custom testers for MyLinkedList and its methods for interator, testing
 *  most of the methods that are needed to iterate. 
 */
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

public class MyListIteratorCustomTester {

    private MyLinkedList<Integer> list;
    private MyLinkedList<Integer>.MyListIterator iterator;
    
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        iterator = list.new MyListIterator();
    }

    /**
     * Aims to test the next() method when iterator is at end of the list 
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextEnd() {
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test(expected = NoSuchElementException.class)
    public void testPreviousStart() {
        iterator.previous();
    }

    /**
     * Aims to test the add(E e) method when an invalid element is added
     */
    @Test(expected = NullPointerException.class)
    public void testAddInvalid() {
        iterator.add(null);
    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test(expected = IllegalStateException.class)
    public void testCantSet() {
        iterator.set(4);
    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test(expected = IllegalStateException.class)
    public void testCantRemove() {
        iterator.remove();
    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() {
        iterator.next();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() {
        assertFalse(iterator.hasPrevious());
    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() {
        assertEquals(-1, iterator.previousIndex());
    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() {
        iterator.next();
        iterator.next();
        iterator.next();

        int expectedNextIndex = list.size();
        int actualNextIndex = iterator.nextIndex();
        assertEquals("nextIndex() should return list size at the end", expectedNextIndex, actualNextIndex);

        try {
            iterator.next();
            fail("Expected NoSuchElementException when calling next() at the end of the list.");
        }
        catch (NoSuchElementException e) {
        }
    }
}
