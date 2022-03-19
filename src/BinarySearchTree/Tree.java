package BinarySearchTree;

import java.util.Objects;

public class Tree {
    private Node root;

    public Tree() {
    }

    public void insert(String data) {
        if (root == null) {
            root = new Node(data);
        } else{
            balance(insert(this.root, data));
        }
    } // works

    private Node insert(Node root, String data) {

        if (data.compareTo(root.getData()) < 0) { // data < root.data
            if (root.getLeft() == null) {
                Node newNode = new Node(data);
                root.setLeft(newNode);
                newNode.setParent(root);
                return newNode;
            } else {
                return insert(root.getLeft(), data);
            }
        } else if (data.compareTo(root.getData()) > 0) { // data > root.data
            if (root.getRight() == null) {
                Node newNode = new Node(data);
                root.setRight(newNode);
                newNode.setParent(root);
                return newNode;
            } else {
                return insert(root.getRight(), data);
            }
        } else{ // else
            return null;
        }

    } // works

    public void traverse(Traversal traversalType) {
        if (this.root != null) {
            traversal(this.root, traversalType);
        }
        System.out.println();
    } // works

    private void traversal(Node node, Traversal traversalType) {
        if (traversalType == Traversal.PRE_ORDER) {
            doDo(node);
        }
        if (node.getLeft() != null) {
            traversal(node.getLeft(), traversalType);
        }
        if (traversalType == Traversal.IN_ORDER) {
            doDo(node);
        }
        if (node.getRight() != null) {
            traversal(node.getRight(), traversalType);
        }
        if (traversalType == Traversal.POST_ORDER) {
            doDo(node);
        }
    } // works

    private void doDo(Node node){
        System.out.print(node.getData() + ",");
    }

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
            if (node.getData().compareTo(searchNode.getData()) < 0) {
                return findNode(searchNode.getLeft(), node);
            } else if (node.getData().compareTo(searchNode.getData()) > 0) {
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

    public Node delete(String data) {
        return delete(find(data));
    } // works

    private Node delete(Node node) { // returns the last known relative (replacement, parent)
        if (node != null) {
            Node relativeNode;
            if (node.getLeft() == null && node.getRight() == null) { // first case, no children
                relativeNode = deleteNoChildren(node); // works
            } else if (node.getLeft() != null && node.getRight() == null) { // second case, left exist
                relativeNode = deleteOneChild(node, true);
            } else if (node.getLeft() == null && node.getRight() != null) { // second case, right exist
                relativeNode = deleteOneChild(node, false);
            } else {
                relativeNode = deleteTwoChildren(node);
            }
            balance(relativeNode);
            return relativeNode;
        }
        return null;
    } // works

    private Node deleteTwoChildren(Node node) {
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
        if (leftSibling != null) {
            replacementNode.setLeft(leftSibling);
            leftSibling.setParent(replacementNode);

        }
        // setting the right child
        if (rightSibling != null) {
            replacementNode.setRight(rightSibling);
            rightSibling.setParent(replacementNode);
        }
        return replacementNode;
    } // works

    private Node deleteOneChild(Node node, boolean hasLeftChild) {
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
            return replacementNode;

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
            return replacementNode;
        }
    } //works

    private Node deleteNoChildren(Node node) {
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
        return parent;
    } // works

    private int nodeH(Node node) {
        if (node == null) {
            return -1;
        } else if (node.getLeft() == null && node.getRight() == null) { // a leaf node
            return 0;
        } else {
            return Math.max(nodeH(node.getLeft()), nodeH(node.getRight())) + 1;
        }
    } // node height

    private int nodeB(Node node) {
        if (node == null) {
            return 0;
        }
        return nodeH(node.getLeft()) - nodeH(node.getRight());
    } // node balance

    public int getBalanceOf(String data) {
        Node node = find(data);
        if (node != null) {
            return nodeB(node);
        }
        return Integer.MAX_VALUE;
    }

    private void balance(Node node) {
        if (node == null) {
            return;
        }
        if (node == this.root) {
            int bf = nodeB(node);
            switch (bf) {
                case 2 -> // left heavy
                        rightRotate(node.getLeft(), node);
                case -2 -> // right heavy
                        leftRotate(node, node.getRight());
            }
        }
        int bf = nodeB(node.getParent());
        switch (bf) {
            case 2 -> { // left heavy
                if (nodeH(node.getLeft()) > nodeH(node.getRight())) { // outside case
//      imbalanced node --> 341
//                         /   \
//  working at(node) --> 241  123
//                       /
//        new node --> 324
                    rightRotate(node, node.getParent());
                } else if (nodeH(node.getLeft()) < nodeH(node.getRight())) { // inside case
//      imbalanced node --> 341
//                         /   \
//  working at(node) --> 241  123
//                          \
//             new node --> 235
                    Node rightChild = node.getRight();
                    Node imbalancedNode = node.getParent();
                    leftRotate(node, rightChild);
                    rightRotate(rightChild, imbalancedNode);
                }
            }


            case -2 -> { // right heavy
                if (nodeH(node.getLeft()) < nodeH(node.getRight())) { // outside case
//                          341 <-- imbalanced node
//                         /   \
//                        241  123 <-- working at(node)
//                                \
//                                161 <-- new node
                    leftRotate(node.getParent(), node);
                } else if (nodeH(node.getLeft()) > nodeH(node.getRight())) { // inside case
//                          341 <-- imbalanced node
//                         /   \
//                        241  123 <-- working at(node)
//                            /
//                          235 <-- new node
                    Node leftChild = node.getLeft();
                    Node imbalancedNode = node.getParent();
                    rightRotate(leftChild, node);
                    leftRotate(imbalancedNode, leftChild);

                }
            }
        }
        //

        balance(node.getParent());

    }

    private void leftRotate(Node node1, Node node2) {
//                                   <==      | <-- (nr)
//                 node2 --> 341             241 <-- node1
//                          /   \           /   \ <-- needs redirection(nr)
//               node1 --> 241  123        324  341 <--node2
//                        /   \       (nr) --> /  \
//                      324  235             235   123
        Node node1Parent = node1.getParent();
        Node node2Left = node2.getLeft();

        // parent of new top node handling
        node2.setParent(node1Parent);
        if (node1Parent != null) {//                                         /
            if (node1Parent.getLeft() == node1) { // node1 is a left child 241
                node1Parent.setLeft(node2);
            }//                                                                   \
            else if (node1Parent.getRight() == node1) { // node 1 is a right child 241
                node1Parent.setRight(node2);
            }
        } else {
            this.root = node2;
        }

        // 235
        if (node2Left != null) {
            node2Left.setParent(node1);
        }
        node1.setRight(node2Left);

        // rotation
        node2.setLeft(node1);
        node1.setParent(node2);

    } // works

    private void rightRotate(Node node1, Node node2) {  // works
//                   (nr) --> |     ==>
//                 node2 --> 341             241  <-- node1
//needs redirection(nr)-->  /   \           /   \
//               node1 --> 241  123        324  341 <--node2
//                        /   \  <--(nr)        /  \
//                      324  235             235   123
        Node node1Right = node1.getRight();
        Node node2Parent = node2.getParent();

        // parent of new top node handling
        node1.setParent(node2Parent);
        if (node2Parent != null) {//                                      \
            if (node2Parent.getLeft() == node2) { // node2 is a left child 241
                node2Parent.setLeft(node1);
            }//                                                                   /
            else if (node2Parent.getRight() == node2) { // node2 is a right child 241
                node2Parent.setRight(node1);
            }
        } else {
            this.root = node1;
        }
        // 235
        if (node1Right != null) {
            node1Right.setParent(node2);
        }
        node2.setLeft(node1Right);
        // rotation
        node1.setRight(node2);
        node2.setParent(node1);
    } // works

}
