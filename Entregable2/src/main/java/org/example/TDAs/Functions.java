package org.example.TDAs;

public class Functions {

    public static int sumOfNonLeafNodes(BinaryTree root) {
        if (root == null) {
            return 0;
        } else if (root.getLeft() == null && root.getRight() == null) {
            return 0;  // Exclude leaf nodes
        } else {
            int sum = root.getValue();
            sum += sumOfNonLeafNodes(root.getLeft());
            sum += sumOfNonLeafNodes(root.getRight());
            return sum;
        }
    }
    public static void printBinaryTree(BinaryTree node) {
        if (node != null) {
            System.out.print(node.getValue() + " ");
            printBinaryTree(node.getLeft());
            printBinaryTree(node.getRight());
        }
    }
}
