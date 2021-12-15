package com.kay.tree;

import com.kay.TreeNode;

public interface BinaryTreeSerializer {
    String serialize(TreeNode treeNode);

    TreeNode deserialize(String content);
}
