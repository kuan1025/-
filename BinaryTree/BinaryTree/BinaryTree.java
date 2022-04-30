package BinaryTree.BinaryTree;

/**
 * 二叉樹
 */
public class BinaryTree {
    public static void main(String[] args) {
        //創建樹
        BinaryT binaryT = new BinaryT();
        HeroNode root = new HeroNode(1, "YoYo");
        HeroNode node2 = new HeroNode(2, "Zed");
        HeroNode node3 = new HeroNode(3, "Zack");
        HeroNode node4 = new HeroNode(4, "Sam");
        HeroNode node5 = new HeroNode(5, "Ted");

        // 這裡先手動創建二叉樹
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        // 測試遍歷
        /**
        binaryT.setRoot(root);
        binaryT.infixOrder();
        System.out.println(binaryT.preS(5));
        System.out.println(binaryT.midS(5));
        System.out.println(binaryT.posS(5));
        **/

        //測試刪除
        binaryT.setRoot(root);
        binaryT.preOrder();
        System.out.println("刪除後");
        binaryT.del(3);
        binaryT.preOrder();
    }
}

class BinaryT {
    private HeroNode root;
    HeroNode temp = root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 遍歷
    public void preOrder() {
        if (this.root != null) {
            root.preOrder();
        } else {
            System.out.println("無法遍歷");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            root.infixOrder();
        } else {
            System.out.println("無法遍歷");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            root.postOrder();
        } else {
            System.out.println("無法遍歷");
        }
    }

    public HeroNode preS(int num) {
        if(root!=null)
        return root.preSearch(num);
        else return null;
    }
    public HeroNode midS(int num) {
        if(root!=null)
        return root.midSearch(num);
        else return null;
    }
    public HeroNode posS(int num) {
        if(root!=null)
        return root.posSearch(num);
        else return null;
    }
    public void del(int n){
        if(root!=null){
            if(root.getNo()==n){
                root=null;
            }else{
                root.delete(n);
            }
        }else{
            System.out.println("root = null");
        }
    }

}

class HeroNode {
    private int no;
    private HeroNode right;
    private HeroNode left;
    private String name;


    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode = > " +
                "no = " + no +
                " name = " + name;
    }
    // 編寫遍歷的方法

    // 1. 前序遍歷
    public void preOrder() {
        System.out.println(this);
        // 向左
        if (this.getLeft() != null) {
            this.left.preOrder();
        }
        //向右
        if (this.getRight() != null) {
            this.right.preOrder();
        }
    }

    // 中序
    public void infixOrder() {
        // 遞歸向左 輸出父節點
        if (this.getLeft() != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        //
        if (this.getRight() != null) {
            this.right.infixOrder();
        }
    }

    // 後序遍歷
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    // 查找 pre
    public HeroNode preSearch(int num) {
        System.out.println("尋找!");
        if (num == this.getNo()) {
            return this;
        }
        HeroNode resNode = null;
        if (this.getLeft() != null) {
            resNode = this.left.preSearch(num);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.getRight() != null) {
            resNode = this.right.preSearch(num);
        }
        if (resNode != null) {
            return resNode;
        } else {
            return null;
        }


    }

    public HeroNode midSearch(int num) {

        HeroNode resNode = null;
        if (this.getLeft() != null) {
            resNode = this.left.midSearch(num);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("尋找!");
        if (num == this.getNo()) {
            return this;
        }
        if (this.getRight() != null) {
            resNode = this.right.midSearch(num);
        }
        if (resNode != null) {
            return resNode;
        } else {
            return null;
        }

    }
    public HeroNode posSearch(int num) {
        HeroNode resNode = null;
        if (this.getLeft() != null) {
            resNode = this.left.posSearch(num);
        }
        if (resNode != null) {
            return resNode;
        }

        if (this.getRight() != null) {
            resNode = this.right.posSearch(num);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("尋找!");
        if (num == this.getNo()) {
            return this;
        }else {
            return null;
        }

    }
    public void delete(int no){
        if(this.left!=null&&this.left.no==no) {
            if (this.left.left != null || this.left.right != null) {
                this.left = this.left.left;
            } else {
                this.left = null;
                return;
            }
        }
        if(this.right!=null&&this.right.no==no){
            if(this.right.right!=null||this.right.left!=null) {
                this.left.right=this.right.right;
                this.right = this.right.left;
            }else{
                this.right=null;
                return;
            }

        }
        if(this.left!=null){
            this.left.delete(no);

        }


        if(this.right!=null){
            this.right.delete(no);
        }

    }



}