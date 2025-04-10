/*
 * Name: Steven Chen
 * Email: stc008@ucsd.edu
 * PID: A17375690
 * Sources Used: Lectures and slides
 *
 * This file contains my implementation of isValidBrakers method.
 */

/**
 * This class contains an algorithm utilizing a stack or queue. 
 */
public class MyAlgorithm {
    /**
     * Returns whether or not the given string contains a valid arrangement of brackets
     * 
     * @param input the input string containing brackets
     * @throws NullPointerException if the given string is null
     * @return the whether or not the given string contains a valid arrangement of brackets
     */
    public static boolean isValidBrackets(String input) {
        if (input == null) {
            throw new NullPointerException();
        }

        MyStack<Character> stack = new MyStack<>(input.length());

        for (char i : input.toCharArray()) {
            if (i == '(' || i == '{' || i == '[') {
                stack.push(i);
            }
            else if (i == ')' || i == '}' || i == ']') {
                if (stack.empty()) {
                    return false;
                }
                char top = stack.pop();
                if ((i == ')' && top != '(') || (i == '}' && top != '{') 
                || (i == ']' && top != '[')) {
                    return false;
                }
            }
        }

        return stack.empty();
    }
}