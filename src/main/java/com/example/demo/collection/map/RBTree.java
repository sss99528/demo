package com.example.demo.collection.map;

import lombok.Data;

public class RBTree<T extends Comparable<T>> {
    private RBTNode<T> root;
    private static final boolean RED = false;
    private static final boolean BLACK = true;


    /**
     * 左旋，降低自身层级，让右孩子顶替自己的位置，把右孩子的左节点放到自身名下。把自己变为右孩子的左孩子
     * 以下流程 也可以得到，左旋过程中，左孩子自始至终是没有改变的
     *
     * 旋转记得一个事情，不仅要修改 父节点对子节点的联系(setLeft/setRight)，子节点和父节点的联系也要修改(setParent)
     * @param rbtNode
     */
    public void leftSpin(RBTNode<T> rbtNode) {
        RBTNode<T> leftChild = rbtNode.getLeft();// 左孩子
        RBTNode<T> rightChild = rbtNode.getRight();// 右孩子

        // 1. 顶替自身位置
        rightChild.setParent(rbtNode.getParent());
        if (rbtNode.getParent()==null){// 如果当前节点为根节点
            this.root = rbtNode.getParent();
        }else {
            if (rbtNode.getParent().getLeft() == rbtNode){// 当前节点是 左孩子
                // 顶替左孩子位置
                rbtNode.getParent().setLeft(rightChild);
            }else {// 当前节点是 右孩子
                rbtNode.getParent().setRight(rightChild);
            }
        }
        // 2. 右孩子的左孩子， 挂到自己名下
        if (rightChild.getLeft()!=null){
            rbtNode.setRight(rightChild.getLeft());
            rightChild.getLeft().setParent(rbtNode);
        }

        // 3. 设置 自身的父节点，现在自己变为 右节点的 左孩子
        rightChild.setLeft(rbtNode);
        rbtNode.setParent(rightChild);
    }


    /**
     * 右旋，降低自身层级，让左孩子顶替自己的位置。 把左孩子的右节点放到自己名下
     */
    public void rightSpin(RBTNode<T> rbtNode){
        RBTNode<T> leftChild = rbtNode.getLeft();
        RBTNode<T> rightChild = rbtNode.getRight();
        /**
         * 1. 左孩子顶替自身的位置
         * 2。 给左孩子的右节点放到自己名下 顶替左孩子的位置
         * 3. 给自己找位置
         */
        leftChild.setParent(rbtNode.getParent());
        if (rbtNode.getParent()==null){
            this.root = leftChild;
        }else {
            if (rbtNode.getParent().getLeft() == rbtNode){
                rbtNode.getParent().setLeft(leftChild);
            }else {
                rbtNode.getParent().setRight(leftChild);
            }
        }

        if (leftChild.getRight()!=null){
            rbtNode.setLeft(leftChild.getRight());
            leftChild.getRight().setParent(rbtNode);
        }

        rbtNode.setParent(leftChild);
        leftChild.setRight(rbtNode);

    }


    /**
     * 添加节点
     *  1.  将节点插入
     *  2. 将插入的节点着色为红色（这样尽可能满足红黑树的原则，能更快转换成红黑树）
     *  3. 旋转 着色，使其满足红黑树要求
     */
    public void insert(RBTNode<T> rbtNode){

        // 1. 将节点丢入二叉查找树
        RBTNode<T> parent = this.root;
        RBTNode<T> cmpNode = null;
        int cmp;
        while (parent!=null){
            cmpNode = parent;
            cmp = rbtNode.getData().compareTo(rbtNode.getData());
            if (cmp<0){
                parent = parent.getLeft();
            }else {
                parent = parent.getRight();
            }
        }


    }















    /**
     * 红黑树节点
     * @param <T>
     */
    @Data
    class RBTNode<T extends Comparable<T>>{
        private boolean color ;// 颜色
        private T  data;        // Data
        private RBTNode<T> left;
        private RBTNode<T> right;
        private RBTNode<T> parent;

        public RBTNode(T data, boolean color, RBTNode<T> parent, RBTNode<T> left, RBTNode<T> right) {
            this.data = data;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }
}
