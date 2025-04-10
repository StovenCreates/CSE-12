/*
 * Name: Steven Chen
 * Email: stc008@ucsd.edu
 * PID: A17375690
 * Sources Used: Lectures and slides
 * 
 * The purpose of this file is to test my file MyBST.java. This
 * file should include most, if not all the test cases that need to pass. 
 */


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class CustomTester {
    private MyBST<Integer, String> bst;

    @Before
    public void setUp() {
        bst = new MyBST<>();
        bst.insert(10, "Ten");
        bst.insert(5, "Five");
        bst.insert(15, "Fifteen");
        bst.insert(3, "Three");
        bst.insert(7, "Seven");
    }

    @Test
    public void testSuccessor() {
        MyBST.MyBSTNode<Integer, String> root = bst.root;
        assertEquals("Fifteen", root.successor().getValue());
        assertEquals("Seven", root.getLeft().successor().getValue());
        assertNull(root.getRight().successor());
    }

    @Test
    public void testInsert() {
        assertNull(bst.insert(20, "Twenty"));
        assertEquals("Twenty", bst.search(20));
        assertEquals("Ten", bst.insert(10, "UpdatedTen"));
        assertEquals("UpdatedTen", bst.search(10));
        assertEquals(6, bst.size());
    }

    @Test
    public void testSearch() {
        assertEquals("Five", bst.search(5));
        assertEquals("Ten", bst.search(10));
        assertNull(bst.search(25));
        assertNull(bst.search(null));
    }

    @Test
    public void testRemove() {
        assertEquals("Three", bst.remove(3));
        assertNull(bst.search(3));
        
        assertEquals("Five", bst.remove(5));
        assertNull(bst.search(5));

        assertEquals("Ten", bst.remove(10));
        assertEquals("Fifteen", bst.root.getValue());

        assertNull(bst.remove(50));
    }

    @Test
    public void testInorder() {
        ArrayList<MyBST.MyBSTNode<Integer, String>> inorderList = bst.inorder();
        assertEquals(5, inorderList.size());
        assertEquals("Three", inorderList.get(0).getValue());
        assertEquals("Seven", inorderList.get(2).getValue());
        assertEquals("Fifteen", inorderList.get(4).getValue());
    }

    @Test
    public void testCopy() {
        MyBST<Integer, String> bst = new MyBST<>();
        bst.insert(20, "Twenty");
        bst.insert(10, "Ten");
        bst.insert(30, "Thirty");
    
        MyBST<Integer, String> copy = bst.copy();
        assertEquals(3, copy.size());
        assertEquals("Twenty", copy.search(20));
        assertEquals("Ten", copy.search(10));
        assertEquals("Thirty", copy.search(30));
    
        bst.insert(25, "Twenty-Five");
        assertNull(copy.search(25));
    }


    @Test
    public void testRemoveFour() {
        MyBST<Integer, String> bst = new MyBST<>();
        bst.insert(20, "Twenty");
        bst.insert(10, "Ten");
        bst.insert(30, "Thirty");
        bst.insert(25, "Twenty-Five");
        bst.insert(35, "Thirty-Five");
        bst.insert(22, "Twenty-Two");
        bst.insert(27, "Twenty-Seven");
    
        assertEquals("Twenty", bst.remove(20));
        assertEquals("Twenty-Two", bst.root.getKey());
        assertNotNull(bst.search(27));
        assertEquals(6, bst.size());
        assertEquals("Ten", bst.root.getLeft().getValue());
        assertEquals("Thirty", bst.root.getRight().getValue());
    }

    @Test
    public void testInsertOne() {
        MyBST<Integer, String> bst = new MyBST<>();
        assertNull(bst.insert(10, "Ten"));
        assertEquals("Ten", bst.search(10));
        assertEquals(1, bst.size());
        assertNotNull(bst.root);
        assertEquals(Integer.valueOf(10), bst.root.getKey());
        assertEquals("Ten", bst.root.getValue());
    }

    @Test
    public void testInOrder() {
        MyBST<Integer, String> bst = new MyBST<>();
        bst.insert(20, "Twenty");
        bst.insert(10, "Ten");
        bst.insert(30, "Thirty");
        bst.insert(15, "Fifteen");
        bst.insert(5, "Five");
    
        ArrayList<MyBST.MyBSTNode<Integer, String>> inorderList = bst.inorder();
        assertEquals(5, inorderList.size());
        assertEquals("Five", inorderList.get(0).getValue());
        assertEquals("Ten", inorderList.get(1).getValue());
        assertEquals("Fifteen", inorderList.get(2).getValue());
        assertEquals("Twenty", inorderList.get(3).getValue());
        assertEquals("Thirty", inorderList.get(4).getValue());
    }

    @Test
    public void testRemoveOne() {
        MyBST<Integer, String> bst = new MyBST<>();
        bst.insert(10, "Ten");
        bst.insert(5, "Five");
        bst.insert(15, "Fifteen");
    
        assertEquals("Five", bst.remove(5));
        assertNull(bst.search(5));
        assertEquals(2, bst.size());
        assertNull(bst.root.getLeft());
    }

    @Test
    public void testRemoveTwo() {
        MyBST<Integer, String> bst = new MyBST<>();
        bst.insert(10, "Ten");
        bst.insert(5, "Five");
        bst.insert(15, "Fifteen");
        bst.insert(12, "Twelve");
    
        assertEquals("Fifteen", bst.remove(15));
        assertNull(bst.search(15));
        assertEquals("Twelve", bst.search(12));
        assertEquals(3, bst.size());
        assertEquals(Integer.valueOf(12), bst.root.getRight().getKey());
    }

    @Test
    public void testRemoveThree() {
        MyBST<Integer, String> bst = new MyBST<>();
        bst.insert(10, "Ten");
        bst.insert(15, "Fifteen");
    
        assertEquals("Ten", bst.remove(10));
        assertEquals("Fifteen", bst.root.getValue());
        assertEquals(1, bst.size());
        assertNull(bst.root.getLeft());
        assertNull(bst.root.getRight());
    }
}
