package BinaryTree.ArrayBinaryTree;

/**
 * 用Arrays 存取資料
 * 以tree來結構化資料
 * tree與存儲資料是能夠互相轉換的
 *
 */
public class Array_BinaryTree {
    public static void main(String[] args) {
        int[] arr ={1,2,3,4,5,6,7};
        ArrayBT arrayBT = new ArrayBT(arr);
        arrayBT.tree();
    }
}
class ArrayBT{
   public int arr[];
   public int index = 0;
    public ArrayBT(int[] arr) {
        this.arr = arr;
    }
    public void tree(){
        tree(0);
    }
    public void tree(int index){
        if(arr.length==0||arr==null){
            System.out.println("資料不足");
        }

        System.out.println(arr[index]);

        if((index*2+1)<arr.length){
            tree(2*index+1);
        }

        if((index*2+2)<arr.length){
            tree(2*index+2);
        }

    }
}