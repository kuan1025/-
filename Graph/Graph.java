package Graph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 圖的表示方式有二
 * 1. 二維陣列
 * 2. 陣列 + 鍊表
 */
public class Graph {
    ArrayList<String> vertexList; // 各點的集合
    private int[][] edges;
    private int numOfEdges; // 邊 的數目
    // 定義一個樹組 紀錄是否已經遍歷過
    private boolean[] isVisited;

    public static void main(String[] args) {

        Graph graph = new Graph(5);
        String vertexValue[] = {"A", "B", "C", "D", "E"};
        for (String value : vertexValue) {
            graph.insertVertex(value);
        }
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.show();

        // graph.dfs();
        graph.bfs();
    }

    public Graph(int n) { // 要先知道有多少個頂點
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    // 插入頂點
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    // 邊的數目
    public int getNumOfEdges() {
        return numOfEdges;

    }

    // 返回vertex的數量
    public int numberOfVertex() {
        return vertexList.size();
    }

    // 返回下標對應的value
    public String getDataFromIndex(int n) {
        return vertexList.get(n);
    }

    // 返回v1和v2權值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 顯示圖對應的矩陣
    public void show() {
        for (int[] vertex : edges) {
            System.out.println(Arrays.toString(vertex));
        }

    }

    // 連接的第一個節點的下標
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    // 根據前個節點的下標或取下個連接節點
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    // 重載
    public void dfs() {
        for (int i = 0; i < getNumOfEdges(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, 0);
            }
        }
        System.out.println();
    }

    // 深度優先遍歷
    public void dfs(boolean[] isVisited, int i) {
        // 先訪問該節點
        System.out.print(getDataFromIndex(i) + "->");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            w = getNextNeighbor(i, w);
        }

    }

    // 廣度優先遍歷
    public void bfs() {
        for (int i = 0; i < getNumOfEdges(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    public void bfs(boolean[] isVisited, int i) {
        System.out.println(getDataFromIndex(i)+ " -> ");
        int u; // 頭節點下標
        int w;// 鄰節點下標
        LinkedList queue = new LinkedList();
        isVisited[i] = true;
        queue.add(i);
        while (!queue.isEmpty()) {
            u = (Integer) queue.removeFirst(); // 取第一個
            w = getFirstNeighbor(u);// 看是否有下個點
            while (w != -1) { // 若有 就顯示 並加入list
                if (!isVisited[w]) {
                    System.out.println(getDataFromIndex(w) + " -> ");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w); // 繼續找下下個
            }
        }
    }
}



