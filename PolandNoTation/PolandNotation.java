package PolandNoTation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class PolandNotation {
    public static void main(String[] args) {
        // 將中綴表達式轉成後綴表達式
        // 若直接掃描字串不方便，因此要將中綴表達式轉到List中保存

        String expression = "1+((2+3)*4)-5";
        //轉成數組
        List<String> ls = toInfixExpressionList(expression);
        System.out.println(ls);
        //將數組轉成後綴表達式
        List<String> transfer = transfer(ls);
        System.out.println(transfer);


        // 給定逆Poland表達式
        //(3+4)x5-6  逆波瀾表達式 3 4 + 5 x 6
        // 說明為了方便，逆波瀾表達式的數字和符號使用空格隔開
        String suffixExpression = "3 4 + 5 * 6 -";
        // 思路
        // 1. 先將"3 4 + 5 x 6 - " 放到ArrayList中
        //2. 將ArrayList 傳遞給一個方法，配合棧完成計算
        List<String> list = getListString(suffixExpression);
        System.out.println(cal(list));
    }
    // 將中綴表達式轉成後綴
    public static List<String> toInfixExpressionList(String s){
        // 定義一個list
        ArrayList<String> ls = new ArrayList<>();
        int i = 0; // 當作指針 用作便利
        String str; // 做多位數的拼接
        char c ; // 每遍歷一個字浮就放到c中
        do{
            // 如果c非數字
            if((c=s.charAt(i))<48||(c=s.charAt(i))>57){
                ls.add(""+c);
                i++;
            }else{ // 如果是數字 要考慮位數
                str = "";
                while (i<s.length()&&(c=s.charAt(i))>48&&(c=s.charAt(i))<=57){
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        }while (i<s.length());
        return ls;
    }
    // 將ArrayList的數據轉成後綴表達式
    public static List<String> transfer(List<String> list){
        Stack<String> s1 = new Stack<>();
        //s2 從頭到尾多是接收輸出，所以可以用ArrayList代替
        List<String> s2 = new ArrayList<>();
        String temp ="";
        //變例+判斷
       for(String item:list){
           if(item.matches("\\d+")){
               s2.add(item);
           }else if(item.equals("(")){
               s1.add(item);
           }else if(item.equals(")")){ /** 這裡自己寫ㄉ */
               while(!s1.peek().equals("(")){
                   s2.add(s1.pop());
               }
               s1.pop();
           }else{
               // 當s1棧頂的運算符優先度>將添加的運算符，會將棧頂運算符移至s2
               while (s1.size()!=0&&Operation.getVal(s1.peek())>=Operation.getVal(item)){
                   s2.add(s1.pop());
               }
               s1.push(item);


           }

       }
       while (s1.size()!=0){
           s2.add(s1.pop());
       }
       // 因為是放到list中(有序) 無須刻意反轉
       return s2;
    }

    // 將一個逆波瀾表達式 依次將數據和運算符 放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        // 將suffixExpression分割
        ArrayList<String> list = new ArrayList<>();
        String[] split = suffixExpression.split(" ");
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    // 完成逆波瀾表達式的運算
    public static int cal(List<String> list) {
        int n1 =0;
        int n2 =0;
        int res = 0;
        Stack<String> stack = new Stack<>();
        for (String item: list) {
        //這裡用正則表式
           if(item.matches("\\d+")){
               stack.push(item);
           }else{
               n1=Integer.parseInt(stack.pop());
               n2=Integer.parseInt(stack.pop());

               if(item.equals("+")){
                   res=n1+n2;
               }else if(item.equals("-")){
                   res=n2-n1;
               }else if(item.equals("*")){
                   res=n1*n2;
               }else if(item.equals("/")){
                   res = n2/n1;
               }else{
                   throw new RuntimeException("運算符有誤");
               }
                    stack.push(res+"");
           }


        }
        return Integer.parseInt(stack.pop());
    }
}
// 編寫一個類 判斷優先級
class Operation{
    private static int ADD = 1;
    private static int  SUB= 1;
    private static int  MUL= 2;
    private static int  DIV= 2;

    public static int getVal(String operation){
        int res = 0;
        switch (operation){
            case "+":
                res=ADD;
                break;
            case "-":
                res=SUB;
                break;
            case "*":
                res=MUL;
                break;
            case "/":
                res=DIV;
                break;
            default:
                System.out.println("不匹配");

        }
        return res;
    }
}