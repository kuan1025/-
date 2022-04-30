package HuffmanCode;


import javax.annotation.processing.Filer;
import java.io.*;
import java.util.*;
import java.util.List;

public class HuffmanCode {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /**
         String content = "i like like like java do you like a java";


         byte[] zip = zip(content);
         byte[] decode = decode(codeList, zip);
         System.out.println(new String(decode));
         */
        // 實戰 壓縮圖片檔
  //zipFile("D://test.jpg","D://testhf.zip");
   zipOpen("D://testhf.zip","D://test.jpg");
        System.out.println("成功");
    }

    public static void zipFile(String src, String des) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(src);
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        fileInputStream.close();
        byte[] zip = zip(bytes);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(des))) {
            oos.writeObject(zip);
            // 記得給與翻譯的list
            oos.writeObject(codeList);
            oos.close();

        }
    }
    public static void zipOpen(String src , String des) throws IOException, ClassNotFoundException {

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(src));
        byte[] b = (byte[]) objectInputStream.readObject();
        Map<Byte,String> huffmanCode =(Map<Byte,String>)objectInputStream.readObject();
        byte[] decode = decode(huffmanCode, b);
        FileOutputStream fileOutputStream = new FileOutputStream(des);
        fileOutputStream.write(decode);
        objectInputStream.close();
        fileOutputStream.close();
    }


    public static List<Node> getNode(byte[] contentByte) {
        ArrayList<Node> list = new ArrayList<Node>();
        HashMap<Byte, Integer> hashMap = new HashMap<>();
        for (byte b : contentByte) {
            Integer num = hashMap.get(b);
            if (num == null) {
                hashMap.put(b, 1);
            } else {
                hashMap.put(b, ++num);
            }
        }

        for (Map.Entry<Byte, Integer> entry : hashMap.entrySet()) {
            list.add(new Node(entry.getKey(), entry.getValue()));
        }

        return list;
    }

    // 創建樹
    public static Node createTree(List<Node> list) {

        while (list.size() > 1) {
            Collections.sort(list);
            Node node1 = list.get(0);
            Node node2 = list.get(1);
            Node parent = new Node(null, node1.weight + node2.weight);
            parent.right = node2;
            parent.left = node1;
            list.remove(node1);
            list.remove(node2);
            list.add(parent);
        }
        return list.get(0);
    }

    // 取得Huffman Code
    static HashMap<Byte, String> codeList = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    public static Map<Byte, String> getCode(Node node) {
        if (node == null) {
            return null;
        }
        getCode(node, "", stringBuilder);
        return codeList;
    }

    public static void getCode(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder builder = new StringBuilder(stringBuilder);
        builder.append(code);
        if (node.data != null) {
            codeList.put(node.data, builder.toString());
        }

        if (node.left != null) {
            getCode(node.left, "0", builder);
        }

        if (node.right != null) {
            getCode(node.right, "1", builder);
        }


    }

    public static void show(Node root) {
        if (root == null) {
            System.out.println("無法顯示");
        } else {
            root.show();
        }
    }

    public static byte[] getCodeByte(byte[] bytes, Map<Byte, String> code) {
        StringBuilder stringBuilder = new StringBuilder();
        int num;
        for (byte b : bytes) {
            stringBuilder.append(code.get(b));
        }
        if (stringBuilder.length() % 8 == 0) {
            num = stringBuilder.length() / 8;
        } else {
            num = stringBuilder.length() / 8 + 1;
        }
        byte[] b = new byte[num];
        String str;
        for (int i = 0, index = 0; i < stringBuilder.length(); i += 8, index++) {
            if (i + 8 <= stringBuilder.length()) {
                str = stringBuilder.substring(i, i + 8);
            } else {
                str = stringBuilder.substring(i);
            }
            b[index] = (byte) Integer.parseInt(str, 2); // 二進制轉10 放入byte
        }
        return b;

    }

    // 封裝以上壓縮方法
    public static byte[] zip(byte[] content) {
        //  byte[] content = s.getBytes(); // 將內容轉成byte
        List<Node> list = getNode(content); // 將byte 做統計 計算多少加權數量
        Node tree = createTree(list);
        Map<Byte, String> code = getCode(tree);
        System.out.println(code);
        byte[] codeByte = getCodeByte(content, code);
        return codeByte;
    }


    ////////////////////////解壓縮

    //解碼的方法
    private static byte[] decode(Map<Byte, String> codeList, byte[] huffmanBytes) {
        StringBuilder builder = new StringBuilder();

        // 判斷是否需要做補高位
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            String s = byteToBitString(!flag, b);

            builder.append(s);
        }
        System.out.println(builder.toString());
        // builder有所有的二進制code 接下來用codeList做解碼

        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : codeList.entrySet()) {
            map.put(entry.getValue(), entry.getKey()); // 反向集合
        }

        List<Byte> list = new ArrayList<>();

        for (int i = 0; i < builder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                String key = builder.substring(i, count + i);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);

            i += count;
        }
        System.out.println(list);
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }

        return b;
    }


    // 將byte轉成2進制
    // flag的用意是因為 byte[]最後一位 未必有滿8bit 因此不用 |=256補高位
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b; // 將b轉成int

        if (flag) {
            temp |= 256;
        }
        //
        String str = Integer.toBinaryString(temp); // 能將int轉成二進制
        // 但是上面的方法有個問題 若為正數並不會保留 0 eg => 0000 0001 變成 1
        // 因此使用這個方法前，要 |256 補一個 1 0000 0000 來保留0
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str; // 補碼
        }

    }
}

// 存放數據
class Node implements Comparable<Node> {
    Byte data; // 存數據 eg 'a' = >97 ,' ' = > 32
    int weight; // 數值 表示出現的次數
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public void show() {
        System.out.println(this);
        if (this.left != null) {
            this.left.show();
        }
        if (this.right != null) {
            this.right.show();
        }
    }

    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node [" +
                "data =" + data +
                ", weight=" + weight +
                ']';
    }

}