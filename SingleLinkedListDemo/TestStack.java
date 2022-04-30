package SingleLinkedListDemo;

import java.util.Stack;

public class TestStack {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.add("Ted");
        stack.add("Zed");
        stack.add("Benny");

        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}
