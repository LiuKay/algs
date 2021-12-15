package com.kay.tree.tool;

import com.kay.Assert;
import com.kay.TreeNode;
import com.kay.ds.BST;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import static com.kay.Utils.isSame;

/**
 * The string result is Perfect Binary Tree.
 */
public class BinaryTreeSerializers {

    public static final String SEP = ",";
    public static final String NULL_NODE = "#";
    public static final BinaryTreeSerializer PRE_ORDER = new PreOrderSerializer();
    public static final BinaryTreeSerializer POST_ORDER = new PostOrderSerializer();
    public static final BinaryTreeSerializer IN_ORDER = new InOrderSerializer();
    public static final BinaryTreeSerializer BREAD_FIRST_ORDER = new BreadFirstSerializer();

    private BinaryTreeSerializers() {}

    static class PreOrderSerializer implements BinaryTreeSerializer {

        @Override
        public String serialize(TreeNode root) {
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

        @Override
        public TreeNode deserialize(String content) {
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

    static class PostOrderSerializer implements BinaryTreeSerializer {

        @Override
        public String serialize(TreeNode root) {
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

        @Override
        public TreeNode deserialize(String content) {
            if (content == null) {
                return null;
            }
            final String[] split = content.split(SEP);
            Deque<String> list = new LinkedList<>();
            for (String s : split) {
                list.addLast(s);
            }
            return deserialize(list);
        }

        public static TreeNode deserialize(Deque<String> nodes) {
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

    static class InOrderSerializer implements BinaryTreeSerializer {

        @Override
        public String serialize(TreeNode root) {
            StringBuilder stringBuilder = new StringBuilder();
            TreeVisitor.inOrderTraverse(root, treeNode -> {
                if (treeNode == null) {
                    stringBuilder.append(NULL_NODE).append(SEP);
                } else {
                    stringBuilder.append(treeNode.val).append(SEP);
                }
            });
            return stringBuilder.toString();
        }

        @Override
        public TreeNode deserialize(String content) {
            throw new UnsupportedOperationException(
                    "Can not deserialize in order tree because don't know the position of root node.");
        }

    }

    static class BreadFirstSerializer implements BinaryTreeSerializer {

        @Override
        public String serialize(TreeNode root) {
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

        @Override
        public TreeNode deserialize(String content) {
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

    //test
    public static void main(String[] args) {
        final int[] nums = {152, 263, 639, 421, 181, 148, 865, 339, 298, 551,};

        final TreeNode treeNode = BST.numsToBST(nums);

        //pre-order
        final String preString = PRE_ORDER.serialize(treeNode);
        final TreeNode preTree = PRE_ORDER.deserialize(preString);
//        System.out.println("pre:" + preString);
        Assert.isTrue(isSame(treeNode, preTree));

        //in-order
        final String inOrderString = IN_ORDER.serialize(treeNode);
//        System.out.println(inOrderString);

        //post-order
        final String postString = POST_ORDER.serialize(treeNode);
//        System.out.println("post:" + postString);
        final TreeNode postTree = POST_ORDER.deserialize(postString);
        Assert.isTrue(isSame(treeNode, postTree));
        Assert.isTrue(isSame(preTree, postTree));

        //bread first
        final String serialize = BREAD_FIRST_ORDER.serialize(treeNode);
        final TreeNode deserialize = BREAD_FIRST_ORDER.deserialize(serialize);
        Assert.isTrue(isSame(treeNode, deserialize));
    }
}
