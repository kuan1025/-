package SingleLinkedListDemo;

import org.w3c.dom.Node;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 創建節點
        LinkedNode l1 = new LinkedNode(1, "yd", "星光");
        LinkedNode l2 = new LinkedNode(6, "eh", "小gg");
        LinkedNode l3 = new LinkedNode(4, "勳", "嘿嘿");
        LinkedNode l5 = new LinkedNode(5, "5", "55");
        LinkedNode l6 = new LinkedNode(3, "5", "55");
        LinkedNode l7 = new LinkedNode(2, "5", "55");


        SingleLinkedList sll = new SingleLinkedList();
        SingleLinkedList all = new SingleLinkedList();

     /*  sll.add(l1);
        sll.add(l2);
        sll.add(l3);*/
        all.addByOrder(l6);
        all.addByOrder(l7);
        sll.addByOrder(l3);//4
        sll.addByOrder(l2);//6
        sll.addByOrder(l5);//5
        LinkedNode lr = new LinkedNode(4, "YoYo", "u");
        sll.revise(lr);
        //sll.delete(5);
        //revise(sll.getHead());
        System.out.println("=====================");
        merge(all.getHead(),sll.getHead());
        System.out.println("=====================");
        sll.list();
        System.out.println(count(sll.getHead()));
        System.out.println(findLastIndexNode(sll.getHead(), 1));
        printList(sll.getHead());

    }
    public static void merge(LinkedNode a,LinkedNode b){ // 主 副
        LinkedNode temp = null;
        LinkedNode B = b.next;
        LinkedNode A = a.next;
        LinkedNode c = b.next;
        while (true){

          while(true){
              if(B.no<A.no){
                 temp= c.next;

              }
          }
            
        }
    }
    public static void printList(LinkedNode head){
        if(head.next==null){
            System.out.println("不存在");
        }
        // 創建一個棧
        Stack<LinkedNode> stack = new Stack<LinkedNode>();
        LinkedNode cur = head.next;
        while(cur!=null){
            stack.add(cur);
            cur=cur.next;
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }
    public static void revise(LinkedNode head) {
        LinkedNode temp = null;
        LinkedNode reverse = new LinkedNode(0,"","");
        LinkedNode cur = head.next;
        while (cur.next!=null){
            temp=cur.next;
            cur.next=reverse.next;
            reverse.next=cur;
            cur=temp;
        }
        head.next=reverse.next;

    }

    // 方法 獲得單鍊表宿校節點個數
    public static int count(LinkedNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        LinkedNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    public static LinkedNode findLastIndexNode(LinkedNode head, int index) {
        if (head.next == null) {
            return null;
        }
        int size = count(head);

        // 確認數據是否合理
        if (index <= 0 || index > size) {
            return null;
        }
        LinkedNode cur = head;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public static LinkedNode showK(int k, LinkedNode head) {
        if (head.next == null) {
            System.out.println("不存在");
        }
        LinkedNode temp = head;
        int num = 0;
        while (true) {
            if (num == k) {

                break;
            }
            num++;
            temp = temp.next;
        }
        return temp;
    }
}

class SingleLinkedList {
    // 初始化一個head節點
    private LinkedNode head = new LinkedNode(0, "", "");

    public LinkedNode getHead() {
        return head;
    }

    // 添加節點
    public void add(LinkedNode linkedNode) {

        // head不能動，因此需要temp做輔助
        LinkedNode temp = head;
        // 遍歷練表，找到最後
        while (true) {
            // 找到練表的最後
            if (temp.next == null) {
                break;
            }
            // 如果沒到最後 將temp後移
            temp = temp.next;
        }
        // 找到最後 將next指向新的節點
        temp.next = linkedNode;
    }

    public void addByOrder(LinkedNode linkedNode) {
        // head不能動 做temp輔助
        LinkedNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }

            if (linkedNode.no > temp.next.no) {
                linkedNode.next = temp.next;
                break;
            } else if (linkedNode.no == temp.next.no) {
                System.out.println("編號存在");
                break;
            }
            temp = temp.next;
        }
        temp.next = linkedNode;
    }

    // 修改節點
    // 根據no來做些改
    public void revise(LinkedNode newNode) {
        if (head.next == null) {
            System.out.println("列表為空");
            return;
        }
        // 定義一個輔助變量
        boolean flag = false;
        LinkedNode temp = head.next;
        while (true) {
            if (temp.next == null) {
                flag = true;
                break;
            }
            if (temp.no == newNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newNode.name;
            temp.nickName = newNode.nickName;
        } else {
            System.out.printf("沒有找到編號%d的節點" + "\n", newNode.no);
        }
    }

    //刪除節點
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("列表為空");
            return;
        }
        LinkedNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("沒有找到");
        }
    }

    //遍歷方法
    public void list() {
        if (head.next == null) {
            System.out.println("練表為空");
            return;
        }
        LinkedNode temp = head.next;
        while (true) {

            System.out.println(temp);
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
    }

}

// 定義節點
class LinkedNode {

    public int no;
    public String name;
    public String nickName;
    public LinkedNode next; // 指向的下個節點!!


    public LinkedNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "LinkedList{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}