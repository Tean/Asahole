package com.netteans.examples.structure;

public class RedBlackTree {
    public class Node {
        private COLOR color;

        private Node parent;
        private Node left;
        private Node right;

        public Node() {
            this(COLOR.BLACK);
        }

        public Node(COLOR color) {
            this.color = color;
        }

        public COLOR getColor() {
            return color;
        }

        public void setColor(COLOR color) {
            this.color = color;
        }

        public void rotationChild(Node child, ROTATE rotate) {
        }

        public void needChangeColor() {
            //TODO: 判断需要变色（条件：如果有）
        }
    }

    private enum COLOR {
        RED,
        BLACK
    }

    private enum ROTATE {
        LEFT,
        RIGHT
    }
}
