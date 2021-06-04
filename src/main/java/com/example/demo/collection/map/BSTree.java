package com.example.demo.collection.map;

import lombok.Data;

/**
 * 二叉查找树 binary sort tree(binary search tree)
 * definition :
 *  （1）若左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * （2）若右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 * （3）左、右子树也分别为二叉排序树；
 * （4）没有键值相等的结点。
 */
@Data
public class BSTree<T extends Comparable<T>>  {
    private BSTNode<T> root;

    public void insertNode(BSTNode<T> bsNode){
        if (root == null ){
            root = bsNode;
            return;
        }
        int cmp;
        BSTNode<T> y = null;
        BSTNode<T> x = root;
        // 查找z的插入位置
        while (x != null) {
            y = x;
            cmp = bsNode.key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else
                x = x.right;
        }


    }

    public void insertNode(T key, Object value){
        insertNode(new BSTNode<T>(key,value,null ,null ,null));
    }



    @Data
    class BSTNode<T extends Comparable<T>> {
        private T key;
        private Object data;
        private BSTNode<T> left;
        private BSTNode<T> right;
        private BSTNode<T> parent;

        public BSTNode(T key,Object data, BSTNode<T> parent, BSTNode<T> left, BSTNode<T> right) {
            this.key = key;
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

    }
}
