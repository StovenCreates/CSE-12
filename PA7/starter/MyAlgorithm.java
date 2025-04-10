/*
 * Name: Steven Chen
 * Email: stc008@ucsd.edu
 * PID: A17375690
 * Sources Used: Lectures and slides
 *
 * The purpose of this file is to implement an algorithm that finds the kth
 * largest value in an ArrayList using a priority queue.
 */

import java.util.ArrayList;

/**
 * This class contains a static method that uses a priority queue to find
 * the kth largest value in a given list of integers.
 */
public class MyAlgorithm {

    /**
     * Finds the kth largest value in the given list.
     * 
     * @param list the list of integers from which to find the kth largest value
     * @param k the position of the largest value to find
     * @return the kth largest value in the list
     * @throws NullPointerException if the list is null
     * @throws IllegalArgumentException if the list is empty or if k is out of bounds
     */
    public static Integer getKthLargest(ArrayList<Integer> list, int k) {
        if (list == null) {
            throw new NullPointerException();
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (k <= 0 || k > list.size()) {
            throw new IllegalArgumentException();
        }

        MyPriorityQueue<Integer> minHeap = new MyPriorityQueue<>();

        for (int i = 0; i < k; i++) {
            minHeap.push(list.get(i));
        }

        for (int i = k; i < list.size(); i++) {
            if (list.get(i) > minHeap.peek()) {
                minHeap.pop();
                minHeap.push(list.get(i));
            }
        }

        return minHeap.peek();
    }
}
