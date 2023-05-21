package org.example.TDAs;
import static org.example.TDAs.Functions.printBinaryTree;
import static org.example.TDAs.Functions.sumOfNonLeafNodes;

public class main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.create(2);  // Create the root node with value 1

        tree.addLeft(1);  // Add left child with value 2
        tree.addRight(3); // Add right child with value 3

        BinaryTree leftChild = tree.getLeft();
        leftChild.addLeft(-1);  // Add left child of the left child with value 4
        leftChild.addRight(4);

        BinaryTree rightChild = tree.getRight();
        rightChild.addLeft(-5);  // Add left child of the right child with value 5
        rightChild.addRight(6); // Add right child of the right child with value 6
        rightChild.getLeft().addLeft(-10);
        rightChild.getLeft().addRight(-3);

        // Print the values of the binary tree
        System.out.println(sumOfNonLeafNodes(tree));
        System.out.println(tree.inorderTraversal());
        System.out.println(tree.findHighestABB(tree).inorderTraversal());
    }


}
