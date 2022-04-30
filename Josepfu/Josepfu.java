package Josepfu;

public class Josepfu {
    public static void main(String[] args) {
        // 測試
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addBoy(6);
        list.list();
        list.countBoy(1,2,5);
    }
}

class CircleSingleLinkedList {
    // 創建一個first節點，當前沒有編號
    private Boy first;

    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("數據錯誤");
            return;
        }
        // for循環創建列表
        // 輔助變量
        Boy cur = null;
        for (int i = 1; i < nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first); // 第一個 構成環狀
                cur = first;
            }
            boy.setNext(first);
            cur.setNext(boy);
            cur = boy;
        }
    }

    // 遍歷
    public void list() {
        if (first == null) {
            System.out.println("鍊表為空");
            return;
        }
        Boy cur = first;

        while (true) {

            System.out.printf("boy編號%d\n", cur.getNo());
            if (cur.getNext() == first) {
                System.out.println("顯示完畢");
                break;
            }
            cur = cur.getNext();
        }
    }

    // 根據用戶的輸入 計算出小孩出圈的順序
    // startNo代表第幾個小孩開始數數
    // countNum 表示數幾下
    // nums代表一開始有多少小孩
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("參數有誤");
        }
        Boy helper = first;
        // 創建輔助值 幫助小孩出圈
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        // 得到 helper(first前一位)
        for (int i = 0; i < startNo-1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 當報數時，讓first和helper指針同時移動m-1 然後出圈
        while(true){
            if(helper==first){ // 代表圈中只有一個節點
                break;
            }
            for (int i = 0; i < countNum-1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小朋友%d出圈\n",first.getNo());
           // 出圈
            helper.setNext(first.getNext());
            first=helper.getNext();
        }
        System.out.printf("最後的小朋友編號%d\n",first.getNo());

    }


}

// 創建一個Boy類 表示節點
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy getNext() {
        return next;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy[" +
                "no=" + no + "]";
    }
}