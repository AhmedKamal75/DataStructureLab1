import BinarySearchTree.Node;
import BinarySearchTree.Traversal;
import BinarySearchTree.Tree;

import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
//        BinarySearchTree.Node node1 = new Node("ahmed");
//        BinarySearchTree.Node node2 = new Node("ahmed");
//        BinarySearchTree.Node node3 = new Node("mohammad");
//
//        System.out.println(node1.equals(node2));
//        System.out.println(node1.equals(node3));
//
//        System.out.println("ahmed".compareToIgnoreCase("mohammad"));
//        System.out.println("a".compareToIgnoreCase("m"));
//        System.out.println("kamal".compareToIgnoreCase("ahmed"));
//        System.out.println("ahmed".compareToIgnoreCase("ahmed"));

        BinarySearchTree.Tree tree = new Tree();

        tree.insert("e");
        tree.insert("b");
        tree.insert("f");
        tree.insert("a");
        tree.insert("d");
        tree.insert("g");
        tree.insert("c");
        tree.insert("z");
        tree.insert("q");
        tree.insert("w");
        tree.insert("r");
        tree.insert("m");
        tree.insert("i");
        tree.insert("o");
        tree.insert("c");
        System.out.println("In order traversal: ");
        tree.traverse(Traversal.IN_ORDER);

//        Node node = tree.find("y");

//        System.out.println("first search for q: " + node.getData() + ", parent: " + node.getParent() + ", left child: " +
//                node.getLeft() + ", right child: " + node.getRight());
//        System.out.println(tree.searchData("y"));

//        System.out.println("min: " + tree.min());
//        System.out.println("max: " + tree.max());

        tree.delete("e");
        tree.delete("b");
        tree.delete("f");
        tree.delete("a");
        tree.delete("d");
        tree.delete("g");
        tree.delete("c");
        tree.delete("z");
        tree.delete("q");
        tree.delete("w");
        tree.delete("r");
        tree.delete("m");
        tree.delete("i");
        tree.delete("o");
        tree.delete("c");
        System.out.println("In order traversal: ");
        tree.traverse(Traversal.IN_ORDER);
    }
}
