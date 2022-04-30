package DoubleLinkedList;

import org.w3c.dom.Node;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        DNode node1 = new DNode(1, "YoYo", "yy");
        DNode node2 = new DNode(2, "Tort", "tt");
        DNode node3 = new DNode(3, "Benny", "BB");
        DNode node4 = new DNode(4, "Zed", "ZZ");
        System.out.println("雙向鍊表測試");
       /* list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.delete(2);
        list.revise(new DNode(3,"OP","OOP"));*/
        list.addByOrder(node3);
        list.addByOrder(node2);
        list.addByOrder(node4);
        list.addByOrder(node1);

        list.list();

    }
}

class DoubleLinkedList {
    private DNode head = new DNode(0, "", "");

    public DNode getHead() {
        return head;
    }

    // 遍歷
    public void list() {
        if (head.next == null) {
            System.out.println("不存在");
            return;
        }
        DNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void add(DNode addNode) {
        DNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = addNode;
        addNode.pre = temp;
    }

    public void revise(DNode reviseNode) {
        DNode temp = head.next;
        boolean flag = false;
        if (head.next == null) {
            return;
        }
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == reviseNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = reviseNode.name;
            temp.nickName = reviseNode.nickName;
        } else {
            System.out.println("每有匹配的no\n");
        }
    }

    //刪除
    public void delete(int no) {
        DNode temp = head.next;
        boolean flag = false;
        if (head.next == null) {
            System.out.println("不存在");
            return;
        }
        while (true) {
            if (temp.next == null) {
                System.out.println("鍊表為空了");
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            if (temp.next != null) {
                // 這裡代碼有問題，若是刪除最後會出現問題(所以要做判斷)
                temp.next.pre = temp.pre;
            }
            temp.pre.next = temp.next;
        }
    }

    public void addByOrder(DNode node) {
        DNode temp = head;


        while (true) {
            if (temp.next == null) {
                node.pre = temp;
                break;
            }
            if (temp.next.no > node.no) {
                node.next=temp.next;
                node.pre = temp.next.pre;

                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

}


class DNode {
    public String name;
    public String nickName;
    public int no;
    public DNode pre; // 若沒定義 默認為null
    public DNode next;

    public DNode(int no, String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
        this.no = no;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", no=" + no +
                '}';
    }
}
