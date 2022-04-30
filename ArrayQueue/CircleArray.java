package ArrayQueue;

import java.util.Scanner;

/**
 *  環狀結構
 *  說明：
 *  front = 0; // 初始值
 *  rear = 0; // 初始值  環狀結構加入新資料後index再往後
 *  關鍵判斷：
 *  (rear+1)%maxSize==front  ： 代表走完一圈 拿來判斷是否滿了
 *   front=(front+1)%maxSize ;  取出資料後font往後 rear 蓋掉新增的資料
 *   最大容量-1 才是實際能裝資料的容量 : 原因 -> 其中一個單位被作為“index”
 */

public class CircleArray {
    public static void main(String[] args) {
        CircleArr circleArr = new CircleArr(4);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)");
            System.out.println("e(exit)");
            System.out.println("a(add)");
            System.out.println("g(get)");
            System.out.println("h(head)");
            key = scanner.next().charAt(0); //
            switch (key) {
                case 's':
                    circleArr.list();
                    System.out.println();
                    break;
                case 'a':
                    System.out.println("輸入要添加的內容");
                    int i = scanner.nextInt();
                    circleArr.addQueue(i);
                    break;
                case 'g':
                    int queue = circleArr.getQueue();
                    System.out.printf("取出的資料%d", queue);
                    break;
                case 'e':
                    System.out.println("退出");
                    loop=false;
                    break;
                case 'h':
                    int i1 = circleArr.headQueue();
                    System.out.printf("頭是%d",i1);
                    break;

            }
        }
    }
}
class CircleArr{
    private int maxSize; // 最大容量
    private int front; // 佇列的頭
    private int rear; // 佇列尾
    private int[] arr; // 用於存放資料

    public CircleArr(int maxSize) {
        this.maxSize= maxSize;
        arr=new int[maxSize];
        this.front = 0; // 初始值
        this.rear = 0; // 初始值
    }
    // 判斷是否滿了
    public boolean isFull(){
       return (rear+1)%maxSize==front;// rear是最後陣列的後一位
    }
    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {

        if (isFull()) {
            System.out.println("佇列滿 不能加入資料");
            return;
        }

        arr[rear] = n; // 後面才將index往後
        rear = (rear+1)%maxSize;

    }
    public int getQueue() {
        // 判斷佇列是否為空
        if (isEmpty()) {
            throw new RuntimeException("為空 不能取資料");
        }
        int temp=front;
        front=(front+1)%maxSize;
        return arr[temp];

    }
    public void list() {
        if (isEmpty()) {
            System.out.println("佇列為空 沒有資料");
            return;
        }
        for (int i = front; i <front+size(); i++) {
            System.out.printf("arr[%d]=%d\n", i%maxSize, arr[i]);
        }
    }
    public int size(){
       return (rear+maxSize-front)%maxSize;
    }
    public int headQueue() {
        if (isEmpty()) {
            System.out.println("沒資料");
        }
        return arr[front];
    }

}