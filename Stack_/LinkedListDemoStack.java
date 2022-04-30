package Stack_;



public class LinkedListDemoStack {
    public static void main(String[] args) {
        LinkedListDemo stack = new LinkedListDemo();

        stack.add(new NodeD("1"));
        stack.add(new NodeD("2"));
        stack.add(new NodeD("3"));
        stack.pop();
        stack.list();

    }
}

class LinkedListDemo {
    public NodeD head = new NodeD("");
    public int top = 0;

    public boolean isEmpty() {
        return head.next == null;
    }

    public void add(NodeD d) {
        NodeD temp = head;

        int temp2 = 0;
        while (true) {
            if (temp.next == null) {
                temp2++;
                break;
            }
            temp = temp.next;
            temp2++;
        }
        d.next=temp.next;
        temp.next=d;
    }

    public void list() {
        NodeD temp = head.next;
        int temp2 = top;
        while (true) {
            System.out.println(temp);
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
    }

    public void pop() {
       NodeD temp = head;
        /*temp.next = temp.next.next; 其實不用做反向練表*/
        while(true){
        if(temp.next.next==null) {

            break;

        }
           temp= temp.next;
        }
        temp.next = null;
        top--;
    }
}
     class NodeD {
        public NodeD next;
        public String content;

        public NodeD(String c) {
            this.content = c;
        }

        @Override
        public String toString() {
            return content;
        }
    }
