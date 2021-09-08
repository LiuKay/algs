package com.kay.tree;

import com.kay.TreeNode;
import com.kay.ds.BST;

import java.util.LinkedList;
import java.util.Queue;

import static com.kay.Utils.isSame;

public class TreeSerializer {

    public static final String SEP = ",";
    public static final String NULL_NODE = "#";

    public static void main(String[] args) {
        final int[] nums = {152, 263, 639, 421, 181, 148, 865, 339, 298, 551,};

        final TreeNode treeNode = BST.numsToBST(nums);

        //pre-order
        final String preString = PreOrderSerializer.serialize(treeNode);
        final TreeNode preTree = PreOrderSerializer.deserialize(preString);
        assert isSame(treeNode, preTree);

        //post-order
        final String postString = PostOrderSerializer.serialize(treeNode);
        final TreeNode postTree = PostOrderSerializer.deserialize(postString);
        assert isSame(treeNode, postTree);
        assert isSame(preTree, postTree);

        //bread first
        final String serialize = BreadFirstSerializer.serialize(treeNode);
        final TreeNode deserialize = BreadFirstSerializer.deserialize(serialize);
        assert isSame(treeNode, deserialize);
    }

    static class PreOrderSerializer {
        public static String serialize(TreeNode root) {
            StringBuilder stringBuilder = new StringBuilder();
            TreeVisitor.preOrderTraverse(root, node -> {
                if (node == null) {
                    stringBuilder.append(NULL_NODE).append(SEP);
                } else {
                    stringBuilder.append(node.val).append(SEP);
                }
            });
            return stringBuilder.toString();
        }

        public static TreeNode deserialize(String content) {
            if (content == null) {
                return null;
            }
            final String[] values = content.split(SEP);
            LinkedList<String> list = new LinkedList<>();
            for (String value : values) {
                list.addLast(value);
            }

            return deserialize(list);
        }

        private static TreeNode deserialize(LinkedList<String> nodes) {
            if (nodes.isEmpty()) {
                return null;
            }

            final String first = nodes.removeFirst();
            if (first.equals(NULL_NODE)) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(first));

            //left first
            root.left = deserialize(nodes);
            root.right = deserialize(nodes);

            return root;
        }
    }

    static class PostOrderSerializer {
        public static String serialize(TreeNode root) {
            StringBuilder stringBuilder = new StringBuilder();
            TreeVisitor.postOrderTraverse(root, node -> {
                if (node == null) {
                    stringBuilder.append(NULL_NODE).append(SEP);
                } else {
                    stringBuilder.append(node.val).append(SEP);
                }
            });
            return stringBuilder.toString();
        }

        public static TreeNode deserialize(String content) {
            if (content == null) {
                return null;
            }
            final String[] split = content.split(SEP);
            LinkedList<String> list = new LinkedList<>();
            for (String s : split) {
                list.addLast(s);
            }
            return deserialize(list);
        }

        public static TreeNode deserialize(LinkedList<String> nodes) {
            if (nodes.isEmpty()) {
                return null;
            }

            final String last = nodes.removeLast();
            if (last.equals(NULL_NODE)) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(last));

            //right first
            root.right = deserialize(nodes);
            root.left = deserialize(nodes);
            return root;
        }
    }


    static class BreadFirstSerializer {
        public static String serialize(TreeNode root) {
            StringBuilder stringBuilder = new StringBuilder();
            TreeVisitor.breadFirstTraverse(root, node -> {
                if (node == null) {
                    stringBuilder.append(NULL_NODE).append(SEP);
                } else {
                    stringBuilder.append(node.val).append(SEP);
                }
            });
            return stringBuilder.toString();
        }

        public static TreeNode deserialize(String content) {
            if (content == null) {
                return null;
            }

            final String[] split = content.split(SEP);

            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(split[0]));
            queue.offer(root);

            int i = 1;
            while (i < split.length) {
                final TreeNode node = queue.poll();

                final String s1 = split[i++];
                if (s1.equals(NULL_NODE)) {
                    node.left = null;
                } else {
                    node.left = new TreeNode(Integer.parseInt(s1));
                    queue.offer(node.left);
                }

                final String s2 = split[i++];
                if (s2.equals(NULL_NODE)) {
                    node.right = null;
                } else {
                    node.right = new TreeNode(Integer.parseInt(s2));
                    queue.offer(node.right);
                }
            }
            return root;
        }
    }

}
