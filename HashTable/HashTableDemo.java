package HashTable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashTable table = new HashTable(15);


        while (true) {
            System.out.println("a 新增員工");
            System.out.println("s 查看員工");
            System.out.println("e 關閉");
            System.out.println("g 根據id找員工");
            char key = scanner.next().charAt(0);

            switch (key) {
                case 'a':
                    System.out.println("輸入新增的ID");
                    int id = scanner.nextInt();
                    System.out.println("輸入新增的名字");
                    String name = scanner.next();
                    table.add(new Emp(id, name));
                    break;
                case 's':
                    table.list();
                    break;
                case 'g':
                    System.out.println("輸入員工id");
                    id = scanner.nextInt();
                    table.getById(id);
                case 'e':
                    return;
            }

        }


    }
}

// HashTable
class HashTable {
    private EmpLinkedList[] list;
    private int size;

    public HashTable(int size) {
        this.size = size;
        list = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            list[i] = new EmpLinkedList();
        }

    }

    public void add(Emp emp) {
        // 根據id得到該員工應該添加到了練表
        list[hashFun(emp.id)].add(emp);
    }

    // 遍歷所有鍊表
    public void list() {
        for (int i = 0; i < size; i++) {
            list[i].show();
            System.out.println();
        }
    }

    //編寫一個散列函數
    // 最簡單的方式是取模法
    public int hashFun(int id) {
        return id % size;
    }

    public void getById(int id) {
        System.out.println(list[hashFun(id)].findEmpById(id));
    }


}


class EmpLinkedList {
    private Emp head;

    // 假定無序
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        } else {
            Emp temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = emp;
        }
    }

    public void show() {

        Emp temp = head;
        while (temp != null) {
            System.out.print("id=>" + temp);
            temp = temp.next;
        }

    }

    // 查找
    // 查找
    public Emp findEmpById(int id) {

        if (head == null) {
            System.out.println("鍊表不存在");
            return null;
        }
        Emp temp = head;
        while (temp != null) {
            if (id == temp.id) {
                return temp;
            }
            temp = temp.next;
        }
        System.out.println("無此id");
        return null;
    }
}


class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.next = null;
        this.name = name;
    }

    @Override
    public String toString() {
        return id +
                " name => " + name;
    }
}