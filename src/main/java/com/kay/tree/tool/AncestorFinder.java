package com.kay.tree.tool;

import com.kay.Assert;
import com.kay.TreeNode;

import java.util.Objects;

public class AncestorFinder {


    /**
     * @return the lowest common ancestor for p,q in root
     * @apiNote : post-order traverse
     * case 1. if p in root and q in root, return LCA.
     * case 2. if p not in root and q not in root, return null.
     * case 3. if only p in root or only q in root, return p or q (important).
     */
    static TreeNode findLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        //base case: find p or q.
        if (root.equals(p) || root.equals(q)) {
            return root;
        }

        TreeNode left = findLowestCommonAncestor(root.left, p, q);
        TreeNode right = findLowestCommonAncestor(root.right, p, q);

        //当 p,q 第一次相遇时，即为最近的共同祖先
        if (left != null && right != null) {
            //first place to meet
            return root;
        }

        if (left == null && right == null) {
            return null;
        }
        //case 3
        return left == null ? right : left;
    }

    public static void main(String[] args) {
        String content = "674,546,741,457,#,#,836,153,486,#,#,19,195,#,#,#,#,#,315,#,#,";
        TreeNode node = BinaryTreeSerializers.BREAD_FIRST_ORDER.deserialize(content);

        TreeNode commonAncestor = findLowestCommonAncestor(node, new TreeNode(315), new TreeNode(486));

        Assert.isTrue(Objects.nonNull(commonAncestor));
        Assert.isTrue(commonAncestor.val == 457);
    }

}
