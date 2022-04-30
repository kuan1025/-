package Stack_;

import java.util.Scanner;

/**
 * 模擬stack
 */
public class ArrayDemoStack {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        String key ="";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while(loop){
            System.out.println("list: ");
            System.out.println("exit: ");
            System.out.println("push: ");
            System.out.println("pop: ");
            System.out.println("請輸入操作");
            key=scanner.next();
            switch (key){
                case "list":
                    arrayStack.list();
                    break;
                case "exit":
                    loop=false;
                    break;
                case "push":
                    try {
                        System.out.println("輸入數據");
                        int i = scanner.nextInt();
                        arrayStack.push(i);
                    }catch (RuntimeException e){
                        throw new RuntimeException("不為int");
                    }
                    break;
                case  "pop":
                    arrayStack.pop();
                    break;
                default:
                    break;
            }
        }
    }
}

//
class ArrayStack {
    private int maxSize;
    private int[] stack; //用數組模擬 用該類做管理
    private int top = -1;

    // 規定大小
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    // 添加數據
    public void push(int value) {
        if (isFull()) {
            System.out.println("滿了");
            return;
        }
        this.top++;
        stack[top] = value;
    }

    // 出棧 將數據返回
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("棧空");
        }
        int p = stack[top];
        top--;
        return p;
    }

    // 遍歷棧 從top往下遍歷
    public void list() {
        if (isEmpty()) {
            System.out.println("為空");
            return;
        }
        while (true) {
            System.out.println(stack[top]);
            top--;
            if(top==-1)
                break;
        }
    }
}