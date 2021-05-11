package com.atmoon.demo.oj;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OJ1789 {
    static class AllocatedMemory {

        private final int POOL_SIZE = 100;

        private final boolean[] memory = new boolean[POOL_SIZE];

        private final Map<Integer, Integer> allocatedMap = new HashMap<>();

        AllocatedMemory() {

        }

        // 返回分配的内存首地址(string)，失败返回字符串 "error"
        String request(int size) {
            if (size <= 0) {
                return "error";
            }
            int start = 0;
            while (start + size <= POOL_SIZE) {
                int i;
                for (i = start; i < start + size; i++) {
                    if (memory[i]) {
                        start = allocatedMap.get(i) + i;
                        break;
                    }
                }
                if (i == start + size) {
                    allocatedMap.put(start, size);
                    allocateMemory(start, size);
                    return String.valueOf(start);
                }
            }
            return "error";
        }

        // 成功返回 true；失败返回 false，失败时框架会自动输出 "error"
        boolean release(int startAddress) {
            if (!allocatedMap.containsKey(startAddress)) {
                return false;
            }
            for (int i = 0; i < allocatedMap.get(startAddress); i++) {
                memory[startAddress + i] = false;
            }
            allocatedMap.remove(startAddress);
            return true;
        }

        private void allocateMemory(int start, int size) {
            for (int i = 0; i < size; i++) {
                memory[start + i] = true;
            }
        }

    }

    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        AllocatedMemory allocatedMemory = new AllocatedMemory();
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        int line = Integer.parseInt(cin.nextLine());
        String[][] ins = new String[line][2];
        for (int i = 0; i < line; i++) {
            ins[i] = cin.nextLine().split("=");
            if (ins[i][0].startsWith("REQUEST")) {
                System.out.println(allocatedMemory.request(Integer.parseInt(ins[i][1])));
            } else {
                boolean ret = allocatedMemory.release(Integer.parseInt(ins[i][1]));
                if (!ret) {
                    System.out.println("error");
                }
            }
        }

        cin.close();
    }

}
