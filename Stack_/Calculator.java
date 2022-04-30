package Stack_;

import java.io.OutputStream;

public class Calculator {
    public static void main(String[] args) {
        String expression = "13+2*6-2";
        // 建立 樹棧 和符號棧
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        // 建立索引掃描 以及相關變量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //將掃描到的data放到ch
        String keepNum ="";

        while (true) {
            // 開始掃描
            ch = expression.substring(index, index + 1).charAt(0);
            // 判斷ch
            if (operStack.isOper(ch)) {
                //判斷符號為空?
                if (!operStack.isEmpty()) {
                    //處理優先度
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);

                    } else {
                        operStack.push(ch);
                    }
                } else {
                    //為空 直接入棧
                    operStack.push(ch);
                }
            } else { // numStack.push(ch - 48);如果是數字 直接入棧 但會有一個問題 不能處理雙位數
                keepNum += ch;
                //如果ch是最後一位
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            // 讓index +1
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        // 最後來計算stack
        while (true) {
            //如果符號棧為null 就是結果
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = operStack.cal(num1, num2, oper);
            numStack.push(res);
        }

        System.out.printf("表達式%s等於 = %d", expression, numStack.pop());
    }
}

class ArrayStack2 {
    private int maxSize;
    private int[] stack; //用數組模擬 用該類做管理
    private int top = -1;

    // 規定大小
    public ArrayStack2(int maxSize) {
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
            if (top == -1)
                break;
        }
    }

    // 判斷符號優先級
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    // 判斷是否為運算符
    public boolean isOper(char val) {
        return val == '*' || val == '/' || val == '+' || val == '-';
    }

    // 計算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; // 存放答案
        switch (oper) {
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1; // 注意順序
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    public int peek() {
        return stack[top];
    }

}