package org.example.TDAs;

public class main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.create(2);

        tree.addLeft(10);
        tree.addRight(3);

        BinaryTree leftChild = tree.getLeft();
        leftChild.addLeft(-1);
        leftChild.addRight(4);

        BinaryTree rightChild = tree.getRight();
        rightChild.addLeft(-5);
        rightChild.addRight(6);
        rightChild.getLeft().addLeft(-100);
        rightChild.getLeft().addRight(-3);

        // Print the values of the binary tree
        System.out.println(tree.findMinimumLeafValue()); // Encuentra hoja con minimo valor
        System.out.println(BinaryTree.sumOfNonLeafNodes(tree)); // Suma de todas la no-hojas
        System.out.println(tree.sumOfNonLeafsByMinLeaf(tree)); // Suma de todas las no-hojas x hoja con minimo valor
        System.out.println(tree.findHighestABB().inorderTraversal()); // Imprime el abb mas alto
    }


}
