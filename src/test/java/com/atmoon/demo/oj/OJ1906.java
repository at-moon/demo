package com.atmoon.demo.oj;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class OJ1906 {
    static class Task {
        int startTime;

        int endTime;

        int serverId;

        Task(int startTime, int endTime, int serverId) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.serverId = serverId;
        }
    }

    static class Point {
        int time;

        int serverId;

        int taskVal;

        public Point(int time, int serverId, int taskVal) {
            this.time = time;
            this.serverId = serverId;
            this.taskVal = taskVal;
        }
    }

    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        int serverNum = cin.nextInt();
        int taskNum = cin.nextInt();
        Task[] tasks = new Task[taskNum];
        for (int i = 0; i < taskNum; i++) {
            int startTime = cin.nextInt();
            int endTime = cin.nextInt();
            int serverId = cin.nextInt();
            tasks[i] = new Task(startTime, endTime, serverId);
        }
        cin.close();

        List<int[]> oneFreeTimes = getOneFreeTime(serverNum, tasks);
        oneFreeTimes.forEach(time -> System.out.println(time[0] + " " + time[1]));
    }

    // 待实现函数，在此函数中填入答题代码
    private static List<int[]> getOneFreeTime(int serverNum, Task[] tasks) {
        // 将线段转为点
        List<Point> points = new ArrayList<>();
        for (Task task : tasks) {
            points.add(new Point(task.startTime, task.serverId, 1));
            points.add(new Point(task.endTime, task.serverId, -1));
        }
        // 根据时间排序
        points.sort(Comparator.comparingInt(point -> point.time));
        return getOneFreeTime(serverNum, points);
    }

    private static List<int[]> getOneFreeTime(int serverNum, List<Point> points) {
        // 判断每个时间点运行服务器数量
        int runningNum = 0;
        int time = points.get(0).time;
        // 每台服务器当前运行的任务数，最多10000台服务器
        int[] serverTaskVal = new int[10000];
        List<int[]> result = new ArrayList<>();
        for (Point point : points) {
            if (point.time != time && runningNum == serverNum - 1) {
                // 合并结果
                combineResult(result, new int[] {time, point.time});
            }
            // 更新时间
            time = point.time;
            int preVal = serverTaskVal[point.serverId];
            serverTaskVal[point.serverId] += point.taskVal;
            if (preVal == 0 && serverTaskVal[point.serverId] == 1) {
                runningNum++;
            } else if (preVal == 1 && serverTaskVal[point.serverId] == 0) {
                runningNum--;
            }
        }
        // 没有满足要求时段
        if (result.size() == 0) {
            result.add(new int[] {-1, -1});
        }
        return result;
    }

    private static void combineResult(List<int[]> result, int[] range) {
        if (result.size() == 0) {
            result.add(range);
            return;
        }
        int[] lastRange = result.get(result.size() - 1);
        if (lastRange[1] == range[0]) {
            lastRange[1] = range[1];
        } else {
            result.add(range);
        }
    }
}
