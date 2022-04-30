package SparseArr;

import java.io.*;
import java.util.stream.Stream;

/**
 * 保存棋盤狀態 然而有許多地方沒被下 若保存所有的棋位很費空間
 * spare array可以省空間
 */
public class SaprseArr01 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // 先建立一個11*11的2維數組
        // 0表示沒有棋子 1 黑子 2 白子

        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;

        //輸出
        for (int[] row : chessArr) {
            for (int data : row)
                System.out.printf("%d\t", data);
            System.out.println();
        }

        // 轉成spare array
        //1. 先取得非0數據(行，列，值)

        int diff = 0;
        int[][] sparse = null;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    diff++;
                }
            }
        }
        sparse = new int[diff + 1][3];
        // 給稀疏數組賦值
        // 第一行
        sparse[0][0] = 11;
        sparse[0][1] = 11;
        sparse[0][2] = diff;
        int s1 = 1;


        // 存放非0值
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    sparse[s1][0] = i;
                    sparse[s1][1] = j;
                    sparse[s1][2] = chessArr[i][j];
                    s1++;
                }
            }
        }
        for (int i = 0; i < sparse.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparse[i][0], sparse[i][1], sparse[i][2]);

        }
        System.out.println("////////還原///////");
        int chessArr2[][] = new int[sparse[0][0]][sparse[0][1]];

        for (int i = 1; i < sparse.length; i++) {
            chessArr2[sparse[i][0]][sparse[i][1]] = sparse[i][2];
        }

        for (int[] row : chessArr2) {
            for (int data : row)
                System.out.printf("%d\t", data);
            System.out.println();
        }
        String s = new String();
        // io練習

        /// 物件流

        Arr arr = new Arr(sparse);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\sparseSave.txt"));
        oos.writeObject(arr);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\sparseSave.txt"));
        Arr a = (Arr)ois.readObject();
        chessArr2=new int[a.arr[0][0]][a.arr[0][1]];

        for (int i = 1; i <= a.arr[0][2]; i++) {
            chessArr2[a.arr[i][0]][a.arr[i][1]]=a.arr[i][2];
        }
                System.out.println("////////////////");
        for (int i = 0; i < chessArr2.length; i++) {
            for (int j = 0;j<chessArr2[i].length; j++) {
                System.out.print("\t"+chessArr2[i][j]);
            }
            System.out.println();
        }





    }


}

class Arr implements Serializable {
    int[][] arr;

    public Arr(int[][] arr) {
        this.arr = arr;
    }
    public void show(){
        for (int i = 0; i < arr.length; i++) {

        }
    }
}
