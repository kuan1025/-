package ArrayQueue;

import java.util.Scanner;

/**
 * 用array來模擬Queue
 * 結論： 先進先出
 * 用font(取資料) rear(新增資料) 兩個皆以-1 為初始值 作為index前個value
 */


public class ArrayQueue {
    public static void main(String[] args) {

        ArrayQUE arrayQUE = new ArrayQUE(3);
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
                    arrayQUE.list();
                    break;
                case 'a':
                    System.out.println("輸入要加入的內容");
                    int i = scanner.nextInt();
                    arrayQUE.addQueue(i);
                    break;
                case 'g':
                    int queue = arrayQUE.getQueue();
                    System.out.printf("取出的資料%d", queue);
                    break;
                case 'e':
                    System.out.println("退出");
                    loop=false;
                    break;
                case 'h':
                    int i1 = arrayQUE.headQueue();
                    System.out.printf("頭是%d",i1);
                    break;

            }
        }


        // 使用陣列模擬que
    }
}

class ArrayQUE {
    private int maxSize; // 最大量
    private int front; // 佇列的頭
    private int rear; // 對列尾
    private int[] arr; // 用於存放資料

    // 創建建構子
    public ArrayQUE(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; // 指向佇列頭部前一個位置 並不包含(所以是0)
        rear = -1; // 指向佇列尾部 (指的是佇列前個位置)

    }

    // 判斷對列是否滿
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 判斷是否為空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加數據到對列
    public void addQueue(int n) {

        if (isFull()) {
            System.out.println("佇列滿了 不能加入資料");
            return;
        }
        rear++; // 讓rear後移
        arr[rear] = n;
    }

    // 獲取對列的數據
    public int getQueue() {
        // 判斷對列是否為空
        if (isEmpty()) {
            throw new RuntimeException("為空 不能取資料");
        }
        front++;
        return arr[front];
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("佇列為空 沒有資料");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //顯示佇列的頭資料，不是取出數據
    public int headQueue() {
        if (isEmpty()) {
            System.out.println("沒資料");
        }
        return arr[front + 1];
    }
}