// Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root != null){
            return Math.abs(height(root.left) - height(root.right)) < 2;
        }
        return false;
    }

    public int height(TreeNode node){
        if (node == null){
            return -1;
        } else if (node.left == null && node.right == null) {
            return 0;
        }
        return Math.max(height(node.left),height(node.right)) + 1;
    }
}