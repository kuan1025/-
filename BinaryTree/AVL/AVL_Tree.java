package BinaryTree.AVL;

public class AVL_Tree {
    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 6, 8, 9};
        Tree tree = new Tree();
        for (int i = 0; i < arr.length; i++) {
            tree.add(new Node(arr[i]));
        }
        tree.show();
        System.out.println(tree.getHeight());


    }
}

class Tree {
    Node root;

    // 取得高度
    public int getHeight() {
        return root.height();
    }

    // 取得左子樹高度
    public int getLeftHeight() {

        return root.left.height() + 1;
    }

    public int getRightHeight() {

        return root.right.height() + 1;
    }


    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void show() {
        if (root == null) {
            System.out.println("無法顯示");
        } else {
            root.show();
        }
    }

    public void delete(int val) {
        if (root == null) {
            return;
        } else {
            Node node = root.search(val);
            if (node == null) {
                return;
            }

            Node parent = root.searchP(val);
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            // 刪除葉子
            if (node.left == null && node.right == null) {
                if (parent.left != null && parent.left.value == val) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == val) {
                    parent.right = null;
                }
            } else if (node.left != null && node.right != null) {
                int min = findMin(node.right); // 找right的最小值 or left的最大值
                node.value = min;
            } else {
                // 剩下的就是 單子樹節點

                if (node.left != null) { // node 左 有數字
                    if (parent != null) {
                        if (parent.left.value == val) { //判斷在parent那邊
                            parent.left = node.left;
                        } else {
                            parent.right = node.left;
                        }
                    } else {                    // node右邊
                        if (parent != null) {
                            if (parent.left.value == val) {
                                parent.left = node.right;
                            } else {
                                parent.right = node.right;
                            }
                        } else {
                            root = node.right;
                        }
                    }
                }
            }
        }
    }

    public int findMin(Node node) {
        Node target = node;
        if (target.left == null) {
            delete(target.value);
            return target.value;
        } else {
            return findMin(target.left);
        }

    }

}

class Node {
    int value;
    Node right;
    Node left;
    static int max = 1;
    static int maxNum = 0;

    public static void setMax(int max) {
        Node.max = max;
    }

    public Node(int value) {
        this.value = value;
    }

    //返還當前節點的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }


    public void show() {
        if (this.left != null) {
            this.left.show();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.show();
        }
    }

    // 查找要刪除的節點
    public Node search(int value) {
        if (value == this.value) {
            return this;
        }
        if (value < this.value && this.left != null) {

            return this.left.search(value);

        } else if (value > this.value && this.right != null) {
            return this.right.search(value);

        } else {
            return null;
        }
    }

    // 查找要刪除節點的parent
    public Node searchP(int val) {
        if ((this.left != null && this.left.value == val) || (this.right != null && this.right.value == val)) {
            return this;
        } else {
            if (this.value > val && this.left != null) {
                return this.left.searchP(val);
            } else if (this.value < val && this.right != null) {
                return this.right.searchP(val);
            } else {
                return null;
            }
        }
    }

    public void add(Node node) {

        if (this.value > node.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {

                this.right.add(node);
            }
        }
        if ((rightHeight()) - (leftHeight()) > 1) {
            if (right!=null&&right.leftHeight()>right.rightHeight()) {
                right.rightRotation();
                leftRotation();
            } else {
                leftRotation();
            }
        } else if (leftHeight() - rightHeight() > 1) {
            if (left!=null&&left.rightHeight()>left.leftHeight()) {
                left.leftRotation();
                rightRotation();
            }else{
                rightRotation();
            }

        }
    }

    // 旋轉
    public void leftRotation() {
        Node NewNode = new Node(value);
        NewNode.left = this.left;
        NewNode.right = this.right.left;
        this.value = this.right.value;
        this.left = NewNode;
        this.right = this.right.right;
    }

    public void rightRotation() {
        Node NewNode = new Node(value);
        NewNode.right = this.right;
        NewNode.left = this.left.right;
        this.value = this.left.value;
        this.right = NewNode;
        this.left = this.left.left;
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}