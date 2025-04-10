/*
 * Name: Steven Chen
 * Email: stc008@ucsd.edu
 * PID: A17375690
 * Sources Used: Lectures and slides
 *
 * This file contains my custom test cases for this PA. I hope that 
 * these tests are able to cover the ones that public tester did not.
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CustomTester {

    private MyDeque<Integer> deque;
    private MyStack<Integer> stack;
    private MyQueue<Integer> queue;
    private MyAlgorithm algorithm;

    @Before
    public void setUp() {
        deque = new MyDeque<>(5);
        stack = new MyStack<>(5);
        queue = new MyQueue<>(5);
        algorithm = new MyAlgorithm();
    }

    // Test cases for MyDeque constructor
    @Test
    public void testMyDequeConstructor() {
        try {
            new MyDeque<>(-1); // Should throw an IllegalArgumentException
            fail("Expected IllegalArgumentException for negative initial capacity");
        }
        catch (IllegalArgumentException e) {
        }
    }

    // Test cases for expandCapacity in MyDeque
    @Test
    public void testExpandCapacity() {
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.expandCapacity();

        assertEquals("Capacity should double and preserve elements", 5, deque.size());
        assertEquals("First element should remain the same", Integer.valueOf(1), deque.peekFirst());
        assertEquals("Last element should remain the same", Integer.valueOf(5), deque.peekLast());
    }

    // Test cases for addFirst in MyDeque
    @Test
    public void testAddFirst() {
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        assertEquals("Deque should be full after 5 adds", 5, deque.size());

        deque.expandCapacity();
        deque.addFirst(6);
        assertEquals("After expanding, size should be 6", 6, deque.size());
        assertEquals("First element should be the last added element", Integer.valueOf(6), deque.peekFirst());
    }

    // Test cases for addLast in MyDeque
    @Test
    public void testAddLast() {
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        assertEquals("Deque should be full after 5 adds", 5, deque.size());

        deque.expandCapacity();
        deque.addLast(6);
        assertEquals("After expanding, size should be 6", 6, deque.size());
        assertEquals("Last element should be the last added element", Integer.valueOf(6), deque.peekLast());
    }

    // Test cases for removeFirst in MyDeque
    @Test
    public void testRemoveFirst() {
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.removeFirst();
        assertEquals("Size should decrease after removal", 2, deque.size());
        assertEquals("New first element should be 2", Integer.valueOf(2), deque.peekFirst());
    }

    // Test cases for removeLast in MyDeque
    @Test
    public void testRemoveLast() {
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.removeLast();
        assertEquals("Size should decrease after removal", 2, deque.size());
        assertEquals("New last element should be 2", Integer.valueOf(2), deque.peekLast());
    }

    // Test cases for MyStack push/pop
    @Test
    public void testStackPushPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals("Top element should be the last pushed", Integer.valueOf(3), stack.pop());
        assertEquals("After popping, new top should be 2", Integer.valueOf(2), stack.peek());
    }

    // Test cases for MyQueue enqueue/dequeue
    @Test
    public void testQueueEnqueueDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals("First element should be the first enqueued", Integer.valueOf(1), queue.dequeue());
        assertEquals("After dequeuing, new front should be 2", Integer.valueOf(2), queue.peek());
    }

    // Test cases for isValidBrackets in MyAlgorithm
    @Test
    public void testIsValidBrackets() {
        assertTrue("Valid brackets", algorithm.isValidBrackets("()"));
        assertTrue("Valid nested brackets", algorithm.isValidBrackets("{[()]}"));
        assertFalse("Invalid brackets", algorithm.isValidBrackets("([)]"));
        assertFalse("Mismatched brackets", algorithm.isValidBrackets("((]"));
        assertTrue("Empty string is valid", algorithm.isValidBrackets(""));
    }

    // Test cases for empty list
    @Test
    public void testAddLastIntoEmptyList() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        deque.addLast(15);
        assertEquals(15, deque.peekLast().intValue());
        assertEquals(1, deque.size());
        assertEquals(deque.peekFirst(), deque.peekLast());
    }

    @Test
    public void testRemoveFirstLastElement() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        deque.addFirst(10);
        assertEquals(10, deque.removeFirst().intValue());
        assertEquals(0, deque.size());
        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
    }

    @Test
    public void testRemoveLastLastElement() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        deque.addLast(20);
        assertEquals(20, deque.removeLast().intValue());
        assertEquals(0, deque.size());
        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
    }


    @Test
    public void testDequeExpandCapacity() {
        MyDeque<Integer> deque = new MyDeque<>(0);
        deque.expandCapacity();
        assertEquals(10, deque.data.length);
        assertEquals(0, deque.size());
        assertEquals(0, deque.front);
        assertEquals(0, deque.rear);
    }

    @Test
    public void testDequeAddFirstOne() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        deque.addFirst(10);
        assertEquals(10, deque.peekFirst().intValue());
        assertEquals(1, deque.size());
        assertEquals(0, deque.front);  
        assertEquals(0, deque.rear);
    }

    @Test
    public void testDequeRemoveLastOne() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        deque.addLast(20);
        assertEquals(20, deque.removeLast().intValue());
        assertEquals(0, deque.size());
        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
        assertEquals(-1, deque.front);
        assertEquals(-1, deque.rear);
    }

    @Test
    public void testDequeRemoveLastTwo() {
        MyDeque<Integer> deque = new MyDeque<>(3);
        deque.addLast(30);
        deque.addLast(40);
        deque.addLast(50);
        deque.removeFirst(); 
        deque.addLast(60);
        assertEquals(60, deque.removeLast().intValue());
        assertEquals(40, deque.removeFirst().intValue());
        assertEquals(50, deque.peekLast().intValue());
    }

    @Test
    public void testAlgorithmIsValidBrackets() {
        MyAlgorithm algo = new MyAlgorithm();
        assertTrue(algo.isValidBrackets("(){}[]"));
        assertTrue(algo.isValidBrackets("{[()]}"));
        assertFalse(algo.isValidBrackets("(]"));
        assertFalse(algo.isValidBrackets("({[)]}"));
        assertFalse(algo.isValidBrackets("(()"));
    }

    @Test
    public void testDequeRemoveFirst() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        deque.addLast(70);
        deque.addLast(80);
        assertEquals(70, deque.removeFirst().intValue());
        assertEquals(80, deque.removeFirst().intValue());
        assertEquals(0, deque.size());
        assertNull(deque.peekFirst());
        assertEquals(-1, deque.front);
        assertEquals(-1, deque.rear);
    }
}
