package BinaryTree.BinarySortTree;


public class BST {
    public static void main(String[] args) {


        int arr[] = {7, 3, 10, 12, 5, 1, 9, 2};
        Tree tree = new Tree();
        for (int i = 0; i < arr.length; i++) {
            tree.add(new Node(arr[i]));
        }
        tree.show();
        System.out.println("++++++++");
        tree.delete(7);
        System.out.println("++++++++");
        tree.show();

    }
}

class Tree {
    Node root;

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
            if(root.left==null&&root.right==null){
                root=null;
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
        if(target.left==null){
            delete(target.value);
            return target.value;
        }else{
           return findMin(target.left);
        }

    }
}

class Node {
    int value;
    Node right;
    Node left;

    public Node(int value) {
        this.value = value;
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
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
