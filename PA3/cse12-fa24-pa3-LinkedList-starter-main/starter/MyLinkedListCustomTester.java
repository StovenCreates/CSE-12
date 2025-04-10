/*
    Name: Steven Chen
    Email: stc008@ucsd.edu
    PID:A1737590
    Sources Used: Lecture Notes
 */
import static org.junit.Assert.*;

import org.junit.*;

/**
 * This class has custom Junit tests that will hopefully cover
 * the hidden tests on Gradescope. I hope to test all of my methods in the Linked
 * List class I implemented. 
 */
public class MyLinkedListCustomTester {

	// Optional: add test variables here
	private MyLinkedList<String> list;
    private MyLinkedList<String> emptyList;
    private String[] strData = {"one", "two", "three"};

	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
		emptyList = new MyLinkedList<>();
        list = new MyLinkedList<>();
        list.add(0, "one");
        list.add(1, "two");
        list.add(2, "three");
	}

	/**
	 * Aims to test the add(E data) method with a valid argument.
	 */
	@Test
	public void testCustomAdd() {
		MyLinkedList<String> list = new MyLinkedList<>();
    	list.add("A");
    	assertEquals("Size should be 1 after adding one element", 1, list.size());
    	assertEquals("Element 'A' should be the first in the list", "A", list.get(0));
    
    	assertThrows(NullPointerException.class, () -> list.add(null));
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the beginning of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToStart() {
		list.add(0, "zero");
        assertEquals("List size should increase after adding to start", 4, list.size());
        assertEquals("First element should be 'zero'", "zero", list.get(0));
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the middle of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToMiddle() {
		MyLinkedList<String> list = new MyLinkedList<>();
    	list.add("A");
    	list.add("C");
    	list.add(1, "B");
    
    	assertEquals("Size should be 3 after middle insertion", 3, list.size());
    	assertEquals("Element 'B' should be at index 1", "B", list.get(1));
    	assertEquals("Element 'C' should be at index 2", "C", list.get(2));
	}

	/**
	 * Aims to test the remove(int index) method. Remove from an empty list.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testCustomRemoveFromEmpty() {
		emptyList.remove(0);
	}

	/**
	 * Aims to test the remove(int index) method.
	 * Remove a valid argument from the middle of MyLinkedList.
	 */
	@Test
	public void testCustomRemoveFromMiddle() {
		MyLinkedList<String> list = new MyLinkedList<>();
    	list.add("A");
    	list.add("B");
    	list.add("C");

    	String removed = list.remove(1);
    	assertEquals("Removed element should be 'B'", "B", removed);
    	assertEquals("Size should be 2 after removal", 2, list.size());
    	assertEquals("Remaining element at index 1 should be 'C'", "C", list.get(1));
	}

	/**
	 * Aims to test the set(int index, E data) method.
	 * Set an out-of-bounds index with a valid data argument.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testCustomSetIdxOutOfBounds() {
		list.set(3, "outOfBounds");
	}

	/**
	 * Aims to test the contains(E data, int start, int end) method.
	 * Data argument exists in the list but outside the given range. 
	 */
	@Test
	public void testCustomContainsExistsOutOfRange() {
		boolean result = list.contains("two", 2, 3);
        assertFalse("Contains should return false when element is outside the range", result);
	}

	/**
	 * Aims to test the indexOfElement(E data) method.
	 * Data argument does not exist in the list.
	 */
	@Test
	public void testCustomIndexOfElementDoesNotExist() {
		int index = list.indexOfElement("non-existent");
        assertEquals("indexOfElement should return -1 for non-existent data", -1, index);
	}
}