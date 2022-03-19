import BinarySearchTree.Node;
import BinarySearchTree.Traversal;
import BinarySearchTree.Tree;

import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree.Tree tree = new Tree();
        String[] inputs = {"e","b","f","a","d","g","c","z","q","w","r","m","i","o"};
        for (String s : inputs){
            tree.insert(s);
            System.out.print("In order traversal: ");
            tree.traverse(Traversal.IN_ORDER);
        }
        for (String s : inputs){
            System.out.println("relative of: " + s + " = " + tree.delete(s));
            System.out.print("In order traversal: ");
            tree.traverse(Traversal.IN_ORDER);
        }

    }
}
