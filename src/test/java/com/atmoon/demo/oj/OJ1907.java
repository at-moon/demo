package com.atmoon.demo.oj;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class OJ1907 {

    private static final int DESK = 1;

    private static final int LAMP = 2;

    private static final int WORK_STATION = 3;

    private static final int DOORWAY = 4;

    private static final int[] DIR_X = new int[] {1, 0, -1, 0};

    private static final int[] DIR_Y = new int[] {0, 1, 0, -1};

    private static int officeSize;

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());

        int timeLimit = cin.nextInt();
        int size = cin.nextInt();

        int[][] officeLayout = new int[size][];
        for (int i = 0; i < size; i++) {
            officeLayout[i] = new int[size];
            for (int j = 0; j < size; j++) {
                officeLayout[i][j] = cin.nextInt();
            }
        }
        cin.close();
        int result = getLightsOffNum(timeLimit, officeLayout);
        System.out.println(result);
    }

    private static int getLightsOffNum(int timeLimit, int[][] officeLayout) {
        officeSize = officeLayout.length;
        // 获取有效点列表(起点，灯，终点)
        List<int[]> lamps = findLampsAndStartEnd(officeLayout);
        // 计算有效点间距离
        int[][] distances = getDistances(lamps, officeLayout);
        // 计算最多熄灯数
        boolean[] visited = new boolean[distances.length];
        visited[0] = true;
        return dfs(distances, 0, visited, timeLimit, 0);
    }

    private static List<int[]> findLampsAndStartEnd(int[][] officeLayout) {
        List<int[]> lamps = new ArrayList<>();
        int[] start = new int[2];
        int[] end = new int[2];
        for (int i = 0; i < officeSize; i++) {
            for (int j = 0; j < officeSize; j++) {
                int[] temp = new int[] {i, j};
                switch (officeLayout[i][j]) {
                    case LAMP:
                        lamps.add(temp);
                        break;
                    case WORK_STATION:
                        start = temp;
                        break;
                    case DOORWAY:
                        end = temp;
                        break;
                    default:
                        break;
                }
            }
        }
        lamps.add(0, start);
        lamps.add(end);
        return lamps;
    }

    private static int[][] getDistances(List<int[]> lamps, int[][] officeLayout) {
        int size = lamps.size();
        int[][] distances = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                distances[i][j] = calculateDistance(officeLayout, lamps.get(i), lamps.get(j));
                distances[j][i] = distances[i][j];
            }
        }
        return distances;
    }

    private static int calculateDistance(int[][] officeLayout, int[] from, int[] to) {
        // 从起点开始到每个点的距离
        int[][] distance = new int[officeSize][officeSize];
        for (int i = 0; i < officeSize; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        distance[from[0]][from[1]] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(from);
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentDistance = distance[current[0]][current[1]];
            // 找到终点即可退出
            if (current[0] == to[0] && current[1] == to[1]) {
                break;
            }
            for (int i = 0; i < DIR_X.length; i++) {
                int nextX = current[0] + DIR_X[i];
                int nextY = current[1] + DIR_Y[i];
                if (nextX >= 0 && nextX < officeSize && nextY >= 0 && nextY < officeSize
                    && officeLayout[nextX][nextY] != DESK
                    && distance[nextX][nextY] == Integer.MAX_VALUE) {
                    int[] temp = new int[] {nextX, nextY};
                    distance[nextX][nextY] = currentDistance + 1;
                    queue.offer(temp);
                }
            }
        }
        return distance[to[0]][to[1]];
    }

    private static int dfs(int[][] distances, int current, boolean[] visited, int timeLimit, int lightsOffNum) {
        if (distances[current][distances.length - 1] > timeLimit || current == distances.length - 1) {
            return lightsOffNum - 1;
        }
        int maxNum = -1;
        for (int i = 0; i < distances.length; i++) {
            if (distances[current][i] > 0 && distances[current][i] < timeLimit && !visited[i]) {
                visited[i] = true;
                int temp = dfs(distances, i, visited, timeLimit - distances[current][i], lightsOffNum + 1);
                // 选择关灯数最多的下一跳
                maxNum = Math.max(temp, maxNum);
                // 所有灯都关闭直接返回
                if (maxNum == distances.length - 2) {
                    return maxNum;
                }
                visited[i] = false;
            }
        }
        return Math.max(lightsOffNum, maxNum);
    }

}
