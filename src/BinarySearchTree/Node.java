package BinarySearchTree;

import java.util.Objects;

public class Node implements Comparable<Node> {
    private String data;
    private Node left;
    private Node right;
    private Node parent;

    public Node(String data, Node left, Node right, Node parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }


    public Node(String data) {
        this(data, null, null, null);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return this.data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(data, node.data);
    }


    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public int compareTo(Node o) {
        return this.getData().compareTo(o.getData());
    }
}
