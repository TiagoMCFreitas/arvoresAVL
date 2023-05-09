package arvoresavl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class ArvoresAVL {

    private class Node {
        String value;
        Node left, right;
        int height;
        ArrayList<String> listaDePalavras = new ArrayList<>();

        Node(String value) {
            this.value = value;
            height = 1;
        }
    }

    private Node root;

    public void insert(String value) {
        root = insert(root, value);
    }

    public ArrayList<String> getListaPelaPalavra(String palavra) {
        Node no = find(root, String.valueOf(palavra.charAt(0)));
        return no.listaDePalavras;
    }

    public void removerPalavra(String palavra) {
        Node no = find(root, String.valueOf(palavra.charAt(0)));

        no.listaDePalavras.remove(palavra);

    }

    public int binarySearch(String palavra) {
        Node no = find(root, String.valueOf(palavra.charAt(0)));
        Collections.sort(no.listaDePalavras);

        int comeco = 0;
        int fim = no.listaDePalavras.size() - 1;
        while (comeco <= fim) {
            int meio = (comeco + fim) / 2;
            int cmp = palavra.compareTo(no.listaDePalavras.get(meio));
            if (cmp == 0) {
                return meio;
            } else if (cmp < 0) {
                fim = meio - 1;
            } else {
                comeco = meio + 1;
            }
        }
        return -1;
    }

    public boolean acharPalavras(String palavra) {
        Node no = find(root, String.valueOf(palavra.charAt(0)));
        for (int i = 0; i < no.listaDePalavras.size(); i++) {
            if (no.listaDePalavras.get(i).equals(palavra)) {
                return true;
            }
        }
        return false;
    }

    public void addPalavra(String palavra) {
        Node no = find(root, String.valueOf(palavra.charAt(0)));
        no.listaDePalavras.add(palavra);

    }

    public ArrayList<String> getLista(String letra) {

        return find(root, letra).listaDePalavras;
    }

    public Node find(String value) {
        return find(root, value);
    }

    private Node find(Node node, String value) {
        if (node == null) {
            return null;
        }
        if (node.value.equals(value)) {
            return node;
        }
        if (value.compareTo(node.value) < 0) {
            return find(node.left, value);
        } else {
            return find(node.right, value);
        }
    }

    private Node insert(Node node, String value) {
        if (node == null) {
            return new Node(value);

        }

        if (value.compareTo(node.value) < 0) {
            node.left = insert(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = insert(node.right, value);
        } else {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && value.compareTo(node.left.value) < 0) {
            return rightRotate(node);
        }

        if (balance < -1 && value.compareTo(node.right.value) > 0) {
            return leftRotate(node);
        }

        if (balance > 1 && value.compareTo(node.left.value) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && value.compareTo(node.right.value) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private Node rightRotate(Node node) {
        Node leftChild = node.left;
        Node rightGrandChild = leftChild.right;

        leftChild.right = node;
        node.left = rightGrandChild;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        leftChild.height = 1 + Math.max(height(leftChild.left), height(leftChild.right));

        return leftChild;
    }

    private Node leftRotate(Node node) {
        Node rightChild = node.right;
        Node leftGrandChild = rightChild.left;

        rightChild.left = node;
        node.right = leftGrandChild;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        rightChild.height = 1 + Math.max(height(rightChild.left), height(rightChild.right));

        return rightChild;
    }

    private Node delete(Node node, String value) {
        if (node == null) {
            return null;
        }

        // Encontra o nó a ser excluído
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = delete(node.left, value);
        } else if (cmp > 0) {
            node.right = delete(node.right, value);
        } else { // O nó atual é o nó a ser excluído
            // Caso 1: o nó a ser excluído é uma folha
            if (node.left == null && node.right == null) {
                node = null;
            }
            // Caso 2: o nó a ser excluído tem apenas um filho
            else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            }
            // Caso 3: o nó a ser excluído tem dois filhos
            else {
                // Encontra o sucessor (menor valor na subárvore direita)
                Node successor = node.right;
                while (successor.left != null) {
                    successor = successor.left;
                }
                // Copia o valor do sucessor para o nó atual
                node.value = successor.value;
                // Remove o sucessor
                node.right = delete(node.right, successor.value);
            }
        }

        // Reequilibra a árvore após a exclusão
        return balance(node);
    }

    private int balanceFactor(Node node) {
        return height(node.left) - height(node.right);
    }

    private Node balance(Node node) {
        if (node == null) {
            return null;
        }

        // Calcula o fator de balanceamento do nó atual
        int balanceFactor = balanceFactor(node);

        // Caso 1: o nó está desbalanceado para a esquerda
        if (balanceFactor > 1) {
            // Verifica se é uma rotação simples ou dupla
            if (balanceFactor(node.left) < 0) {
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        }
        // Caso 2: o nó está desbalanceado para a direita
        else if (balanceFactor < -1) {
            // Verifica se é uma rotação simples ou dupla
            if (balanceFactor(node.right) > 0) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }

        // O nó está balanceado
        return node;
    }
    // Métodos de busca e percurso da árvore (não são necessários para a inserção)

    public boolean contains(String value) {
        Node current = root;

        while (current != null) {
            if (value.equals(current.value)) {
                return true;
            } else if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.value + " ");
            inOrderTraversal(node.right);
        }
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.print(node.value + " ");
        }
    }

    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.value + " ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    private void printAVLTree(Node node, String indent, boolean last) {
        if (node != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("'--");
                indent += "  ";
            } else {
                System.out.print("|--");
                indent += "| ";
            }
            System.out.println(node.value);

            printAVLTree(node.left, indent, false);
            printAVLTree(node.right, indent, true);
        }
    }

    public void printAVLTree() {
        printAVLTree(root, "", true);
    }
}
