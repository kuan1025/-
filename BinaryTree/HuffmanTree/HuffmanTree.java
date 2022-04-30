package BinaryTree.HuffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        Node node = Node(arr);
        node.sort();
    }


    public static Node Node(int[] arr) {
        Node parent = null;
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        Collections.sort(nodes);
        while (nodes.size() > 1) {
            // 建立2叉樹
            parent = new Node(nodes.get(0).value + nodes.get(1).value);
            parent.left = nodes.get(0);
            parent.right = nodes.get(1);
            nodes.add(parent);
            nodes.remove(parent.left);
            nodes.remove(parent.right);

            Collections.sort(nodes);
            System.out.println(nodes);
        }
        return parent;
    }
}

class Node implements Comparable<Node> {
    public Node right;
    public Node left;
    int value = 0;

    public void sort() {
        System.out.println(this);
        if (this.left != null) {
            this.left.sort();
        }
        if (this.right != null) {
            this.right.sort();
        }
    }


    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return value - o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }



    }
