/*
 * Name: Steven Chen
 * Email: stc008@ucsd.edu
 * PID: A17375690
 * Sources Used: Lectures and slides
 *
 * The purpose of this file is to create a class that
 * is an implementation of a Binary Search Tree. 
 */

import java.util.ArrayList;

/**
 * This class is an implementation of a Binary Search Tree.
 * It has generic keys and values that can do tasks such
 * as inserting, searching, removing, etc. 
 * 
 * Instance variables:
 * root - root node of BST
 * size - number of nodes in the tree
 * 
 * @param K the type of keys
 * @param V the type of values
 */
public class MyBST<K extends Comparable<K>, V> {

    //Instance Variables
    MyBSTNode<K, V> root = null;
    int size = 0;

    /**
     * Finds the node with the given key in the BST
     *
     * @param key the key to find
     * @return the node with the specified key or null if not found
     */
    private MyBSTNode<K, V> findNode(K key) {
        MyBSTNode<K, V> current = root;
        while (current != null) {
            int comparison = key.compareTo(current.getKey());
            if (comparison == 0) {
                return current;
            }
            else if (comparison < 0) {
                current = current.getLeft();
            }
            else {
                current = current.getRight();
            }
        }
        return null;
    }

    /**
     * Replaces an old node with a new node
     *
     * @param oldNode the node being replaced
     * @param newNode the node to replace with
     */
    private void replaceNode(MyBSTNode<K, V> oldNode, MyBSTNode<K, V> newNode) {
        if (oldNode.getParent() == null) {
            root = newNode;
        }
        else if (oldNode == oldNode.getParent().getLeft()) {
            oldNode.getParent().setLeft(newNode);
        }
        else {
            oldNode.getParent().setRight(newNode);
        }

        if (newNode != null) {
            newNode.setParent(oldNode.getParent());
        }
    }

    /**
     * In-order traversal of the BST starting from the node
     * and appends each visited node to the provided ArrayList
     *
     * @param node the starting node
     * @param result the ArrayList to store the visited nodes in in-order
     */
    private void inorderTraversal(MyBSTNode<K, V> node, ArrayList<MyBSTNode<K, V>> result) {
        if (node != null) {
            inorderTraversal(node.getLeft(), result);
            result.add(node);
            inorderTraversal(node.getRight(), result);
        }
    }

    /**
     * Copies a subtree rooted at the given node creating a new node
     * with the same key and value
     *
     * @param node the node being copied
     * @param parent the parent node of the new copied node
     * @return the root of the copied subtree or null if the input node is null
 */
private MyBSTNode<K, V> copyNode(MyBSTNode<K, V> node, MyBSTNode<K, V> parent) {
    if (node == null) {
        return null;
    }
    MyBSTNode<K, V> newNode = new MyBSTNode<>(node.getKey(), node.getValue(), parent);
    newNode.setLeft(copyNode(node.getLeft(), newNode));
    newNode.setRight(copyNode(node.getRight(), newNode));
    return newNode;
}

    public int size() {
        return size;
    }

    /**
     * Inserts a new node into the BST with the given key and value
     *
     * @param key the key of the node being inserted
     * @param value the value of the node being inserted
     * @return the last value associated with the key or null if the key is new
     * @throws NullPointerException if  key is null
     */
    public V insert(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }
    
        MyBSTNode<K, V> newNode = new MyBSTNode<>(key, value, null);
        if (root == null) {
            root = newNode;
            size++;
            return null;
        }
    
        MyBSTNode<K, V> current = root;
        while (true) {
            int comparison = key.compareTo(current.getKey());
            if (comparison == 0) {
                V oldValue = current.getValue();
                current.setValue(value);
                return oldValue;
            }
            else if (comparison < 0) {
                if (current.getLeft() == null) {
                    current.setLeft(newNode);
                    newNode.setParent(current);
                    size++;
                    return null;
                }
                current = current.getLeft();
            }
            else {
                if (current.getRight() == null) {
                    current.setRight(newNode);
                    newNode.setParent(current);
                    size++;
                    return null;
                }
                current = current.getRight();
            }
        }
    }

    /**
     * Searches for a node with the given key and returns its value
     *
     * @param key the key of the node being searched
     * @return the value of the node with the given key or null if not found
     */
    public V search(K key) {
        if (key == null) {
            return null;
        }
    
        MyBSTNode<K, V> current = root;
        while (current != null) {
            int comparison = key.compareTo(current.getKey());
            if (comparison == 0) {
                return current.getValue();
            }
            else if (comparison < 0) {
                current = current.getLeft();
            }
            else {
                current = current.getRight();
            }
        }
        return null;
    }

    /**
     * Removes a node with the given key
     *
     * @param key the key of the node being removed
     * @return the value of the removed node or null if the key is not found
     */
    public V remove(K key) {
        if (key == null) {
            return null;
        }
    
        MyBSTNode<K, V> nodeToRemove = findNode(key);
        if (nodeToRemove == null) {
            return null;
        }
    
        V oldValue = nodeToRemove.getValue();
    
        if (nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null) {
            replaceNode(nodeToRemove, null);
        }
        else if (nodeToRemove.getLeft() == null) {
            replaceNode(nodeToRemove, nodeToRemove.getRight());
        }
        else if (nodeToRemove.getRight() == null) {
            replaceNode(nodeToRemove, nodeToRemove.getLeft());
        }
        else {
            MyBSTNode<K, V> successor = nodeToRemove.getRight();
            while (successor.getLeft() != null) {
                successor = successor.getLeft();
            }
            nodeToRemove.setKey(successor.getKey());
            nodeToRemove.setValue(successor.getValue());
            replaceNode(successor, successor.getRight());
        }
    
        size--;
        return oldValue;
    }

    public ArrayList<MyBSTNode<K, V>> inorder() {
        ArrayList<MyBSTNode<K, V>> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }

    public MyBST<K, V> copy() {
        MyBST<K, V> copy = new MyBST<>();
        copy.root = copyNode(this.root, null);
        copy.size = this.size;
        return copy;
    }

    static class MyBSTNode<K, V> {
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         *
         * @param key    the key the MyBSTNode<K,V> will
         * @param value  the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         *
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         *
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }

        /**
         * Finds and returns the successor of the current node
         *
         * @return the successor node or null if no successor exists
         */
        public MyBSTNode<K, V> successor() {
            if (this.right != null) {
                MyBSTNode<K, V> current = this.right;
                while (current.getLeft() != null) {
                    current = current.getLeft();
                }
                return current;
            }

            MyBSTNode<K, V> current = this;
            while (current.getParent() != null && current == current.getParent().getRight()) {
                current = current.getParent();
            }
            return current.getParent();
        }

        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null :
                    this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null :
                    this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}
