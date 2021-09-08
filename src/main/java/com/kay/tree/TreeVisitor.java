package com.kay.tree;

import com.kay.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public final class TreeVisitor {

    private TreeVisitor() {}


    public static void preOrderTraverse(TreeNode root, Consumer<TreeNode> consumer) {
        if (root == null) {
            consumer.accept(null);
            return;
        }

        //action
        consumer.accept(root);

        preOrderTraverse(root.left, consumer);
        preOrderTraverse(root.right, consumer);
    }

    public static void postOrderTraverse(TreeNode root, Consumer<TreeNode> consumer) {
        if (root == null) {
            consumer.accept(null);
            return;
        }
        postOrderTraverse(root.left, consumer);
        postOrderTraverse(root.right, consumer);

        //action
        consumer.accept(root);
    }

    public static void inOrderTraverse(TreeNode root, Consumer<TreeNode> consumer) {
        if (root == null) {
            consumer.accept(null);
            return;
        }
        inOrderTraverse(root.left, consumer);

        //action
        consumer.accept(root);

        inOrderTraverse(root.right, consumer);
    }

    //level order or BFT
    public static void breadFirstTraverse(TreeNode root, Consumer<TreeNode> consumer) {
        if (root == null) {
            consumer.accept(null);
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                consumer.accept(null);
                continue;
            }
            //action
            consumer.accept(node);

            queue.offer(node.left);
            queue.offer(node.right);
        }

    }

}
