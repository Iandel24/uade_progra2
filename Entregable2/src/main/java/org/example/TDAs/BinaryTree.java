package org.example.TDAs;

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

    public static int sumOfNonLeafNodes(BinaryTree root) {
        if (root == null) {
            return 0;
        } else if (root.getLeft() == null && root.getRight() == null) {
            return 0;  // Excluir nodos hoja
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

    // Recorrido en preorden: raíz -> izquierda -> derecha
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

    // Recorrido en orden: izquierda -> raíz -> derecha
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

    // Recorrido en postorden: izquierda -> derecha -> raíz
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

    // Método para verificar si un valor está presente en el árbol
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

    // Método para obtener la altura del árbol
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

    // Método para contar el número de nodos en el árbol
    public int countNodes() {
        return countNodes(this);
    }

    private int countNodes(BinaryTree node) {
        if (node == null) {
            return 0;
        }
        return countNodes(node.left) + countNodes(node.right) + 1;
    }

    public int findMinimumLeafValue() {
        if (this == null) {
            throw new IllegalArgumentException("El árbol binario está vacío.");
        }

        if (this.getLeft() == null && this.getRight() == null) {
            // Caso base: el nodo actual es una hoja
            return this.getValue();
        }

        int leftMin = Integer.MAX_VALUE;
        int rightMin = Integer.MAX_VALUE;

        if (this.getLeft() != null) {
            // Encontrar recursivamente el valor mínimo en el subárbol izquierdo
            leftMin = this.getLeft().findMinimumLeafValue();
        }

        if (this.getRight() != null) {
            // Encontrar recursivamente el valor mínimo en el subárbol derecho
            rightMin = this.getRight().findMinimumLeafValue();
        }

        // Devolver el valor mínimo entre los nodos hoja
        return Math.min(leftMin, rightMin);
    }

    public int sumOfNonLeafsByMinLeaf(BinaryTree tree) {
        return (tree.findMinimumLeafValue() * sumOfNonLeafNodes(tree));
    }

    public BinaryTree findHighestABB() {
        if (this == null) {
            return null;
        }

        // Verificar si el árbol actual es un ABB
        if (isABB(this)) {
            return this;
        }

        // Encontrar recursivamente el ABB más alto en los subárboles izquierdo y derecho
        BinaryTree leftABB = this.getLeft().findHighestABB();
        BinaryTree rightABB = this.getRight().findHighestABB();

        // Devolver el ABB más alto encontrado entre el árbol actual y sus subárboles
        if (leftABB != null && rightABB != null) {
            // Ambos subárboles son ABBs, así que devolver el que tenga mayor altura
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

        // Verificar recursivamente si los subárboles izquierdo y derecho también son ABBs
        return isABB(node.getLeft(), min, +node.getValue() - 1) &&
                isABB(node.getRight(), node.getValue() + 1, max);
    }
}
