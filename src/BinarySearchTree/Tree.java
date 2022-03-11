package BinarySearchTree;

import java.util.Objects;

public class Tree {
    private Node root;

    public Tree() {
    }

    public void insert(String data) {
        if (root == null) {
            root = new Node(data);
        }
        insert(this.root, data);
    } // works

    private void insert(Node root, String data) {

        if (data.compareToIgnoreCase(root.getData()) < 0) { // data < root.data
            if (root.getLeft() == null) {
                Node newNode = new Node(data);
                root.setLeft(newNode);
                newNode.setParent(root);
            } else {
                insert(root.getLeft(), data);
            }
        } else if (data.compareToIgnoreCase(root.getData()) > 0) { // data > root.data
            if (root.getRight() == null) {
                Node newNode = new Node(data);
                root.setRight(newNode);
                newNode.setParent(root);
            } else {
                insert(root.getRight(), data);
            }
        } // else
    } // works

    public void traverse(Traversal traversalType) {
        if (this.root != null) {
            traversal(this.root, traversalType);
        }
    } // works

    private void traversal(Node node, Traversal traversalType) {
        if (traversalType == Traversal.PRE_ORDER) {
            System.out.println(node.getData());
        }
        if (node.getLeft() != null) {
            traversal(node.getLeft(), traversalType);
        }
        if (traversalType == Traversal.IN_ORDER) {
            System.out.println(node.getData());
        }
        if (node.getRight() != null) {
            traversal(node.getRight(), traversalType);
        }
        if (traversalType == Traversal.POST_ORDER) {
            System.out.println(node.getData());
        }
    } // works

    public String searchData(String data) {
        Node node = find(data);
        if (node == null) {
            return null;
        }
        return Objects.requireNonNull(find(data)).getData();
    } // works

    private Node find(String data) {
        if (this.root != null) {
            return findNode(this.root, new Node(data));
        }
        return null;
    } // works

    private Node findNode(Node searchNode, Node node) {
        if (searchNode == null) {
            return null;
        }

        if (searchNode.getData().equalsIgnoreCase(node.getData())) {
            return searchNode;
        } else {
            if (node.getData().compareToIgnoreCase(searchNode.getData()) < 0) {
                return findNode(searchNode.getLeft(), node);
            } else if (node.getData().compareToIgnoreCase(searchNode.getData()) > 0) {
                return findNode(searchNode.getRight(), node);
            }
        }
        return null;
    } //works

    public String min() {
        Node node = min(this.root);
        if (node != null) {
            return node.getData();
        }
        return null;
    } // works

    private Node min(Node node) {
        if (node != null) {
            if (node.getLeft() == null) {
                return node;
            }
            return min(node.getLeft());
        }
        return null;
    } // works

    public String max() {
        Node node = max(this.root);
        if (node != null) {
            return node.getData();
        }
        return null;
    } // works

    private Node max(Node node) {
        if (node != null) {
            if (node.getRight() == null) {
                return node;
            }
            return max(node.getRight());
        }
        return null;
    } // works

    public boolean delete(String data) {
        return delete(find(data));
    } // works

    public boolean delete(Node node) {
        if (node != null) {
            if (node.getLeft() == null && node.getRight() == null) { // first case, no children
                deleteNoChildren(node); // works
            } else if (node.getLeft() != null && node.getRight() == null) { // second case, left exist
                deleteOneChild(node, true);
            } else if (node.getLeft() == null && node.getRight() != null) { // second case, right exist
                deleteOneChild(node, false);
            } else {
                deleteTwoChildren(node);
            }
        }
        return true;
    } // works

    private void deleteTwoChildren(Node node) {
        // get replacement node, i choose the right branch to get a replacement
//        Node tempNode = min(node.getRight());
        Node tempNode = max(node.getLeft());
        delete(tempNode);
        Node replacementNode = new Node(tempNode.getData());

        // node relatives

        Node parent = node.getParent();
        Node leftSibling = node.getLeft();
        Node rightSibling = node.getRight();

        // set relatives of node to replacement node
        // setting the parent
        replacementNode.setParent(parent);
        if (parent != null) {
            if (parent.getLeft() == node) { // node is a left child
                parent.setLeft(replacementNode);
            } else if (parent.getRight() == node) { // node is a right child
                parent.setRight(replacementNode);
            }
        } else {
            this.root = replacementNode;
        }
        // setting the left child
        if (leftSibling != null){
            replacementNode.setLeft(leftSibling);
            leftSibling.setParent(replacementNode);

        }
        // setting the right child
        if (rightSibling != null){
            replacementNode.setRight(rightSibling);
            rightSibling.setParent(replacementNode);
        }
    } // works

    private void deleteOneChild(Node node, boolean hasLeftChild) {
        Node parent = node.getParent();

        if (hasLeftChild) { // node has a left child

            // get the replacement node
            Node tempNode = max(node.getLeft());
            delete(tempNode);
            Node replacementNode = new Node(tempNode.getData());
            replacementNode.setParent(parent);

            // parent of node become parent of replacement node

            if (parent != null) {
                if (parent.getLeft() == node) { // node is a left child
                    parent.setLeft(replacementNode);
                } else if (parent.getRight() == node) { // node is a right child
                    parent.setRight(replacementNode);
                }
            } else {
                this.root = replacementNode;
            }

            // connecting the left child of node to the replacement node

            if (node.getLeft() != null) {
                node.getLeft().setParent(replacementNode);
            }
            replacementNode.setLeft(node.getLeft());

        } else { //  node has a right child

            // get the replacement node
            Node tempNode = min(node.getRight());
            delete(tempNode);
            Node replacementNode = new Node(tempNode.getData());
            replacementNode.setParent(parent);

            // parent of node become parent of replacement node

            if (parent != null) {
                if (parent.getLeft() == node) {  // node is a left child
                    parent.setLeft(replacementNode);
                } else if (parent.getRight() == node) {// node is a right child
                    parent.setRight(replacementNode);
                }
            } else {
                this.root = replacementNode;
            }

            // connecting the right child of node to the replacement node

            if (node.getRight() != null) {
                node.getRight().setParent(replacementNode);
            }
            replacementNode.setRight(node.getRight());

        }
    } //works

    private void deleteNoChildren(Node node) {
        Node parent = node.getParent();
        if (parent != null) {
            if (parent.getLeft() == node) { // node is a left child
                parent.setLeft(null);
                node.setParent(null);
            } else if (parent.getRight() == node) { // node is a right child
                parent.setRight(null);
                node.setParent(null);
            }
        } else {
            this.root = null;
        }
    } // works

}
