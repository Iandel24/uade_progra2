package org.example.TDAs;

import org.example.TDAs.IBinaryTree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree implements IBinaryTree {
    private int value;
    private BinaryTree left;
    private BinaryTree right;

    public void create(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public int getValue() {
        return value;
    }

    public boolean isEmpty() {
        return (left == null && right == null);
    }

    public void addLeft(int value) {
        BinaryTree newLeft = new BinaryTree();
        newLeft.create(value);
        left = newLeft;
    }

    public void addRight(int value) {
        BinaryTree newRight = new BinaryTree();
        newRight.create(value);
        right = newRight;
    }

    public void removeLeft() {
        left = null;
    }

    public void removeRight() {
        right = null;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public BinaryTree getRight() {
        return right;
    }


    // Preorder traversal: root -> left -> right
    public List<Integer> preorderTraversal() {
        List<Integer> traversal = new ArrayList<>();
        preorderTraversal(this, traversal);
        return traversal;
    }

    private void preorderTraversal(BinaryTree node, List<Integer> traversal) {
        if (node != null) {
            traversal.add(node.value);
            preorderTraversal(node.left, traversal);
            preorderTraversal(node.right, traversal);
        }
    }

    // Inorder traversal: left -> root -> right
    public List<Integer> inorderTraversal() {
        List<Integer> traversal = new ArrayList<>();
        inorderTraversal(this, traversal);
        return traversal;
    }

    private void inorderTraversal(BinaryTree node, List<Integer> traversal) {
        if (node != null) {
            inorderTraversal(node.left, traversal);
            traversal.add(node.value);
            inorderTraversal(node.right, traversal);
        }
    }

    // Postorder traversal: left -> right -> root
    public List<Integer> postorderTraversal() {
        List<Integer> traversal = new ArrayList<>();
        postorderTraversal(this, traversal);
        return traversal;
    }

    private void postorderTraversal(BinaryTree node, List<Integer> traversal) {
        if (node != null) {
            postorderTraversal(node.left, traversal);
            postorderTraversal(node.right, traversal);
            traversal.add(node.value);
        }
    }

    // Method to check if a value is present in the tree
    public boolean contains(int value) {
        return contains(this, value);
    }

    private boolean contains(BinaryTree node, int value) {
        if (node == null) {
            return false;
        }
        if (node.value == value) {
            return true;
        }
        return contains(node.left, value) || contains(node.right, value);
    }

    // Method to get the height of the tree
    public int getHeight() {
        return getHeight(this);
    }

    private int getHeight(BinaryTree node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Method to count the number of nodes in the tree
    public int countNodes() {
        return countNodes(this);
    }

    private int countNodes(BinaryTree node) {
        if (node == null) {
            return 0;
        }
        return countNodes(node.left) + countNodes(node.right) + 1;
    }
    public BinaryTree findHighestABB(BinaryTree root) {
        if (root == null) {
            return null;
        }

        // Verifica si el árbol actual es un ABB
        if (isABB(root)) {
            return root;
        }

        // Encuentra recursivamente el ABB más alto en los subárboles izquierdo y derecho
        BinaryTree leftABB = findHighestABB(root.getLeft());
        BinaryTree rightABB = findHighestABB(root.getRight());

        // Retorna el ABB más alto encontrado entre el árbol actual y sus subárboles
        if (leftABB != null && rightABB != null) {
            // Ambos subárboles son ABBs, así que retorna el que tenga mayor altura
            return (leftABB.getHeight() >= rightABB.getHeight()) ? leftABB : rightABB;
        } else if (leftABB != null) {
            // Solo el subárbol izquierdo es un ABB
            return leftABB;
        } else if (rightABB != null) {
            // Solo el subárbol derecho es un ABB
            return rightABB;
        } else {
            // No se encontró ningún ABB en el árbol actual ni en sus subárboles
            return null;
        }
    }

    private boolean isABB(BinaryTree root) {
        return isABB(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isABB(BinaryTree node, int min, int max) {
        if (node == null) {
            return true;
        }

        if (node.getValue() < min || node.getValue() > max) {
            return false;
        }

        // Verifica recursivamente si los subárboles izquierdo y derecho también son ABBs
        return isABB(node.getLeft(), min, + node.getValue() - 1) &&
                isABB(node.getRight(), node.getValue() + 1, max);
    }

}

