package BinaryTree.ThreadBinaryTree;

public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "1");
        HeroNode node2 = new HeroNode(2, "2");
        HeroNode node3 = new HeroNode(3, "3");
        HeroNode node4 = new HeroNode(4, "4");
        HeroNode node5 = new HeroNode(5, "5");
        HeroNode node6 = new HeroNode(6, "6");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadBinaryT t = new ThreadBinaryT();
        t.setRoot(root);
        t.threadNodes();
        System.out.println(node5.getRight());
        System.out.println(node5.getLeft());
        System.out.println("-------");
        t.binaryList();


    }
}
class ThreadBinaryT {
    private HeroNode root;
    private HeroNode pre=null;
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
    public void threadNodes(){
        threadNodes(root);
    }
    // 線索化
    public void threadNodes(HeroNode node){

        if(node==null){
            return;
        }
        //1. 線索化左子樹
        if(node.getLeft()!=null) {

            threadNodes(node.getLeft());

        }
        //2. 線索化當前結點
        if(node.getLeft()==null){

            node.setLeft(pre);
            node.leftType=1;
        }
        if(pre!=null&&pre.getRight()==null) { // 這裡是靠後一步完成
            pre.setRight(node);
            pre.rightType = 1;
        }
        pre = node;
        //3. 線索化右子樹
        if(node.getRight()!=null){

            threadNodes(node.getRight());
        }


    }
    // 線索化遍歷
    public void binaryList(){
        HeroNode node =root;
         while(node!=null){
             while(node.leftType==0){
                 node=node.getLeft();
             }
             System.out.println(node);

             while(node.rightType==1){
                 node=node.getRight();
                 System.out.println(node);
             }
             node = node.getRight();
         }
    }


}
class HeroNode {
    private int no;
    private HeroNode right;
    private HeroNode left;
    private String name;
    // 說明
    // 1. 如果leftType == 0 代表左子樹 1 則是前驅節點
    // 2.   rightType == 1   右子樹  後趨結點

    public int leftType;
    public int rightType;

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
        } else {
            return null;
        }

    }

    public void delete(int no) {
        if (this.left != null && this.left.no == no) {
            if (this.left.left != null || this.left.right != null) {
                this.left = this.left.left;
            } else {
                this.left = null;
                return;
            }
        }
        if (this.right != null && this.right.no == no) {
            if (this.right.right != null || this.right.left != null) {
                this.left.right = this.right.right;
                this.right = this.right.left;
            } else {
                this.right = null;
                return;
            }

        }
        if (this.left != null) {
            this.left.delete(no);

        }


        if (this.right != null) {
            this.right.delete(no);
        }

    }

}
