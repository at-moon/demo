package com.atmoon.demo.oj;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class OJ1795 {
    static final class Connection {
        int srcBoard;

        int snkBoard;

        int weight;

        Connection(int srcBoard, int snkBoard, int weight) {
            this.srcBoard = srcBoard;
            this.snkBoard = snkBoard;
            this.weight = weight;
        }
    }

    static final class Boardpair {
        int srcBoard;

        int snkBoard;

        Boardpair(int srcBoard, int snkBoard) {
            this.srcBoard = srcBoard;
            this.snkBoard = snkBoard;
        }
    }

    private static final int MAX_BOARD_NUM = 1000;

    private static List<Integer> getBestRoute(List<Boardpair> boardsList, List<Connection> connectionsList) {
        // 将每个起点对应的连接梳理出来
        Map<Integer, List<Connection>> connectionMap = transList2Map(connectionsList);
        List<Integer> result = new ArrayList<>();
        boardsList.forEach(boardPair -> result.add(bfs(boardPair, connectionMap)));
        return result;
    }

    private static Map<Integer, List<Connection>> transList2Map(List<Connection> connectionsList) {
        Map<Integer, List<Connection>> connectionMap = new HashMap<>();
        for (Connection conn : connectionsList) {
            List<Connection> temp = connectionMap.getOrDefault(conn.srcBoard, new ArrayList<>());
            temp.add(conn);
            connectionMap.put(conn.srcBoard, temp);
        }
        return connectionMap;
    }

    private static int bfs(Boardpair boardpair, Map<Integer, List<Connection>> connectionMap) {
        Queue<Connection> queue = new LinkedList<>();
        // 到达某个单板的权重、跳数
        int[] weight = new int[MAX_BOARD_NUM];
        int[] hopCount = new int[MAX_BOARD_NUM];
        Arrays.fill(weight, Integer.MAX_VALUE);
        Arrays.fill(hopCount, Integer.MAX_VALUE);
        connectionMap.getOrDefault(boardpair.srcBoard, new ArrayList<>()).forEach(queue::offer);
        weight[boardpair.srcBoard] = 0;
        hopCount[boardpair.srcBoard] = 0;
        while (!queue.isEmpty()) {
            Connection connection = queue.poll();
            int from = connection.srcBoard;
            int to = connection.snkBoard;
            int hopCountTemp = hopCount[from] + 1;
            int weightTemp = weight[from] + connection.weight;
            // 跳数不增加时权重更小则更新
            if (hopCountTemp <= hopCount[to] && weightTemp < weight[to]) {
                hopCount[to] = hopCountTemp;
                weight[to] = weightTemp;
                connectionMap.getOrDefault(to, new ArrayList<>()).forEach(queue::offer);
            }
        }
        return weight[boardpair.snkBoard] == Integer.MAX_VALUE ? -1 : weight[boardpair.snkBoard];
    }

    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        int nRow = cin.nextInt();
        int mRow = cin.nextInt();
        List<Connection> connectionsList = new ArrayList<>();
        for (int i = 0; i < nRow; i++) {
            int srcBoard = cin.nextInt();
            int destBoard = cin.nextInt();
            int weight = cin.nextInt();
            connectionsList.add(new Connection(srcBoard, destBoard, weight));
        }
        List<Boardpair> boardsList = new ArrayList<>();
        for (int i = 0; i < mRow; i++) {
            int srcBoard = cin.nextInt();
            int destBoard = cin.nextInt();
            boardsList.add(new Boardpair(srcBoard, destBoard));
        }
        cin.close();
        List<Integer> result = getBestRoute(boardsList, connectionsList);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
