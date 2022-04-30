package Recursion;

public class FibonacciNum {
    public static void main(String[] args) {

    }
    public static int fCal(int n){
        if(n==1||n==0){
            return n;
        }else{
           return (fCal(n-1)+fCal(n-2));
        }
    }
}
